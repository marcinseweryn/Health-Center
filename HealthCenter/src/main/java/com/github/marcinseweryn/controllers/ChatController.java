package com.github.marcinseweryn.controllers;

import java.security.Principal;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.github.marcinseweryn.model.ChatMessage;
import com.github.marcinseweryn.model.User;
import com.github.marcinseweryn.service.UserService;

@Controller
public class ChatController {

	@Autowired
	private UserService userService;
	
	@MessageMapping("/message")
	@SendTo("/chat/messages")
	public ChatMessage getMessages(ChatMessage message, Principal principal) {
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		User user = userService.findUserByID(Integer.parseInt(principal.getName()));
		String messageFrom = user.getName() + " " + user.getSurname();
		
		message.setDate(currentDate.toString().substring(0, 19));
		message.setFrom(messageFrom); 
		System.out.println(message);
		return message;
	}
	
}