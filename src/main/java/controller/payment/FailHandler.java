package controller.payment;

import common.Handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FailHandler implements Handler {

    public FailHandler() {
        System.out.println("FailHandler 생성!");
    }

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/payment/fail.jsp";
    }

    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "";
    }

    public String getPath() {
        return path + "/payment/fail";
    }
}
