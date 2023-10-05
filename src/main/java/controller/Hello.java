package controller;

import common.Handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Hello implements Handler {
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/index.jsp";
    }

    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "";
    }

    public String getPath() {
        return "/highwayrest";
    }
}
