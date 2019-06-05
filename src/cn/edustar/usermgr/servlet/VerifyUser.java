package cn.edustar.usermgr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.edustar.usermgr.pojos.User;
import cn.edustar.usermgr.service.UserService;
import cn.edustar.usermgr.util.CommonUtil;

public class VerifyUser extends HttpServlet {

	/**
	 * 本程序用来验证用户：给定用户名和密码，返回用户是否存在
	 */
	private static final long serialVersionUID = -2134182849207179768L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out =  response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		WebApplicationContext app_ctxt = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		UserService userService = (UserService)app_ctxt.getBean("userService");
		if(userService == null) 
		{
			out.print("UserService 无法加载");
			out.close();
			return;
		}
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		if(userName == null || password == null || userName.equals("") || password.equals(""))
		{
			out.print("用户名或密码为空");
			out.close();
			return;
		}
		User u = userService.getUserByQueryString(userName);
		if(u == null)
		{
			out.print("用户不存在");
			out.close();
			return;
		}
		if(u.getStatus() != 0)
		{
			out.print("用户状态不正常");
			out.close();
			return;
		}
		if(!CommonUtil.equalPassword(u.getPassword(), password))
		{
			out.print("密码不匹配");
			out.close();
			return;
		}
		out.print("OK");
		out.close();
		return;
	}
}
