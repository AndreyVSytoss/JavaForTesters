package com.example.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.input.XmlStreamReader;

import com.thoughtworks.xstream.XStream;

public class ConctactDataGenerator {

	public static void main(String[] args) throws IOException {
		if (args.length < 3) {
			System.out.println("Please specify parameters: <amount of test data> <file> <format>");
			return;
		}
		
		int amount = Integer.parseInt(args[0]);
		File file = new File(args[1]);
		String format = args[2];
		
		if (file.exists()){
			System.out.println("File exists, please remove it manually:" + file);
			return;
		}
		
		List<ContactData> contacts = generateRandomContacts(amount);
		if ("csv".equals(format)){
			saveContactsToCsvFile(contacts, file);
		} else if ("xml".equals(format)){
			saveContactsToXmlFile(contacts, file);
		} else {
			System.out.println("Unknown format" + format);
			return;	
		}
	}

	private static void saveContactsToXmlFile(List<ContactData> contacts, File file) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("contact", ContactData.class);
		String xml = xstream.toXML(contacts);
		FileWriter writer = new FileWriter(file);
		writer.write(xml);
		writer.close();
	}
	
	public static List<ContactData> loadContactsFromXmlFile(File file) {
		XStream xstream = new XStream();
		xstream.alias("contact", ContactData.class);
		return (List<ContactData>) xstream.fromXML(file);
	}

	private static void saveContactsToCsvFile(List<ContactData> contacts, File file) throws IOException {
		FileWriter writer = new FileWriter(file);
		for (ContactData contact : contacts) {
			writer.write(contact.getFirstname() + "," + contact.getLastname() + "," + contact.getEmail() + ","
			+ contact.getMobilenumb() + "," + contact.getHomenumb() + "," + contact.getSecondpfone() + ","
			+ contact.getWorknumb() + "," + contact.getAddress()  + "," + contact.getAddress2() + ","
			+ contact.getEmail2() + "," + contact.getBmonth() + "," + contact.getBday() + "," + contact.getByear() + ",!" + "\n");
		}
		writer.close();
	}
	

	public static List<ContactData> loadContactsFromCsvFile(File file) throws IOException {
		List<ContactData> list = new ArrayList<ContactData>();
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = bufferedReader.readLine();
		while(line != null){
			String[] part = line.split(",");
			ContactData contact = new ContactData()
			.withFirstName(part[0])
			.withLastName(part[1])
			.withEmail(part[2])
			.withMobileNumber(part[3])
			.withHomeNumber(part[4])
			.withSecondPfone(part[5])
			.withWorkNumber(part[6])
			.withAddress(part[7])
			.withaddress2(part[8])
			.withEmail2(part[9])
			.withBMonth(part[10])
			.withBDay(part[11])
			.withBYear(part[12]);
			list.add(contact);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return list;
	}


	public static List<ContactData> generateRandomContacts(int amount) {

		List<ContactData> list = new ArrayList<ContactData>();
		for (int i = 0; i < amount; i++){
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
			list.add(contact);
		}
		return list;
	}
	
	public static String generateRandomString() {
		Random rnd = new Random();
		if(rnd.nextInt(3) == 0){
			return "";
		} else {
			return "test" + rnd.nextInt();
		}
	}
	
	public static String generateRandomDay() {
		Random rnd = new Random();
		if(rnd.nextInt(3) == 0){
			return "-";
		} else {
			return Integer.toString((rnd.nextInt(31)+1));
		}
	}
	
	public static String generateRandomYear() {
		Random rnd = new Random();
		if(rnd.nextInt(3) == 0){
			return "";
		} else {
			return Integer.toString((rnd.nextInt(10000)));
		}
	}
	
	public static String generateRandomMonth(){
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
