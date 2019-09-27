package com.xiaomi.test;

import com.xiaomi.dao.GoodsTypeDao;
import com.xiaomi.domain.GoodsType;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class GoodsTyesTest {
    @Test
    public void testFindAlls() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        GoodsTypeDao goodsTypeDao = (GoodsTypeDao) context.getBean("goodsTypeDao");
        List<GoodsType> typeAlls = goodsTypeDao.findTypeAlls(0, 3, "level=1");
        for (GoodsType typeAll : typeAlls) {
            System.out.println(typeAll.toString());
        }
    }
}
