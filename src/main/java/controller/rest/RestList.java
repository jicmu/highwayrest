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

public class RestList implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String routeCd = request.getParameter("routeCd");
        HighwayRestService highwayRestService = new HighwayRestService();
        HighwayService highwayService = new HighwayService();
        Highway highway = highwayService.getByCd(routeCd);
        ArrayList<HighwayRest> list = highwayRestService.getByRCd(routeCd);

        request.setAttribute("highway", highway);
        request.setAttribute("list", list);
        request.setAttribute("view", "/rest/restlist.jsp");
        return "/index.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return path + "/restlist";
    }
}
