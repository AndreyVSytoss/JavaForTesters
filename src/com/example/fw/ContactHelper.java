package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;

public class ContactHelper extends HelperBase {

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public void submitContactCreation() {
		click(By.name("submit"));
	}

	public void fillContactForm(ContactData contact) {
		type(By.name("lastname"), contact.firstname);
		type(By.name("firstname"), contact.lastname);
		type(By.name("address"), contact.address);
		type(By.name("home"), contact.homenumb);
		type(By.name("mobile"), contact.mobilenumb);
		type(By.name("work"), contact.worknumb);
		type(By.name("email"), contact.email);
		type(By.name("email2"), contact.email2);
		selectByText(By.name("bday"), contact.bday);
		selectByText(By.name("bmonth"), contact.bmonth);	  
	    type(By.name("byear"), contact.byear);
	    type(By.name("address2"), contact.address2);
	    type(By.name("phone2"), contact.secondpfone);
	}

	public void openAddContactPage() {
		click(By.linkText("add new"));
	}

	public void deleteContact(int index) {
		selectContactByIndex(index);
		click(By.xpath("//input[@value = 'Delete']"));
	}

	public void initContactModification(int index) {
		selectContactByIndex(index);
	}

	public void updateContact() {		
		click(By.xpath("//input[@value = 'Update']"));
	}
	
	public List<ContactData> getContacts(){
		int selectContact = 1;
		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> contactValues = driver.findElements(By.name("entry"));
		for (WebElement contactValue : contactValues) {
			ContactData contact = new ContactData();
			String firstName = contactValue.findElement(By.xpath("//tr[@name = 'entry']["+selectContact+"]/td[2]")).getText();
			String lastName = contactValue.findElement(By.xpath("//tr[@name = 'entry']["+selectContact+"]/td[3]")).getText();
			String email = contactValue.findElement(By.xpath("//tr[@name = 'entry']["+selectContact+"]/td[4]/a")).getText();
			String phoneNumber = contactValue.findElement(By.xpath("//tr[@name = 'entry']["+selectContact+"]/td[5]")).getText();
			contact.firstname = firstName;
			contact.lastname = lastName;
			contact.email = email;
			contact.homenumb = phoneNumber;
			contacts.add(contact);
			selectContact++;
		}
		return contacts;
	}
}