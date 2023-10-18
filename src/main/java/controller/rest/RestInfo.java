package controller.rest;

import common.Handler;
import data.entity.HighwayRest;
import service.master.HighwayRestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestInfo implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String svarCd = request.getParameter("svarCd");
        HighwayRestService highwayRestService = new HighwayRestService();
        HighwayRest highwayrest = highwayRestService.getBySCd(svarCd);

        request.setAttribute("highwayrest", highwayrest);
        request.setAttribute("view", "/rest/restinfo.jsp");
        return "/index.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return path + "/restinfo";
    }
}
