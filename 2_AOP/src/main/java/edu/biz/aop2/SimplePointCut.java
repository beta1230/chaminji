package edu.biz.aop2;

import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class SimplePointCut extends StaticMethodMatcherPointcut {
	@Override
	public boolean matches(Method method, Class<?> targetClass) { // 조건식에 의한 필터링
		return method.getName().equals("one") && targetClass == First.class;
	}
	
}
