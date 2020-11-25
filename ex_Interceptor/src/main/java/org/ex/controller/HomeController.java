package org.ex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	//테스트용 관리자 아이디와 비밀번호
	public static final String ADMIN_ID = "admin";
	public static final String ADMIN_PW = "1234";

	@RequestMapping("/login")
	public void login() {
	}

	@RequestMapping("/main/main0100")
	public void main0100() {
	}

	@RequestMapping("/main/main0101")
	public void main0101() {
	}

	//로그인 후 호출되는 주소
	@RequestMapping(value = "/loginOk", method = RequestMethod.POST)
	public String loginOk(String id, String pw, HttpSession session) {
		
		//핸들링 후 실행될 주소
		String returnURL = "";

		if (session.getAttribute("id") != null) {
			session.removeAttribute("id");
		}
		
		if(ADMIN_ID.equals(id) && ADMIN_PW.equals(pw)) {
			session.setAttribute("id", id);
			//직전 호출 주소
			String priorUrl = (String) session.getAttribute("url_prior_login");
			
			if(priorUrl != null) { //main0100외 다른 직전 호출 주소가 있으면 해당 주소로 이동하고 url_prior_login 세션값은 삭제
				returnURL= "redirect:"+priorUrl;
				session.removeAttribute("url_prior_login");
			}
			
			returnURL = "redirect:/main/main0100";
		}else {
			returnURL = "loginFail";
		}
		
		return returnURL;

	}
	
	@RequestMapping("/logout")
	public void logout(HttpSession session) {
		session.removeAttribute("id");
	}
}
