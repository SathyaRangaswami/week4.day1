package week4.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CarWale {
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	driver.get( "https://www.carwale.com");
	driver.findElement(By.xpath("//span[text()='Used']")).click();
	WebElement loc = driver.findElement(By.xpath("(//div[@class='o-brXWGL'])[2]//input"));
	loc.sendKeys("chennai");
	WebElement locstate = driver.findElement(By.xpath("//span[text()=', Tamil Nadu']"));
	Actions builder=new Actions(driver);
	builder.click(locstate).perform();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//div[text()='Choose your Budget']"));
	
	WebElement slidemin = driver.findElement(By.xpath("//button[@role='slider']"));
	WebElement slidemax = driver.findElement(By.xpath("//div[@class='o-cpnuEd o-efHQCX o-NBTwp o-brXWGL _3c7U4X ']/button[2]"));

	/*Point location = slidemin.getLocation();
	int x = location.getX();
	int y = location.getY();
	System.out.println("x&y :"+x+ "  " +y);	
	Point location2 = slidemax.getLocation();
	System.out.println("location of slider"+location +"      "+location2);
	int x2 = slidemax.getLocation().getX();
	int y2 = slidemax.getLocation().getY();
	System.out.println("MaxSlide value "+x2+" "+y2);
	
	//builder.dragAndDropBy(slidemin, 629, 397  ).perform();*/
	builder.clickAndHold(slidemin).moveToElement(slidemin, 70,0).click().perform();
	//3-50    4-70, 5-80,90  6-100 7-110 9-150
	String slidermin = slidemin.getAttribute("aria-valuenow");
	System.out.println("min value: "+slidermin );
	builder.clickAndHold(slidemax).moveToElement(slidemax,-130,0).click().perform();
	//16-=-70 17=-50 15 =-90 14=-100 13=-110 12=-1130
	String slidermax = slidemax.getAttribute("aria-valuenow");
	System.out.println("max value: "+slidermax);
	
	String minvalue = driver.findElement(By.xpath("//input[@placeholder='Min']")).getAttribute("value");
	System.out.println("checkbox Minimun value:"+minvalue);
	String maxvalue = driver.findElement(By.xpath("//input[@placeholder='Max']")).getAttribute("value");
	System.out.println("checkbox Minimun value:"+maxvalue);
	if(slidermin.equals(minvalue))
	{System.out.println("minium value is same");
	
	}else
		System.out.println("minimum value not same");
	if(slidermax.equals(maxvalue))
{System.out.println("maximun value is same");
	
	}else
		System.out.println("maxmium value not same");
		
	//driver.close();
	}
}
