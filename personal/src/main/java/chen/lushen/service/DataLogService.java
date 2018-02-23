package chen.lushen.service;

import java.util.List;

import chen.lushen.enitiy.DataLog;


public interface DataLogService {
	int insert(DataLog record);
	List<DataLog> selectMohu(DataLog record);
    List<DataLog> selectAll(String account);
    int update(DataLog record);
    int delete(String title);
}
