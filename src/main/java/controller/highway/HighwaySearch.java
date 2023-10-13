package controller.highway;

import common.Handler;
import data.entity.Highway;
import service.master.HighwayService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class HighwaySearch implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int searchType = Integer.parseInt(request.getParameter("searchType"));
        int memberNo = Integer.parseInt(request.getParameter("memberNo"));
        String searchWord = request.getParameter("searchWord");
        String routeNm = request.getParameter("routeNm");

        System.out.println(memberNo);
        System.out.println(searchType);
        System.out.println(routeNm);
        System.out.println(searchWord);

        HighwayService highwayService = new HighwayService();
        ArrayList<Highway> list = highwayService.getByNm(routeNm);

        request.setAttribute("list", list);
        return "/highway/highwaylist.jsp";
    }

    @Override
    public String getPath() {
        return path + "/highwaysearch";
    }
}
