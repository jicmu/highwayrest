package controller.rest;

import common.Handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestList implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/rest/restlist.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int searchType = Integer.parseInt(request.getParameter("searchType"));
        int memberNum = Integer.parseInt(request.getParameter("memberNum"));
        String searchWord = request.getParameter("searchWord");

        return null;
    }

    @Override
    public String getPath() {
        return path + "/restlist";
    }
}
