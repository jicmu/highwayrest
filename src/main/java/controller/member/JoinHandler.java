package controller.member;

import common.Handler;
import data.entity.Member;
import service.member.MemberService;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JoinHandler implements Handler {

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("view", "/member/join.jsp");
		return "/index.jsp";
	}

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		MemberService service = new MemberService();
		service.addMember(new Member(0, id, password, name, nickname, email, tel));

		JSONObject obj = new JSONObject();
		obj.put("id", id);

		request.setAttribute("view", "/member/login.jsp");
		return "redirect/index.jsp";
	}

	@Override
	public String getPath() {
		return path + "/member/join";
	}
}
