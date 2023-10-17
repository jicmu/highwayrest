package controller.member;

import common.Handler;
import data.entity.Member;
import service.member.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FindPwdHandler implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/member/findPwd.jsp");
        return "/index.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String tel = request.getParameter("tel");

        MemberService memberService = new MemberService();
        Member m1 = memberService.getMember(id);
        Member m2 = memberService.getMemberByTel(tel);
        System.out.println("m1:" + m1);
        System.out.println("tel:" + tel);
        System.out.println("m2:" + m2);
        System.out.println(m1.getMemberNo() == m2.getMemberNo());

        if ( (m1 != null && m2 != null) && m1.getMemberNo() == m2.getMemberNo()) {
            request.setAttribute("id", id);
            request.setAttribute("view", "/member/resetPwd.jsp");
            return "/index.jsp";
        } else if (m1 == null && m2 == null) {
            request.setAttribute("msg", "존재하는 회원이 없습니다.");
        } else {
            request.setAttribute("msg", "정보가 일치하지 않습니다.");
        }

        request.setAttribute("view", "/member/findPwd.jsp");
        return "/index.jsp";
    }

    @Override
    public String getPath() {
        return path + "/member/findPwd";
    }
}
