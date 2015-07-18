package com.example.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;


public class TestBase {
	
	protected ApplicationManager app;
	
	@BeforeTest
	public void setUp() throws Exception {
		app = new ApplicationManager();
		}

	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
		}

	@DataProvider
	public Iterator<Object[]> randomValidGroupGenerator(){
		List<Object[]> list = new ArrayList<Object[]>();
		for (int i = 0; i < 5; i++){
			GroupData group = new GroupData()
			.withName(generateRandomString())
			.withHeader(generateRandomString())
			.withFooter(generateRandomString());
			list.add(new Object[]{group});
		}
		// ...
		return list.iterator();
	}
	
	@DataProvider
	public Iterator<Object[]> randomValidContactGenerator(){
		List<Object[]> list = new ArrayList<Object[]>();
		for (int i = 0; i < 5; i++){
			ContactData contact = new ContactData();
			contact.firstname = generateRandomString();
			contact.lastname = generateRandomString();
			contact.email = generateRandomString();
			contact.mobilenumb = generateRandomString();
			contact.homenumb = generateRandomString();
			contact.secondpfone = generateRandomString();
			contact.worknumb = generateRandomString();
			contact.address = generateRandomString();
			contact.address2 = generateRandomString();
			contact.email2 = generateRandomString();
			contact.bmonth = generateRandomMonth();
			contact.bday = generateRandomDay();
			contact.byear = generateRandomYear();
			
			if (contact.email.equals("")) {
				contact.email = contact.email2;
				contact.email2 = "";
			}
			
			if (contact.homenumb.equals("")){
				contact.homenumb = contact.mobilenumb;
				if(contact.mobilenumb.equals("")){
					contact.homenumb = contact.worknumb;
				}
			}
			
			list.add(new Object[]{contact});
		}
		return list.iterator();
	}
	
	public String generateRandomString() {
		Random rnd = new Random();
		if(rnd.nextInt(3) == 0){
			return "";
		} else {
			return "test" + rnd.nextInt();
		}
	}
	
	public String generateRandomDay() {
		Random rnd = new Random();
		if(rnd.nextInt(3) == 0){
			return "-";
		} else {
			return Integer.toString((rnd.nextInt(31)+1));
		}
	}
	
	public String generateRandomYear() {
		Random rnd = new Random();
		if(rnd.nextInt(3) == 0){
			return "";
		} else {
			return Integer.toString((rnd.nextInt(10000)));
		}
	}
	
	public String generateRandomMonth(){
		Random rnd = new Random();
		String[] month = {"January", "February", "March", "April",
				"May", "June", "July", "August",
				"September", "October", "November", "December"};
		if(rnd.nextInt(3) == 0){
			return "-";
		} else {
			return month[rnd.nextInt(month.length)];
		}
	}
}
