package selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SeleniumTest2 {
	private WebDriver driver;
	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "D:/devetools/chromedriver_win32/chromedriver.exe";
	
	private String base_url;
	
	public SeleniumTest2() {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
		driver = new ChromeDriver();
//		base_url = "http://www.naver.com";
		base_url = "https://www.yogiyo.co.kr/mobile/#/%EC%84%9C%EC%9A%B8%ED%8A%B9%EB%B3%84%EC%8B%9C/150041/%EC%B9%98%ED%82%A8/";
//		base_url = "https://www.daangn.com/hot_articles";
	}
	
	public void crawl() throws Exception {
		driver.get(base_url);
		driver.findElement(By.name("address_input")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.linkText("현재 위치로 설정합니다.")).click();
		Thread.sleep(2000);
//		System.out.println(driver.getPageSource());
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,10240)", "");
		Thread.sleep(2000);

		executor.executeScript("window.scrollBy(0,10240)", "");
		Thread.sleep(2000);
		
		executor.executeScript("window.scrollBy(0,10240)", "");
		Thread.sleep(2000);
		
		WebElement clsContent = driver.findElement(By.id("content")).findElements(By.cssSelector(".content > div")).get(3);
		List<WebElement> infos = clsContent.findElements(By.cssSelector(".restaurant-list > div table .restaurants-info"));
		for(WebElement el : infos) {
			System.out.println(el.getText());
		}
	}
	
	public static void main(String[] args) throws Exception {
		new SeleniumTest2().crawl();
	}
}
