package controller.review;

import common.Handler;
import common.ImageFile;
import data.entity.RestFood;
import data.entity.Review;
import orders.Order;
import orders.OrderService;
import service.master.RestFoodService;
import service.review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class myReview implements Handler {

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String memberNo = String.valueOf(request.getSession().getAttribute("loginNum"));

        ReviewService reviewService = new ReviewService();
        OrderService orderService = new OrderService();

        ArrayList<Review> myList = reviewService.getReviewByMember(Integer.parseInt(memberNo));
        List<Order> myOrders = orderService.getMyOrders(Integer.parseInt(memberNo));
        String restNo = "";
        for (Order myOrder : myOrders) {
            restNo = myOrder.getRestNo();
        }

        request.setAttribute("restNo", restNo);request.setAttribute("myList", myList);

        HashMap<Integer, ArrayList<String>> images = new HashMap<>();
        for (Review r : myList) {
            String path = "src\\main\\java\\controller\\yeonann\\pic" + r.getMemberNo()+ "\\" + r.getReviewNo();
            images.put(r.getReviewNo(), ImageFile.getImageFiles(path));
        }
        request.setAttribute("images", images);
        request.setAttribute("view","/review/myList.jsp");

        return "/index.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return path + "/myReview";
    }
}
