package cn.edustar.usermgr.action;

import javax.servlet.ServletContext;

import cn.edustar.usermgr.action.base.BaseUserAction;

/**
 * 登录
 * 
 * @author Yang XinXin
 * @version 2.0.0, 2010-09-01 09:39:36
 */
public class LoginAction extends BaseUserAction {
	private static final long serialVersionUID = 2939658770748232529L;

	@Override
	protected String execute(String cmd) throws Exception {
		if ("true".equals(configService.getConfigByKey("isShowVerifyCode").getValue())) {
			if (!isValidVerifyCode(sessionVerifyCode, vercode)) {
				return INPUT;
			}
		}
		if (null == this.getUserByUsernameOrEmail(username)) {
			return INPUT;
		}
		if ("input".equals(verifyUserStatus(user.getStatus()))) {
			return INPUT;
		}
		if (!equalPassword(user.getPassword(), password)) {
			return INPUT;
		}
		updateUserLoginInfo();
		writeCookieToClient();
		writeSessionInServer();

//		if (null != p && !"".equals(p) && 0 < p.length()) {
//			return "Product";
//		} else if (null != p2 && !"".equals(p2) && 0 < p2.length()) {
//			return "Public";
//		} else {
//			return SUCCESS;
//		}
		
		if (null == p) {
			p = "";
		}
		
		if (null == p2) {
			p2 = "";
		}
		
		if (null == redUrl) {
			redUrl = "";
		}
		
		request.setAttribute("userTicket", userTicket);
		request.setAttribute("redUrl", redUrl);
		request.setAttribute("p2", p2);
		request.setAttribute("p", p);
		
		ServletContext sc = request.getSession().getServletContext();
		String projects = sc.getInitParameter("project_login");
		if (null != projects && !"".equals(projects)) {
			String[] s = projects.split(",");
			request.setAttribute("projects", s);
		}
		return "loginok";
	}

}
