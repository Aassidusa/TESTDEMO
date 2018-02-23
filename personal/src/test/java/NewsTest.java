

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import chen.lushen.enitiy.User;
import chen.lushen.service.UserService;



/**
 * @author 陈佳伟
 * @version 第6版
 * junit测试 
 * 测试能否连接到数据库
 * */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-mybatis.xml"})

public class NewsTest {
 
	private static Logger logger=Logger.getLogger(NewsTest.class);
	
	@Resource
	private UserService ne;
	
	@Test
	public void testNews() {
		User u=new User();
		u.setAccount("a");
		u.setAddress("a");
		u.setEmail("a");
		u.setGender("a");
		u.setPassword("a");
		u.setPhonenumber("a");
		u.setRealname("陈佳伟");
		u.setUsername("a");
		ne.insert(u);
			}
//	@Test
//	public void testNews1(){
////		News news=new News();
////		news.setNewsid(5);
////		news.setColumnid(3);
////		news.setNewslabel("社会");
////		news.setNewshits(23);
////		news.setNewsperson("陈伟");
////		news.setContent("绿色发展");
////		news.setCreatedate(new Date());
////
////		//news.setContent("test.html");
////		System.out.println("取值->"+news.getContent());
////		int a=newsservice.insert(news);
////		System.out.println("添加->"+a);
//		//修改
////		News news=new News();
////		news.setNewsid(5);
////		news.setColumnid(3);
////		news.setNewslabel("社会");
////		news.setNewshits(23);
////		news.setNewsperson("陈伟");
////		news.setContent("绿色发展");
////		news.setCreatedate(new Date());
////
////		//news.setContent("test.html");
////		System.out.println("取值->"+news.getContent());
////		int a=newsservice.updateByPrimaryKey(news);
////		System.out.println("添加->"+a);
//		//模糊
////		News news=new News();
////		List <News> list=newsservice.selectMohu("理"+","+"发");
////		Iterator<News> it=list.iterator();
////		while(it.hasNext()){
////			System.out.println(it.next());
////			System.out.println("\t");
//		}
		
		

	}

