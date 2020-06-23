package controllers;

import java.util.List;

import models.Category;
import models.Subscriber;
import repositories.SubscriberRepository;

public class SubscriberController{

	private SubscriberRepository subscriberRepository;
	
	public SubscriberController() {
		this.subscriberRepository = new SubscriberRepository();
	}
	
	public boolean checkIfSubscriberHasNoNullFields(Subscriber subscriber) {
		boolean result = false;
		if(subscriber.getCategory() != null && subscriber.getEmail() != null &&  subscriber.getName() != null) {
			result = true;
		}
		return result;
	}
	
	public boolean checkIfSubscriberHasANullField(Subscriber subscriber) {
		boolean result = false;
		if(subscriber.getCategory() == null || subscriber.getEmail() == null || subscriber.getName() == null) {
			result = true;
		}
		return result;
	}

	public boolean addSubscriber(Subscriber subscriber) {
		boolean result = false;
		if(checkIfSubscriberHasNoNullFields(subscriber)) {
			result = subscriberRepository.insertSubscriber(subscriber);
		}
		return result;
	}
	
	public boolean addSubscribers(List<Subscriber> subscriberList) {
		boolean result = false;
		boolean nullValue = false;
		for(Subscriber subscriber:subscriberList) {
			if(checkIfSubscriberHasANullField(subscriber)) {
				nullValue = true;
			}
		}
		if(!nullValue) {
			result = subscriberRepository.insertSubscribers(subscriberList);
		}
		return result;
	}
	public boolean editSubscriberName(String subscriberEmail, String newName) {
		boolean result = false;
		Subscriber subscriber = subscriberRepository.selectSubscriber(subscriberEmail);
		subscriber.setName(newName);
		if(checkIfSubscriberHasNoNullFields(subscriber)) {
			result = subscriberRepository.updateSubscriber(subscriber);
		}
		return result;
	}
	
	public boolean editSubscriberEmail(String subscriberEmail, String newEmail) {
		boolean result = false;
		Subscriber subscriber = subscriberRepository.selectSubscriber(subscriberEmail);
		subscriber.setEmail(newEmail);
		if(checkIfSubscriberHasNoNullFields(subscriber)) {
			result = subscriberRepository.updateSubscriber(subscriber);
		}
		return result;
	}
	
	public boolean editSubscriberCategory(String subscriberEmail, Category newCategory) {
		boolean result = false;
		Subscriber subscriber = subscriberRepository.selectSubscriber(subscriberEmail);
		subscriber.setCategory(newCategory);
		if(checkIfSubscriberHasNoNullFields(subscriber)) {
			result = subscriberRepository.updateSubscriber(subscriber);
		}
		return result;
	}
	
	public boolean deleteSubscriber(String subscriberEmail) {
		boolean result = false;
		Subscriber subscriber = subscriberRepository.selectSubscriber(subscriberEmail);
		if(checkIfSubscriberHasNoNullFields(subscriber)) {
			result = subscriberRepository.deleteSubscriber(subscriberEmail);
		}
		return result;
	}
}
