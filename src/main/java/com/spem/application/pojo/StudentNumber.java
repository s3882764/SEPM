package com.spem.application.pojo;

public class StudentNumber {

	public StudentNumber() {
	}

	public StudentNumber(String number) {
		this.number = number;
	}

	private String number;



	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "StudentNumber [number=" + number + "]";
	}
	
}
