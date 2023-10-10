package controller;

import common.Handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderHandler implements Handler {

    public OrderHandler() {
        System.out.println("Order 생성!");
    }
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/order.jsp";
    }

    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "";
    }

    public String getPath() {
        return "/orders";
    }
}
