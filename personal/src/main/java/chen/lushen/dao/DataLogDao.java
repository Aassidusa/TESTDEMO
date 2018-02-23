package chen.lushen.dao;

import java.util.List;

import chen.lushen.enitiy.DataLog;


/**
 * @author 陈佳伟
 * @version 第1版
 * 日志
 * Dao层接口与Mapping对应 增删改查
 * */



public interface DataLogDao {
    int deleteByPrimaryKey(String title);

    int insert(DataLog record);

    int insertSelective(DataLog record);

    DataLog selectByPrimaryKey(String title);

    List<DataLog> selectMohu(DataLog record);
    List<DataLog> selectAll(String account);
    int updateByPrimaryKeySelective(DataLog record);

    int updateByPrimaryKey(DataLog record);
}