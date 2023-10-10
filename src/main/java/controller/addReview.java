package controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import common.Handler;
import review.Review;
import review.ReviewService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class addReview implements Handler {
    String go = "/review/add.jsp";

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //int orderNo = Integer.parseInt(request.getParameter("orderNo"));

        request.setAttribute("go", "/review/add.jsp");

        return go;
    }


    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO 사진 업로드 할 주소와 이미지 크기
        String path = "C:\\Users\\RYU\\Desktop\\jsp&servlet\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\webapps\\shopprod\\";
        int size = 100 * 1024 * 1024;

        MultipartRequest req = new MultipartRequest(request, path, size, "utf-8",
                new DefaultFileRenamePolicy());

                File[] f = { req.getFile("img1"), req.getFile("img2"), req.getFile("img3"), req.getFile("img4") };
                String[] imgs = new String[4];
                for (int i = 0; i < f.length; i++) {
                    if (f[i] != null && f[i].length() != 0) {
                        imgs[i] = f[i].getName();
                    } else {
                        imgs[i] = "";
                    }
                }
                for(String s: imgs) {
                    System.out.println(s);
                }

        int orderNo = Integer.parseInt(request.getParameter("orderNo"));
        int star = Integer.parseInt(req.getParameter("star"));
        String content = req.getParameter("content");

        ReviewService service = new ReviewService();
        //TODO 차후에 VO에서 img 추가하고 생성자 새로 생성해서 가져올 것
        service.addReview(new Review(0, star, null, content));
        go = "redirect:/index.jsp";
        return go;
    }

    public String getPath() {
        return "/addReview";
    }
}
