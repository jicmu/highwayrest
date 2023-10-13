package controller.highway;

import common.Handler;
import common.ImageFile;
import data.entity.Highway;
import data.entity.HighwayRest;
import service.master.HighwayRestService;
import service.master.HighwayService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HighwayCRUD implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getContextPath());

        String data = ImageFile.getImageFile("free-icon-call-order-6425539", "/Users/jeonggukyoo/icons");

        request.setAttribute("data", data);

        return "/highway/master/home.jsp";
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
        service2.addHighwayRest(new HighwayRest(0, highwayrest[0], highwayrest[1], Integer.parseInt(highwayrest[2]), highwayrest[3], highwayrest[4], highwayrest[5]));
        return "/index.jsp";
    }

    @Override
    public String getPath() {
        return path + "/master/home";
    }
}
