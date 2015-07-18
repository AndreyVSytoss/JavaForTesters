package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {
	
	@Test(dataProvider = "randomValidContactGenerator")
	public void createSomeContactTests(ContactData contact) throws Exception {
    app.navigateTo().mainPage();
    
    //save old state
    List<ContactData> oldcList = app.getContactHelper().getContacts();
    
    //actions
    app.getContactHelper().openAddContactPage();
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactCreation();
    app.navigateTo().returnToHomePage();
    
    //save new state
    List<ContactData> newcList = app.getContactHelper().getContacts();
    
    //compare states
    oldcList.add(contact);
    Collections.sort(oldcList);
    assertEquals(newcList, oldcList);
  }
}
