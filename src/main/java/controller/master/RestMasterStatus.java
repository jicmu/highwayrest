package controller.master;

import common.Handler;
import service.master.RestMasterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RestMasterStatus implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String status = request.getParameter("status");

        System.out.println("id: " + id + " / status: " + status);

        RestMasterService service = new RestMasterService();
        service.changeStatus(Integer.parseInt(status), id);

        HttpSession session = request.getSession(false);
        session.removeAttribute(id);

        return "/master/login.jsp";
    }

    @Override
    public String getPath() {
        return path + "/restmaster/status";
    }
}
