package controller.review;

import common.Handler;
import common.ImageFile;
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

        ReviewService service = new ReviewService();
        ArrayList<RestReviewDTO> list = service.getAll(svarCd);

        int memberNo, reviewNo;
        ArrayList<ArrayList<String>> images = new ArrayList<>();
        for (RestReviewDTO r : list) {
            memberNo = r.getMemberNo();
            reviewNo = r.getReviewNo();
            String path = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + memberNo + "\\" + reviewNo;
            images.add(ImageFile.getImageFiles(path));
        }

        request.setAttribute("images", images);
        request.setAttribute("svarCd", svarCd);
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
