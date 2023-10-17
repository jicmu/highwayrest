package controller.member;

import common.Handler;
import service.member.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendSMS implements Handler {
    @Override
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tel = request.getParameter("tel");
        int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000); // 난수 생성

        MemberService memberService = new MemberService();
//        memberService.certifiedTel(tel, randomNumber);

        return "responsebody/" + randomNumber;
    }

    @Override
    public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }

    @Override
    public String getPath() {
        return path + "/member/sendSMS";
    }
}
