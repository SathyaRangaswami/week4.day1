package week4.day1.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowTC {
	
		public static void main(String[] args) throws InterruptedException {
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.get("https://dev67813.service-now.com");
			WebElement frameser = driver.findElement(By.id("gsft_main"));
			driver.switchTo().frame(frameser);
			driver.findElement(By.id("user_name")).sendKeys("admin");
			driver.findElement(By.id("user_password")).sendKeys("Alliswell@123");
			driver.findElement(By.id("sysverb_login")).click();
			driver.findElement(By.id("filter")).sendKeys("incident"+Keys.ENTER);
			driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
			driver.switchTo().frame("gsft_main");
			driver.findElement(By.id("sysverb_new")).click();
			
			//driver.findElement(By.id("gsft_main"));
			driver.findElement(By.id("sys_display.incident.caller_id"));
			driver.findElement(By.xpath("//span[@class='icon icon-search']")).click();
			Set<String> callerSet = driver.getWindowHandles();
			List<String> callerList=new ArrayList<String>(callerSet);
			driver.switchTo().window(callerList.get(1));
			String text2 = driver.findElement(By.xpath("//tr[@data-type='list2_row']/td[3]/a")).getText();
			System.out.println("Caller ID: "+text2);
			driver.findElement(By.xpath("//tr[@data-type='list2_row']/td[3]/a")).click();
			//Thread.sleep(2000);
			System.out.println(callerList.size());
			driver.switchTo().window(callerList.get(0));
			driver.switchTo().frame("gsft_main");
			
			String incidentNo = driver.findElement(By.id("incident.number")).getAttribute("value");
			System.out.println("Incident number"+incidentNo);
			driver.findElement(By.name("incident.short_description")).sendKeys("testleaf test case for example");
			
			driver.findElement(By.id("sysverb_insert")).click();
			driver.findElement(By.xpath("//div[@class='input-group']/input")).sendKeys(incidentNo+Keys.ENTER);
			String text = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
			System.out.println("Incident no from search :"+text);
			if(text.equals(incidentNo))
			{
				System.out.println("incident is submitted and verified");
			}
			else
			{
				System.out.println("incident is not available");
			}
			driver.switchTo().defaultContent();
			driver.close();

	}

}
