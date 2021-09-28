package week4.day1.assignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CherCherFrames {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		WebElement frame1 = driver.findElement(By.id("frame1"));
		driver.switchTo().frame(frame1);
		driver.findElement(By.xpath("//b[@id='topic']/following::input")).sendKeys(" Text TopicFrame");
		WebElement framecheck = driver.findElement(By.id("frame3"));
		driver.switchTo().frame(framecheck);
		driver.findElement(By.id("a")).click();
		driver.switchTo().defaultContent();
		WebElement framedrop = driver.findElement(By.id("frame2"));
		driver.switchTo().frame(framedrop);
		WebElement drop = driver.findElement(By.id("animals"));
		Select drop1=new Select(drop);
		drop1.selectByIndex(3);
		driver.switchTo().defaultContent();
		driver.close();
		
	}

}
