package controller.member;

import common.Handler;
import data.entity.Member;
import member.MemberService;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IdCheckHandler implements Handler {

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Member m = (new MemberService()).getMember(id);
		boolean flag = false;

		if (m == null) {
			flag = true;
		}
		JSONObject obj = new JSONObject();
		obj.put("flag", flag);
		String txt = obj.toJSONString();
		return "responsebody/" + txt;
	}

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return null;
	}

	@Override
	public String getPath() {
		return path + "/member/idcheck";
	}
}
