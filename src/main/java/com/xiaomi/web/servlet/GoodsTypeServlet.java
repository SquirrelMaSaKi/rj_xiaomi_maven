package com.xiaomi.web.servlet;

import com.alibaba.fastjson.JSON;
import com.xiaomi.domain.GoodsType;
import com.xiaomi.service.GoodsTypeService;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GoodsTypeServlet2", value = "/goodstypeservlet")
public class GoodsTypeServlet extends BaseServlet {
    private GoodsTypeService goodsTypeService = (GoodsTypeService) ContextLoader.getCurrentWebApplicationContext().getBean("goodsTypeService");
    public String goodstypelist(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");

        //获取商品的类型
        List<GoodsType> goodsTypeList = goodsTypeService.findTypeByLevel(1);
        //导入json
        String json = JSON.toJSONString(goodsTypeList);

        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
