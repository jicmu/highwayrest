package controller.paymentClient;

import common.Handler;
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

public class CancelApiHandler implements Handler {

    public CancelApiHandler() {
        System.out.println("CancelApiHandler 생성!");
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

        try {
            JSONObject parsed = (JSONObject) parser.parse(sb.toString());

            String ordersNo = (String) parsed.get("ordersNo");

            int accept = orderService.cancel(ordersNo);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String getPath() {
        return path + "/api/cancel";
    }
}
