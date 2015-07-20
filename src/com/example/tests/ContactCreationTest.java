package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class ContactCreationTest extends TestBase {
	
	@Test(dataProvider = "randomValidContactGenerator")
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
