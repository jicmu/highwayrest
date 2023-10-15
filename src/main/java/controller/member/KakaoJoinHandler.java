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

public class KakaoJoinHandler implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String nickname = request.getParameter("nickname");

            MemberService memberService = new MemberService();
            Member m = memberService.getMember(id);

            if (m == null) {
                memberService.addMember(new Member(0, id, "password", name, nickname, email, "0"));
            }

            Member loginM = memberService.getMember(id);
            HttpSession session = request.getSession(true);
            session.setAttribute("loginId", id);
            session.setAttribute("nickname", loginM.getNickname());
            session.setAttribute("loginNum", loginM.getMemberNo());

            JSONObject obj = new JSONObject();
            obj.put("response", "ok");
            String txt = obj.toJSONString();
            return "responsebody/" + txt;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getPath() {
        return path + "/member/kakaoJoin";
    }
}
