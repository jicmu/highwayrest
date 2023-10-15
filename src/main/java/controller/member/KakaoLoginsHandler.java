package controller.member;

import common.Handler;
import data.entity.Member;
import org.json.simple.JSONObject;
import service.member.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class KakaoLoginsHandler implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=UTF-8");

            String id = request.getParameter("id");

            MemberService memberService = new MemberService();
            Member m = memberService.getMember(id);
            String nickname = m.getNickname();

            HttpSession session = request.getSession(true);
            session.setAttribute("loginId", id);
            session.setAttribute("nickname", nickname);
            session.setAttribute("loginNum", m.getMemberNo());

            JSONObject obj = new JSONObject();
            obj.put("nickname", nickname);
            String txt = obj.toJSONString();
            return "responsebody/" + txt;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getPath() {
        return path + "/member/kakaoLogins";
    }
}
