package controller.member;

import common.Handler;
import data.entity.Member;
import service.member.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginHandler implements Handler {

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("view", "/member/login.jsp");
		return "/index.jsp";
	}

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String id = request.getParameter("id");
			String password  = request.getParameter("password");

			MemberService service = new MemberService();
			Member m = service.getMember(id);

		if (m != null && password.equals(m.getPassword())) {
			HttpSession session = request.getSession();
			session.setAttribute("loginId", id);
			session.setAttribute("loginNum", m.getMemberNo());
			session.setAttribute("nickname", m.getNickname());
			return "redirect/index.jsp";
		}

		request.setAttribute("msg", "아이디 또는 비밀번호를 확인해주세요.");
		request.setAttribute("view", "/member/login.jsp");
		return "/index.jsp";
	}

	@Override
	public String getPath() {
		return  path + "/member/login";
	}
}
