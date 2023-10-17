package controller.review;

import common.Handler;
import data.dto.RestReviewDTO;
import service.review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class listReview implements Handler {
    String go = "/review/list.jsp";
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String svarCd = request.getParameter("svarCd");
        System.out.println(svarCd);
        ReviewService service = new ReviewService();
        ArrayList<RestReviewDTO> list = service.getAll(svarCd);//휴게소별 전체 후기 조회
        request.setAttribute("svarcCd", svarCd);
        request.setAttribute("list", list);

        request.setAttribute("view", "/review/list.jsp");
        return go;
    }


    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "";
    }

    @Override
    public String getPath() {
        return path + "/listReview";
    }
}
