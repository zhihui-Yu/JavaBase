package com.base;

public class Developer implements IDeveloper{
	private String name;
	
	public Developer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Developer(String name) {
		super();
		this.name = name;
	}

	@Override
	public void writeCode() {
		//System.out.println("Developer " + name + " writes code");
	}

}
