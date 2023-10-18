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
import java.util.Arrays;

public class addReview implements Handler {
    String go = "/review/add.jsp";

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //int orderNo = Integer.parseInt(request.getParameter("orderNo"));

        request.setAttribute("view", "/review/add.jsp");

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

        String content = mr.getParameter("content");
        int memberNo = Integer.parseInt(mr.getParameter("memberNo"));
        int orderNo = Integer.parseInt(mr.getParameter("orderNo"));
        int star = Integer.parseInt(mr.getParameter("star"));
        String restNo = mr.getParameter("restNo");

        ReviewService service = new ReviewService();
        int reviewNo = service.getSeq();

        //해당 폴더가 없으면 중첩된 폴더 생성: memberNo 안에 fileDate 중첩된 폴더 생성
        String nPath = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + memberNo + File.separator  + reviewNo;
        String osName1 = System.getProperty("os.name");
        if (osName1.contains("win")) {
            nPath = "C:\\Users\\RYU\\Desktop\\project\\photo\\" + memberNo + File.separator + reviewNo;
        } else if (osName1.contains("mac")) {
            nPath = "";
        } else if (osName1.contains("nix") || osName1.contains("nux")) {
            nPath = "";
        }

        File dir = new File(nPath);

        File firstFile = mr.getFile("file");
        if (firstFile != null) {//업로드한 파일이 있을 때
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
            File[] fNames = oDir.listFiles();//기존 폴더의 파일 목록 읽기
            for (int i = 0; i < fNames.length; i++) {//존재하는 파일 수만큼 반복
                if (fNames[i].isFile()) {//파일이고
                    if (fNames[i].exists()) {//있으면
                        if (oDir.exists()) {
                        }
                        File moveFile = new File(nDir, fNames[i].getName());
                        fNames[i].renameTo(moveFile);
                    }
                }
            }
        }

        String[] newFilesName = nDir.list();//이동한 파일 목록들 읽기
        System.out.println(Arrays.toString(newFilesName));
        String[] imgs = new String[3];
        if (newFilesName != null) {
            for (int i = 0; i < newFilesName.length; i++) {//파일 수 만큼 반복
                if (newFilesName[i] != null) {
                    imgs[i] = String.valueOf(newFilesName[i]);
                }
            }
        }

        for (int j = 0; j < 3; j++) {
            if (imgs[j] == null) {
                imgs[j] = "";
            }
        }

        service.addReview(new Review(reviewNo, memberNo, null, content, star, orderNo, imgs[0], imgs[1], imgs[2], restNo));
        return "/listReview";
    }

    public String getPath() {
        return path + "/addReview";
    }
}
