package edu.biz.setter2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DevelopeApp {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("setter1.xml")
			.getBean("develope", Develope.class).coding();
		
	}
}
