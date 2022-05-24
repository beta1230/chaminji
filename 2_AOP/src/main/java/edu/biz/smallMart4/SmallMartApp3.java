package edu.biz.smallMart4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SmallMartApp3 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("small4.xml");
		
		SmallMart mart2 = ctx.getBean("mart", SmallMart.class);
		try {
			mart2.getProduct("전자제품");
			mart2.getProduct2("생필품");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
