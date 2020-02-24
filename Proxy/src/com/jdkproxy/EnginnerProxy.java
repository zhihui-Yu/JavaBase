package com.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理 需要实现invocationHandler
 * @author listener
 *
 */
public class EnginnerProxy implements InvocationHandler {
	Object obj;
	
	public Object bind(Object obj)
	{
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
		.getClass().getInterfaces(), this);
	}
	
	
	/**
	 * proxy : 对象的代理类
	 * method : 对象的方法
	 * args : 方法的参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//System.out.println("jdk : Enginner writes document");
		Object res = method.invoke(obj, args);
		return res;
	}

}
