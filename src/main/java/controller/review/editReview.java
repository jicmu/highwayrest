package controller.review;

import common.Handler;
import review.Review;
import review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class editReview implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewService service = new ReviewService();
        int ReviewNo = Integer.parseInt(request.getParameter("ReviewNo"));
        Review r = service.getReviewByReviewNo(ReviewNo);
        request.setAttribute("r", r);
        return null;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return path + "editReview";
    }
}
