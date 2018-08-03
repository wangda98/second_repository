package com.cyberwise.workanalysis.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class roleFilter
 */
@WebFilter(filterName = "doFilter", urlPatterns = "/*")
public class roleFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String uri = req.getRequestURI();// 获取登陆地址
		uri = uri.replaceAll(req.getContextPath(), "");
		if (uri.endsWith("/") ||uri.endsWith("getImageCode") || uri.endsWith("login.html") || uri.endsWith("css") || uri.endsWith("js")
				|| uri.endsWith("cyberLogin") || uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".gif")) {
			chain.doFilter(request, resp);// 放行
			return;
		}
		if (session.getAttribute("account") != null && session.getAttribute("password") != null) {// 注意：登录页面千万不能过滤
			chain.doFilter(request, response);
			return;
		} else {// 如果不是login.jsp进行过滤
			resp.sendRedirect("login.html");
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {

	}
}
