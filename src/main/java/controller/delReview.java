package controller;

import common.Handler;
import review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class delReview implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));

        ReviewService service = new ReviewService();
        service.delReview(reviewNo);
        return null;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return "/delReview";
    }
}
