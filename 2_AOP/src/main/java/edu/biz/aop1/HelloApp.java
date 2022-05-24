package edu.biz.aop1;

import org.springframework.aop.framework.ProxyFactory;

public class HelloApp {
	public static void main(String[] args) {
		HelloInter hello1 = new Hello();
		hello1.sayHello();
		
		System.out.println("==================");

		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(new MsgDecorator());
		pf.addAdvice(new MsgDecorator());
		pf.setTarget(hello1);
		
		HelloInter hello2 = (HelloInter) pf.getProxy();
		
		hello2.sayHello();
		
		System.out.println("==================");
		System.out.println(hello1); // hello1.toString();
		System.out.println(String.format("%x", hello1.hashCode()));
		System.out.println(hello2);
		System.out.println(String.format("%x", hello2.hashCode()));
		System.out.println(hello1 == hello2);
		
	}

}
