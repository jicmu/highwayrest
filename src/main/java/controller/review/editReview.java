package controller.review;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import common.Handler;
import common.ImageFile;
import common.Size;
import data.entity.Review;
import org.apache.commons.io.FileUtils;
import service.review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class editReview implements Handler {
    String go = "/index.jsp";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewService service = new ReviewService();
        int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
        Review r = service.getReviewByReviewNo(reviewNo);
        request.setAttribute("r", r);

        ArrayList<String> images = ImageFile.getImageFiles("C:\\Users\\RYU\\Desktop\\project\\photo\\" + r.getMemberNo() + "\\" + r.getReviewNo());
        request.setAttribute("images", images);

        request.setAttribute("view", "/review/edit.jsp");
        return go;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ReviewService service = new ReviewService();
        //oPath로 파일을 받고 -> 기존 파일 삭제하고 -> oPath에 받은 파일은 nPath로 이동

//        String oPath = "C:\\Users\\RYU\\Desktop\\project\\photo\\";//사진이 저장되는 경로

        MultipartRequest mr = new MultipartRequest(request, "/", Size.MidSize.getValue(), "utf-8", new DefaultFileRenamePolicy());
        String svarCd = mr.getParameter("svarCd");

        int reviewNo = Integer.parseInt(mr.getParameter("reviewNo"));
        String content = mr.getParameter("content");
        int star = Integer.parseInt(mr.getParameter("star"));
        String restNo = mr.getParameter("restNo");

        Review r = service.getReviewByReviewNo(reviewNo);

//        String nPath = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + r.getMemberNo() + File.separator + r.getReviewNo();

//        File firstFile = mr.getFile("file");//받아온 사진
//        File oDir = new File(oPath);
//        File nDir = new File(nPath);//첨부된 사진이 이동할 폴더
//
//        if (firstFile != null) {//첨부된 파일이 있다면
//
//            File oFiles = new File(nPath);//기존 사진이 저장된 경로
//            FileUtils.cleanDirectory(oFiles);//해당 경로 폴더 내 파일 전부 삭제
//
//            if (oDir.exists()) {
//                File[] fNames = oDir.listFiles();//기존 폴더의 파일 목록 읽기
//                for (int i = 0; i < fNames.length; i++) {//존재하는 파일 수만큼 반복
//                    if (fNames[i].isFile()) {//파일이고
//                        if (fNames[i].exists()) {//있으면
//                            if (oDir.exists()) {
//                            }
//                            File moveFile = new File(nDir, fNames[i].getName());
//                            fNames[i].renameTo(moveFile);
//                        }
//                    }
//                }
//            }
//        }

//        String[] newFilesName = nDir.list();//이동한 파일 목록들 읽기
//        System.out.println(Arrays.toString(newFilesName));
//        String[] imgs = new String[3];
//        if (newFilesName != null) {
//            for (int i = 0; i < newFilesName.length; i++) {//파일 수 만큼 반복
//                if (newFilesName[i] != null) {
//                    imgs[i] = String.valueOf(newFilesName[i]);
//                    System.out.println(imgs[i]);
//                }
//            }
//        }
//
//        for (int j = 0; j < 3; j++) {
//            if (imgs[j] == null) {
//                imgs[j] = "";
//            }
//        }

        service.editReview(new Review(reviewNo, 0, null, content, star, 0, "", "", "", restNo));

        return "redirect/myReview";
    }

    @Override
    public String getPath() {
        return path + "/editReview";
    }
}
