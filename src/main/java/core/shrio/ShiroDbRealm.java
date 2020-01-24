package core.shrio;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import mapper.UDictMapper;
import mapper.UserMapper;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import entity.User;

import javax.annotation.Resource;
import java.util.List;


public class ShiroDbRealm extends AuthorizingRealm {

	Logger logger=Logger.getLogger(ShiroDbRealm.class);

    @Resource
	UserMapper userMapper;

	@Resource
	UDictMapper uDictMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		logger.info("doGetAuthorizationInfo===============>" + principalCollection.getPrimaryPrincipal());


		return (AuthorizationInfo)principalCollection.getPrimaryPrincipal();
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		logger.info("doGetAuthendoGetAuthenticationInfoticationInfo===============>" + authenticationToken.getPrincipal());
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		Wrapper wrapper=new QueryWrapper();
		((QueryWrapper) wrapper).eq("user_name",token.getUsername());
		User tblUser=userMapper.selectOne(wrapper);

		if(null!=tblUser ){
			logger.info("udict  Select===============>" + tblUser.getEmail());
		}else{
			logger.info("tblUser"+tblUser);

		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(tblUser, tblUser.getPassword(),super.getName());
		return  info;
	}

}
