package edu.biz.smallMart2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SmallMartApp3 {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("small2.xml");
		
//		SmallMart mart1 = ctx.getBean("mart1", SmallMart.class);
		SmallMart mart2 = ctx.getBean("mart2", SmallMart.class);
		try {
			mart2.getProduct("전자제품");
			mart2.getProduct2("생필품");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
