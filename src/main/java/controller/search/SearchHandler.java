package controller.search;

import common.Handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchHandler implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int searchType = Integer.parseInt(request.getParameter("searchType"));
        int memberNum = Integer.parseInt(request.getParameter("memberNum"));
        String searchWord = request.getParameter("searchWord");

        String view = "";

        if(searchType == 1){

            view = "";
        } else if (searchType == 2) {

            view = "";
        } else if (searchType == 3) {

            view = "";
        }

//        SearchService searchService = new SearchService;
//        searchService.addSearch(new Search(0, searchType, memberNum, null, searchWord));

        request.setAttribute("view", view);
        return "/index.jsp";
    }

    @Override
    public String getPath() {
        return null;
    }
}
