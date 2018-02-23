import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import chen.lushen.enitiy.DataLog;
import chen.lushen.service.DataLogService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-mybatis.xml"})
public class LogTest {

	private static Logger logger=Logger.getLogger(LogTest.class);
	@Resource
	DataLogService ser;

	 @Test
	 public void test(){
		 DataLog d=new DataLog();
		 d.setAccount("Aassidusa");
		 d.setTitle("习近平");
		 List list=ser.selectAll("Aassidusa");
		  System.out.println(list.size());
		  for(int i=0;i<list.size();i++){
			 DataLog ds= (DataLog) list.get(i);
			 System.out.println(ds.getTitle());
		  }
		 System.out.println(list.toString());
	 }
}
