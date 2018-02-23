package chen.lushen.enitiy;

import java.util.Date;


/**
 * @author 陈佳伟
 * @version 第一版
 * 日志实体类
 * 
 * */



public class DataLog {
	 private String title;

	    private String account;

	    private String strhtml;

	    private Date time;

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title == null ? null : title.trim();
	    }

	    public String getAccount() {
	        return account;
	    }

	    public void setAccount(String account) {
	        this.account = account == null ? null : account.trim();
	    }

	    public String getStrhtml() {
	        return strhtml;
	    }

	    public void setStrhtml(String strhtml) {
	        this.strhtml = strhtml == null ? null : strhtml.trim();
	    }

	    public Date getTime() {
	        return time;
	    }

	    public void setTime(Date time) {
	        this.time = time;
	    }
}