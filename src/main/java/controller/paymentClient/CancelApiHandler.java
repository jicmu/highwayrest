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


        // kakao 결제 취소
        URL credentialUrl = Thread.currentThread().getContextClassLoader().getResource("../../WEB-INF/credential.properties");

        Properties properties = new Properties();

        properties.load(new FileReader(credentialUrl.getPath()));

        String kakaoKey = properties.getProperty("kakaoKey");

        try {
            URL url = new URL("https://kapi.kakao.com/v1/payment/cancel");

            HttpURLConnection huc = (HttpURLConnection) url.openConnection();

            huc.setRequestMethod("POST");
            huc.setRequestProperty("Authorization", "KakaoAK " + kakaoKey);
            huc.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            huc.setDoInput(true);
            huc.setDoOutput(true);

            // TODO 추후 수정
            String companyId = "1";

            Map<String, String> params = new HashMap<>();

            params.put("cid", "TC0ONETIME");
            params.put("tid", ordersNo);
            params.put("cancel_tax_free_amount", "0");
            params.put("cancel_amount", String.valueOf(orderService.findTotalPrice(ordersNo)));

            String param = "";
            for (Map.Entry<String, String> entry : params.entrySet()) {
                param += entry.getKey() + "=" + entry.getValue() + "&";
            }

            huc.getOutputStream().write(param.getBytes("utf-8"));

            BufferedReader cancelBr = new BufferedReader(new InputStreamReader(huc.getInputStream()));

            JSONParser jsonParser = new JSONParser();
            JSONObject parsed = (JSONObject) jsonParser.parse(cancelBr);


            int cancel = orderService.cancel(ordersNo);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

        public String getPath() {
        return path + "/api/cancel";
    }
}
