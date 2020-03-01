package com.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib 动态代理
 * @author listener
 *
 */
public class EnginnerCGLibProxy {
	Object obj;

	public Object bind(final Object target) {
		this.obj = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(obj.getClass());
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				//System.out.println("cglib : Enginner writes document");
				Object res = method.invoke(target, args);
				return res;
			}
		});
		return enhancer.create();
	}
}
