package com.boonya.base.utils.downLoad;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载工具类
 * 
 * @packge com.wlyd.fmcgwms.util.downLoad.FileDownLoad
 * @date 2016年4月28日 下午3:47:33
 * @author arrow
 * @comment
 * @update pengjunlin 2016年4月28日 下午15:27:50 添加注释
 */
public class FileDownLoad {

	private FileOutputStream fs;// 文件输出流

	/**
	 * 抓取本地图片
	 * 
	 * @MethodName: download
	 * @Description:
	 * @param path
	 * @param response
	 * @throws
	 */
	public static void download(String path, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes("gbk"), "iso-8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 网络图片下载
	 * 
	 * @MethodName: downloadNet
	 * @Description:
	 * @param response
	 * @throws MalformedURLException
	 * @throws
	 */
	public void downloadNet(HttpServletResponse response)
			throws MalformedURLException {
		int bytesum = 0;
		int byteread = 0;

		// 网络访问地址
		URL url = new URL("windine.blogdriver.com/logo.gif");

		try {
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			fs = new FileOutputStream("c:/abc.gif");

			byte[] buffer = new byte[1204];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
