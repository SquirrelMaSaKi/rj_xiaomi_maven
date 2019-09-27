package com.xiaomi.test;

import com.xiaomi.dao.GoodsDao;
import com.xiaomi.domain.Goods;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class GoodsTest {
    @Test
    public void testFindPageByWheres() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        GoodsDao goodsDao = (GoodsDao) context.getBean("goodsDao");
        List<Goods> goods = goodsDao.findPageByWheres(0, 4, "typeid=1 and star=5");
        for (Goods good : goods) {
            System.out.println(good.toString());
        }
    }
}
