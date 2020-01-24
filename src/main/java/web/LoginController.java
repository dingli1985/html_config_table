package web;

import com.xhp.hrms.core.shrio.ShiroDbRealm;
import com.xhp.hrms.exception.LoginException;
import com.xhp.hrms.mapper.UDictMapper;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class LoginController{

	Logger logger=Logger.getLogger(LoginController.class);


	@Resource
	UDictMapper uDictMapper;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	/**
	 * 初始化 登录页面
	 * @return
	 */
	public String toLogin() {
		return "login";

	}
	

	/**
	 * 执行登录操作
	 * @param userName 用户名
	 * @param passWord 密码
	 * @return
	 */
	@RequestMapping(value = "/login/do", method = RequestMethod.POST)
	public String doLogin(String userName,String passWord) throws Exception{
		UsernamePasswordToken token=new UsernamePasswordToken();
		token.setUsername(userName);
		token.setPassword(passWord.toCharArray());

		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}



		if(currentUser.isAuthenticated()){
			logger.info("currentUser======>"+currentUser.getPrincipal());
			return "/index";
		}else{
			logger.info("isAuthencate=========>"+currentUser.isAuthenticated());
			logger.info("UserName Or Password is Wrong");
			return "/login";
		}
	}
}
