package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import models.Category;
import models.Subscriber;
import repositories.UserRepository;

public class EmailController{

	private UserRepository userRepository;

	public EmailController() {
		this.setUserRepository(new UserRepository());
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean emailChecker(String emailString) {
		//TODO refactor this. Fix exception messages, try catches, add 2 at symbols exception..
		//if at symbol doesn't exist, if at is on the front of the email address, if the last dot comes before at symbol, if the dot does not exist,
		//if dot and at are touching(aliyalcin@.iyte.com) throws an exception.
		int atLocation = emailString.indexOf("@");
		boolean result = false;
			if(atLocation <= 0) {
			}
			else if(atLocation == 0){
			}
			else {
				int dotLocation = emailString.lastIndexOf(".");
				if(dotLocation < 0) {
				}
				else if(dotLocation < atLocation) {
				}
				else if(dotLocation == (atLocation + 1)) {
				}
				else {
					result = true;
				}
			}
			return result;
	}
	
	public boolean saveEmails(File fiel) {
		return true;
	}
	
	public List<Subscriber> readEmailsFromFile(String filepath) throws UnsupportedEncodingException, FileNotFoundException, IOException{	
		List<Subscriber> subscriberList = new ArrayList<Subscriber>();
		try (
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath),"UTF-8"));
			Reader reader = bufferedReader;
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
					.withHeader("Name", "Email", "Grade Year")
					.withIgnoreHeaderCase()
					.withTrim());
		) {
			for (CSVRecord csvRecord : csvParser) {
				String name = csvRecord.get("Name");
				String email = csvRecord.get("Email");
				Category gradeYear = Category.valueOf(csvRecord.get("Grade Year"));
				
				Subscriber newSubscriber = new Subscriber(name, email, gradeYear);
				subscriberList.add(newSubscriber);
				System.out.println("Record No - " + csvRecord.getRecordNumber());
				System.out.println("---------------");
				System.out.println("Name : " + name);
				System.out.println("Email : " + email);
				System.out.println("gradeYear : " + gradeYear);
				System.out.println("---------------\n\n");
			}
		}
		return subscriberList;
	}

}
