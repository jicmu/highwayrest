package controller.master;

import common.Handler;
import common.ImageFile;
import common.Size;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Upload implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/master/example.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImageFile.fileUpload("/Users/jeonggukyoo/example", Size.SmallSize, request);

        return "/index.jsp";
    }

    @Override
    public String getPath() {
        return path + "/master/upload";
    }
}
