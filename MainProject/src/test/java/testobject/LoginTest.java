package testobject;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseobject.BaseClass;

public class LoginTest extends BaseClass{
	
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	@DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][] {
            {"customer2@practicesoftwaretesting.com", "welcome01", true},
            {"customer2@practicesoftwaretesting", "welcome01", false},
            {"customer2@practicesoftwaretesting.com", "pass3", false}
        };
    }
	
	@Test(dataProvider = "loginData",priority=1,enabled=true)
	public void validusersignInOutTest(String username, String password, boolean expectedResult)
	{
		
		getLoginObj().sigin();
		// Wait until the URL changes from the login page
		wait.until(ExpectedConditions.urlContains("auth/login"));
		System.out.println("CurrentUrl"+driver.getCurrentUrl());
		Assert.assertEquals("https://practicesoftwaretesting.com/auth/login", driver.getCurrentUrl());
		getLoginObj().signinForm(username, password);
		
		if (expectedResult) {
	        // Valid login
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        wait.until(ExpectedConditions.urlContains("account"));

	        Assert.assertTrue(driver.getCurrentUrl().contains("account"),
	                "Expected successful login, but failed for user: " + username);

	        // Logout after success
	        getLoginObj().signoutForm();

	    } else {
	        // Invalid login
	        wait.until(ExpectedConditions.urlContains("auth/login"));

	        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"),
	                "Expected login failure, but login succeeded for user: " + username);
	    }
	}
	
	

	@Test(enabled=true, priority=2)
	public void validnewsignInTest() throws InterruptedException
	{
		getLoginObj().sigin();
		// Wait until the URL changes from the login page
		wait.until(ExpectedConditions.urlContains("auth/login"));
		System.out.println("CurrentUrl"+driver.getCurrentUrl());
		Assert.assertEquals("https://practicesoftwaretesting.com/auth/login", driver.getCurrentUrl());
		getLoginObj().registerNewuser();
		getLoginObj().enterName("Mettu","jacob");
		getLoginObj().enterDob("1993-02-11");
		getLoginObj().enterStreet("Kapsolas");
		getLoginObj().enterPostalcode(6789);
		getLoginObj().enterCities("Larnaca");
		getLoginObj().enterStates("Larnaca"); 
		getLoginObj().enterCountry("Cyprus");
		getLoginObj().enterPhonenum(3455555);
		getLoginObj().enterEmail("metu89@gmail.com");
		
		getLoginObj().enterPasswd("Welcome014#");
		getLoginObj().newregisterSubmit();
		// wait for a physical element on the Login page
	    wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.urlContains("auth/login"));
		Assert.assertEquals("https://practicesoftwaretesting.com/auth/login", driver.getCurrentUrl());

	}
	//Invalid dob
	@Test(enabled=true,priority=3)
	public void invalidnewsignInTest()
	{
		getLoginObj().sigin();
		// Wait until the URL changes from the login page
		wait.until(ExpectedConditions.urlContains("auth/login"));
		Assert.assertEquals("https://practicesoftwaretesting.com/auth/login", driver.getCurrentUrl());
		getLoginObj().registerNewuser();
		getLoginObj().enterName("Mettu","jacob");
		getLoginObj().enterDob("1993-022-11");
		getLoginObj().enterStreet("Kapsolas");
		getLoginObj().enterPostalcode(6789);
		getLoginObj().enterCities("Larnaca");
		getLoginObj().enterStates("Larnaca");
		getLoginObj().enterCountry("Cyprus");
		getLoginObj().enterPhonenum(3455555);
		getLoginObj().enterEmail("metu89@gmail.com");
		
		getLoginObj().enterPasswd("Welcome014#");
		getLoginObj().newregisterSubmit();
		//New page of login is not loaded
		//  wait for a physical element on the Login page
	    wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.urlContains("auth/register"));
		Assert.assertEquals("https://practicesoftwaretesting.com/auth/register", driver.getCurrentUrl());

	}
	@Test(enabled=true,priority=4)
	public void validadminsignInOutTest()
	{
		getLoginObj().sigin();
		// Wait until the URL changes from the login page
		wait.until(ExpectedConditions.urlContains("auth/login"));
		Assert.assertEquals("https://practicesoftwaretesting.com/auth/login", driver.getCurrentUrl());
		getLoginObj().signinForm("admin@practicesoftwaretesting.com", "welcome01");
		
		// INSTEAD OF Thread.sleep, wait for a physical element on the Login page
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		//Admin login leads to a dashboard and admin can edit/delete users
		wait.until(ExpectedConditions.urlContains("admin/dashboard"));
		Assert.assertEquals("https://practicesoftwaretesting.com/admin/dashboard", driver.getCurrentUrl());
		
		//sign out
		getLoginObj().regsignoutForm();
	}
	
	@Test(enabled=true,priority=5)
	public void invalidpassadminsignInTest()
	{
		getLoginObj().sigin();
		// Wait until the URL changes from the login page
		wait.until(ExpectedConditions.urlContains("auth/login"));
		Assert.assertEquals("https://practicesoftwaretesting.com/auth/login", driver.getCurrentUrl());
		getLoginObj().signinForm("admin@practicesoftwaretesting.com", "welcome0123");
		Assert.assertEquals("Invalid email or password", getLoginObj().signinErrMess());
	}
	

	//validates Forgot password link
	@Test(enabled=true,priority=6)
	public void forgetyourPassTest()
	{
		getLoginObj().sigin();
		getLoginObj().forgotpassword();
		Assert.assertEquals("https://practicesoftwaretesting.com/auth/forgot-password", driver.getCurrentUrl());
		// Sending the email for set new password
		getLoginObj().setnewPassword("metu89@gmail.com");
		getLoginObj().setnewPasswordButton();
		//New page should be loaded for password setting Which is not happening
	}
	
	//To delete a user
	@Test(dependsOnMethods = {"validnewsignInTest"}, enabled=true,priority=7)
	public void deleteUserTest()
	{
		getLoginObj().sigin();
		// Wait until the URL changes from the login page
		wait.until(ExpectedConditions.urlContains("auth/login"));
		Assert.assertEquals("https://practicesoftwaretesting.com/auth/login", driver.getCurrentUrl());
		getLoginObj().signinForm("admin@practicesoftwaretesting.com", "welcome01");
		
		//Admin login leads to a dashboard and admin can edit/delete users
		wait.until(ExpectedConditions.urlContains("admin/dashboard"));
		Assert.assertEquals("https://practicesoftwaretesting.com/admin/dashboard", driver.getCurrentUrl());
		
		//Admin select for user menu for deleting the user
		getLoginObj().UserSearch("Mettu");
		//Hard assert
		Assert.assertTrue(getLoginObj().isUserDisplayed("Mettu"), "User 'Mettu' was not found in the display list.");
		//deleting the user
		getLoginObj().userDelete("Mettu");
		
	}
	
	@Test(enabled=true,priority=8)
	public void validusersignInPagesTest()
	{
		
		getLoginObj().sigin();
		// Wait until the URL changes from the login page
		wait.until(ExpectedConditions.urlContains("auth/login"));
		System.out.println("CurrentUrl"+driver.getCurrentUrl());
		Assert.assertEquals("https://practicesoftwaretesting.com/auth/login", driver.getCurrentUrl());
		getLoginObj().signinForm("customer@practicesoftwaretesting.com", "welcome01");
		
		//Testing My account page
		wait.until(ExpectedConditions.urlContains("account"));
		Assert.assertEquals("https://practicesoftwaretesting.com/account", driver.getCurrentUrl());
		//testing My favourites page

		getLoginObj().myfavouritePage();
		Assert.assertEquals("https://practicesoftwaretesting.com/account/favorites", driver.getCurrentUrl());
		//testing My profile page

		getLoginObj().myprofilePage();
		Assert.assertEquals("https://practicesoftwaretesting.com/account/profile", driver.getCurrentUrl());
		//testing My invoices page
		getLoginObj().myinvoicePage();
		Assert.assertEquals("https://practicesoftwaretesting.com/account/invoices", driver.getCurrentUrl());
		//testing My messages page
		getLoginObj().mymessagesPage();
		Assert.assertEquals("https://practicesoftwaretesting.com/account/messages", driver.getCurrentUrl());
	}
	//Password weak
}
