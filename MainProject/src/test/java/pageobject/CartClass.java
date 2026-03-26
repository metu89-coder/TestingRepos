package pageobject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartClass {
	private WebDriver driver;
	private WebDriverWait wait;
	
	//locators
	private By plieritem=By.xpath("//img[@alt='Pliers']");
	private By addbutton=By.xpath("//button[@id='btn-add-to-cart']");
	private By messageadd=By.xpath("//div[@class='ng-tns-c969959638-0 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-success']");
	private By carticon=By.xpath("//span[@id='lblCartCount']");
	private By proceedcheckout=By.xpath("//div[@class='d-flex justify-content-between ng-star-inserted']//button[@type='button'][normalize-space()='Proceed to checkout']");
	private By proceed=By.xpath("//div[@class='float-end ng-star-inserted']//button[@type='button'][normalize-space()='Proceed to checkout']");
	private By streetname1=By.xpath("//input[@id='street']");
	private By cityname=By.xpath("//input[@id='city']");
	private By statename=By.xpath("//input[@id='state']");
	private By pinnum=By.xpath("//input[@id='postal_code']");
	private By proceedbutton=By.xpath("//app-address//div[@class='float-end']");
	private By payment=By.xpath("//select[@id='payment-method']");
	private By bankname=By.xpath("//input[@id='bank_name']");
	private By bankaccountname=By.xpath("//input[@id='account_name']");
	private By bankaccountnumber=By.xpath("//input[@id='account_number']");
	private By bankconfirm=By.xpath("//button[normalize-space()='Confirm']");
	private By bankmessage=By.cssSelector("[data-test='payment-success-message']");
	//private By bankdrop=By.xpath("")
	//constructors
	public CartClass(WebDriver driver)
	{
		this.driver=driver;
		wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	//Select pliers for adding the cart
	public String seladdItem()
	{
		WebElement mysel = wait.until(ExpectedConditions.presenceOfElementLocated(plieritem));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", mysel);
		WebElement additem = wait.until(ExpectedConditions.presenceOfElementLocated(addbutton));
		js.executeScript("arguments[0].click();", additem);

		WebElement addmess = wait.until(ExpectedConditions.presenceOfElementLocated(messageadd));
		return addmess.getText();
		
	}
	public void cartadd(String streetname,String city,String state,String post)
	{
		WebElement cart = wait.until(ExpectedConditions.presenceOfElementLocated(carticon));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", cart);
		WebElement checkout = wait.until(ExpectedConditions.presenceOfElementLocated(proceedcheckout));
		js.executeScript("arguments[0].click();", checkout);

		WebElement proceedlink=wait.until(ExpectedConditions.presenceOfElementLocated(proceed));
		js.executeScript("arguments[0].click();", proceedlink);

		//Adding billing details
		WebElement streetlink=wait.until(ExpectedConditions.presenceOfElementLocated(streetname1));
		js.executeScript("arguments[0].value = arguments[1];", streetlink,streetname);
		WebElement citylink=wait.until(ExpectedConditions.presenceOfElementLocated(cityname));
		js.executeScript("arguments[0].value = arguments[1];", citylink,city);
		WebElement statelink=wait.until(ExpectedConditions.presenceOfElementLocated(statename));
		js.executeScript("arguments[0].value = arguments[1];", statelink,state);
		WebElement postlink=wait.until(ExpectedConditions.presenceOfElementLocated(pinnum));
		//This is a fix to stay the values set
		String robustScript = 
			    "arguments[0].value = arguments[1]; " +
			    "arguments[0].dispatchEvent(new Event('input', { bubbles: true })); " +
			    "arguments[0].dispatchEvent(new Event('change', { bubbles: true })); " +
			    "arguments[0].blur();"; 

			js.executeScript(robustScript, postlink, post);
			
		//Clicking onto proccedto checkout
		WebElement proceedBtn = driver.findElement(By.cssSelector("button[data-test='proceed-3']"));
		// Force it to enable and then click it
		js.executeScript("arguments[0].removeAttribute('disabled'); arguments[0].click();", proceedBtn);
				
	}
	public String setbankdetails(String bank,String accountname,String accountnumber)
	{
		//Choosing payment option as bank transfer
		WebElement paymentbutton = driver.findElement(payment);
		paymentbutton.click();
		Select sel=new Select(paymentbutton);
		sel.selectByContainsVisibleText("Bank Transfer");
		driver.findElement(bankname).sendKeys(bank);
		driver.findElement(bankaccountname).sendKeys(accountname);
		driver.findElement(bankaccountnumber).sendKeys(accountnumber);
		driver.findElement(bankconfirm).click();
		WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(bankmessage));
		return (driver.findElement(bankmessage).getText());
		
	}
}
