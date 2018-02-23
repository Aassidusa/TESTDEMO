package chen.lushen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chen.lushen.dao.UserDao;
import chen.lushen.enitiy.User;
import chen.lushen.service.UserService;







/**
 * @author 陈佳伟
 * @version 第二版
 * 服务层的具体实现
 * 
 * */


@Qualifier("UserService")
@Transactional
@Service("UserService")
public class UserServiceImpl implements UserService{
 
	@Resource
	private UserDao userDao;

	@Override
	public int insert(User record) {
		// TODO Auto-generated method stub
		return userDao.insert(record);
	}

	@Override
	public User selectByPrimaryKey(String account) {
		// TODO Auto-generated method stub
		return userDao.selectByPrimaryKey(account);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return userDao.updateByPrimaryKey(record);
	}


	
	
	

	

}
