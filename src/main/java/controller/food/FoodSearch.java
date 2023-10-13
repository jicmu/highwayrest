package controller.food;

import common.Handler;
import data.entity.RestFood;
import service.master.RestFoodService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class FoodSearch implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int searchType = Integer.parseInt(request.getParameter("searchType"));
        int memberNo = Integer.parseInt(request.getParameter("memberNo"));
        String searchWord = request.getParameter("searchWord");
        String name = request.getParameter("name");

        System.out.println(memberNo);
        System.out.println(searchType);
        System.out.println(name);
        System.out.println(searchWord);

        RestFoodService restFoodService = new RestFoodService();
        ArrayList<RestFood> list = restFoodService.getByName(name);

        request.setAttribute("list", list);
        return "/order/orderlist.jsp";
    }

    @Override
    public String getPath() {
        return path + "/foodsearch";
    }
}
