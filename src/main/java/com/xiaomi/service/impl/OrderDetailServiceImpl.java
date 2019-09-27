package com.xiaomi.service.impl;

import com.xiaomi.dao.OrderDetailDao;
import com.xiaomi.domain.OrderDetail;
import com.xiaomi.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Override
    public List<OrderDetail> findByPid(int pid) {
        return orderDetailDao.findByPid(pid);
    }
}
