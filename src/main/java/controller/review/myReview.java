package controller.review;

import common.Handler;
import data.entity.Review;
import orders.Order;
import orders.OrderService;
import service.review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

        request.setAttribute("restNo", restNo);
        request.setAttribute("myList", myList);

        request.setAttribute("view","/review/myList.jsp");

        return "/index.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/myReview";
    }

    @Override
    public String getPath() {
        return path + "/myReview";
    }
}
