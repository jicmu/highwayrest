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
    String go = "/review/mylist.jsp";
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String memberNo = String.valueOf(request.getSession().getAttribute("memberNo"));

        ReviewService service = new ReviewService();
      
        ArrayList<Review> mylist = service.getReviewByMember(Integer.parseInt(memberNo));
        request.setAttribute("mylist", mylist);

//        ArrayList<String> images = ImageFile.getImageFiles("C:\\Users\\RYU\\Desktop\\project\\photo\\" + r.getMemberNo() + "\\" + r.getReviewNo());
//        request.setAttribute("images", images);

        request.setAttribute("view","/review/mylist.jsp");


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
