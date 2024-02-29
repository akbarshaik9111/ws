package com.app.akbar.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String subject, String body, String to, File file) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			//true - To enable to send attachment in the mail
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			helper.addAttachment("Plans-Info", file);
			
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
