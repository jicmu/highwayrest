package controller.highway;

import common.Handler;
import data.entity.HighwayRest;
import data.entity.RestFood;
import service.master.HighwayRestService;
import service.master.RestFoodService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class RestFoodCRUD implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HighwayRestService service = new HighwayRestService();
        ArrayList<HighwayRest> list = service.getAll();

        request.setAttribute("list", list);

        return "/highway/master/restfood.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] foodList = request.getParameterValues("food");

        RestFoodService service = new RestFoodService();

        ArrayList<RestFood> list = new ArrayList<>();
        for(String food : foodList) {
            String[] item = food.split("/");
            list.add(new RestFood(0, item[0], 0, item[1]));
        }

        service.addFood(list);

        request.setAttribute("OK", "OK");

        return "/highway/master/restfood.jsp";
    }

    @Override
    public String getPath() {
        return path + "/master/restfood";
    }
}
