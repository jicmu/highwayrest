package controller.paymentStore;

import common.Handler;
import orders.Order;
import orders.OrderService;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageStoreApiHandler implements Handler {

    public PageStoreApiHandler() {
        System.out.println("PageApiHandler 생성!");
    }

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderService();

        // TODO 세션으로 변경
        String loginStore = (String) request.getSession().getAttribute("loginStore");
        loginStore = "306";

        String page = request.getParameter("page");
        String amount = request.getParameter("amount");

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        List<Order> orders = orderService.findByRestNoWithPaging(Integer.parseInt(loginStore), Integer.parseInt(page), Integer.parseInt(amount));

        Map<Integer, String> ordersMap = new HashMap<>();

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderNo", order.getOrderNo());
            jsonObject.put("foodNo", order.getFoodNo());
            jsonObject.put("restNo", order.getRestNo());
            jsonObject.put("pay", order.getPay());
            jsonObject.put("quantity", order.getQuantity());
            jsonObject.put("memberNo", order.getMemberNo());
            jsonObject.put("ordersNo", order.getOrdersNo());
            jsonObject.put("status", order.getStatus());
            jsonObject.put("oDate", order.getODate().toString());
            jsonObject.put("menu", order.getMenu());

            ordersMap.put(i, jsonObject.toJSONString());
        }

        JSONObject ordersJson = new JSONObject(ordersMap);


        return "responsebody/" + ordersJson.toJSONString();
    }

    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "";
    }

    public String getPath() {
        return path + "/api/pageStore";
    }
}
