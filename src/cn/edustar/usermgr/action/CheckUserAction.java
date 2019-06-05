package cn.edustar.usermgr.action;

import java.io.IOException;

import cn.edustar.usermgr.action.base.BaseAction;
import cn.edustar.usermgr.pojos.UserToken;
import cn.edustar.usermgr.service.UserService;


public class CheckUserAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	
	public String execute() throws IOException{
		
		String token = request.getParameter("token");
		
		response.setContentType("application/json;charset=UTF-8");
		if(token==null || token.length() == 1)
		{
			response.getWriter().write("{\"status\":\"error\",\"description\":\"缺少 token 参数。\"}");
			return NONE;
		}
		
		//不验证超时时间了
		UserToken loginUserToken = userService.getUserTokenByToken(token);
		if(loginUserToken == null)
		{
			response.getWriter().write("{\"status\":\"error\",\"description\":\"没有记录。\"}");
			return NONE;
		}
		
		response.getWriter().write("{\"status\":\"success\",\"LoginUser\":{\"LoginName\":\"" + loginUserToken.getLoginName() + "\",\"UserGuid\":\"" + loginUserToken.getUserGuid() + "\"}}");
				
		return NONE;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	
}
