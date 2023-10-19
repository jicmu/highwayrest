package controller.master;

import common.Handler;
import data.entity.RestFood;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import service.master.RestFoodService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class FoodMenuList implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String svarCd = request.getParameter("svarCd");
        System.out.println("svarCd:" + svarCd);

        RestFoodService service = new RestFoodService();
        ArrayList<RestFood> list = service.getBySCd(svarCd);

        JSONArray array = new JSONArray();
        for(RestFood food : list) {
            JSONObject item = new JSONObject();

            item.put("no", food.getNo());
            item.put("name", food.getName());
            item.put("stdRestCd", food.getStdRestCd());
            item.put("foodCost", food.getFoodCost());


            array.add(item);
        }

        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(array);
        return "response/";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String svarCd = request.getParameter("svarCd");
        System.out.println("svarCd:" + svarCd);

        RestFoodService service = new RestFoodService();
        ArrayList<RestFood> list = service.getBySCd(svarCd);

        JSONArray array = new JSONArray();
        JSONObject obj = new JSONObject();
        for(RestFood food : list) {
            array.add(food);
        }
        obj.put("dataList", array);

        return "response/" + array.toJSONString();
    }

    @Override
    public String getPath() {
        return path + "/food/menu";
    }
}
