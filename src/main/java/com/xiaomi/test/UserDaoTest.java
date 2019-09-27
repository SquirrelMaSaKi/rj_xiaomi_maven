package com.xiaomi.test;

import com.xiaomi.dao.UserDao;
import com.xiaomi.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserDaoTest {
    @Test
    public void testFindAll() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void testGetCount() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        long count = userDao.getCount("gender='男'");
        System.out.println(count);
    }

    @Test
    public void testFindByPages() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        List<User> users = userDao.findByPages(0, 3, "gender='男'");
        for (User user : users) {
            System.out.println(user.toString());
        }
    }
}
