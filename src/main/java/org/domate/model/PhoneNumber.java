package org.domate.model;

public class PhoneNumber {

	private String internationalPrefix;
	private String number;

	public PhoneNumber() {
	}

	public String getInternationalPrefix() {
		return internationalPrefix;
	}

	public void setInternationalPrefix(String internationalPrefix) {
		this.internationalPrefix = internationalPrefix;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
