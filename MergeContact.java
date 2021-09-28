package week4.day1.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	    driver.get("http://leaftaps.com/opentaps/control/login");
	    driver.findElement(By.id("username")).sendKeys("demosalesmanager");
	    driver.findElement(By.id("password")).sendKeys("crmsfa");
	    driver.findElement(By.className("decorativeSubmit")).click();
	    driver.findElement(By.linkText("CRM/SFA")).click();
	    driver.findElement(By.linkText("Contacts")).click();
	    driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
	    driver.findElement(By.xpath("//img[@src='/images/fieldlookup.gif']")).click();
	    //window handling
	    Set<String> fromSet=driver.getWindowHandles();
	    List<String> fromList=new ArrayList<String>(fromSet); 	        
	    driver.switchTo().window(fromList.get(1));
	    System.out.println("Title of From :"+  driver.getTitle());
	   WebElement fromId = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a"));
	    System.out.println("frist contact from : "+fromId.getText());
	    fromId.click();
	    driver.switchTo().window(fromList.get(0));
	    driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click();
	    Set<String> toSet=driver.getWindowHandles();
	    List<String> toList=new ArrayList<String>(toSet); 	        
	    driver.switchTo().window(toList.get(1));
	    System.out.println("Title of To: "+ driver.getTitle());
	    WebElement toId = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[2]/a"));
	    System.out.println("Title from to:  "+toId.getText());
	    toId.click();
	    driver.switchTo().window(toList.get(0));
	    driver.findElement(By.xpath("//a[text()='Merge']")).click();
	    
	    //alert
	    Alert alert = driver.switchTo().alert();	
	    alert.accept();
	    String title = driver.getTitle();
	    System.out.println("Title :"+title);
	    if(title.contains("View Contact"))
	    {
	    	System.out.println("Title is verified");
	    }
	    else
	    	System.out.println("Title is not verified");
	   driver.close();
	    
	}
	
}
