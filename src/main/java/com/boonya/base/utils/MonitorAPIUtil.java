package com.boonya.base.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 监管仓接口工具类
 * 
 * @packge com.wlyd.fmcgwms.util.MonitorAPIUtil
 * @date 2016年4月29日 上午11:46:32
 * @author pengjunlin
 * @comment
 * @update 添加注释
 */
public class MonitorAPIUtil {

	/**
	 * GET方式获取网络连接的JSON数据
	 * 
	 * @MethodName: getJsonContentGet
	 * @Description:
	 * @param path
	 * @param param
	 * @return
	 * @throws
	 */
	public static String getJsonContentGet(String path, String param) {
		String line;
		String result = "";
		try {
			// String path =
			// "http://192.168.200.131/MonitorWeb/api/Allocation?codes=cd-19-32";
			URL url = new URL(path + param);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5000);
			conn.setRequestMethod("GET");
			conn.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return result;
	}

	/**
	 * POST方式获取网络连接的JSON数据
	 * 
	 * @MethodName: getJsonContentPost
	 * @Description:
	 * @param urlStr
	 * @param values
	 * @return
	 * @throws
	 */
	public static String getJsonContentPost(String urlStr, String values) {
		try {
			// 获取HttpURLConnection连接对象
			URL url = new URL(urlStr);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();
			// 设置连接属性
			httpConn.setConnectTimeout(3000);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			StringBuffer params = new StringBuffer();
			params.append(values);
			byte[] bypes = params.toString().getBytes("UTF-8");
			httpConn.getOutputStream().write(bypes);
			int respCode = httpConn.getResponseCode();
			if (respCode == 200) {
				System.out.println("200");
				return ConvertStream2Json(httpConn.getInputStream());
			}
			System.out.println(respCode);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 将输入流转化成JSON字符串
	 * 
	 * @MethodName: ConvertStream2Json
	 * @Description:
	 * @param inputStream
	 * @return
	 * @throws
	 */
	private static String ConvertStream2Json(InputStream inputStream) {
		String jsonStr = "";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		// 将输入流转移到内存输出流中
		try {
			while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, len);
			}
			// 将内存流转换为字符串
			jsonStr = new String(out.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}
}
