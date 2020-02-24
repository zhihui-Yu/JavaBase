package com.test;

import com.base.Developer;
import com.base.IDeveloper;
import com.cglib.EnginnerCGLibProxy;
import com.jdkproxy.EnginnerProxy;
import com.staticproxy.DeveloperProxy;

public class Test {
	public static void main(String[] args) {
		// 原来需要指定人员 然后调用指定人员的方法
		IDeveloper jerry = new Developer("Jerry");
		jerry.writeCode();
		System.out.println("===============================");
		// 静态代理
		// 只要指定人员， 调方法的事情让代理类来做。
		IDeveloper jerryProxy = new DeveloperProxy(jerry);
		jerryProxy.writeCode();
		
		System.out.println("===============================");
		//jdk代理 必须实现invocationHandler 
		IDeveloper jerryProxy2 = (IDeveloper) new EnginnerProxy().bind(jerry);
		jerryProxy2.writeCode();
		
		System.out.println("===============================");
		//cglib 不能代理final类
		IDeveloper jerryProxy3 = (IDeveloper) new EnginnerCGLibProxy().bind(jerry);
		jerryProxy3.writeCode();
	}
}
