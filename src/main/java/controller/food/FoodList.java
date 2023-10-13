package controller.food;

import common.Handler;
import data.entity.RestFood;
import service.master.HighwayRestService;
import service.master.RestFoodService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class FoodList implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stdRestCd = request.getParameter("stdRestCd");
        RestFoodService restFoodService = new RestFoodService();
        ArrayList<RestFood> list = restFoodService.getBySCd(stdRestCd);

        request.setAttribute("stdRestCd", stdRestCd);
        request.setAttribute("list", list);
        return "/order/orderlist.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return path + "/foodlist";
    }
}
