package controller.payment;

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
            String partnerOrderId = "2";
            String partnerUserId = "testId";

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

            return "redirect/" + path + "/payment/success";
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
