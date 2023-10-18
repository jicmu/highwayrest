package controller.member;

import common.Handler;
import data.entity.Member;
import service.member.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FindIdHandler implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("view", "/member/findId.jsp");
        return "/index.jsp";
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tel = request.getParameter("tel");
        System.out.println("tel:" + tel);
        MemberService memberService = new MemberService();
        Member m = memberService.getMemberByTel(tel);
        System.out.println("m:" + m);

        if (m != null) {
            request.setAttribute("foundId", m.getId());
            request.setAttribute("view", "/member/foundId.jsp");
            return "/index.jsp";
        }

        request.setAttribute("msg", "존재하는 회원이 없습니다.");
        request.setAttribute("view", "/member/findId.jsp");
        return "/index.jsp";
    }

    @Override
    public String getPath() {
        return path + "/member/findId";
    }
}
