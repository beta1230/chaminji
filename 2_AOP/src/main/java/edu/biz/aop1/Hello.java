package edu.biz.aop1;

//target
public class Hello implements HelloInter {
	// join point
	public void sayHello() {
		System.out.println("안녕");
	}
}
