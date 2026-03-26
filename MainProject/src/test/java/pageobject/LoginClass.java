package pageobject;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class LoginClass {
	private WebDriver driver;
	private WebDriverWait wait;
	
	//Locators
	private By signin=By.cssSelector(".nav-link[data-test='nav-sign-in']");
	private By emailid=By.xpath("//input[@id='email']");
	private By pass=By.xpath("//input[@id='password']");
	private By loginbutton=By.xpath("//input[@value='Login']");
	private By accountsig=By.id("menu");
	private By alertbox1=By.cssSelector(".modal-footer .btn");
	private By register=By.xpath("//a[@aria-label='Register your account']");
	private By first=By.id("first_name");
	private By last=By.id("last_name");
	private By dt=By.id("dob");
	private By lane=By.id("street");
	private By post=By.id("postal_code");
	private By town=By.id("city");
	private By stat=By.id("state");
	private By count=By.id("country");
	private By phnumber=By.id("phone");
	private By emailaddr=By.id("email");
	private By passwd=By.id("password");
	private By newregister=By.xpath("//button[@type='submit']");
	private By signout=By.xpath("//a[normalize-space()='Sign out']");
	private By errormessage=By.xpath("//div[@class='help-block']");
	private By forgot=By.xpath("//a[@aria-label='Forgot your Password?']");
	private By forgotemail=By.xpath("//input[@id='email']");
	private By setnewpassword=By.xpath("//input[@value='Set New Password']");
	private By userslist=By.xpath("//a[normalize-space()='Users']");
	private By usersearchfield=By.xpath("//input[@type='text']");
	private By usersearchbutton=By.xpath("//button[@type='submit']");
	private By userTable = By.cssSelector("table");
	private By userRows = By.cssSelector("tbody tr");
	private By myfavourite = By.cssSelector("[data-test='nav-my-favorites']");

	public LoginClass(WebDriver driver)
	{
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void sigin()
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(signin));
		driver.findElement(signin).click();
		
	}
	public void signinForm(String username, String password)
	{
		driver.findElement(emailid).sendKeys(username);
		driver.findElement(pass).sendKeys(password);
		driver.findElement(loginbutton).click();
		
	}
	public String signinErrMess()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		WebElement errordisplay=wait.until(ExpectedConditions.visibilityOfElementLocated(errormessage));
		String errorMessagedisplay=(String)js.executeScript("return arguments[0].textContent;", errordisplay);
		//return errorMessagedisplay;
		return errorMessagedisplay;
	}
	public void signoutForm()
	{
		
		WebElement accoutmenu=wait.until(ExpectedConditions.visibilityOfElementLocated(accountsig));
		accoutmenu.click();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", accoutmenu);
		//Locate the "Sign out" link (using the 'a' tag) and click it
		WebElement signOutLink = wait.until(ExpectedConditions.presenceOfElementLocated(signout));	
		//JavascriptExecutor js1 = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", signOutLink);
	}
	public void regsignoutForm()
	{
		
		WebElement accoutmenu=wait.until(ExpectedConditions.visibilityOfElementLocated(accountsig));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", accoutmenu);
		
		WebElement signOutLink = wait.until(ExpectedConditions.presenceOfElementLocated(signout));	
		//JavascriptExecutor js1 = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", signOutLink);
		
		
	}
	public void registerNewuser()
	{
		WebElement regsiterlink=driver.findElement(register);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", regsiterlink);
		js.executeScript("arguments[0].click();", regsiterlink);
				
	}
	public void enterName(String firstname,String lastname)
	{
		driver.findElement(first).sendKeys(firstname);
		driver.findElement(last).sendKeys(lastname);
	}
	public void enterDob(String date)
	{
		driver.findElement(dt).sendKeys(date);
	}
	public void enterStreet(String place)
	{
		driver.findElement(lane).sendKeys(place);
	}
	public void enterPostalcode(int code)
	{
		driver.findElement(post).sendKeys(String.valueOf(code));
	}
	public void enterCities(String city)
	{
		driver.findElement(town).sendKeys(city);
	}
	public void enterStates(String state)
	{
		driver.findElement(stat).sendKeys(state);
	}
	public void enterCountry(String country)
	{
		WebElement element=driver.findElement(count);
		Select sel=new Select(element);
		sel.selectByVisibleText(country);
	}
	public void enterPhonenum(int phone)
	{
		driver.findElement(phnumber).sendKeys(String.valueOf(phone));
	}
	public void enterEmail(String email)
	{
		driver.findElement(emailaddr).sendKeys(email);
	}
	public void enterPasswd(String password)
	{
		driver.findElement(passwd).sendKeys(password);
	}
	public void newregisterSubmit()
	{
		WebElement newreglink = wait.until(ExpectedConditions.visibilityOfElementLocated(newregister));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", newreglink);
		js.executeScript("arguments[0].click();", newreglink);
	}
	public void forgotpassword()
	{
		WebElement forgotlink = wait.until(ExpectedConditions.visibilityOfElementLocated(forgot));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", forgotlink);
		js.executeScript("arguments[0].click();", forgotlink);
	}
	public void setnewPassword(String forgotemailaddress)
	{
		driver.findElement(forgotemail).sendKeys(forgotemailaddress);
	}
	public void setnewPasswordButton()
	{
		driver.findElement(setnewpassword).click();
	}
	public void UserSearch(String deleteuser)
	{
		
		WebElement accoutmenu=wait.until(ExpectedConditions.visibilityOfElementLocated(accountsig));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", accoutmenu);
		
		//Locate the "users" link and click it
		try 
		{
		    WebElement usersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(userslist));
			JavascriptExecutor js1=(JavascriptExecutor)driver;
			js1.executeScript("arguments[0].click();", usersLink);
			driver.findElement(usersearchfield).sendKeys(deleteuser);
			driver.findElement(usersearchbutton).click();
		} 
		catch (TimeoutException e) 
		{
		  
		    // Now try finding the link again
		    wait.until(ExpectedConditions.elementToBeClickable(userslist)).click();
		}
		
	}
	public boolean isUserDisplayed(String userName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		// Wait and capture the list once
		List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(userRows));
		//  Perform the logic on the captured list
		boolean isPresent = rows.stream()
		        .anyMatch(row -> {
		            try {
		            		
		                return row.getText().contains(userName);
		            } catch (StaleElementReferenceException e) {
		                // If the table is extremely volatile, find the list one last time here
		                return false; 
		            }
		        });

		System.out.println(isPresent);
		return isPresent;
        
    }
	public void userDelete(String userName)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// Ignore StaleElementReferenceException during the wait period
	    wait.ignoring(StaleElementReferenceException.class)
	        .until(d -> {
	            List<WebElement> rows = d.findElements(userRows);
	            for (WebElement row : rows) {
	                if (row.getText().contains(userName)) {
	                    // Find and click the button immediately while we have the fresh row
	                    row.findElement(By.className("btn-danger")).click();
	                    return true; 
	                }
	            }
	            return false;
	        });
	    
	}
	public void myfavouritePage()
	{
		
		WebElement accoutmenu=wait.until(ExpectedConditions.visibilityOfElementLocated(accountsig));
		accoutmenu.click();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", accoutmenu);
		//Locate the "My favourites" link (using the 'a' tag) and click it
		WebElement myFavLink = wait.until(ExpectedConditions.presenceOfElementLocated(
		        By.cssSelector("[data-test='nav-my-favorites']")));	
	    js.executeScript("arguments[0].click();", myFavLink);
	}
	public void myprofilePage()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement myProLink = wait.until(ExpectedConditions.presenceOfElementLocated(
		        By.cssSelector("[data-test='nav-my-profile']")));	
		//JavascriptExecutor js1 = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", myProLink);
	}
	public void myinvoicePage()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement myInvoLink = wait.until(ExpectedConditions.presenceOfElementLocated(
		        By.cssSelector("[data-test='nav-my-invoices']")));	
		//JavascriptExecutor js1 = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", myInvoLink);
	}
	public void mymessagesPage()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement mymessLink = wait.until(ExpectedConditions.presenceOfElementLocated(
		        By.cssSelector("[data-test='nav-my-messages']")));	
		//JavascriptExecutor js1 = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", mymessLink);
	}
	
	
}
