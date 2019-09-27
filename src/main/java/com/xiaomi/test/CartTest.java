package com.xiaomi.test;

import com.xiaomi.dao.CartDao;
import com.xiaomi.domain.Cart;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CartTest {
    @Test
    public void testFindByUidAndPid() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        CartDao cartDao = (CartDao) context.getBean("cartDao");
        Cart cart = cartDao.findByUidAndPid(9, 6);
        System.out.println(cart.toString());
    }
}
