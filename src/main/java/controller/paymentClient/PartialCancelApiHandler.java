package controller.paymentClient;

import common.Handler;
import orders.Order;
import orders.OrderService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PartialCancelApiHandler implements Handler {

    public PartialCancelApiHandler() {
        System.out.println("PartialCancelApiHandler 생성!");
    }

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "";
    }

    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderService();

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        JSONParser parser = new JSONParser();

        StringBuilder sb = new StringBuilder();
        String json = "";
        while ((json = br.readLine()) != null) {
            sb.append(json);
        }

        String orderNo = "";
        try {
            JSONObject parsed = (JSONObject) parser.parse(sb.toString());

            orderNo = (String) parsed.get("orderNo");


        } catch (ParseException e) {
            e.printStackTrace();
        }

        Order order = orderService.findByOrderNo(Integer.parseInt(orderNo));

        try {
            orderService.cancelKakaoPay(order.getOrdersNo(), String.valueOf(order.getPay()));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        int cancel = orderService.partialCancel(Integer.parseInt(orderNo));

        return "";
    }

        public String getPath() {
        return path + "/api/partial-cancel";
    }
}
