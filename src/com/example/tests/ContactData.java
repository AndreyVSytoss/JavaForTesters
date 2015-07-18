package com.example.tests;

public class ContactData  implements Comparable<ContactData>{
	public String firstname;
	public String lastname;
	public String address;
	public String homenumb;
	public String mobilenumb;
	public String worknumb;
	public String email;
	public String email2;
	public String bday;
	public String bmonth;
	public String byear;
	public String address2;
	public String secondpfone;
	
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

}