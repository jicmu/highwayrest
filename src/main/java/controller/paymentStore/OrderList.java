package controller.paymentStore;

import common.Handler;
import orders.Order;
import orders.OrderService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrderList implements Handler {

    public OrderList() {
        System.out.println("data.entity.OrderList 생성!");
    }

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderService();

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String loginNum = request.getParameter("loginNum");
        System.out.println("loginNum: " + loginNum);

//        if (loginStoreStr == null || loginStoreStr.isEmpty()) {
//            response.getWriter().write("<html><head><meta charset='utf-8'></head><body><script>alert('파라미터가 필요합니다.'); history.go(-1);</script></body></html>");
//            return "responsebody/";
//        }

//        String loginStore = loginStoreStr;

        List<Order> orderList = orderService.findByRestNo(loginNum);

        request.setAttribute("loginStore", loginNum);

        request.setAttribute("orderList", orderList);

        request.setAttribute("view", "/store/orderlist.jsp");
        return "/store/order.jsp";
    }

    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginStore = request.getParameter("loginStore");
        System.out.println("post loginStore: " + loginStore);

        OrderService orderService = new OrderService();
        List<Order> list = orderService.findByRestNo(loginStore);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONArray array = new JSONArray();
        for(Order order : list) {
            JSONObject item = new JSONObject();

            item.put("orderNo", order.getOrderNo());
            item.put("foodNo", order.getFoodNo());
            item.put("restNo", order.getRestNo());
            item.put("pay", order.getPay());
            item.put("quantity", order.getQuantity());
            item.put("memberNo", order.getMemberNo());
            item.put("ordersNo", order.getOrdersNo());
            item.put("status", order.getStatus());
            item.put("oDate", format.format(order.getODate()));
            item.put("menu", order.getMenu());

            array.add(item);
        }
        int length = array.toJSONString().getBytes("utf-8").length;
        System.out.println("array Size: " + length);

        response.setContentType("application/json; charset=utf-8");
        response.setContentLength(length);
        response.getWriter().print(array);

        return "response/";
    }

    public String getPath() {
        return path + "/store/orders";
    }
}
