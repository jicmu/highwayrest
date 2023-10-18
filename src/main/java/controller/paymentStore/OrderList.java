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

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String loginStoreStr = request.getParameter("loginStore");

        if (loginStoreStr == null || loginStoreStr.isEmpty()) {
            response.getWriter().write("<html><head><meta charset='utf-8'></head><body><script>alert('파라미터가 필요합니다.'); history.go(-1);</script></body></html>");
            return "responsebody/";
        }

        String loginStore = loginStoreStr;
//        loginStore = "00306";

        List<Order> orderList = orderService.findByRestNo(loginStore);

        request.setAttribute("loginStore", loginStore);

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
