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

public class KakaoLoginHandler implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");

            MemberService memberService = new MemberService();
            Member m = memberService.getMember(id);
            JSONObject obj = new JSONObject();

            if (m == null) {
                obj.put("dbcheck", "null");
            }
            String txt = obj.toJSONString();
            return "responsebody/" + txt;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getPath() {
        return path + "/member/kakaoLogin";
    }
}
