package com.xiaomi.dao;

import com.xiaomi.domain.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetailDao {
    void add(OrderDetail orderDetail);

    List<OrderDetail> findOrderDetails(@Param("oid") String oid);

    List<OrderDetail> findByPid(@Param("pid") int pid);
}
