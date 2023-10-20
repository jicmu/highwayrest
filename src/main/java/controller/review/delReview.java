package controller.review;

import common.Handler;
import data.entity.Review;
import org.apache.commons.io.FileUtils;
import service.review.ReviewService;

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

        String path = "C:\\Users\\user\\Desktop\\project\\photo\\" + r.getMemberNo() + File.separator + r.getReviewNo();

        File delDir = new File(path);
        if(delDir.exists()){
            FileUtils.deleteDirectory(delDir);
        }

        service.delReview(reviewNo);
        return "redirect/myReview";
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
