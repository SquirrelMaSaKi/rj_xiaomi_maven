package com.xiaomi.test;

import com.xiaomi.dao.OrderDetailDao;
import com.xiaomi.domain.OrderDetail;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class OrderDetailTest {
    @Test
    public void testFindOrderDetails() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        OrderDetailDao orderDetailDao = (OrderDetailDao) context.getBean("orderDetailDao");
        List<OrderDetail> orderDetails = orderDetailDao.findOrderDetails("20190912170617128");
        for (OrderDetail orderDetail : orderDetails) {
            System.out.println(orderDetail.toString());
        }
    }
    @Test
    public void testFindByPid() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        OrderDetailDao orderDetailDao = (OrderDetailDao) context.getBean("orderDetailDao");
        List<OrderDetail> orderDetails = orderDetailDao.findByPid(1);
        for (OrderDetail orderDetail : orderDetails) {
            System.out.println(orderDetail.toString());
        }
    }
}
