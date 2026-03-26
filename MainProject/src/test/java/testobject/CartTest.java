package testobject;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseobject.BaseClass;

public class CartTest extends BaseClass{
	@Test
	public void addCartRegUser()
	{
		getLoginObj().sigin();
		// Wait until the URL changes from the login page
		wait.until(ExpectedConditions.urlContains("auth/login"));
		System.out.println("CurrentUrl"+driver.getCurrentUrl());
		Assert.assertEquals("https://practicesoftwaretesting.com/auth/login", driver.getCurrentUrl());
		getLoginObj().signinForm("customer2@practicesoftwaretesting.com", "welcome01");
		//wait until URL changes to account page
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.urlContains("account"));
		//Loading Homepage
		
		getHomeObj().clickHomeLink();
		assertEquals("https://practicesoftwaretesting.com/", driver.getCurrentUrl());
		// Selecting and add an item  to cart
		Assert.assertEquals(getCartObj().seladdItem(),"Product added to shopping cart.");
		//Adding to cart
		getCartObj().cartadd("Chayakudi","Austria","Goa","5433");
		Assert.assertEquals(getCartObj().setbankdetails("SBI", "School", "23421"),"Payment was successful");
		getLoginObj().signoutForm();
	}

}

