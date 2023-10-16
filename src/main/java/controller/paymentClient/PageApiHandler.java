package controller.paymentClient;

import common.Handler;
import orders.Order;
import orders.OrderService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageApiHandler implements Handler {

    public PageApiHandler() {
        System.out.println("PageApiHandler 생성!");
    }

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderService();

        int user = (int) request.getSession().getAttribute("loginNum");
        String page = request.getParameter("page");
        String amount = request.getParameter("amount");

        List<Order> orders = orderService.findByMemberIdWithPaging(user, Integer.parseInt(page), Integer.parseInt(amount));

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
        return path + "/api/page";
    }
}
