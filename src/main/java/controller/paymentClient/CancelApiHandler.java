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
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

        String ordersNo = "";
        try {
            JSONObject parsed = (JSONObject) parser.parse(sb.toString());

            ordersNo = (String) parsed.get("ordersNo");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            orderService.cancelKakaoPay(ordersNo);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        int cancel = orderService.cancel(ordersNo);

        return "";
    }

        public String getPath() {
        return path + "/api/cancel";
    }
}
