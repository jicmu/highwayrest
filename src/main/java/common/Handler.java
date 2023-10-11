package common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Handler {

    String path = "/highwayrest";
    String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    String getPath();
}
