package cn.edustar.usermgr.dao;

import cn.edustar.usermgr.pojos.User;
import cn.edustar.usermgr.pojos.UserToken;

/**
 * 用户DAO
 * 
 * @author Yang XinXin
 * @version 2.0.0, 2010-09-01 11:03:02
 */
public interface UserDao {
	
	public void saveOrUpdate(User user);

	public User getUserByQueryString(String queryString, String value);
	
	public void updatePasswordByUsername(String username, String password);
	
	public void updateStatusByUsername(String username, int status);
	
	public void resetQuestionAndAnswerByUsername(String username, String question, String answer);
	
	public void updateUserInfoByUsername(String username, String trueName, String email, int role);
	
	public void deleteUser(User user);
	
	public int getUserCounts();
	
	public void saveOrUpdateUserToken(UserToken userToken);
	public UserToken getUserTokenByLoginName(String loginName);
	public UserToken getUserTokenByToken(String token);
	public void deleteUserToken(UserToken userToken);
	public void deleteUserTokenByLoginName(String loginName);
	public void deleteUserTokenByToken(String token);
	public void deleteUnValidToken();
}
