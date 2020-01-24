package core.shrio;

import com.xhp.hrms.entity.User;


public class ShiroFactory implements IShiro {

	public User getUser(String userName) {
		User user=new User();
		user.setUserName("dingli");
		user.setAge(15);
		return user;
	}

}
