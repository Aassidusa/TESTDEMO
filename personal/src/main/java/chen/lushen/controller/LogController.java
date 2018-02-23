package chen.lushen.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import chen.lushen.enitiy.DataLog;
import chen.lushen.service.DataLogService;

/**
 * @author 陈佳伟
 * @version 第五版
 * 逻辑处理
 * 
 * */

@Controller 
@RequestMapping("/write") 
public class LogController {

	@Resource
	private DataLogService dls;
	private HttpSession session=null;
    private static int mhpd=0;// 用于判断用户是否请求查询功能
	private List list=null;

	
	/**
	 * @author 陈佳伟
	 * @version 第三版
	 * @param HttpServletRequest 接受页面请求和传来的数据
	 * @return boolean 
	 * 功能 插入新日志
	 * 用于日志的插入 通过session 获取当前用户的集合 并添加数据 重新展示出来 只需调用insert（）不用查询全表
	 * */
	
	@ResponseBody //指定controller 返回数据对象的body区
	@RequestMapping(value="/insertData",produces="application/json;charset=UTF-8")//value 指定请求的确切地址 produces 说明数据的返回类型 及其编码格式
	public boolean insertData(HttpServletRequest request){
		session=request.getSession();
		list=(List) session.getAttribute("alllist");//获取当前集合
		JSONObject js=JSONObject.fromObject(request.getParameter("datalog"));//获取页面传来的json数据
		boolean sdsd=false;
		 session=request.getSession();
	    String account=	(String) session.getAttribute("account");
	    String title=js.getString("title");
	    String strhtml=js.getString("markupStr");
	    
	    DataLog da=new DataLog();
	    da.setAccount(account);
	    da.setTitle(title);
	    da.setStrhtml(strhtml);
	  da.setTime(new Date());
	  list.add(0, da);
	  System.out.println(list.size());
	  session.setAttribute("alllist", list);
	  int b=  dls.insert(da);
	    if(b>0){
	    sdsd=true;	
	    }
return sdsd;
		
	}
	
	
	
	/**
	 * @author 陈佳伟
	 * @version 第五版
	 * @param HttpServletRequest 接受页面请求和传来的数据
	 * @return json数组
	 * 功能 查询全部数据
	 * 用于展示全部日志内容 通过获取或替换session里的集合内容  分别用于全部和查询
	 * */
	
	@ResponseBody 
	@RequestMapping(value="/showAll",produces="application/json;charset=UTF-8")
	public JSONArray showAll(HttpServletRequest request){
		
		 session=request.getSession();
	String nickname=	(String) session.getAttribute("nickname");
	
	
         if(mhpd==1){
        	 list=(List) session.getAttribute("cxlist");
         }else{
        	 list=(List) session.getAttribute("alllist");
        		
         }
		
	
	
		JSONArray jsarray=new JSONArray();
	    jsarray=jsarray.fromObject(list);
	    JSONObject js=new JSONObject();
	     js.put("nickname", nickname);
	     jsarray.add(js);
	     mhpd=0;
		return jsarray;
		
	}
	
	
	/**
	 * @author 陈佳伟
	 * @version 第五版
	 * @param HttpServletRequest 接受页面请求和传来的数据
	 * @return boolean 
	 * 功能 模糊查询
	 * 通过请求 获取用户查询关键字 获取查询内容 放入集合 
	 * */
	@ResponseBody 
	@RequestMapping(value="/selectMohu",produces="application/json;charset=UTF-8")
	public boolean selectMohu(HttpServletRequest request){
		mhpd=1;
		session=request.getSession();
		
		JSONObject js=JSONObject.fromObject(request.getParameter("chaxun"));
	   DataLog d=new DataLog();
	   d.setTitle(js.getString("shuru"));
	   String account=(String) session.getAttribute("account");
	   d.setAccount(account);
		 List cxlist=dls.selectMohu(d);
	   session.setAttribute("cxlist", cxlist);
		
		return true;
		
	}
	
	
	/**
	 * @author 陈佳伟
	 * @version 第四版
	 * @param HttpServletRequest 接受页面请求和传来的数据
	 * @return 无返回
	 * 功能 删除
	 * 通过请求 获取用户删除的条件 调用delete（）进行对数据库删除 同时调用集合的remove（）方法移除当前集合需要删除的内容
	 * */
	
	@ResponseBody 
	@RequestMapping(value="/delete",produces="application/json;charset=UTF-8")
	public void delete(HttpServletRequest request){
		session=request.getSession();
	
		list=(List) session.getAttribute("alllist");
		JSONObject js=JSONObject.fromObject(request.getParameter("shanchu"));
		String title=js.getString("title");
		
		for(int i=0;i<list.size();i++){
			DataLog ds=(DataLog) list.get(i);
		
			if(ds.getTitle().equals(title)){
				list.remove(i);
				dls.delete(title);
			}
		}
		
		
		
	}
}
