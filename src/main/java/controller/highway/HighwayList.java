package controller.highway;

import common.Handler;
import data.entity.Highway;
import service.master.HighwayService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class HighwayList implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HighwayService highwayService = new HighwayService();
        ArrayList<Highway> list = highwayService.getAll();

        request.setAttribute("list", list);
        request.setAttribute("view", "/highway/highwaylist.jsp");
        return "/index.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       return null;
    }

    @Override
    public String getPath() {
        return path + "/highwaylist";
    }
}
