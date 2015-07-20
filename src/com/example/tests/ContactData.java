package com.example.tests;

public class ContactData  implements Comparable<ContactData>{
	private String firstname;
	private String lastname;
	private String address;
	private String homenumb;
	private String mobilenumb;
	private String worknumb;
	private String email;
	private String email2;
	private String bday;
	private String bmonth;
	private String byear;
	private String address2;
	private String secondpfone;
	
	public ContactData() {}
	
	public ContactData(String firstname, String lastname, String address,
			String homenumb, String mobilenumb, String worknumb,
			String email, String email2, String bday, String bmonth,
			String byear, String address2, String secondpfone) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.homenumb = homenumb;
		this.mobilenumb = mobilenumb;
		this.worknumb = worknumb;
		this.email = email;
		this.email2 = email2;
		this.bday = bday;
		this.bmonth = bmonth;
		this.byear = byear;
		this.address2 = address2;
		this.secondpfone = secondpfone;
	}	

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getAddress() {
		return address;
	}

	public String getHomenumb() {
		return homenumb;
	}

	public String getMobilenumb() {
		return mobilenumb;
	}

	public String getWorknumb() {
		return worknumb;
	}

	public String getEmail() {
		return email;
	}

	public String getEmail2() {
		return email2;
	}

	public String getBday() {
		return bday;
	}

	public String getBmonth() {
		return bmonth;
	}

	public String getByear() {
		return byear;
	}

	public String getAddress2() {
		return address2;
	}

	public String getSecondpfone() {
		return secondpfone;
	}

	@Override
	public String toString() {
		return "ContactData [firstname=" + firstname + ", lastname=" + lastname
				+ ", homenumb=" + homenumb + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		int result = 1;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactData other = (ContactData) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false; 
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (homenumb == null) {
			if (other.homenumb != null)
				return false;
		} else if (!homenumb.equals(other.homenumb))
			return false; 
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		return true; 
	}

	@Override
	public int compareTo(ContactData other) {
		if (this.firstname.toLowerCase().equals(other.firstname.toLowerCase())){
			if (this.lastname.toLowerCase().equals(other.lastname.toLowerCase())){
				if (this.email.toLowerCase().equals(other.email.toLowerCase())){
						return this.homenumb.toLowerCase().compareTo(other.homenumb.toLowerCase());
				} else {
					return this.email.toLowerCase().compareTo(other.email.toLowerCase());
				}
			} else {
				return this.lastname.toLowerCase().compareTo(other.lastname.toLowerCase());
			}
		} else {
			return this.firstname.toLowerCase().compareTo(other.firstname.toLowerCase());
		} 
	}

	public ContactData withFirstName(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public ContactData withLastName(String lastname) {
		this.lastname = lastname;
		return this;
	}
	
	public ContactData withEmail(String email){
		this.email = email;
		return this;
	}

	public ContactData withMobileNumber(String mobilenumb) {
		this.mobilenumb = mobilenumb;
		return this;
	}

	public ContactData withHomeNumber(String homenumb) {
		this.homenumb = homenumb;
		return this;
	}

	public ContactData withSecondPfone(String secondpfone) {
		this.secondpfone = secondpfone;
		return this;
	}

	public ContactData withWorkNumber(String worknumb) {
		this.worknumb = worknumb;
		return this;
	}

	public ContactData withAddress(String address) {
		this.address = address;
		return this;
	}

	public ContactData withaddress2(String address2) {
		this.address2 = address2;
		return this;
	}

	public ContactData withEmail2(String email2) {
		this.email2 = email2;
		return this;
	}

	public ContactData withBMonth(String bmonth) {
		this.bmonth = bmonth;
		return this;
	}

	public ContactData withBDay(String bday) {
		this.bday = bday;
		return this;
	}

	public ContactData withBYear(String byear) {
		this.byear = byear;
		return this;
	}

}