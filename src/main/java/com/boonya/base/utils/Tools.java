package com.boonya.base.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.boonya.base.utils.ehcache.EhcacheUtil;
import com.boonya.base.utils.mybatis.Page;

/**
 * 系统通用工具类
 * @packge com.wlyd.fmcgwms.util.Tools
 * @date   2016年4月29日  上午11:40:42
 * @author pengjunlin
 * @comment   
 * @update 添加注释
 */
public class Tools {

	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 将JSON字符串反序列化为java对象
	 * 
	 * @throws IOException
	 */
	public static <T> T jsonToObj(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将JSON字符串反序列化为List<Object>对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static List<Object> jsonToList(String jsonStr) {
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		Object[] obj = jsonArray.toArray();
		List<Object> list = Arrays.asList(obj);
		return list;
	}

	/**
	 * 将JSON字符串反序列化为List<T>对象
	 * 
	 * @param <T>
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonStr, Class<T> clazz) {
		return JSON.parseArray(jsonStr, clazz);
	}

	/**
	 * 将对象序列化成json
	 */
	public static String toJson(Object obj) {
		try {
			return JSON.toJSONString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 将对象序列化成json
	 */
	public static String toJsonFormatDate(Object obj,String pattern) {
		try {
			return JSON.toJSONStringWithDateFormat(obj, pattern, SerializerFeature.UseISO8601DateFormat);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 封装分页
	 * 
	 * @param <T>
	 * @param lt
	 * @param paper
	 * @return
	 */
	public static <T> String listPageToJson(List<T> lt, Page paper) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("total", paper.getTotalResult());
		model.put("rows", lt);
		System.out.println("++:"+JSON.toJSONString(model));
		return JSON.toJSONStringWithDateFormat(model, DateUtil.PATTERN_DATE, SerializerFeature.UseISO8601DateFormat);
	}
	
	/**
	 * 将分页集合数据转为JSON数据字符串
	 * @MethodName: listPageToJson 
	 * @Description: 
	 * @param lt
	 * @param paper
	 * @param pattern
	 * @return
	 * @throws
	 */
	public static <T> String listPageToJson(List<T> lt, Page paper,String pattern) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("total", paper.getTotalResult());
		model.put("rows", lt);
		System.out.println("++:"+JSON.toJSONString(model));
		return JSON.toJSONStringWithDateFormat(model, pattern, SerializerFeature.UseISO8601DateFormat);
	}
	/**
	 * 封装分页
	 * 
	 * @param <T>
	 * @param lt
	 * @param paper
	 * @return
	 */
	public static <T> String listPageToJson(List<T> lt) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("total", lt.size());
		model.put("rows", lt);
		return JSON.toJSONString(model);
	}
	/**
	 * 入库计划商品详细信息专用
	 * @param <T>
	 * @param lt
	 * @param paper
	 * @return
	 */
	public static <T> String listPageToJsonOrderInfo(List<T> lt) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("total", lt.size());
		model.put("rows", lt);
		return JSON.toJSONStringWithDateFormat(model, DateUtil.PATTERN_DATE, SerializerFeature.UseISO8601DateFormat);
	}

	/**
	 * 封装分页
	 * 
	 * @param <T>
	 * @param lt
	 * @param paper
	 * @return
	 */
	public static <T> String listPageToJsonLib(List<T> lt, Page paper) {
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("total", paper.getTotalResult());
			model.put("rows", lt);
			return objectMapper.writeValueAsString(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 检测字符串是否不为空(null,"","null")
	 * 
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s.trim()) && !"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * 
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s.trim()) || "null".equals(s);
	}

	public static boolean isEmpty(Collection<?> c) {
		return (c == null || c.size() == 0) ? true : false;
	}

	/**
	 * 字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @param splitRegex
	 *            分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 百分数转小数
	 * 
	 * @return
	 */
	public static Float todecimal(String str) {
		Float f = null;
		if (str.contains("%")) {
			str = str.replaceAll("%", "");
			f = (Float.valueOf(str)) / 100;
		}
		return f;
	}

	/**
	 * map转list
	 * 
	 * @param <T>
	 * @param context
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> mapTransitionList(Map<String, T> context) {
		List<T> list = new ArrayList<T>();
		Iterator<?> iter = context.entrySet().iterator(); // 获得map的Iterator
		while (iter.hasNext()) {
			Entry<?, ?> entry = (Entry<?, ?>) iter.next();
			list.add((T) entry.getValue());
		}
		return list;
	}

	public static String jsonStrSucc(String msg) {
		return toJson(jsonSucc(msg));
	}

	public static String jsonStrSuccWithExtra(String msg, Object extraInfo) {
		return toJson(jsonSuccWithExtra(msg, extraInfo));
	}

	public static String jsonStrFail(String msg) {
		return toJson(jsonFail(msg));
	}

	public static String jsonStrFailWithExtra(String msg, Object extraInfo) {
		return toJson(jsonFailWithExtra(msg, extraInfo));
	}

	private static Map<String, Object> jsonSucc(String msg) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("success", true);
		json.put("msg", msg);
		return json;
	}

	private static Map<String, Object> jsonSuccWithExtra(String msg, Object extraInfo) {
		Map<String, Object> json = jsonSucc(msg);
		json.put("extra", extraInfo);
		return json;
	}

	private static Map<String, Object> jsonFail(String msg) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("success", false);
		json.put("msg", msg);
		return json;
	}

	private static Map<String, Object> jsonFailWithExtra(String msg, Object extraInfo) {
		Map<String, Object> json = jsonFail(msg);
		json.put("extra", extraInfo);
		return json;
	}

	/**
	 * 
	 * 方法描述：驼峰变下划线，如：adOrder-->ad_order
	 * 
	 * @param:
	 * @return:
	 * @version: 1.0
	 * @author: fgjun
	 * @version: 2014-3-20 上午10:26:16
	 */
	public static String camel4underline(String param) {
		Pattern p = Pattern.compile("[A-Z]");
		if (param == null || param.equals("")) {
			return "";
		}
		StringBuilder builder = new StringBuilder(param);
		Matcher mc = p.matcher(param);
		int i = 0;
		while (mc.find()) {
			builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
			i++;
		}

		if ('_' == builder.charAt(0)) {
			builder.deleteCharAt(0);
		}
		return builder.toString();
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param: 需要过滤html标签的字符串
	 * @return: 过滤后的字符串
	 * @version: 1.0
	 */
	public static String filterHtml(String inputString) {
		String htmlStr = inputString;
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		java.util.regex.Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try {
			String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
			String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			// System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;
	}

	public static String getBaseUrl() {
		HttpServletRequest request = getRequest();
		return request.getContextPath();
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 上传文件访问url转为硬盘路径
	 * @MethodName: uploadUrlToPath 
	 * @Description: 
	 * @param fileUrl
	 * @return
	 * @throws
	 */
	public static String uploadUrlToPath(String fileUrl) {
		if (isEmpty(fileUrl)) {
			return "";
		}
		String savePath = (String) EhcacheUtil.get("savePath");
		String saveUrl = (String) EhcacheUtil.get("saveUrl");
		fileUrl = fileUrl.replace(saveUrl, savePath);
		return fileUrl;
	}

	/**
	 * 上传文件硬盘路径转为访问url
	 * @MethodName: uploadPathToUrl 
	 * @Description: 
	 * @param filePath
	 * @return
	 * @throws
	 */
	public static String uploadPathToUrl(String filePath) {
		if (isEmpty(filePath)) {
			return "";
		}
		String savePath = (String) EhcacheUtil.get("savePath");
		String saveUrl = (String) EhcacheUtil.get("saveUrl");
		filePath = filePath.replace(savePath, saveUrl);
		return filePath;
	}

	/**
	 * 获取6位随机数
	 * 
	 * @return
	 */
	public static int getRandomNumber() {
		return (int) ((Math.random() * 9 + 1) * 100000);
	}

	public static boolean isValidIntPK(Integer i) {
		return i != null && i > 0;
	}

	/**
	 * 跳转到失败提示页面
	 * 
	 * @param tipMsg
	 * @param redirectUrl
	 * @return
	 */
	public static ModelAndView jump_fail(String tipMsg, String redirectUrl) {
		return jump_page(tipMsg, redirectUrl, "/fail_tip");
	}

	private static ModelAndView jump_page(String tipMsg, String redirectUrl, String page) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tipMsg", tipMsg);
		model.put("redirectUrl", redirectUrl);
		ModelAndView mav = new ModelAndView(page, model);
		return mav;
	}

	public static String generateUploadFilePath(String dirName, String fileExt) {
		return generateUploadFileDirectory(dirName) + generateUploadFileName(fileExt);
	}

	/**
	 * 生成文件上传的保存目录并返回目录完整名，该方法会自动创建目录
	 * 
	 * @param dirName
	 *            目录名，如：image,media
	 * @return
	 */
	public static String generateUploadFileDirectory(String dirName) {
		String savePath = (String) EhcacheUtil.get("savePath");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String dir = savePath + dirName + "/" + ymd + "/";
		File dirFile = new File(dir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		return dir;
	}

	/**
	 * 生成上传文件的文件名
	 * 
	 * @param fileExt
	 *            文件扩展名
	 * @return
	 */
	public static String generateUploadFileName(String fileExt) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		return newFileName;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileExt
	 *            文件扩展名
	 * @return
	 */
	public static void deleteFile(String filePath) {
		File f = new File(filePath);
		deleteFile(f);
	}

	/**
	 * 删除文件
	 * 
	 * @param fileExt
	 *            文件扩展名
	 * @return
	 */
	public static void deleteFile(File f) {
		if (f.isFile()) {
			f.delete();
		}
	}
	
	public static String getLocationType(Integer key){
		String locationType="";
		switch (key) {
		case 1:
			locationType="存储";
			break;
		case 2:
			locationType="暂存";
			break;
		case 3:
			locationType="月台";
			break;
		default:
			break;
		}
		return locationType;
	}
	
	/**
	 * 获取查询结果手动分页数据集合
	 * @MethodName: getResutByPaging 
	 * @Description: 
	 * @param list
	 * @param pageSize
	 * @param pageNo
	 * @return
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getResutByPaging(List list,int pageSize,int pageNo){
		if(list==null) return list;
		List result=new ArrayList();
		int startIndex=0,endIndex=0;
		if(list.size()>pageSize){
			if(list.size()%pageSize!=0){
				startIndex=pageNo==1?0:(pageNo-1)*pageSize;
				endIndex=pageNo==1?pageNo*pageSize:(list.size()/pageSize==pageNo?pageNo*pageSize:list.size());
			}else{
				startIndex=pageNo==1?0:(pageNo-1)*pageSize;
				endIndex=pageNo*pageSize;
			}
		}else{
			startIndex=0;
			endIndex=list.size();
		}
		for (int i = startIndex; i < endIndex; i++) {
			result.add(list.get(i)); 
		}
		return result;
	}
	
	/**
	 * 生成固定长度的随机数
	 * 
	 * @MethodName: getFixLenthString 
	 * @Description: 
	 * @param strLength
	 * @return
	 * @throws
	 */
	public static String getFixLenthString(int strLength) {  
	    Random rm = new Random();  
	    // 获得随机数  
	    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = String.valueOf(pross);  
	    // 返回固定的长度的随机数  
	    return fixLenthString.substring(1, strLength + 1);  
	}  
	
	/**
	 * 判断当前组织账号是否允许调用平台接口
	 * 
	 * @MethodName: isAllowedToCallSaasInterface 
	 * @Description: 
	 * @param esCorCode
	 * @return
	 * @throws
	 */
	public static boolean isAllowedToCallSaasInterface(String esCorCode){
		if(esCorCode==null||esCorCode.trim().equals("10000")||esCorCode.trim().equals("10003")||esCorCode.trim().equals("10002"))return false;
		return true;
	}
}
