package week4.day1.assignment;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesHtml {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("http://leafground.com/pages/frame.html");
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@src='default.html']"));
		driver.switchTo().frame(frame1);
		driver.findElement(By.id("Click")).click();
		WebElement snap = driver.findElement(By.id("Click"));
		File src1=snap.getScreenshotAs(OutputType.FILE);
		File dst=new File("./snaps/frame.png");
		FileUtils.copyFile(src1, dst);
		System.out.println("Get text 1st frame:"+snap.getText());
		//nested frame
        driver.switchTo().parentFrame();
		WebElement framenested1 = driver.findElement(By.xpath("//iframe[@src='page.html']"));
		driver.switchTo().frame(framenested1);
		WebElement framenested2 = driver.findElement(By.xpath("//iframe[@src='nested.html']"));
		driver.switchTo().frame(framenested2);
		driver.findElement(By.id("Click1")).click();
		driver.switchTo().defaultContent();
		//count frames
		List<WebElement> listFrames = driver.findElements(By.tagName("iframe"));
		int size = listFrames.size();
		System.out.println("Count of frames: "+size);
		driver.close();
		
	}

}
