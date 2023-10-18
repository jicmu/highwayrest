package controller.paymentStore;

import common.Handler;
import orders.Order;
import orders.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderList implements Handler {

    public OrderList() {
        System.out.println("OrderList 생성!");
    }

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderService();

        // TODO 세션으로 변경
        String loginStore = (String) request.getSession().getAttribute("loginStore");
        loginStore = "306";

        List<Order> orderList = orderService.findByRestNo(Integer.parseInt(loginStore));

        request.setAttribute("orderList", orderList);

        request.setAttribute("view", "/store/order.jsp");

        return "/index.jsp";
    }

    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "";
    }

    public String getPath() {
        return path + "/store/orders";
    }
}
