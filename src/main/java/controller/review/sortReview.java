package controller.review;

import common.Handler;
import data.dto.RestReviewDTO;
import service.review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class sortReview implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewService service = new ReviewService();
        String svarCd = request.getParameter("svarCd");

//        request.setAttribute("list", list);
        return "/review/list.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return path + "/sortReview";
    }
}