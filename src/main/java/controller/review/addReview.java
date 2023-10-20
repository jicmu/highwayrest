package controller.review;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import common.Handler;
import common.Size;
import data.entity.Review;
import orders.Order;
import orders.OrderService;
import service.review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class addReview implements Handler {
    String go = "/index.jsp";

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int orderNo = Integer.parseInt(request.getParameter("orderNo"));
        OrderService oservice = new OrderService();
        Order o = oservice.findByOrderNo(orderNo);
        String restNo = o.getRestNo();

        request.setAttribute("view", "/review/add.jsp");
        request.setAttribute("orderNo", orderNo);
        request.setAttribute("restNo", restNo);

        return go;
    }


    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "C:\\Users\\user\\Desktop\\project\\photo\\";
        String osName = System.getProperty("os.name");
        if (osName.contains("win")) {
            path = "C:\\Users\\user\\Desktop\\project\\photo";
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

        String nPath = "C:\\Users\\user\\Desktop\\project\\photo\\" + memberNo + File.separator  + reviewNo;
        String osName1 = System.getProperty("os.name");
        if (osName1.contains("win")) {
            nPath = "C:\\Users\\user\\Desktop\\project\\photo\\" + memberNo + File.separator + reviewNo;
        } else if (osName1.contains("mac")) {
            nPath = "";
        } else if (osName1.contains("nix") || osName1.contains("nux")) {
            nPath = "";
        }

        File dir = new File(nPath);

        File firstFile = mr.getFile("file");
        if (firstFile != null) {
            if (!dir.exists()) {
                dir.mkdirs();
                System.out.println("폴더를 생성합니다.");
            } else {
                System.out.println("폴더가 이미 존재하는 관계로 생성하지 않습니다.");
            }
        } else {
            System.out.println("업로드한 파일이 없습니다.");
        }

        File oDir = new File(path);
        File nDir = new File(nPath);
        if (oDir.exists()) {
            File[] fNames = oDir.listFiles();
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

        String[] newFilesName = nDir.list();
        String[] imgs = new String[3];
        if (newFilesName != null) {
            for (int i = 0; i < newFilesName.length; i++) {
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
        return "redirect/myReview";
    }

    public String getPath() {
        return path + "/addReview";
    }
}