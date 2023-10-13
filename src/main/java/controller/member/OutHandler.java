package controller.member;

import common.Handler;
import service.member.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OutHandler implements Handler {

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		session.invalidate();

		return "/index.jsp";
	}

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		MemberService service = new MemberService();
		service.delMember(id);

		HttpSession session = request.getSession(false);
		session.invalidate();

		return "/index.jsp";
	}

	@Override
	public String getPath() {
		return path + "/member/out";
	}
}