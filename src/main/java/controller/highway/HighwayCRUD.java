package controller.highway;

import common.Handler;
import common.ImageFile;
import data.entity.Highway;
import data.entity.HighwayRest;
import data.entity.RestMaster;
import service.master.HighwayRestService;
import service.master.HighwayService;
import service.master.RestMasterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class HighwayCRUD implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println(request.getContextPath());
//
//        String data = ImageFile.getImageFile("free-icon-call-order-6425539", "/Users/jeonggukyoo/icons");
//
//        request.setAttribute("data", data);

        return "/master/home.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        // 도로
        String[] highway = request.getParameter("highway").split("/");
        HighwayService service1 = new HighwayService();
        service1.addHighway(new Highway(0, Integer.parseInt(highway[0]), highway[1], highway[2]));

        // 휴게소
        String[] highwayrest = request.getParameter("highwayrest").split("/");
        HighwayRestService service2 = new HighwayRestService();
        service2.addHighwayRest(new HighwayRest(0, highwayrest[0], highwayrest[1], Integer.parseInt(highwayrest[2]), highwayrest[3], highwayrest[4], highwayrest[5].replaceAll(",", " ")));

        // 휴게소 마스터
        RestMasterService service3 = new RestMasterService();
        int masterNo = service3.getNo();
        int status = service3.addMaster(new RestMaster(masterNo, highwayrest[0], null, null, highwayrest[3], 0));

        if(status == 1) {
            request.setAttribute("status", "1");
            return "redirect/master/login";
        } else {
            request.setAttribute("status", "0");
            return "redirect/master/login";
        }
    }

    @Override
    public String getPath() {
        return path + "/master/home";
    }
}
