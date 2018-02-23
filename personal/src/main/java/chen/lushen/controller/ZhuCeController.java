package chen.lushen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import chen.lushen.enitiy.DataLog;
import chen.lushen.enitiy.User;
import chen.lushen.listener.LoginListener;
import chen.lushen.service.DataLogService;
import chen.lushen.service.UserService;




@Controller 
@RequestMapping("/zhuce") 
public class ZhuCeController {

	@Resource
	private UserService us;//调用增删改查方法新闻接口 服务层
   
	@Resource
	private DataLogService dls;
	
	private List list=null;
	
	private final static int success=1;
	private final static int fail=0;
	
	 HttpSession session=null;
	
	 
		/**
		 * @author 陈佳伟
		 * @version 第五版
		 * @param HttpServletRequest 接受页面请求和传来的数据
		 * @return boolean 
		 * 功能 用户注册
		 * 通过请求 获取注册信息 进行插入 
		 * */
	 
	@ResponseBody //指定controller 返回数据对象的body区
	@RequestMapping(value="/insertYH",produces="application/json;charset=UTF-8")//value 指定请求的确切地址 produces 说明数据的返回类型 及其编码格式
	public boolean insertYH(HttpServletRequest request){
		
		int pd=0;
		boolean bpd=false;
		JSONObject js=JSONObject.fromObject(request.getParameter("zhuceinfo"));
		User user=new User();
		user.setAccount(js.getString("account"));
		user.setPhonenumber(js.getString("phone"));
		user.setPassword(js.getString("password"));
		user.setUsername(js.getString("nickname"));
		user.setRealname(js.getString("realname"));
		user.setEmail(js.getString("email"));
		user.setAddress(js.getString("address"));
		user.setGender(js.getString("gender"));
		
		pd=us.insert(user);
		if(pd>0){
			bpd=true;
		}
		request.getSession().invalidate();//注册的同时 移除前一个用户信息 否则数据混乱
		return bpd;
		
	}
	
	
	/**
	 * @author 陈佳伟
	 * @version 第五版
	 * @param HttpServletRequest 接受页面请求和传来的数据
	 * @return Json对象 
	 * 功能 验证用户 若成功获取当前用户信息返回给前台
	 * 通过请求 获取用户查询关键字 获取查询内容 放入集合 
	 * */
	@ResponseBody
	@RequestMapping(value="/yanzheng",produces="application/json;charset=UTF-8")
	public JSONObject yanzheng(HttpServletRequest request){
//		HashMap<String,String> map=new HashMap<String,String>();
		 session=request.getSession();
	
		 JSONObject fhjson=new JSONObject();
		 String cw="no";
		 String cf="";
		
		 
		 JSONObject js=JSONObject.fromObject(request.getParameter("yanzheng"));
	
		 User u=us.selectByPrimaryKey(js.getString("username"));
		  String account=u.getAccount();
		
		 session.setAttribute("nickname", u.getUsername());
		 session.setAttribute("account", account);
	
		 
	   String password=js.getString("password");
	   if(u.getAccount()!=""&&u.getAccount()!=null&&u.getPassword().equals(password)){
		
		   
		   if(session.getAttribute("alllist")==null){
			   list=dls.selectAll(account);
			   session.setAttribute("alllist", list); //登录 直接获取用户所有日志信息
		   }
		   else{
			   list=(List) session.getAttribute("alllist");
			   session.setAttribute("alllist", list); 
		   }
		 
		  
		   session.setAttribute("user", u);//登录 直接获取用户的个人信息
		   request.getSession().setAttribute("loginuser", u);
		  cw="ok";
	   }
	   
	   
	  LoginListener l=new LoginListener();//用Listener监听用户是否在其他地方登录 （其他浏览器 同一个浏览器没注销 无法在同一个浏览器 进行重复登录）
	 
	  if(l.pd==1){
		  l.pd=2;
		 cf="no";
	  }else if(l.pd==3){
		 cf="yes";
	  }else {
		  cf="ok";
	  }
 
	  fhjson.put("cuowu", cw);
      fhjson.put("chongfu", cf);
	 
		return fhjson;
		
	}
	
	/**
	 * @author 陈佳伟
	 * @version 第三版
	 * @param HttpServletRequest 接受页面请求和传来的数据
	 * @return Json对象 
	 * 功能 用户页面用户名称
	 * 直接获取当前用户信息 返回
	 * */
	
	@ResponseBody
	@RequestMapping(value="/HuoQuNickname",produces="application/json;charset=UTF-8")
	public JSONObject HuoQuNickname(HttpServletRequest request){
		 JSONObject fhjson=new JSONObject();
		 
	 session=request.getSession();
		String nickname= (String) session.getAttribute("nickname");
		fhjson.put("nickname", nickname);
		
		return fhjson;
		
	}
	
	
	/**
	 * @author 陈佳伟
	 * @version 第三版
	 * @param HttpServletRequest 接受页面请求和传来的数据
	 * @return Json对象 
	 * 功能 查询用户个人注册信息
	 * 直接通过session获取当前用户信息
	 * */
	
	@ResponseBody
	@RequestMapping(value="/HuoQuInfo",produces="application/json;charset=UTF-8")
	public JSONObject HuoQuInfo(HttpServletRequest request){
		
 JSONObject fhjson=new JSONObject();
		
	 session=request.getSession();
		User yonghu=(User) session.getAttribute("user");
		fhjson.put("account", yonghu.getAccount());
		fhjson.put("phone", yonghu.getPhonenumber());
		fhjson.put("nickname", yonghu.getUsername());
		fhjson.put("realname", yonghu.getRealname());
		fhjson.put("sex", yonghu.getGender());
		fhjson.put("email", yonghu.getEmail());
		fhjson.put("adress", yonghu.getAddress());
		
		
		return fhjson;
		
	}
	
	/**
	 * @author 陈佳伟
	 * @version 第五版
	 * @param HttpServletRequest 接受页面请求和传来的数据
	 * @return Json数组 
	 * 功能 获取首页面 用户日志内容展示
	 * 通过session获取用户日志内容 并判断内容条数 进行返回
	 * */
	
	@ResponseBody
	@RequestMapping(value="/HuoQuHomeLog",produces="application/json;charset=UTF-8")
	public JSONArray HuoQuHomeLog(HttpServletRequest request){

		session=request.getSession();
		list=(List) session.getAttribute("alllist");
		List homelist=null;
		JSONArray jsay=new JSONArray();
		if(list.size()>=3){
			homelist=list.subList(0, 3);
			jsay=jsay.fromObject(homelist);
			JSONObject j=new JSONObject();
			j.put("showlog", "yes");
			jsay.add(0, j);
		}else if(list.size()==2){
			homelist=list.subList(0, 2);
			jsay=jsay.fromObject(homelist);
			JSONObject j=new JSONObject();
			j.put("showlog", "yes");
			jsay.add(0, j);
		}else if(list.size()==1){
			homelist=list;
			jsay=jsay.fromObject(homelist);
			JSONObject j=new JSONObject();
			j.put("showlog", "yes");
			jsay.add(0, j);
		}else if(list.size()==0){
		
			JSONObject j=new JSONObject();
			j.put("showlog", "no");
			jsay.add(j);
			
		}
		
	
			
		
		
		
		
		return jsay;
		
	}
	
	/**
	 * @author 陈佳伟
	 * @version 第三版
	 * @param HttpServletRequest 接受页面请求和传来的数据
	 * @return boolean 
	 * 功能 修改用户个人信息
	 * 通过请求 获取用户修改信息 调用update（）方法进行更新
	 * */
	
	@ResponseBody
	@RequestMapping(value="/notifyInfo",produces="application/json;charset=UTF-8")
	public boolean notifyInfo(HttpServletRequest request){
		
		JSONObject js=JSONObject.fromObject(request.getParameter("xiugai"));
	   User u=new User();
	   HttpSession session=request.getSession();
	    User ss=(User) session.getAttribute("user");
	   u.setAccount(js.getString("account"));
	   u.setAddress(js.getString("address"));
	   u.setEmail(js.getString("email"));
	   u.setGender(js.getString("gender"));
	   u.setPhonenumber(js.getString("phone"));
	   u.setRealname(js.getString("realname"));
	   u.setUsername(js.getString("nickname"));
	   u.setPassword(ss.getPassword());
       boolean pd=false;
         if(  us.updateByPrimaryKey(u)>1){
        	 pd=true;
         }
         session.setAttribute("user", u);
		return pd;
		
	}
	/**
	 * @author 陈佳伟
	 * @version 第一版
	 * @param HttpServletRequest 接受页面请求和传来的数据
	 * @return 无返回 
	 * 功能 注销用户
	 * session的invalidate（）方法 注销当前用户
	 * */
	@ResponseBody
	@RequestMapping(value="/loginOut",produces="application/json;charset=UTF-8")
	public void loginOut(HttpServletRequest request){
		request.getSession().invalidate();
	}
}
