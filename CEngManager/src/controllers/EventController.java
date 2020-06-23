package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import models.Category;
import models.Event;
import models.Subscriber;
import repositories.EventRepository;
import repositories.SubscriberRepository;

public class EventController{

	private EventRepository eventRepository;
	private SubscriberRepository subscriberRepository;

	public EventController() {
		this.eventRepository = new EventRepository();
		this.subscriberRepository = new SubscriberRepository();
	}
	
	public boolean sendEventsToSubscribers(List<Category> categories, String eventTitles) {
		boolean flag = true;

		Event event = eventRepository.selectEvent(eventTitles);
		if (eventSenderHelper(categories, event))
			flag = true;
		return flag;
	}
	
	private boolean eventSenderHelper(List<Category> categories, Event event) {
		boolean flag = true;
		List<Subscriber> subscriberList = new ArrayList<>();
		for (Category category: categories) {
			subscriberList.addAll(subscriberRepository.selectSpecificCategorySubscribers(category));
		}
		List<String> emailList = new ArrayList<String>();
		for(Subscriber subscriber:subscriberList) {
			emailList.add(subscriber.getEmail());
		}
				
		Properties properties=new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "mail.iyte.edu.tr");
		properties.put("mail.smtp.port", "587");
		
		String myAccount="bentchamalam@std.iyte.edu.tr";
		String passw="Benmalik-1";
		
		Session session=Session.getInstance(properties,new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccount,passw);
			}
		});
		
		for(String emails:emailList) {
			Message message=prepareMessage(session,myAccount,emails,event.getContent(),event.getTitle());
			try {
				Transport.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
		}
		return flag;
		
	}
	private static Message prepareMessage(Session session,String myAccountEmail,String recipient,String content,String title) {
		Message message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
			message.setSubject(title);
			message.setText(content);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return message;
	}

}
