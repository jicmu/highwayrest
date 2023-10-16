package controller.review;

import common.Handler;
import data.entity.Review;
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
        int svarCd = Integer.parseInt(request.getParameter("svarCd"));
        ArrayList<Review> list = service.getAll(svarCd);

        String sort = request.getParameter("sort");
        if(sort.equals("sortHighRate")){
            list = service.getAllByHighRate(svarCd);
        }else if(sort.equals("sortLowRate")){
            list = service.getAllByLowRate(svarCd);
        }

        request.setAttribute("list", list);
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