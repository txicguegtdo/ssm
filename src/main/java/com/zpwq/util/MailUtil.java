package com.zpwq.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Created by ZPWQ on 2016/5/3.
 */
public class MailUtil {
    // 配置发送邮件的环境属性
    private  Properties props = new Properties();
    private Session mailSession;
    public boolean validate() throws Exception{
    	return validate("imap");
    }
    public boolean validate(String protocol) throws Exception{
    	props.put("mail.store.protocol", protocol);
    	props.put("mail."+protocol+".host", props.getProperty("mail.smtp.host"));
    	mailSession.setDebug(true);
		// 利用Session对象获得Store对象
		try{
			Store store = mailSession.getStore();
			store.connect(props.getProperty("mail.smtp.host"), props.getProperty("mail.user"), props.getProperty("mail.password"));
			return true;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
    }
    public MailUtil(String address,String password,String host){
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.user", address);
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", password);
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        mailSession = Session.getInstance(props, authenticator);
    }
    public void sendMessage(String address_to,String address_cc,String subject ,String mail_body,String subPath){
        MimeMessage message = new MimeMessage(mailSession);
        try{
            //设置主题
            message.setSubject(subject);
            // 设置发件人
            InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
            message.setFrom(form);
            // 设置收件人
            if(address_to != null ){
                new InternetAddress();
				InternetAddress[] address = InternetAddress.parse(address_to);
                message.setRecipients(Message.RecipientType.TO, address);

            }
            // 设置抄送人
            if(address_cc != null ){
                new InternetAddress();
				InternetAddress[] address = InternetAddress.parse(address_cc);
                message.setRecipients(Message.RecipientType.CC, address);
            }
            //设置正文和附件
            Multipart multipart = new MimeMultipart();

            //添加正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(mail_body, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);

            try{
                //添加附件
                BodyPart messageBodyPart= new MimeBodyPart();
                DataSource source = new FileDataSource(subPath);
                //添加附件的内容
                messageBodyPart.setDataHandler(new DataHandler(source));
                String filename=  MimeUtility.encodeText(source.getName());
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
            }catch(Exception e){

            }

            message.setContent(multipart);

            // 发送邮件
            Transport.send(message);
            System.out.println(props.getProperty("mail.user")+"发送成功!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void sendMessage(String address_to,String address_cc,String subject ,String mail_body,Map<String,String> attachment){
        MimeMessage message = new MimeMessage(mailSession);
        try{
            //设置主题
            message.setSubject(subject);
            // 设置发件人
            InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
            message.setFrom(form);
            // 设置收件人
            if(address_to != null && !"".equals(address_to)){
                new InternetAddress();
				InternetAddress[] address = InternetAddress.parse(address_to);
                message.setRecipients(Message.RecipientType.TO, address);
            }else{
            	throw new Exception("未设置收件人！");
            }
            // 设置抄送人
            if(address_cc != null && !"".equals(address_cc)){
                new InternetAddress();
				InternetAddress[] address = InternetAddress.parse(address_cc);
                message.setRecipients(Message.RecipientType.CC, address);
            }
            //设置正文和附件
            Multipart multipart = new MimeMultipart();

            //添加正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(mail_body, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);
            
            for(Entry<String,String> entry :attachment.entrySet()){
            	try{
                    //添加附件
                    BodyPart messageBodyPart= new MimeBodyPart();
                    DataSource source = new FileDataSource(entry.getKey());
                    //添加附件的内容
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(MimeUtility.encodeText(entry.getValue()));
                    multipart.addBodyPart(messageBodyPart);
                }catch(Exception e){
                	e.printStackTrace();
                }
            }
            message.setContent(multipart);

            // 发送邮件
            Transport.send(message);
            System.out.println(props.getProperty("mail.user")+"发送成功!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    

    public static void main(String[] args) throws Exception {
        MailUtil mail = new MailUtil("zhangpw@asiainfo.com","yx20161018@","mail.asiainfo.com");
        System.out.println(mail.validate());
        //mail.sendMessage("zpw1990@126.com,1046846122@qq.com","zpw_1990@126.com","测试用例","你好!!","");
    }
}
