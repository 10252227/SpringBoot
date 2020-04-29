package com.yhz.sbd.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
public class UrlInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// TODO
		if (modelAndView == null || modelAndView.getViewName().startsWith("redirect")) {
			return;
		}

		String uri = request.getServletPath();
		System.err.println("==============="+uri);
		String template = (String) modelAndView.getModelMap().get("template");
		if (StringUtils.isBlank(template)) {
			if (uri.startsWith("/")) {
				uri = uri.substring(1);
			}
			modelAndView.getModelMap().put("template", uri);
		}

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}



}
