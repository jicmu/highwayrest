package controller.rest;

import common.Handler;
import data.entity.Highway;
import data.entity.HighwayRest;
import service.master.HighwayRestService;
import service.master.HighwayService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class RestSearch implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int searchType = Integer.parseInt(request.getParameter("searchType"));
        int memberNo = Integer.parseInt(request.getParameter("memberNo"));
        String searchWord = request.getParameter("searchWord");
        String svarNm = request.getParameter("svarNm");

        System.out.println(memberNo);
        System.out.println(searchType);
        System.out.println(svarNm);

        HighwayRestService highwayRestService = new HighwayRestService();
        ArrayList<HighwayRest> list = highwayRestService.getByNm(svarNm);

        request.setAttribute("list", list);
        return "/rest/restlist.jsp";
    }

    @Override
    public String getPath() {
        return path + "/restsearch";
    }
}
