package demo.Ltst_maven_tstNG;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo {
	
	protected static WebDriver driver=null;
	
	@BeforeTest
	 //@BeforeSuite
	@Parameters("url")
	public void config(String URL) {
		System.out.println("starts...");
		
		WebDriverManager.chromedriver().setup();
		
		driver=new ChromeDriver();
		
		driver.get(URL);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));//Or (5,TimeUnit.SECONDS)
	}

	
	@DataProvider
	public String[] data() {
		
	//	Object emailArr[][]=new Object[][] {{"test1@gmail.com"},{"test1@gmail.com"},{"test1@gmail.com"} };
		//or
//		Object[][] emailArr=new Object[3][1];
//		emailArr[0][0]= "test1@gmail.com";
//		emailArr[1][0]= "test2@gmail.com";
//		emailArr[2][0]= "test3@gmail.com";
		
		String[] emailArr=new String[] {"test1@gmail.com","test2@gmail.com","test3@gmail.com"};
		
		return emailArr;
		
	}
	
	
	//@Parameters({"msg"})
	@Test(priority=1,dataProvider="data")
	public  void negativeTestcase(String Email) throws InterruptedException {
		// TODO Auto-generated method stub
	
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(Email);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		
		if(driver.findElement(By.xpath("//p[text()='Email address doesn’t exist. Please check again and retry']")).isDisplayed()==false)
		Assert.fail();
		else
			System.out.println("Test1 Passed...");
		
		WebElement email = driver.findElement(By.xpath("//input[@placeholder='Email']"));
		String existingText=email.getAttribute("value");
		for(int i=0;i<existingText.length();i++) {
			email.sendKeys(Keys.BACK_SPACE);
		}
		
		//Assert.assertTrue(true);
//		driver.findElement(By.xpath("//input[@placeholder='Email']")).clear();	
//		driver.findElement(By.xpath("//input[@placeholder='Password']")).clear();
		
	}
	
	
	@Parameters("msg2")
	@Test(priority=2)
	public  void positiveTestcase(String MSG) {
		// TODO Auto-generated method stub
		
		driver.findElement(By.xpath("//p[text()='Forgot Password?']")).click();
		
		//WebDriverWait waitObj = new WebDriverWait(driver,Duration.ofSeconds(5));
		//WebElement forgotPass = waitObj.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Forgot Password?']")));
		//forgotPass.click();

		if(driver.findElement(By.xpath("//p[text()='Enter registered email address']")).isDisplayed()){
			System.out.println(MSG);
			//Assert.assertTrue(true);
			}else{
			System.out.println("test is Failed!");
			Assert.fail();
			}
			
	}
	



	@AfterMethod   //***To get method name as ss image name
	public void tearDown(ITestResult result) throws IOException, InterruptedException {
		
		takeSS(result.getName()); //calling method to take ss

	}
	
	
	// This method will take SS
	  private void takeSS(String methodName) throws IOException, InterruptedException {
		
		TakesScreenshot takeSS = ((TakesScreenshot)driver);
		File ssFile = takeSS.getScreenshotAs(OutputType.FILE);
		
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		File destination = new File("screenshots/"+timestamp+methodName+".png");
		
		FileUtils.copyFile(ssFile, destination);
		
		System.out.println("SS taken\n");
		
		Thread.sleep(1000);
		
		
		
		}
		
	
	 
	
	//@AfterTest
	@AfterSuite
	public void end() {
		System.out.println("ends...");
	}
}
