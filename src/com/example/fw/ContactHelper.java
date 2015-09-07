package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;
import com.example.utils.SortedListOf;

public class ContactHelper extends WebDriverHelperBase {

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public ContactHelper createContact(ContactData contact) {
	    openAddContactPage();
	    fillContactForm(contact);
	    submitContactCreation();
	    returnToHomePage();
	    rebuildCache();
	    return this;
	}
	
	public ContactHelper modifyContact(int index, ContactData contact) {
	    initContactModification(index);
	    fillContactForm(contact);
	    submitContactModification();
	    returnToHomePage();	
	    rebuildCache();
	    return this;
	}
	
	public ContactHelper deleteContact(int index) {
		selectContactByIndex(index);
		submitContactDeletion();
		returnToHomePage();
		rebuildCache();
		return this;
	}
	
	private SortedListOf<ContactData> cachedContacts;
	
	public SortedListOf<ContactData> getContacts(){
		if (cachedContacts == null){
			rebuildCache();
		} 
		return cachedContacts;
	}
	
	private void rebuildCache() {
			int selectContact = 1;
			cachedContacts = new SortedListOf<ContactData>();
			List<WebElement> contactValues = driver.findElements(By.name("entry"));
			for (WebElement contactValue : contactValues) {
				String firstName = contactValue.findElement(By.xpath("//tr[@name = 'entry']["+selectContact+"]/td[2]")).getText();
				String lastName = contactValue.findElement(By.xpath("//tr[@name = 'entry']["+selectContact+"]/td[3]")).getText();
				String email = contactValue.findElement(By.xpath("//tr[@name = 'entry']["+selectContact+"]/td[4]/a")).getText();
				String phoneNumber = contactValue.findElement(By.xpath("//tr[@name = 'entry']["+selectContact+"]/td[5]")).getText();
				
				cachedContacts.add(new ContactData().withFirstName(firstName).withLastName(lastName).withEmail(email).withHomeNumber(phoneNumber));
				selectContact++;
			}
	}
	//----------------------------------------------------------------------------------------
	public ContactHelper submitContactCreation() {
		click(By.name("submit"));
		cachedContacts = null;
		return this;
	}

	public void fillContactForm(ContactData contact) {
		type(By.name("lastname"), contact.getFirstname());
		type(By.name("firstname"), contact.getLastname());
		type(By.name("address"), contact.getAddress());
		type(By.name("home"), contact.getHomenumb());
		type(By.name("mobile"), contact.getMobilenumb());
		type(By.name("work"), contact.getWorknumb());
		type(By.name("email"), contact.getEmail());
		type(By.name("email2"), contact.getEmail2());
		selectByText(By.name("bday"), contact.getBday());
		selectByText(By.name("bmonth"), contact.getBmonth());	  
	    type(By.name("byear"), contact.getByear());
	    type(By.name("address2"), contact.getAddress2());
	    type(By.name("phone2"), contact.getSecondpfone());
	}

	public ContactHelper openAddContactPage() {
		click(By.linkText("add new"));
		return this;
	}

	private ContactHelper submitContactDeletion() {
		click(By.xpath("//input[@value = 'Delete']"));
		cachedContacts = null;
		return this;
	}

	public ContactHelper initContactModification(int index) {
		selectContactByIndex(index);
		return this;
	}

	public ContactHelper submitContactModification() {		
		click(By.xpath("//input[@value = 'Update']"));
		cachedContacts = null;
		return this;
	}
	
	public ContactHelper returnToHomePage() {
		click(By.linkText("home page"));
		return this;
	}
}