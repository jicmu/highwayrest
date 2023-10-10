package controller;

import common.Handler;
import common.ImageFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Look implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> list = ImageFile.getImageFiles("/Users/jeonggukyoo/example");
        request.setAttribute("list", list);

        System.out.println("list.size(): " + list.size());

        return "/master/look.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return path + "/master/look";
    }
}
