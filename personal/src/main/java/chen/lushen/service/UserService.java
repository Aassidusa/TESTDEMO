package chen.lushen.service;

import java.util.List;

import chen.lushen.enitiy.User;



public interface UserService {
	
	 int insert(User record);
	 User selectByPrimaryKey(String account);
	 int updateByPrimaryKey(User record);
}
