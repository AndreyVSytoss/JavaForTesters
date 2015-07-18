package com.example.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{

	public NavigationHelper(ApplicationManager manager) {
		super(manager);
	}

	public void groupsPage() {
		driver.findElement(By.linkText("groups")).click();
	}

	public void mainPage() {
		driver.get(manager.baseUrl + "/addressbookv4.1.4/addressbookv4.1.4/");
	}

	public void returnToHomePage() {
		driver.findElement(By.linkText("home page")).click();
	}

}
