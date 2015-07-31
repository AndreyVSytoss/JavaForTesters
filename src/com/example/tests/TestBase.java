package com.example.tests;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import static com.example.tests.GroupDataGenerator.generateRandomGroups;
import static com.example.tests.ConctactDataGenerator.generateRandomContacts;



import com.example.fw.ApplicationManager;


public class TestBase {
	
	protected ApplicationManager app;
	
	@BeforeTest
	public void setUp() throws Exception {
		Properties properties = new Properties();
		properties.load(new FileReader(new File("application.properties")));
		app = new ApplicationManager(properties);
		}

	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
		}
	
	protected List<Object[]> wrapGroupsForDataProvider(List<GroupData> groups) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (GroupData group: groups) {
			list.add(new Object[]{group});
		}
		return list;
	}

	@DataProvider
	public Iterator<Object[]> randomValidContactGenerator() {
		return wrapContactsForDataProvider(generateRandomContacts(5)).iterator();
	}

	public List<Object[]> wrapContactsForDataProvider(List<ContactData> contacts) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (ContactData contact : contacts) {
			list.add(new Object[]{contact});
		}
		return list;
	}
}
