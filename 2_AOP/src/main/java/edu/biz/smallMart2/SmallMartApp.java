package edu.biz.smallMart2;

import java.lang.reflect.Method;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import edu.biz.aop2.SimplePointCut;
import edu.biz.smallMart.advice.AfterRetLog;
import edu.biz.smallMart.advice.AroundLog;
import edu.biz.smallMart.advice.BeforeLog;
import edu.biz.smallMart.advice.ThrowsLog;

public class SmallMartApp {
	public static void main(String[] args) {
		SmallMart mart1 = new SmallMartImpl();		

		System.err.println("======================");

		ProxyFactory pf = new ProxyFactory();
//		pf.addAdvice(new BeforeLog());
//		pf.addAdvisor(new DefaultPointcutAdvisor(new StaticMethodMatcherPointcut() {
//			
//			@Override
//			public boolean matches(Method method, Class<?> targetClass) {
//				return method.getName().endsWith("2");
//			}
//		}, new BeforeLog()));
		
		pf.addAdvisors(new DefaultPointcutAdvisor(new SmallMartPointCut(), new BeforeLog()));
		pf.setTarget(mart1);
		
		SmallMart mart2 = (SmallMart)pf.getProxy();
		try {
			mart2.getProduct("전자제품");
			mart2.getProduct2("생필품");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
