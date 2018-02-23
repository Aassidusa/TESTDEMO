package chen.lushen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chen.lushen.dao.DataLogDao;
import chen.lushen.dao.UserDao;
import chen.lushen.enitiy.DataLog;
import chen.lushen.service.DataLogService;


/**
 * @author 陈佳伟
 * @version 第二版
 * 服务层的具体实现
 * 
 * */

@Qualifier("DataLogService") //确定是当前服务接口 
@Transactional //事务 一旦出错 不会对数据造成影响
@Service("DataLogService") //进行注解
public class DataLogImpl implements DataLogService{

	@Resource
	private DataLogDao dataLogDao;


	@Override
	public int insert(DataLog record) {
		// TODO Auto-generated method stub
		return dataLogDao.insert(record);
	}

	@Override
	public List<DataLog> selectMohu(DataLog record) {
		// TODO Auto-generated method stub
		return dataLogDao.selectMohu(record);
	}

	@Override
	public List<DataLog> selectAll(String account) {
		// TODO Auto-generated method stub
		return dataLogDao.selectAll(account);
	}

	@Override
	public int delete(String title) {
		// TODO Auto-generated method stub
		return dataLogDao.deleteByPrimaryKey(title);
	}

	@Override
	public int update(DataLog record) {
		// TODO Auto-generated method stub
		return dataLogDao.updateByPrimaryKey(record);
	}

}
