package com.boonya.base.utils.mybatis;

import java.io.Serializable;
public class Page implements Serializable{
	/**
	 * 分页类
	 */
	private static final long serialVersionUID = -3390469753961799282L;
	private int rows=10; //每页显示记录数
	private int totalPage;		//总页数
	private int totalResult;	//总记录数
	private int page=1;	//当前页
	private int currentResult;	//当前记录起始索引
	private boolean entityOrField;	//true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr;		//最终页面显示的底部翻页导航，详细见：getPageStr();
	public int getTotalPage() {
		if(totalResult%rows==0)
			totalPage = totalResult/rows;
		else
			totalPage = totalResult/rows+1;
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if(totalResult>0){
			if(page==1){
				sb.append("<a href=\"javascript:void(0)\">首页</a>\n");
				sb.append("<a href=\"javascript:void(0)\">上一页</a>\n");
			}else{	
				sb.append("	<a href=\"#@\" onclick=\"nextPage(1)\">首页</a>\n");
				sb.append("	<a href=\"#@\" onclick=\"nextPage("+(page-1)+")\">上一页</a>\n");
			}
			int showTag = 3;	//分页标签显示数量
			int startTag = 1;
			if(page>showTag){
				startTag = page-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=totalPage && i<=endTag; i++){
				if(page==i)
					sb.append("<a href=\"javascript:void(0)\" class=\"show\">"+i+"</a>\n");
				else
					sb.append("	<a href=\"#@\" onclick=\"nextPage("+i+")\">"+i+"</a>\n");
			}
			if(page==totalPage){
				sb.append("<a href=\"javascript:void(0)\">下一页</a>\n");
				sb.append("<a href=\"javascript:void(0)\">尾页</a>\n");
			}else{
				sb.append("	<a href=\"#@\" onclick=\"nextPage("+(page+1)+")\">下一页</a>\n");
				sb.append("	<a href=\"#@\" onclick=\"nextPage("+totalPage+")\">尾页</a>\n");
			}
			//sb.append("	第"+page+"页\n");
			sb.append("	共"+totalPage+"页\n");
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function nextPage(page){");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"page":"page")+"=\";}\n");
			sb.append("		else{url += \"?"+(entityOrField?"page":"page")+"=\";}\n");
			sb.append("		document.forms[0].action = url+page;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('page')>-1){\n");
			sb.append("				var reg = /page=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'page=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"page":"page")+"=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"page":"page")+"=\";}\n");
			sb.append("		document.location = url + page;\n");
			sb.append("	}\n");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}
	
	public int getPage() {
		if(page<=0)
			page = 1;
		if(page>getTotalPage())
			page = getTotalPage();
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCurrentResult() {
		currentResult = (getPage()-1)*getRows();
		if(currentResult<0)
			currentResult = 0;
		return currentResult;
	}
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	public boolean isEntityOrField() {
		return entityOrField;
	}
	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}
	
}
