package com.outbreak.entity;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailPoster {
	String from="15008181891@163.com";
    String password="mky999729mzz";
    String to="383250208@qq.com";
    String emailHost="smtp.163.com";
    String smtpPort="25";
    public EmailPoster(String receiver){
    	to=receiver;
    }
    public void sendEmail(String title, String content){
        Properties p=new Properties();
        p.setProperty("mail.smtp.host", emailHost);
        p.setProperty("mail.smtp.port", smtpPort);
        p.setProperty("mail.smtp.socketFactory.port", smtpPort);
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.socketFactory.class", "SSL_FACTORY");

        Session session=Session.getInstance(p, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from,password);
            }
        });
        session.setDebug(true);

        MimeMessage message=new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,to);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            message.setSubject(title);
            message.setContent(content,"text/html;charset=GBK");
            message.setSentDate(new Date());
            message.saveChanges();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void sendRegCheck(String receiver, int check) {
    	new EmailPoster(receiver).sendEmail("outbreak??????????", "<p>????????????????</p><p>????outbreak??????????????????????????"+check+"</p>");
    }
    public static void sendPwResetCheck(String receiver, int check) {
    	new EmailPoster(receiver).sendEmail("????????", "<p>????????????????</p><p>????????????????????"+check+"??????????????????????????</p>");
    }
    public static void sendIfInvited(String receiver, String topic, boolean hasRegistered) {
    	String content="<p>????????????????</p><p>????outbreak??????????????????????"+topic+"????</p>";
    	if(hasRegistered) {
    		content+="<p>??????????????????????????????????????</p>";
    	}else {
    		content+="<p>??????????????????????????????????????????????????????????????????outbreak123</p>";
    	}
    	new EmailPoster(receiver).sendEmail("????????", content);
    }
    public static void test(){
        EmailPoster emailPoster=new EmailPoster("383250208@qq.com");
        emailPoster.sendEmail("????????","OUTBREAK????????????????????\n????????231??????????????");
    }
    public static void testRegCheck() {
    	EmailPoster.sendRegCheck("383250208@qq.com", 154396);
    }
    public static void main(String[] args){
        //test();
        //EmailPoster.test();
    	//EmailPoster.testRegCheck();
    	//EmailPoster.sendIfInvited("383250208@qq.com", "????", false);
    	EmailPoster.sendPwResetCheck("383250208@qq.com", 123456);
    }
}
