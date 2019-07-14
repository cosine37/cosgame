package com.cosine.cosgame.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Service
public class LoginInterceptor extends HandlerInterceptorAdapter{
	 protected static final Log logger = LogFactory.getLog(LoginInterceptor.class);
	 
	 @Override
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException{
		 logger.info("------preHandle------");
		 HttpSession session = request.getSession(true);
		 logger.info(session.getAttribute("username"));
		 if (session.getAttribute("username") == null) {
			 response.sendRedirect("/login");
			 return false;
		 } else {
			 return true;
		 }		
	 }
	 
	 @Override
	 public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	            ModelAndView modelAndView) throws Exception{
		 logger.info("------postHandle------");
	 }
	 
	 @Override
	 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
		 logger.info("------afterCompletion------");
	 }
}
