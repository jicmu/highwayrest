package controller;

import common.Handler;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PaymentKakao implements Handler {
    private static final long serialVersionUID = 1L;

    public PaymentKakao() {
        super();
    }

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        URL credentialUrl = Thread.currentThread().getContextClassLoader().getResource("../../WEB-INF/credential.properties");

        Properties properties = new Properties();

        properties.load(new FileReader(credentialUrl.getPath()));

        String kakaoKey = properties.getProperty("kakaoKey");

        try {
            URL url = new URL("https://kapi.kakao.com/v1/payment/ready");

            HttpURLConnection huc = (HttpURLConnection) url.openConnection();

            huc.setRequestMethod("POST");
            huc.setRequestProperty("Authorization", "KakaoAK " + kakaoKey);
            huc.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            huc.setDoInput(true);
            huc.setDoOutput(true);

            String partnerOrderId = "2";
            String partnerUserId = "testId";

            Map<String, String> params = new HashMap<>();

            params.put("cid", "TC0ONETIME");
            params.put("partner_order_id", partnerOrderId); // TODO Order 번호를 받아와서 수정
//            params.put("partner_user_id", (String) request.getSession().getAttribute("user_id"));;
            params.put("partner_user_id", partnerUserId);

            String[] items = request.getParameterValues("items");

            params.put("item_name",  "'" + items[0] + " 외 " + (items.length - 1) + "개'");
            params.put("quantity", String.valueOf(items.length));

            params.put("total_amount", request.getParameter("total"));
            params.put("tax_free_amount", "0");

            params.put("approval_url", "http://localhost/payment/kakao/approve");
            params.put("cancel_url", "http://localhost/payment/cancel");
            params.put("fail_url", "http://localhost/payment/fail");

            String param = "";

            for (Map.Entry<String, String> entry : params.entrySet()) {
                param += entry.getKey() + "=" + entry.getValue() + "&";
            }

            huc.getOutputStream().write(param.getBytes("utf-8"));

            BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));

            JSONParser jsonParser = new JSONParser();
            JSONObject parsed = (JSONObject) jsonParser.parse(br);

            request.getSession().setAttribute("tid", parsed.get("tid"));
            return "redirect/" + (String) parsed.get("next_redirect_pc_url");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "/";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return "/payment/kakao";
    }
}
