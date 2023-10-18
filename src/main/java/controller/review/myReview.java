package controller.review;

import common.Handler;
import common.ImageFile;
import data.entity.Review;
import service.review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class myReview implements Handler {
    String go = "/review/mylist.jsp";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String loginNum = String.valueOf(request.getSession().getAttribute("loginNum"));
        int memberNo = Integer.parseInt(request.getParameter("memberNo"));

        ReviewService service = new ReviewService();

        ArrayList<Review> myReview = service.getReviewByMember(memberNo);
        request.setAttribute("myReview", myReview);

        HashMap<Integer, ArrayList<String>> images = new HashMap<>();
        //map.put(reviewNo, path)
        for (Review r : myReview) {
            String path = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + r.getMemberNo()+ "\\" + r.getReviewNo();
            images.put(r.getReviewNo(), ImageFile.getImageFiles(path));
        }

        request.setAttribute("images", images);
        request.setAttribute("view", "/review/mylist.jsp");


        return "/review/myList.jsp";
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
