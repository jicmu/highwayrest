package controller.payment;

import common.Handler;
import orders.Order;
import orders.OrderService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PaymentKakaoApprove implements Handler {
    private static final long serialVersionUID = 1L;

    public PaymentKakaoApprove() {
        super();
    }

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        URL credentialUrl = Thread.currentThread().getContextClassLoader().getResource("../../WEB-INF/credential.properties");

        Properties properties = new Properties();

        properties.load(new FileReader(credentialUrl.getPath()));

        String kakaoKey = properties.getProperty("kakaoKey");

        try {
            String partnerOrderId = (String) request.getSession().getAttribute("partnerOrderId");
            String partnerUserId = (String) request.getSession().getAttribute("loginId");

            request.getSession().removeAttribute("partnerOrderId");
            request.getSession().removeAttribute("partnerUserId");

            String stdRestCd = (String) request.getSession().getAttribute("stdRestCd");

            request.getSession().removeAttribute("stdRestCd");

            request.getSession().removeAttribute("partnerOrderId");

            // Approve
            String approveUrlStr = "https://kapi.kakao.com/v1/payment/approve";

            URL approveUrl = new URL(approveUrlStr);

            HttpURLConnection approveHuc = (HttpURLConnection) approveUrl.openConnection();

            approveHuc.setDoInput(true);
            approveHuc.setDoOutput(true);

            approveHuc.setRequestMethod("POST");
            approveHuc.setRequestProperty("Authorization", "KakaoAK " + kakaoKey);
            approveHuc.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            Map<String, String> approveParamMap = new HashMap<>();

            approveParamMap.put("cid", "TC0ONETIME");
            approveParamMap.put("tid", (String) request.getSession().getAttribute("tid"));

            request.getSession().removeAttribute("tid");

            approveParamMap.put("partner_order_id", partnerOrderId);
            approveParamMap.put("partner_user_id", partnerUserId);
            approveParamMap.put("pg_token", request.getParameter("pg_token"));

            String approveParam = "";
            for (Map.Entry<String, String> entry : approveParamMap.entrySet()) {
                approveParam += entry.getKey() + "=" + entry.getValue() + "&";
            }

            approveHuc.getOutputStream().write(approveParam.getBytes("utf-8"));

            BufferedReader approveBr = new BufferedReader(new InputStreamReader(approveHuc.getInputStream()));

            JSONParser jsonParser = new JSONParser();

            JSONObject parsedApproved = (JSONObject) jsonParser.parse(approveBr);

            request.getSession().setAttribute("paymentResult", parsedApproved.toJSONString());

            // DB 등록
            String foodNos = (String) request.getSession().getAttribute("foodNos");
            request.getSession().removeAttribute("foodNos");
            String items = (String) request.getSession().getAttribute("items");
            request.getSession().removeAttribute("items");
            String quantities = (String) request.getSession().getAttribute("quantities");
            request.getSession().removeAttribute("quantities");

            JSONObject parsedItems = (JSONObject) jsonParser.parse(items);
            JSONObject parsedQuantities = (JSONObject) jsonParser.parse(quantities);
            JSONObject parsedFoodNos = (JSONObject) jsonParser.parse(foodNos);

            OrderService orderService = new OrderService();

            for (Object o : parsedItems.keySet()) {
                String item = (String) o;
                String amount = (String) parsedItems.get(o);
                String quantity = (String) parsedQuantities.get(o);
                String foodNo = (String) parsedFoodNos.get(o);


                orderService.order(Order.builder()
                        .foodNo(Integer.parseInt(foodNo))
                        .restNo(Integer.parseInt(stdRestCd))
                        .pay(Integer.parseInt(amount))
                        .quantity(Integer.parseInt(quantity))
                        .memberNo(1) // session에서 가져와야함
                        .ordersNo((String) parsedApproved.get("tid"))
                        .build()
                );
            }

            return "redirect/" + path + "/orders";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return path;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return path + "/payment/kakao/approve";
    }
}
