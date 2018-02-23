package chen.lushen.dao;

import chen.lushen.enitiy.User;

/**
 * @author 陈佳伟
 * @version 第1版
 * 用户信息
 * Dao层接口与Mapping对应 增删改查
 * */


public interface UserDao {
    int deleteByPrimaryKey(String account);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String account);
    

    
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}