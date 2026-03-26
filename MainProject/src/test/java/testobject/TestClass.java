package testobject;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import baseobject.BaseClass;
import pageobject.HomeClass;

public class TestClass extends BaseClass{
	
	@Test(enabled=true,priority=1)
	public void testDocumentationLink()
	{
		
		//saving original window handle
		String origWindow=driver.getWindowHandle();
		//Testing documention link
		getHomeObj().clickDocumentationLink();
		
		//Validate the documentation URL
		Set<String> newtabWin=driver.getWindowHandles();
		String expectedUrl="https://testsmith-io.github.io/practice-software-testing/#/";
		// Logic to switch: "If the key isn't for the origWindow, it must be the new one"
		for (String handle : newtabWin) {
		    if (!handle.equals(origWindow)) {
		        driver.switchTo().window(handle);
		    }
		}
		assertEquals(expectedUrl, driver.getCurrentUrl(),"Invalid URL");
		driver.close();
		driver.switchTo().window(origWindow);
		
	}	
	
	@Test(enabled=true,priority=2)
	public void testingLink()
	{
		getHomeObj().clickTestingLink();	
	}

	@Test(enabled=true,priority=3)
	public void bugLink()
	{
		//saving original window handle
		String origWindow1=driver.getWindowHandle();
		getHomeObj().clickBugLink();
		
		//Validate the documentation URL
		Set<String> newtabWin1=driver.getWindowHandles();
		String expectedUrl="https://with-bugs.practicesoftwaretesting.com/#/?bug-hunting=true";
		// Logic to switch: "If the key isn't for the origWindow, it must be the new one"
		for (String handle : newtabWin1) {
			if (!handle.equals(origWindow1)) {
				 driver.switchTo().window(handle);
			}
		}
		assertEquals(expectedUrl, driver.getCurrentUrl(),"Invalid URL");
		driver.close();
		driver.switchTo().window(origWindow1);
		
	}
	
	@Test(enabled=true,priority=4)
	public void homeLink()
	{
		getHomeObj().clickHomeLink();
		assertEquals("https://practicesoftwaretesting.com/", driver.getCurrentUrl());
	}
	
	@Test(enabled=true,priority=5)
	public void categoryLink()
	{
		getHomeObj().clickCategoriesLink();
		//Checks first dropdowndown category works fine
		assertEquals("https://practicesoftwaretesting.com/category/hand-tools", driver.getCurrentUrl());
		
	}
	@Test(enabled=true,priority=6)
	public void langLink()
	{
		getHomeObj().clickLan();
		List<WebElement> languages=getHomeObj().getLangOptions();
			
		for(WebElement element:languages)
		{	//printing all the languages
			System.out.printf("Languages are "+element.getText());
		}
	}
	
	@Test(enabled=true,priority=7)
	public void contactLink()
	
	{	
		//saving original window handle
		String origWindow2=driver.getWindowHandle();
		getHomeObj().clickcontact();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("/contact"));
	
		// Simple assertion
	    String currentUrl = driver.getCurrentUrl();
	    System.out.println("Current URL is: " + currentUrl);
	    assertEquals(currentUrl, "https://practicesoftwaretesting.com/contact", "URL mismatch!");
		
	    //Filling the form
	    getHomeObj().firstNameContactForm("Saira");
	    getHomeObj().lastNameContactForm("Saibu");
	    getHomeObj().emailContactForm("saira@gmail.com");
	    getHomeObj().subjectContactForm();
	    getHomeObj().messageContactForm("For any enquires required, we need to give the details");
	    getHomeObj().sendContactForm();
	   
		//navigate back to home
		driver.navigate().back();
	}
	
	@Test(enabled=true,priority=8)
	public void checkpagination()
	{
		getHomeObj().pagination();
	}
	
}
