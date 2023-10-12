package controller.member;

import common.Handler;
import data.entity.Member;
import member.MemberService;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JoinHandler implements Handler {

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/member/join.jsp";
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

		return "redirect/member/login.jsp";
	}

	@Override
	public String getPath() {
		return path + "/member/join";
	}
}
