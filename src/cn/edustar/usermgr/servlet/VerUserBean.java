package cn.edustar.usermgr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edustar.usermgr.pojos.Ticket;
import cn.edustar.usermgr.pojos.User;
import cn.edustar.usermgr.service.UserService;
import cn.edustar.usermgr.util.CommonUtil;

/**
 * @author Yang XinXin
 * @version 3.0.0, 2011-01-05 17:57:31
 */
public class VerUserBean extends ServletBeanBase {
	private static final int INT_SESSION_ABATE_TIME_NEGATIVE = -1;
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String ticket = request.getParameter("UserTicket");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		User user = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String saveticket = request.getParameter("saveticket");
		if (null != username && null != password && !"".equals(username)
				&& !"".equals(password) && 0 < username.length()
				&& 0 < password.length()) {
			user = userService.getUserByQueryString(username);
			if (null == user) {
				out.println("");
				return;
			}
			if (CommonUtil.equalPassword(user.getPassword(), password)) {
				Ticket t = userService.createUserTicket(username);
				if ("1".equals(saveticket)) {
					Cookie cookie = new Cookie("UserTicket", t.getTicket());
					cookie.setMaxAge(INT_SESSION_ABATE_TIME_NEGATIVE);
					String domain = getDomain(request.getServerName());
					if (!"".equals(domain)
							&& !Character.isDigit(domain.charAt(0))) {
						cookie.setDomain("." + domain);
					}
					cookie.setPath("/");
				}
				System.out.println("返回的票证是：" + t.getTicket());
				out.print(t.getTicket());
				return;
			} else {
				out.println("");
				return;
			}
		}
		user = userService.getUserByUserTicket(ticket);
		if (null != user) {
			System.out.println("验证成功！");
			out.println(user.getId() + "," + user.getUsername() + ","
					+ user.getPassword() + "," + user.getGuid() + ","
					+ URLEncoder.encode(user.getTrueName(), "UTF-8") + ","
					+ user.getRole() + "," + user.getUsn());
			System.out.println("统一用户输出的信息：" + user.getId() + ","
					+ user.getUsername() + "," + user.getPassword() + ","
					+ user.getGuid() + ","
					+ URLEncoder.encode(user.getTrueName(), "UTF-8") + ","
					+ user.getRole() + "," + user.getUsn());
		} else {
			out.println("<script>alert('error');</script>");
			System.out.println("验证出错！");
		}
		out.flush();
		out.close();
	}

	private String getDomain(String url) {
		String returnDomain = "";
		Boolean isIP = true;
		if (url.contains(".")) {
			String[] tempArray = url.split("\\.");
			for (int i = 0; i < tempArray.length; i++) {
				try {
					Integer.parseInt(tempArray[i]);
				} catch (NumberFormatException e) {
					isIP = false;
					break;
				}
			}
			if (isIP) {
				returnDomain = "";
			} else {
				switch (tempArray.length) {
				case 2:
					returnDomain = url;
					break;
				default:
					returnDomain = url.substring(url.indexOf(".") + 1);
					break;
				}
			}
		} else {
			returnDomain = "";
		}
		return returnDomain;
	}

}
