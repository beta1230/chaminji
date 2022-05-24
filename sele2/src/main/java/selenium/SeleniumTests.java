package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTests {
	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "D:/devetools/chromedriver_win32/chromedriver.exe";
	
	private WebDriver driver;
	private String base_url;
	
	public SeleniumTests() {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		driver = new ChromeDriver();
		base_url = "http://oe.moran.works/member/profile/mono";
	}
	
	public static void main(String[] args) throws Exception {
		new SeleniumTests().crawl();
	}
	
	public void crawl() throws Exception {
		driver.get(base_url);
		
		System.out.println("profile 페이지 소스");
		System.out.println(driver.getPageSource());
		
		Thread.sleep(3000);
		String score = driver.findElement(By.id("score")).getText();
		System.out.println("profile 페이지 score");
		System.out.println("mono의 점수는 " + score + "점 입니다.");

		String post = driver.findElement(By.id("post")).getText();
		System.out.println("profile 페이지 post");
		System.out.println("mono가 작성한 글은 " + post + "개 입니다.");
	
	}
	

}
