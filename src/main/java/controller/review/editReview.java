package controller.review;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import common.Handler;
import common.Size;
import data.entity.Review;
import org.apache.commons.io.FileUtils;
import service.review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class editReview implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewService service = new ReviewService();
        int ReviewNo = Integer.parseInt(request.getParameter("ReviewNo"));
        Review r = service.getReviewByReviewNo(ReviewNo);
        request.setAttribute("r", r);
        return null;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //수정-> 기존 후기의 사진 폴더 삭제 -> 새로운 폴더 생성(현재 날짜로 변경) -> 사진 저장
        
        //기존 후기에 첨부된 사진이 저장된 폴더의 주소
        ReviewService service = new ReviewService();
        int reviewNo = service.getSeq();
        Review r = service.getReviewByReviewNo(reviewNo);

        //기존 후기에 첨부된 사진과 폴더 삭제
        String path = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + r.getMemberNo() + "\\" + r.getWDate() + "\\" + r.getReviewNo();
        String osName1 = System.getProperty("os.name");
        if (osName1.contains("win")) {
            path = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + r.getMemberNo() + "\\" + r.getWDate() + "\\" + r.getReviewNo();
        } else if (osName1.contains("mac")) {
            path = "";
        } else if (osName1.contains("nix") || osName1.contains("nux")) {
            path = "";
        }
        
        //기존 후기의 폴더/파일 삭제
        File dir = new File(path);
        if(dir.exists()){//폴더가 존재하면
            FileUtils.cleanDirectory(dir);//폴더와 파일 모두 삭제
        }
        //새로운 폴더 생성 및 사진 첨부 -> 수정한 현재 시점의 날짜 구하기
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fileDate = sdf.format(date);

        String nPath = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + r.getMemberNo() + "\\" + fileDate + "\\" + reviewNo;
        if (osName1.contains("win")) {
            nPath = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + r.getMemberNo() + "\\" + fileDate + "\\" + reviewNo;
        } else if (osName1.contains("mac")) {
            nPath = "";
        } else if (osName1.contains("nix") || osName1.contains("nux")) {
            nPath = "";
        }

        File nDir = new File(nPath);

        MultipartRequest mr = new MultipartRequest(request, nPath, Size.MidSize.getValue(), "utf-8",
                new DefaultFileRenamePolicy());
        
        int star = Integer.parseInt(mr.getParameter("star"));
        String content = mr.getParameter("content");
        int memberNo = Integer.parseInt(mr.getParameter("memberNo"));
        int orderNo = Integer.parseInt(mr.getParameter("orderNo"));

        File[] files = {mr.getFile("img1"), mr.getFile("img2"), mr.getFile("img3")};
        String[] imgs = new String[3];
        for (int i = 0; i < files.length; i++) {
            if (files[i] != null && files[i].length() != 0) {
                imgs[i] = files[i].getName();
            } else {
                imgs[i] = null;
            }
        }

        //해당 폴더가 없으면 중첩된 폴더 생성: memberNo 안에 fileDate 중첩된 폴더 생성
        if (imgs[0] != null) {//업로드한 파일이 있을 때
            if (!nDir.exists()) {//폴더가 없으면
                nDir.mkdirs();//폴더 생성
                System.out.println("폴더를 생성합니다.");
            } else {
                System.out.println("폴더가 이미 존재하는 관계로 생성하지 않습니다.");
            }
        } else {//업로드한 파일이 없을 때
            System.out.println("업로드한 파일이 없습니다.");
        }

        service.editReview(new Review(reviewNo, memberNo, null, content, star, orderNo, imgs[0], imgs[1], imgs[2]));

        return null;
    }

    @Override
    public String getPath() {
        return path + "editReview";
    }
}
