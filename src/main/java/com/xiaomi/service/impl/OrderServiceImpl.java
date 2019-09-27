package com.xiaomi.service.impl;

import com.xiaomi.dao.*;
import com.xiaomi.domain.*;
import com.xiaomi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private GoodsDao goodsDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(Order order, List<OrderDetail> orderDetails) {
        //开启事务
        try {
            //1.开启事务
            //DruidUtils.startTransaction();
            //2.调用OrderDao向order表添加订单
            orderDao.add(order);
            //3.向订单详情表添加
            for (OrderDetail orderDetail : orderDetails) {
                orderDetailDao.add(orderDetail);
            }
            //4.清空购物车
            cartDao.deleteByUid(order.getUid());
            //5.提交
            //DruidUtils.commit();
        } catch (Exception e) {
            e.printStackTrace();
//            try {
//                //回滚
//                DruidUtils.rollback();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
            throw new RuntimeException(e.getMessage(), e);
        }
//        finally {
//            //关闭
//            DruidUtils.close();
//        }
    }

    @Override
    public void updateStatus(String r6_order, String s) {
        orderDao.updateStatus(r6_order, s);
    }

    @Override
    public List<Order> orderQuery(int uid) {
        List<Order> orderList = orderDao.orderFindById(uid);
        if (orderList != null && orderList.size() > 0) {
            for (Order order : orderList) {
                Address address = addressDao.findById(order.getAid());
                order.setAddress(address);
            }
        }
        return orderList;
    }

    @Override
    public Order orderDetailQuery(String oid) {
        Order order = orderDao.findByOid(oid);

        //根据order查询地址
        Address address = addressDao.findById(order.getAid());
        order.setAddress(address);

        //根据order查询订单详情
        List<OrderDetail> orderDetailList = orderDetailDao.findOrderDetails(oid);

        //查询【订单详情】中所关联的商品信息
        if (orderDetailList != null && orderDetailList.size() > 0) {
            for (OrderDetail detail : orderDetailList) {
                Goods goods = goodsDao.findById(detail.getPid());
                detail.setGoods(goods);
            }
        }
        order.setOrderDetails(orderDetailList);

        return order;
    }

    @Override
    public PageBean<Order> orderByPage(int pageNum, int pageSize, String condition) {
        long totalSize = orderDao.getCount(condition);
        int offset = (pageNum-1)*pageSize;
        List<Order> data = orderDao.orderByPages(offset, pageSize, condition);
        return new PageBean<>(pageNum, pageSize, totalSize, data);
    }
}
