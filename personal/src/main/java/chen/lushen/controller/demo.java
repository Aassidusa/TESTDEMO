package chen.lushen.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller 
@RequestMapping("/demo") 
public class demo {

	@ResponseBody //指定controller 返回数据对象的body区
	@RequestMapping(value="/method",produces="application/json;charset=UTF-8")//value 指定请求的确切地址 produces 说明数据的返回类型 及其编码格式
	public JSONObject method(HttpServletRequest request){
		JSONObject js=new JSONObject();
		js.put("demo", "qunidaye");
		
		System.out.println("sss");
		return js;
		
	}

}
