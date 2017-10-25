package com.github.marcinseweryn.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.github.marcinseweryn.components.Messages;
import com.github.marcinseweryn.model.User;

import antlr.collections.List;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Messages messages;
	
	@Override
	public String forgottenLoginDataFirstStep(String email) {
		
		User user = userService.findUserByEmail(email);
		
		if(user != null){
			
			////////////////////	Code generator	///////////////////////

			
			Integer ID = user.getID();
			Random random = new Random();
			Integer codeSize = random.nextInt(2) + 4;
			String code = "";
			
			for(int i = 0; i < codeSize; i++){
				int ASCIIcode = random.nextInt(122 - 48 + 1) + 48;
				char character = (char) ASCIIcode;
				
				code += String.valueOf(character);  
			}
			System.out.println(code);
		
			//////////////////////////////////////////////////////////////////	
			
			///////////////////////// Mail message //////////////////////////
			
			String title = messages.get("mail.forgotAccount.title1");
			String welcome = messages.get("welcome");
			String text = messages.get("mail.forgotAccount.message");
			String secCode = messages.get("mail.forgotAccount.securityCode");
			String accountNumber = messages.get("accountNumber");
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("healthcenter1111@gmail.com");
			message.setTo(email);
			message.setSubject(title);
			message.setText(welcome + " " + user.getName() + ",\n\n"
					+ text + "\n"
					+ accountNumber + ": " + user.getID() + "\n"
					+ secCode + ": " + code);
			mailSender.send(message);
			
			///////////////////////////////////////////////////////////////
			
			return code;
		}else{
			
			return "incorrect";
		}
		
	}

	@Override
	public void forgottenLoginDataSecondStep(String email, User user) {
			
		Integer ID = user.getID();
		
		////////////////////	Password generator	///////////////////////

		Random random = new Random();
		Integer passwordSize = random.nextInt(4) + 6;
		String password = "";
		
		for(int i = 0; i < passwordSize; i++){
			int ASCIIcode = random.nextInt(122 - 48 + 1) + 48;
			char character = (char) ASCIIcode;
			
			password += String.valueOf(character);  
		}
		System.out.println(password);
	
		//////////////////////////////////////////////////////////////////	
		
		///////////////////////// Mail message //////////////////////////
		
		String newPassword = messages.get("mail.forgotAccount.newPassword");
		String title = messages.get("mail.forgotAccount.title2");
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("healthcenter1111@gmail.com");
		message.setTo(email);
		message.setSubject(title);
		message.setText( newPassword + " " + password);
		mailSender.send(message);
		
		/////////////////////////////////////////////////////////////////
		
		////////////////////// Update password //////////////////////////
		
		user.setPassword(password);			
		ArrayList<Integer> usersIDs = new ArrayList<Integer>();
		usersIDs.add(ID);
		userService.updateUsers(usersIDs, user);
		
		////////////////////////////////////////////////////////////////

	}
	
	
}
