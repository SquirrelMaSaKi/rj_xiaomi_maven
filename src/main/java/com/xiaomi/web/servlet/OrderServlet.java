package com.xiaomi.web.servlet;

import com.xiaomi.domain.*;
import com.xiaomi.service.AddressService;
import com.xiaomi.service.CartService;
import com.xiaomi.service.OrderService;
import com.xiaomi.utils.RandomUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderServlet1", value = "/orderservlet")
public class OrderServlet extends BaseServlet {
    private CartService cartService = (CartService) ContextLoader.getCurrentWebApplicationContext().getBean("cartService");
    private AddressService addressService = (AddressService) ContextLoader.getCurrentWebApplicationContext().getBean("addressService");
    private OrderService orderService = (OrderService) ContextLoader.getCurrentWebApplicationContext().getBean("orderService");


    public String getOrderView(HttpServletRequest request, HttpServletResponse response) {

       //从数据库查询要购买的东西
       User user = (User) request.getSession().getAttribute("user");
       if (user == null) {
           return "redirect:/login.jsp";
       }

       List<Cart> carts = cartService.findByUid(user.getId());
       List<Address> addresses = addressService.findByUid(user.getId());

       request.setAttribute("carts", carts);
       request.setAttribute("addList", addresses);

       return "/order.jsp";
   }

   public String addOrder(HttpServletRequest request, HttpServletResponse response) {
       User user = (User) request.getSession().getAttribute("user");

       if (user == null) {
           return "redirect:/login.jsp";
       }
       String aid = request.getParameter("aid");

       //获取购买的东西
       List<Cart> carts = cartService.findByUid(user.getId());

       if (carts==null || carts.size()==0) {
            request.setAttribute("msg", "购物车空了，请选择商品");
            return "/message.jsp";
       }

       //创建一个订单号
       String oid = RandomUtils.createOrderId();


       //创建订单详情
       List<OrderDetail> orderDetails = new ArrayList<>();
       BigDecimal sum = new BigDecimal(0);
       for (Cart cart : carts) {
           OrderDetail orderDetail = new OrderDetail(null,oid ,cart.getPid() ,cart.getNum() ,cart.getMoney());
           orderDetails.add(orderDetail);
           sum = sum.add(cart.getMoney());

       }

       //创建订单
       Order order = new Order(oid, user.getId(), sum, "1", new Date(), Integer.valueOf(aid));

       try {
           orderService.saveOrder(order,orderDetails);
           request.setAttribute("order", order);
           return "/orderSuccess.jsp";
       } catch (Exception e) {
           e.printStackTrace();
           request.setAttribute("msg", "提交订单失败" + e.getMessage());
           return "/message.jsp";
       }
   }

   public String getOrderList(HttpServletRequest request, HttpServletResponse response) {
       User user = (User) request.getSession().getAttribute("user");
       if (user == null) {
           return "redirect:/login.jsp";
       }

       //调用业务
       List<Order> orders = orderService.orderQuery(user.getId());
       request.setAttribute("orderList", orders);
       return "/orderList.jsp";
   }

   public String getOrderDetail(HttpServletRequest request, HttpServletResponse response) {
       User user = (User) request.getSession().getAttribute("user");
       if (user == null) {
           return "redirect:/login.jsp";
       }

       String oid = request.getParameter("oid");

       //前端需要的有订单的详情以及各种价格，所以再Order中都有相关的属性
       Order order = orderService.orderDetailQuery(oid);
       request.setAttribute("order", order);
       return "/orderDetail.jsp";
   }
}
