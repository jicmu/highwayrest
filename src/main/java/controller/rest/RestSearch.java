package controller.rest;

import common.Handler;
import data.entity.Highway;
import data.entity.HighwayRest;
import data.entity.Search;
import service.master.HighwayRestService;
import service.master.HighwayService;
import service.search.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class RestSearch implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String routeCd = request.getParameter("routeCd");
        int direction = Integer.parseInt(request.getParameter("direction"));

        HighwayRestService highwayRestService = new HighwayRestService();
        HighwayService highwayService = new HighwayService();
        Highway highway = highwayService.getByCd(routeCd);

        ArrayList<HighwayRest> arr = highwayRestService.getByRCd(routeCd);
        ArrayList<HighwayRest> list = new ArrayList<>();
        for(HighwayRest r : arr){
            if(r.getGudClssCd() == direction){
                list.add(r);
            }
        }

        request.setAttribute("highway", highway);
        request.setAttribute("list", list);
        request.setAttribute("view", "/rest/restlist.jsp");
        return "/index.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int searchType = Integer.parseInt(request.getParameter("searchType"));
        String num = request.getParameter("memberNo");
        String searchWord = request.getParameter("searchWord");
        String svarNm = request.getParameter("svarNm");

        if(num.isEmpty()){
            num = "0";
        }

        int memberNo = Integer.parseInt(num);

        if(memberNo != 0){
            SearchService searchService = new SearchService();
            searchService.addSearch(new Search(0, searchType, memberNo, null, searchWord));
        }

        HighwayRestService highwayRestService = new HighwayRestService();
        ArrayList<HighwayRest> list = highwayRestService.getByNm(svarNm);

        request.setAttribute("list", list);
        request.setAttribute("view", "/rest/restlist.jsp");
        return "/index.jsp";
    }

    @Override
    public String getPath() {
        return path + "/restsearch";
    }
}
