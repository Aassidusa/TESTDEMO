package chen.lushen.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import chen.lushen.enitiy.User;

/**
 * @author 陈佳伟
 * @version 第五版
 * 监听用户状态
 * 
 * */
public class LoginListener implements HttpSessionAttributeListener{
   private Map<String, HttpSession> map=new HashMap<String,HttpSession>();
    public static int pd=0;
   public LoginListener(){}
   
   public LoginListener(int a){
	   
   }
   
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name=event.getName();
	
		HttpSession session=null;
		if(name.equals("loginuser")){
		
			User user=(User) event.getValue();
			
			pd=1;
		
			if(map.get(user.getAccount())!=null){
				
				 session=map.get(user.getAccount());
			
				session.removeAttribute(user.getAccount());
				
				
				session.invalidate();
			}
		
			map.put(user.getAccount(), event.getSession());
		}
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name=event.getName();
		
		if(name.equals("loginuser")){
		
			User user=(User) event.getValue();
			pd=3;
			map.remove(user.getAccount());
		}
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public Map<String,HttpSession> getMap(){
		return map;
	}

	public void setMap(Map<String,HttpSession> map){
		this.map=map;
	}
	
}
