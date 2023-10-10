package controller.payment;

import common.Handler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

public class PaymentSuccess implements Handler {

    public PaymentSuccess() {
        System.out.println("PaymentSuccess 생성!");
    }

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("PaymentSuccess.doGet");

        String paymentResult = (String) request.getSession().getAttribute("paymentResult");
        request.getSession().removeAttribute("paymentResult");

        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(paymentResult);

            request.setAttribute("tid", jsonObject.get("tid"));
            request.setAttribute("amount", jsonObject.get("amount"));
            request.setAttribute("approvedAt", jsonObject.get("approved_at"));
            request.setAttribute("itemName", jsonObject.get("item_name"));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "/payment/success.jsp";
    }

    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("PaymentSuccess.doPost");
        return doGet(request, response);
    }

    public String getPath() {
        return path + "/payment/success";
    }
}
