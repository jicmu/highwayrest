package controller.member;

import common.Handler;
import data.entity.Member;
import service.member.MemberService;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class IdCheckHandler implements Handler {

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("id");
		Member m = (new MemberService()).getMember(id);
		boolean flag = false;
		String msg = "";

		if (id == "") {
			msg = "아이디를 입력해주세요.";
		} else if (id.length() < 4 || id.length() > 10) {
			msg = "4자 이상, 10자 이하로 작성해주세요.";
		} else if (!Pattern.matches("^[a-z0-9]+$", id)) {
			msg = "영어 소문자와 숫자로 구성해주세요.";
		} else if (m == null) {
			flag = true;
			msg = "사용 가능한 아이디입니다.";
		} else {
			msg = "다른 아이디를 입력해주세요.";
		}

		JSONObject obj = new JSONObject();
		obj.put("flag", flag);
		obj.put("msg", msg);
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
