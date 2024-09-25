package demo.Ltst_maven_tstNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class passwrd extends Demo{
	
	@Override
	@Test(enabled = false)
    public void positiveTestcase(String MSG) {
        // Overriding Test1 from Class1 but disabling it
    }
	
	@Override
	@Test(enabled = false)
    public void negativeTestcase(String Email) {
        // Overriding Test1 from Class1 but disabling it
    }
	

	@Test(priority=1)
	void testPsswrd() {
//		new ChromeDriver();
		System.out.println("testPasswrd function starts...");
		try {
			
			driver.findElement(By.xpath("//div[@class='back-to-login']")).click();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Test@123");
		String EnteredPasswrd = driver.findElement(By.xpath("//input[@placeholder='Password']")).getAttribute("value");	
		System.out.println(EnteredPasswrd);
		
	}
	
	
	@Test(priority=2)
	void LogoImage() {
		System.out.println("LogoImage function starts...");
		
		WebElement logo = driver.findElement(By.xpath("//img[@alt='BoxPay']"));
		System.out.println(logo.getCssValue("width")); 
		//Or
		System.out.println(logo.getSize().getWidth()); 
		
		System.out.println(logo.getAttribute("src")); 
		
	}
	
	
	@Parameters("msg3")
	@Test(priority=3)
	void contactUsLink(String msg) {
		System.out.println("contactUsLink function starts...");
		
		driver.findElement(By.xpath("//a[text()='Contact BoxPay']")).click();
		System.out.println(msg); 
		
	}
	
	
}
