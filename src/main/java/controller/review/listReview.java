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
    String go = "/index.jsp";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String svarCd = request.getParameter("svarCd");

        ReviewService service = new ReviewService();
        ArrayList<RestReviewDTO> list = service.getAll(svarCd);

        for(int i = 0; i < list.size(); i++) {
            RestReviewDTO r = list.get(i);
            int memberNo = r.getMemberNo();
            int reviewNo = r.getReviewNo();
            String path = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + memberNo + "\\" + reviewNo;

            ArrayList<String> img = ImageFile.getImageFiles(path);

            int j = img.size();
            for(int k =0; k < j; k++) {
                if(k == 0)
                    r.setImg1(img.get(k));
                else if(k == 1)
                    r.setImg2(img.get(k));
                else if(k == 2)
                    r.setImg3(img.get(k));
            }
            list.set(i, r);
        }

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
