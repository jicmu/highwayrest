package controller.highway;

import common.Handler;
import data.entity.Highway;
import data.entity.Search;
import service.master.HighwayService;
import service.search.SearchService;

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

        SearchService searchService = new SearchService();
        searchService.addSearch(new Search(0, searchType, memberNo, null, searchWord));

        HighwayService highwayService = new HighwayService();
        ArrayList<Highway> list = highwayService.getByNm(routeNm);

        request.setAttribute("list", list);
        request.setAttribute("view", "/highway/highwaylist.jsp");
        return "/index.jsp";
    }

    @Override
    public String getPath() {
        return path + "/highwaysearch";
    }
}
