package controller.review;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import common.Handler;
import common.Size;
import data.entity.Review;
import service.review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addReview implements Handler {
    String go = "/review/add.jsp";

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //int orderNo = Integer.parseInt(request.getParameter("orderNo"));

        request.setAttribute("go", "/review/add.jsp");

        return go;
    }


    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "C:\\Users\\RYU\\Desktop\\project\\photo\\";
        String osName = System.getProperty("os.name");
        if (osName.contains("win")) {
            path = "C:\\Users\\RYU\\Desktop\\project\\photo";
        } else if (osName.contains("mac")) {
            path = "";
        } else if (osName.contains("nix") || osName.contains("nux")) {
            path = "";
        }

        MultipartRequest mr = new MultipartRequest(request, path, Size.MidSize.getValue(), "utf-8",
                new DefaultFileRenamePolicy());

//       MultipartRequest mr = ImageFile.fileUpload(path, Size.BigSize, request);

        File[] files = {mr.getFile("img1"), mr.getFile("img2"), mr.getFile("img3")};
        String[] imgs = new String[3];
        for (int i = 0; i < files.length; i++) {
            if (files[i] != null && files[i].length() != 0) {
                imgs[i] = files[i].getName();
            } else {
                imgs[i] = null;
            }
        }

        String content = mr.getParameter("content");
        int memberNo = Integer.parseInt(mr.getParameter("memberNo"));
        int orderNo = Integer.parseInt(mr.getParameter("orderNo"));
        int star = Integer.parseInt(mr.getParameter("star"));

        //현재 날짜 구하기
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //20231012 형식으로 출력됨
        String fileDate = sdf.format(date);

        ReviewService service = new ReviewService();
        int reviewNo = service.getSeq();

        String nPath = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + memberNo + "\\" + fileDate + "\\" + reviewNo;
        String osName1 = System.getProperty("os.name");
        if (osName1.contains("win")) {
            nPath = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + memberNo + "\\" + fileDate + "\\" + reviewNo;
        } else if (osName1.contains("mac")) {
            nPath = "";
        } else if (osName1.contains("nix") || osName1.contains("nux")) {
            nPath = "";
        }

        File dir = new File(nPath);

        //해당 폴더가 없으면 중첩된 폴더 생성: memberNo 안에 fileDate 중첩된 폴더 생성
        if (imgs[0] != null) {//업로드한 파일이 있을 때
            if (!dir.exists()) {//폴더가 없으면
                dir.mkdirs();//폴더 생성
                System.out.println("폴더를 생성합니다.");
            } else {
                System.out.println("폴더가 이미 존재하는 관계로 생성하지 않습니다.");
            }
        } else {//업로드한 파일이 없을 때
            System.out.println("업로드한 파일이 없습니다.");
        }

        //파일 이동
        File oDir = new File(path);
        File nDir = new File(nPath);
        if (oDir.exists()) {
            File[] fNames = oDir.listFiles();//목록 읽기
            for (int i = 0; i < fNames.length; i++) {
                if (fNames[i].isFile()) {
                    if (fNames[i].exists()) {
                        if (oDir.exists()) {
                        }
                        File moveFile = new File(nDir, fNames[i].getName());
                        fNames[i].renameTo(moveFile);
                    }
                }
            }
        }

        service.addReview(new Review(reviewNo, memberNo, null, content, star, orderNo, imgs[0], imgs[1], imgs[2]));
        return "redirect/index.jsp";
    }

    public String getPath() {
        return path + "/addReview";
    }
}
