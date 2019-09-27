package com.xiaomi.test;

import com.xiaomi.dao.OrderDao;
import com.xiaomi.domain.Order;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class OrderTest {
    @Test
    public void testGetCount() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        OrderDao orderDao = (OrderDao) context.getBean("orderDao");
        List<Order> orders = orderDao.orderByPages(0, 2, "status=1");
        for (Order order : orders) {
            System.out.println(order.toString());
        }
    }
}
