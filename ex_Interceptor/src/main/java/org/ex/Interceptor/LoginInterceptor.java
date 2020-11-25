package org.ex.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	//컨트롤러로 request 가 들어가기 전에 수행 return => true :  컨트롤러 uri 로 감 false : 컨트롤러로 요청안감
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("[preHandle]");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if(id == null) {
			//직전 요청 url을 세션에 기록
			String urlPrior = request.getRequestURL().toString()+"?"+request.getQueryString();
			request.getSession().setAttribute("url_prior_login", urlPrior);
			
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		return true;
	}

	//컨트롤러 실행후 아직 뷰 실행전
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// TODO Auto-generated method stub
		System.out.println("[postHandle]");
		super.postHandle(request, response, handler, modelAndView);
	}
	//화면을 response 끝난 뒤에
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("[afterCompletion]");
		super.afterCompletion(request, response, handler, ex);
	}

	//비동기 요청시 postHandle와 afterCompletion 은 실행되지 않고, 이 메소드가 실행됩니다.
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("[afterConcurrent]");
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
