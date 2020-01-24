package core.shrio;

import entity.User;

public interface IShiro {
	
	/**
	 * 获取用户行
	 * @return
	 */
	public User getUser(String userName);

}
