package controller.review;

import common.Handler;
import data.entity.Review;
import service.review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class myReview implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int memberNo = Integer.parseInt(request.getParameter("memberNo"));

        ReviewService service = new ReviewService();
        ArrayList<Review> mylist = service.getReviewByMember(memberNo);
        request.setAttribute("mylist", mylist);

        return null;
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
