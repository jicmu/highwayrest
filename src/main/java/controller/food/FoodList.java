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
        String restNo = request.getParameter("restNo");
        RestFoodService restFoodService = new RestFoodService();
        ArrayList<RestFood> list = restFoodService.getBySCd(restNo);

        request.setAttribute("restNo", restNo);
        request.setAttribute("list", list);
        request.setAttribute("view", "/food/foodlist.jsp");
        return "/index.jsp";
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
