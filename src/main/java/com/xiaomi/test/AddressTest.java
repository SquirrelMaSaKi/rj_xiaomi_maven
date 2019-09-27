package com.xiaomi.test;

import com.xiaomi.dao.AddressDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AddressTest {
    @Test
    public void testUpdateDefault2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        AddressDao addressDao = (AddressDao) context.getBean("addressDao");
        addressDao.updateDefault2(3);
    }
}
