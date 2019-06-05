package cn.edustar.usermgr.action;

import javax.servlet.ServletContext;

import cn.edustar.usermgr.action.base.BaseUserAction;

/**
 * 注销
 * 
 * @author Yang XinXin
 * @version 2.0.0, 2010-09-27 15:53:11
 */
public class LogoutAction extends BaseUserAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4313895777978629167L;

	/* (non-Javadoc)
	 * 
	 * @see cn.edustar.usermgr.action.base.BaseAbstractAction#execute(java.lang.String)
	 */
	@Override
	protected String execute(String cmd) throws Exception {
		destroyCookies();
		cleanSession();
		// return SUCCESS;
		if(null == redUrl) {
			redUrl = "logout.jsp";
		}
		request.setAttribute("redUrl", redUrl);
		
		ServletContext sc = request.getSession().getServletContext();
		String projects = sc.getInitParameter("project_logout");
		if (null != projects && !"".equals(projects)) {
			String[] s = projects.split(",");
			request.setAttribute("projects", s);
		}
		
		return "logout";
	}

}
