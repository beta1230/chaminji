package edu.biz.smallMart3;

import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class SmallMartPointCut extends StaticMethodMatcherPointcut {

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
//		return method.getName().equals("getProduct2") && targetClass == SmallMart.class;
		return method.getName().equals("getProduct2");
	}

}
