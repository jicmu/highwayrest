package controller.payment;

import common.Handler;
import orders.Order;
import orders.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderListHandler implements Handler {

    public OrderListHandler() {
        System.out.println("OrderListHandler 생성!");
    }

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderService();

        String loginNum = String.valueOf(request.getSession().getAttribute("loginNum"));

        List<Order> orderList = orderService.getMyOrders(Integer.parseInt(loginNum));

        request.setAttribute("orderList", orderList);

        return "/payment/orderList.jsp";
    }

    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "";
    }

    public String getPath() {
        return path + "/orders";
    }
}
