package com.xiaomi.dao;

import com.xiaomi.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    void add(Order order);

    void updateStatus(@Param("id") String r6_order, @Param("status") String s);

    List<Order> orderFindById(@Param("uid") int uid);

    Order findByOid(@Param("oid")String oid);

    long getCount(@Param("condition") String condition);

    //List<Order> orderByPage(int pageNum, int pageSize, String condition);
    List<Order> orderByPages(@Param("offset") int offset,@Param("pageSize") int pageSize,@Param("condition") String condition);
}
