package edu.biz.smallMart2;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import edu.biz.smallMart.advice.BeforeLog;

public class SmallMartApp2 {
	public static void main(String[] args) {
		SmallMart mart1 = new SmallMartImpl();		

		System.err.println("======================");
		ProxyFactory pf = new ProxyFactory();
		
		AspectJExpressionPointcut apc = new AspectJExpressionPointcut();
		apc.setExpression("execution(* *2(..))");
		
		pf.addAdvisors(new DefaultPointcutAdvisor(apc, new BeforeLog()));
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
