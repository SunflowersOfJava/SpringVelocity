package com.boonya.base.utils;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * QR二维码工具类
 * @packge com.wlyd.fmcgwms.util.QRCodeUtil
 * @date   2016年4月29日  上午11:36:14
 * @author pengjunlin
 * @comment   
 * @update 添加注释
 */
public class QRCodeUtil {

	/**
	 * 编码格式
	 */
	private static final String CHARSET = "utf-8";
	/**
	 * 图片格式
	 */
	private static final String FORMAT_NAME = "JPG";
	/**
	 * 二维码尺寸
	 */
	private static final int QRCODE_SIZE = 300;
	/**
	 *  LOGO宽度
	 */
	private static final int WIDTH = 60;
	/**
	 *  LOGO高度
	 */
	private static final int HEIGHT = 60;

	/**
	 * 创建图片
	 * 
	 * @MethodName: createImage
	 * @Description:
	 * @param content
	 *            文字内容
	 * @param imgPath
	 *            图片路径
	 * @param needCompress
	 *            是否压缩
	 * @return
	 * @throws Exception
	 * @throws
	 */
	private static BufferedImage createImage(String content, String imgPath,
			boolean needCompress) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
						: 0xFFFFFFFF);
			}
		}
		if (imgPath == null || "".equals(imgPath)) {
			return image;
		}
		// 插入图片
		QRCodeUtil.insertImage(image, imgPath, needCompress);
		return image;
	}

	/**
	 * 插入LOGO
	 * 
	 * @param source
	 *            二维码图片
	 * @param logoPath
	 *            LOGO图片地址
	 * @param needCompress
	 *            是否压缩
	 * @throws Exception
	 */
	private static void insertImage(BufferedImage source, String logoPath,
			boolean needCompress) throws Exception {
		File file = new File(logoPath);
		if (!file.exists()) {
			// System.err.println("" + logoPath + "   该文件不存在！");
			return;
		}
		Image src = ImageIO.read(new File(logoPath));
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > WIDTH) {
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRCODE_SIZE - width) / 2;
		int y = (QRCODE_SIZE - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param logoPath
	 *            LOGO地址
	 * @param destPath
	 *            存放目录
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static String encode(String content, String logoPath,
			String destPath, boolean needCompress) throws Exception {
		BufferedImage image = QRCodeUtil.createImage(content, logoPath,
				needCompress);
		mkdirs(destPath);
		String newFileName = Tools.generateUploadFileName("jpg");
		ImageIO.write(image, FORMAT_NAME,
				new File(destPath + "/" + newFileName));
		return newFileName;
	}

	/**
	 * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
	 * 
	 * @param destPath
	 *            存放目录
	 */
	public static void mkdirs(String destPath) {
		File file = new File(destPath);
		// 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param destPath
	 *            存储地址
	 * @throws Exception
	 */
	public static void encode(String content, String imgPath, String destPath)
			throws Exception {
		QRCodeUtil.encode(content, imgPath, destPath, false);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            内容
	 * @param destPath
	 *            存储地址
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static void encode(String content, String destPath,
			boolean needCompress) throws Exception {
		QRCodeUtil.encode(content, null, destPath, needCompress);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            内容
	 * @param destPath
	 *            存储地址
	 * @throws Exception
	 */
	public static void encode(String content, String destPath) throws Exception {
		QRCodeUtil.encode(content, null, destPath, false);
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param output
	 *            输出流
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static void encode(String content, String imgPath,
			OutputStream output, boolean needCompress) throws Exception {
		BufferedImage image = QRCodeUtil.createImage(content, imgPath,
				needCompress);
		ImageIO.write(image, FORMAT_NAME, output);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            内容
	 * @param output
	 *            输出流
	 * @throws Exception
	 */
	public static void encode(String content, OutputStream output)
			throws Exception {
		QRCodeUtil.encode(content, null, output, false);
	}

	/**
	 * 解析二维码
	 * 
	 * @param file
	 *            二维码图片
	 * @return
	 * @throws Exception
	 */
	public static String decode(File file) throws Exception {
		BufferedImage image;
		image = ImageIO.read(file);
		if (image == null) {
			return null;
		}
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
				image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
		result = new MultiFormatReader().decode(bitmap, hints);
		String resultStr = result.getText();
		return resultStr;
	}

	/**
	 * 解析二维码
	 * 
	 * @param path
	 *            二维码图片地址
	 * @return
	 * @throws Exception
	 */
	public static String decode(String path) throws Exception {
		return QRCodeUtil.decode(new File(path));
	}

	/**
	 * Main函数（保留，当API使用）
	 * 
	 * @MethodName: main
	 * @Description:
	 * @param args
	 * @throws Exception
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		// 文件保存目录路径
		// String savePath = (String) EhcacheUtil.get("savePath");
		File f = new File(
				"F:\\project\\物道三期\\编码\\static\\upload\\image\\20140518\\20140518151633_107.jpg");
		f.delete();
		String savePath = "F:/project/物道三期/编码/static/upload/";
		savePath += "image/";
		savePath += new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/";
		String content = "";
		content += "BEGIN:VCARD\n";
		content += "VERSION:3.0\n";
		content += "N:付国军\n";
		content += "ORG:四川物联亿达科技 运营部\n";
		content += "TITLE:软件开发工程师\n";
		content += "TEL;WORK;VOICE:0825666666\n";// 工作电话
		content += "TEL;HOME;VOICE:08256778447\n";// 家庭电话
		content += "TEL;CELL;VOICE:15196946635\n";// 手机
		content += "ADR:遂宁西部现代物流港\n";
		// content += "NICKNAME:海鸥\n";
		// content += "URL:http://work.56dao.net\n";// 个人主页
		content += "NOTE:擅长编程\n";
		content += "END:VCARD";
		QRCodeUtil.encode(content, "c:/logo.png", savePath, true);
	}
}
