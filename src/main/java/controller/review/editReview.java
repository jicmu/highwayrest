package controller.review;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import common.Handler;
import common.Size;
import data.entity.Review;
import org.apache.commons.io.FileUtils;
import service.review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class editReview implements Handler {
    String go = "/review/edit.jsp";
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewService service = new ReviewService();
        int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
        Review r = service.getReviewByReviewNo(reviewNo);
        request.setAttribute("r", r);
        request.setAttribute("view", "/highwayrest/edit.jsp");
        return go;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewService service = new ReviewService();


        String content = request.getParameter("content");
        int star = Integer.parseInt(request.getParameter("star"));
        int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));

        System.out.println(content);
        System.out.println(reviewNo);
        System.out.println(star);

        service.editReview(new Review(reviewNo, 0, null, content, star, 0, "", "", ""));

        return "redirect/listReview";
    }

    @Override
    public String getPath() {
        return path + "/editReview";
    }
}
