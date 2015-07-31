package com.example.tests;

import static com.example.tests.ConctactDataGenerator.generateRandomContacts;
import static com.example.tests.ConctactDataGenerator.loadContactsFromFile;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class ContactCreationTest extends TestBase {
	
	@DataProvider
	public Iterator<Object[]> contactsFromFile() {
		return wrapContactsForDataProvider(loadContactsFromFile(new File("contacts.txt"))).iterator();
	}
	
	@Test(dataProvider = "contactsFromFile")
	public void createSomeContactTests(ContactData contact) throws Exception {
		
    //save old state
	SortedListOf<ContactData> oldcList = app.getContactHelper().getContacts();
    
    //actions
    app.getContactHelper().createContact(contact);
    
    //save new state
    SortedListOf<ContactData> newcList = app.getContactHelper().getContacts();
    
    //compare states
    assertThat(newcList, equalTo(oldcList.withAdded(contact)));
  }
}
