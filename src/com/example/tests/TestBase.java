package com.example.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import static com.example.tests.GroupDataGenerator.generateRandomGroups;

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
		List<GroupData> groups = generateRandomGroups(5);
		List<Object[]> list = wrapGroupsForDataProvider(groups);
		return list.iterator();
	}
	
	private List<Object[]> wrapGroupsForDataProvider(List<GroupData> groups) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (GroupData group: groups) {
			list.add(new Object[]{group});
		}
		return list;
	}

	@DataProvider
	public Iterator<Object[]> randomValidContactGenerator(){
		List<Object[]> list = new ArrayList<Object[]>();
		for (int i = 0; i < 5; i++){
			ContactData contact = new ContactData()
			.withFirstName(generateRandomString())
			.withLastName(generateRandomString())
			.withEmail(generateRandomString())
			.withMobileNumber(generateRandomString())
			.withHomeNumber(generateRandomString())
			.withSecondPfone(generateRandomString())
			.withWorkNumber(generateRandomString())
			.withAddress(generateRandomString())
			.withaddress2(generateRandomString())
			.withEmail2(generateRandomString())
			.withBMonth(generateRandomMonth())
			.withBDay(generateRandomDay())
			.withBYear(generateRandomYear());
			
			if (contact.getEmail().equals("")) {
				contact.withEmail(contact.getEmail2());
				contact.withEmail2("");
			}
			
			if (contact.getHomenumb().equals("")){
				contact.withHomeNumber(contact.getMobilenumb());
				if(contact.getMobilenumb().equals("")){
					contact.withHomeNumber(contact.getWorknumb());
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
