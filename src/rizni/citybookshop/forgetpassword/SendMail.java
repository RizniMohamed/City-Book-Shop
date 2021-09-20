package rizni.citybookshop.forgetpassword;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import rizni.citybookshop.reuseable.MessageBox;

import javax.mail.Session;
import javax.mail.Transport;

class SendMail{

	public void send(String to,String sub,String msg){  
		//Get properties object    
		Properties props = new Properties();  

		props.put("mail.smtp.auth", "true");    
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");    
		props.put("mail.smtp.port", "587");    

		//get Session   
		Session session = Session.getDefaultInstance(props, new Authenticator() {    
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {    
				return new PasswordAuthentication("mnriznimohamed@gmail.com","odinlnvngleadjtg");  
			}    
		});    
		//compose message    
		try {    
			MimeMessage message = new MimeMessage(session);  
			message.setFrom(new InternetAddress("mnriznimohamed@gmail.com"));  
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));   
			message.setSubject(sub);    
			message.setText(msg);    
			//send message  
			Transport.send(message);    
			System.out.println("message sent successfully");    
		} catch (MessagingException e) { new MessageBox().showDialog("Error","Mail seriver", "Unable to send mail : " + e);}  

	} 

}  


