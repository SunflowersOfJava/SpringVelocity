package com.boonya.base.utils.mail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.boonya.base.utils.Log;
import com.boonya.base.utils.ehcache.EhcacheUtil;

/**
 * 简单邮件（不带附件的邮件）发送器
 * 
 * @packge com.wlyd.fmcgwms.util.mail.SendMail
 * @date 2016年6月13日 上午9:56:59
 * @author wenliang
 * @comment
 * @update
 */
public class SendMail {
	
	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 */
	public boolean sendTextMail(SendMailInfo mailInfo) {
		// 判断是否需要身份认证
		MailAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MailAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			Log.getLogger(getClass()).error(">>>发送文本邮件异常：" + ex.getMessage());
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	public boolean sendHtmlMail(SendMailInfo mailInfo) {
		// 判断是否需要身份认证
		MailAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MailAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			Log.getLogger(getClass()).error(">>>发送网页邮件异常：" + ex.getMessage());
		}
		return false;
	}

	public static void send(String actionStr, String interfaceName,
			String json, String result) {
		String apiVersion=(String) EhcacheUtil.get("API_VERSION");
		SendMailInfo mailInfo = new SendMailInfo();
		mailInfo.setValidate(true);

		StringBuilder sb = new StringBuilder();
		sb.append("尊敬的（先生/女士）阁下：<p>");
		sb.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！");
		sb.append(actionStr
				+ ",在 "
				+ new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss")
						.format(new Date()) + " 调用" + interfaceName
				+ "时失败，请求参数为：" + json + ",返回结果为:" + result + ",请尽快跟进！");
		sb.append("<p>");
		sb.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！<br>");
		sb.append("<b>WMS-OMS接口编码说明：</b><br>");
		sb.append("<font color='red'>");
		sb.append("\t\t20001:获取客户(货主)接口-<br>");
		sb.append("\t\t20002:获取承运商信息接口-<br>");
		sb.append("\t\t20003:获取仓库信息接口-<br>");
		sb.append("\t\t20004:获取商品信息接口-<br>");
		sb.append("\t\t20005:订单任务下发接口-<br>");
		sb.append("\t\t20006:订单中止或取消-<br>");
		sb.append("\t\t20007:订单退货接口-<br>");
		sb.append("\t\t20008:订单处理结果接口-<br>");
		sb.append("\t\t20009:库存同步接口-<br>");
		sb.append("\t\t20010:电子面单接口-<br>");
		sb.append("</font>");
		sb.append("\t此致<br>");
		sb.append("四川物联亿达"+(apiVersion==null?"":apiVersion)+"研发团队");
		mailInfo.setContent(sb.toString());

		String toAddrs = (String) EhcacheUtil.get("EMAIL_ADDRESS");
		if (toAddrs != null && !toAddrs.equals("")) {
			String mails[] = toAddrs.split(",");
			for (int i = 0; i < mails.length; i++) {
				mailInfo.setToAddress(mails[i]);
				SendMail sms = new SendMail();
				sms.sendHtmlMail(mailInfo);
			}
		}
	}
}
