package controller.member;

import common.Handler;
import data.entity.Member;
import member.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginHandler implements Handler {

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/member/login.jsp";
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
			session.setAttribute("nickname", m.getNickname());
			return "redirect/memberindex.jsp";
		}

		return "/member/login.jsp";
	}

	@Override
	public String getPath() {
		return  path + "/member/login";
	}
}
