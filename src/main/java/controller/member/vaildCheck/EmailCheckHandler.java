package controller.member.vaildCheck;

import common.Handler;
import data.entity.Member;
import org.json.simple.JSONObject;
import service.member.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmailCheckHandler implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String email = request.getParameter("email").trim();
        Member m = (new MemberService()).getMemberByEmail(email);
        boolean flag = false;

        if (m == null) {
            flag = true;
        }

        JSONObject obj = new JSONObject();
        obj.put("flag", flag);
        String txt = obj.toJSONString();
        return "responsebody/" + txt;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return path + "/member/emailcheck";
    }
}
