package controller.review;

import common.Handler;
import common.ImageFile;
import common.Size;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class addReview implements Handler {
    String go = "/index.jsp";

    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //int orderNo = Integer.parseInt(request.getParameter("orderNo"));

        request.setAttribute("go", "/review/add.jsp");

        return go;
    }


    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String path = "C:\Users\RYU\Desktop\project\photo\";
//        int size = 100 * 1024 * 1024;
//
//        MultipartRequest req = new MultipartRequest(request, path, size, "utf-8",
//                new DefaultFileRenamePolicy());
//
//                File[] f = { req.getFile("File1"), req.getFile("File2"), req.getFile("File3"), req.getFile("File4") };
//                String[] imgs = new String[4];
//                for (int i = 0; i < f.length; i++) {
//                    if (f[i] != null && f[i].length() != 0) {
//                        imgs[i] = f[i].getName();
//                    } else {
//                        imgs[i] = "";
//                    }
//                }
//                for(String s: imgs) {
//                    System.out.println(s);
//                }
//
//        int orderNo = Integer.parseInt(request.getParameter("orderNo"));
//        int star = Integer.parseInt(req.getParameter("star"));
//        String content = req.getParameter("content");
//
//        ReviewService service = new ReviewService();
//        //TODO 차후에 VO에서 File 추가하고 생성자 새로 생성해서 가져올 것
//        service.addReview(new Review(0, star, null, content));
//        go = "redirect:/index.jsp";

        String path = "C:\\Users\\RYU\\Desktop\\project\\photo\\";

        ImageFile.fileUpload(path, Size.BigSize, request);

        return go;
    }

    public String getPath() {
        return path + "/addReview";
    }
}
