package controller.review;

import common.Handler;
import data.entity.Review;
import service.review.ReviewService;

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
        int star = Integer.parseInt(request.getParameter("star"));
        String content = request.getParameter("content");

        ReviewService service = new ReviewService();
        //TODO 이미지파일
//        service.editReview(new Review(0, 0, null, content, star, 0 ));

        return null;
    }

    @Override
    public String getPath() {
        return path + "editReview";
    }
}
