package controller.review;

import common.Handler;
import review.Review;
import review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class listReview implements Handler {
    String view = "/index.jsp";
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("review 확인");
        ReviewService service = new ReviewService();
        ArrayList<Review> list = service.getAll();

        request.setAttribute("list", list);
        request.setAttribute("view", "/review/list.jsp");
        return view;
    }


    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "";
    }

    @Override
    public String getPath() {
        return path + "/listReview";
    }
}
