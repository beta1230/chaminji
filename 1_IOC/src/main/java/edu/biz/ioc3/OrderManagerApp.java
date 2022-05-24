package edu.biz.ioc3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderManagerApp {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("ioc3.xml");
//		OrderManager manager = ctx.getBean("orderManager", OrderManager.class);
		OrderManager manager = (OrderManager) ctx.getBean("orderManager");
		OrderManager manager1 = (OrderManager) ctx.getBean("orderManager");
		OrderManager manager2 = (OrderManager) ctx.getBean("orderManager");
		OrderManager manager3 = (OrderManager) ctx.getBean("orderManager");
		OrderManager manager4 = (OrderManager) ctx.getBean("orderManager");
		manager.order();
		
		System.out.println(manager);
		System.out.println(manager1);
		System.out.println(manager2);
		System.out.println(manager3);
		System.out.println(manager4);
		
		Money money = ctx.getBean("money", Money.class);
		System.out.println(money.getAmount());
		System.out.println(ctx.getBean("money", Money.class));
		System.out.println(ctx.getBean("money", Money.class));
		System.out.println(ctx.getBean("money", Money.class));
		System.out.println(ctx.getBean("money", Money.class));
		
		ctx.close();
	}

}
