package com.example.tests;

import static com.example.tests.ConctactDataGenerator.generateRandomContacts;
import static com.example.tests.ConctactDataGenerator.loadContactsFromXmlFile;
import static com.example.tests.ConctactDataGenerator.loadContactsFromCsvFile;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class ContactCreationTest extends TestBase {
	
	@DataProvider
	public Iterator<Object[]> contactsFromFile() throws IOException {
		return wrapContactsForDataProvider(loadContactsFromXmlFile(new File("contacts.xml"))).iterator();
	}
	
	@Test(dataProvider = "contactsFromFile")
	public void createSomeContactTests(ContactData contact) throws Exception {
		
    //save old state
	SortedListOf<ContactData> oldcList = app.getModel().getContacts();
    
    //actions
    app.getContactHelper().createContact(contact);
    
    //save new state
    SortedListOf<ContactData> newcList = app.getModel().getContacts();
    List<ContactData> dblist =  app.getHibernateHelper().listContacts();
   
    //compare states
    assertThat(newcList, equalTo(oldcList));
	if (wantToCheck()){
    if ("yes".equals(app.getProperty("check.db"))) {
    	assertThat(app.getModel().getContacts(), equalTo(app.getHibernateHelper().listContacts()));	
    }

    if ("yes".equals(app.getProperty("check.ui"))) {
        assertThat(app.getModel().getContacts(), equalTo(app.getContactHelper().getUiContacts()));
    }
	}
  }
}
