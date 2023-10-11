package controller.review;

import common.Handler;
import review.Review;
import review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class delReview implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));

        ReviewService service = new ReviewService();
        Review r = service.getReviewByReviewNo(reviewNo);

        String[] imgs = null; //TODO File 속성
        //String[] imgs = { r.getFile1(), r.getFile2(), r.getFile3(), r.getFile4() };

        for (String f : imgs) {
            if (f != null) {
                File del = new File(path + f);
                del.delete();
            }
        }

        service.delReview(reviewNo);
        return null;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return path + "/delReview";
    }
}
