package com.boonya.base.utils.FileUpload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.boonya.base.utils.Tools;
import com.boonya.base.utils.ehcache.EhcacheUtil;

/**
 * 本地文件上载
 * 
 * @packge com.wlyd.fmcgwms.util.FileUpload.LocalUpload
 * @date 2016年4月28日 下午4:34:41
 * @author arrow
 * @comment
 * @update pengjunlin 2016年4月28日 下午15:27:50 添加注释,代码重构
 */
public class LocalUpload {

	/**
	 * 处理并上传文件
	 * 
	 * @MethodName: handleFileAndUpload
	 * @Description:
	 * @param request
	 * @param sizeMap
	 * @param extMap
	 * @param dirName
	 * @param out
	 * @throws FileUploadException
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static void handleFileAndUpload(HttpServletRequest request,
			HashMap<String, Integer> sizeMap, HashMap<String, String> extMap,
			String dirName, PrintWriter out) throws FileUploadException {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> itr = items.iterator();
		// 最大文件大小
		long maxSize = 1024;
		if (sizeMap.get(dirName) != null) {
			maxSize = sizeMap.get(dirName);
		}
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			long fileSize = item.getSize();// 字节为单位
			if (!item.isFormField()) {
				// 检查文件大小
				if (item.getSize() > maxSize) {
					String _size = (maxSize / (1024 * 1024)) <= 0 ? maxSize
							/ 1024 + "k" : (maxSize / (1024 * 1024)) + "M";
					out.println(getError("上传文件大小超过限制。不超过" + _size));
					return;
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。"));
					return;
				}
				// String newFileName = Tools.generateUploadFileName(fileExt);
				String uploadedFilePath = Tools.generateUploadFilePath(dirName,
						fileExt);

				String orginalPathWithOutExt = uploadedFilePath.substring(0,
						uploadedFilePath.lastIndexOf("."));
				String orginalPath_80x80 = orginalPathWithOutExt + "_80x80.jpg";
				String orginalPath_120x120 = orginalPathWithOutExt
						+ "_120x120.jpg";
				String orginalPath_175x175 = orginalPathWithOutExt
						+ "_175x175.jpg";
				String orginalPath_320x320 = orginalPathWithOutExt
						+ "_320x320.jpg";
				String orginalPath_640x640 = orginalPathWithOutExt
						+ "_640x640.jpg";
				String orginalPath_1024x1024 = orginalPathWithOutExt
						+ "_1024x1024.jpg";

				try {
					File uploadedFile = new File(uploadedFilePath);
					item.write(uploadedFile);
				} catch (Exception e) {
					out.println(getError("上传文件失败。"));
					return;
				}

				if ("image".equals(dirName)) {
					try {
						Thumbnails.of(new File(uploadedFilePath)).size(80, 80)
								.toFile(new File(orginalPath_80x80));
						Thumbnails.of(new File(uploadedFilePath))
								.size(120, 120)
								.toFile(new File(orginalPath_120x120));
						Thumbnails.of(new File(uploadedFilePath))
								.size(175, 175)
								.toFile(new File(orginalPath_175x175));
						Thumbnails.of(new File(uploadedFilePath))
								.size(320, 320)
								.toFile(new File(orginalPath_320x320));
						Thumbnails.of(new File(uploadedFilePath))
								.size(640, 640)
								.toFile(new File(orginalPath_640x640));
						Thumbnails.of(new File(uploadedFilePath))
								.size(1024, 1024)
								.toFile(new File(orginalPath_1024x1024));
					} catch (Exception e) {
						out.println(getError("生成缩略图失败。"));
						return;
					}
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("fileName",
						fileName.substring(fileName.lastIndexOf("\\") + 1));// 解析文件名
				obj.put("url", Tools.uploadPathToUrl(uploadedFilePath));
				obj.put("fileExt", fileExt);
				obj.put("fileSize", fileSize);

				out.println(obj.toJSONString());
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 文件上传
	 * 
	 * @MethodName: fileUpload
	 * @Description:
	 * @param response
	 * @param request
	 * @throws FileUploadException
	 * @throws
	 */
	public static void fileUpload(HttpServletResponse response,
			HttpServletRequest request) throws FileUploadException {
		PrintWriter out = null;
		try {
			response.setContentType("text/html; charset=UTF-8");
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 文件保存目录路径
		String savePath = (String) EhcacheUtil.get("savePath");
		// 文件保存目录URL
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		// extMap.put("flash", "swf,flv");
		extMap.put("flash", "flv");
		// extMap.put("media",
		// "swf,flv,mp4,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("media", "flv,mp4,wav,wma,wmv,avi,mpg,asf,rm,rmvb");
		// extMap.put("file",
		// "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		extMap.put("file", "rar,zip,info");
		// 定义允许上传的文件大小
		HashMap<String, Integer> sizeMap = new HashMap<String, Integer>();// 字节为单位
		sizeMap.put("image", 5 * 1024 * 1024);// 200k
		sizeMap.put("flash", 100 * 1024 * 1024);
		sizeMap.put("media", 100 * 1024 * 1024);
		sizeMap.put("file", 3 * 1024 * 1024);

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			out.println(getError("请选择文件。"));
			return;
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			uploadDir.mkdirs();
			// out.println(getError("上传目录不存在。"));
			// return;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			out.println(getError("上传目录没有写权限。"));
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			out.println(getError("目录名不正确。"));
			return;
		}
		// 处理并上传文件
		LocalUpload.handleFileAndUpload(request, sizeMap, extMap, dirName, out);
	}

	/**
	 * 文件上传模型
	 * 
	 * @MethodName: uploadModel
	 * @Description:
	 * @param response
	 * @param fileName
	 * @throws
	 */
	public static void uploadModel(HttpServletResponse response, String fileName) {
		BufferedInputStream bufferedInput = null;
		BufferedOutputStream bufferedOutput = null;
		try {
			// 定义输出类型
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型
			response.reset();// 清除缓冲中的数据
			// //attachment --- 作为附件下载
			// //inline --- 在线打开
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\";");

			/*
			 * URL url = new URL((String) EhcacheUtil.get("saveUrl") +
			 * "modelExcel/" + fileName); URLConnection conn =
			 * url.openConnection(); BufferedInputStream bufferedInputStream =
			 * new BufferedInputStream( conn.getInputStream());
			 */
            // start----
			String filepath="excel/"+fileName;//注意filepath的内容；  
			InputStream in = null;
		    in = LocalUpload.class.getClassLoader().getResourceAsStream(filepath);
			bufferedInput = new BufferedInputStream(in);
			// end----
			bufferedOutput = new BufferedOutputStream(
					response.getOutputStream());
			int len = -1;
			byte[] bt = new byte[1024];
			while ((len = bufferedInput.read(bt)) > 0) {
				bufferedOutput.write(bt, 0, len);
				bufferedOutput.flush();
			}
			// 关闭输入流
			bufferedOutput.close();
			bufferedInput.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * 从文件中读取文本
	 */
	public void readFromFile(String filename) {

		BufferedInputStream bufferedInput = null;
		byte[] buffer = new byte[1024];

		try {

			// 创建BufferedInputStream 对象
			bufferedInput = new BufferedInputStream(new FileInputStream(
					filename));

			int bytesRead = 0;

			// 从文件中按字节读取内容，到文件尾部时read方法将返回-1
			while ((bytesRead = bufferedInput.read(buffer)) != -1) {

				// 将读取的字节转为字符串对象
				String chunk = new String(buffer, 0, bytesRead);
				System.out.print(chunk);
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// 关闭 BufferedInputStream
			try {
				if (bufferedInput != null)
					bufferedInput.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * poi获取导入文件流
	 * 
	 * @MethodName: getInputStream
	 * @Description:
	 * @param request
	 * @param fileName
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public static InputStream getInputStream(HttpServletRequest request,
			String fileName) throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		try {
			if (isMultipart == true) {
				// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 将所有的表单项目都保存到List中
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> itr = items.iterator();
				// 循环list，取得表单项
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					// 检查当前项目是普通表单项目还是文件。
					if (item.isFormField()) {
						// 如果是普通表单项目，显示表单内容。
						String fieldName = item.getFieldName();
						// 对应form表单中type="text" name="name"
						if (fieldName.equals(fileName)) {
							// log.info("the field name is" + item.getString());
							// // 显示表单内容
						}
					} else {
						// 如果是文件
						if (item.getFieldName().equals(fileName)) {
							// 如果上传文件的file的name为" filecer"
							InputStream inStream = item.getInputStream();
							return inStream;
						}
					}
				}
			} else {
				// log.info("the enctype must be multipart/form-data");
			}
		} catch (Exception e) {
			// log.info("获取文件输入流有误........" + e.getMessage());
		}
		return null;
	}

	/**
	 * 获取设置JSON错误信息字符串
	 * 
	 * @MethodName: getError
	 * @Description:
	 * @param message
	 * @return
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private static String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}

}
