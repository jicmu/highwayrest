package controller.review;

import common.Handler;
import service.review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class sortReview implements Handler {
    String go = "/review/list.jsp";
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewService service = new ReviewService();

        ArrayList<Review> list = new ArrayList<>();

        if(sort.equals("sortHighRate")){
            list = service.getAllByHighRate();
        }else if(sort.equals("sortLowRate")){
            list = service.getAllByLowRate();
        }
        request.setAttribute("list", list);
        request.setAttribute("view", "/review/review.jsp");

        return go;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return path + "sortReview";
    }
}