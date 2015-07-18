package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.GroupData;

public class GroupHelper extends HelperBase {
	
	public GroupHelper (ApplicationManager manager) {
		super(manager);
	}

	public void createGroup(GroupData group) {
		manager.navigateTo().groupsPage();
		initGroupCreation();
	    fillGroupForm(group);
	    submitGroupCreation();
	    returnToGroupPage();
		
	}
	
	public List<GroupData> getGroups() {
		List<GroupData> groups = new ArrayList<GroupData>(); 
		
		manager.navigateTo().groupsPage();
		List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
		for (WebElement checkbox : checkboxes) {
			String title = checkbox.getAttribute("title");
			title.substring("Select (".length(), title.length() - ")".length());
			String name = title.substring("Select (".length(), title.length() - ")".length());
			groups.add(new GroupData().withName(name));
		}
		return groups;
	}
	
	public GroupHelper modifyGroup(int index, GroupData group) {
		initGroupModification(index);
		fillGroupForm(group);
		submitGroupModification();
		returnToGroupPage();	
		return this;
	}
	
	public GroupHelper deleteGroup(int index) {
		selectGroupByIndex(index);
		submitGroupDeletion();
		returnToGroupPage();
		return this;
	}
	
	//-----------------------------------------------------------------------------------------------
	public GroupHelper returnToGroupPage() {
		click(By.linkText("group page"));
		return this;
	}

	public GroupHelper fillGroupForm(GroupData group) {
		type(By.name("group_name"), group.getName());
		type(By.name("group_header"), group.getHeader());
		type(By.name("group_footer"), group.getFooter());
		return this;
	}

	public GroupHelper initGroupCreation() {
		click(By.name("new"));
		return this;
	}

	public GroupHelper submitGroupCreation() {
		click(By.name("submit"));
		return this;
	}

	public GroupHelper initGroupModification(int index) {
		selectGroupByIndex(index);
		click(By.name("edit"));		
		return this;
	}

	public GroupHelper submitGroupModification() {
		click(By.name("update"));		
		return this;
	}
	
	private void submitGroupDeletion() {
		click(By.name("delete"));
	}

}
