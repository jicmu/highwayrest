package controller.review;

import common.Handler;
import data.entity.Review;
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

        //TODO File 속성, 리뷰 삭제 시 사진 및 폴더 삭제도 같이. path 구하는 방법?
        String nPath = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + r.getMemberNo() + "\\" + r.getWDate() + "\\" + r.getReviewNo();
        //사진 삭제
        String[] imgs = {r.getImg1(), r.getImg2(), r.getImg3()};

        for (String f : imgs) {
            if (f != null) {
                File del = new File(nPath + f);
                del.delete();
            }
        }

        if (nPath.contains("reviewNo")) {
            //폴더 삭제
            File dir = new File(nPath);
            while (dir.exists()) {
                File[] dir_list = dir.listFiles();//폴더 내 파일 리스트
                if (dir_list.length == 0 && dir.isDirectory()) {//파일이 없으면
                    dir.delete();//폴더 삭제
                }
            }
        }

        service.delReview(reviewNo);
        return null;
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
