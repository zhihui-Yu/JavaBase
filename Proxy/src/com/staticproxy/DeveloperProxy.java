package com.staticproxy;

import com.base.IDeveloper;

/**
 * ¥˙¿Ì¿‡
 * @author listener
 *
 */
public class DeveloperProxy implements IDeveloper {
	private IDeveloper developer;
	
	public DeveloperProxy(IDeveloper developer) {
		super();
		this.developer = developer;
	}


	@Override
	public void writeCode() {
		System.out.println("static : Write documentation...");
		this.developer.writeCode();
	}

}
