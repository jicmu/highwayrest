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

public class editReview implements Handler {
    String go = "/index.jsp";

    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewService service = new ReviewService();
        int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
        Review r = service.getReviewByReviewNo(reviewNo);
        request.setAttribute("r", r);

        ArrayList<String> images = ImageFile.getImageFiles("C:\\Users\\user\\Desktop\\project\\photo\\" + r.getMemberNo() + File.separator + r.getReviewNo());
        request.setAttribute("images", images);

        request.setAttribute("view", "/review/edit.jsp");
        return go;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ReviewService service = new ReviewService();

        String oPath = "C:\\Users\\user\\Desktop\\project\\photo\\";

        MultipartRequest mr = new MultipartRequest(request, oPath, Size.MidSize.getValue(), "utf-8", new DefaultFileRenamePolicy());

        int reviewNo = Integer.parseInt(mr.getParameter("reviewNo"));
        String content = mr.getParameter("content");
        int star = Integer.parseInt(mr.getParameter("star"));
        String restNo = mr.getParameter("restNo");

        Review r = service.getReviewByReviewNo(reviewNo);

        String nPath = "C:\\Users\\user\\Desktop\\project\\photo\\" + r.getMemberNo() + File.separator + r.getReviewNo();

        File firstFile = mr.getFile("file");
        File oDir = new File(oPath);
        File nDir = new File(nPath);

        if (firstFile != null) {

            File oFiles = new File(nPath);
            FileUtils.cleanDirectory(oFiles);

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

        service.editReview(new Review(reviewNo, 0, null, content, star, 0, imgs[0], imgs[1], imgs[2], restNo));

        return "redirect/myReview";
    }

    @Override
    public String getPath() {
        return path + "/editReview";
    }
}
