package baseobject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageobject.CartClass;
import pageobject.HomeClass;
import pageobject.LoginClass;
import pageobject.SearchClass;

public class BaseClass {
	protected WebDriver driver;
	protected WebDriverWait wait;
	private HomeClass homeObj;
	private LoginClass loginObj;
	private SearchClass searchObj;
	private CartClass cartObj;
	
	@BeforeMethod
	public void browserSetup()
	{
		driver=new ChromeDriver();
		driver.get("https://practicesoftwaretesting.com/");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	//This method is called for object creation
	public HomeClass getHomeObj()
	{
		return homeObj=new HomeClass(driver);
	}
	
	public LoginClass getLoginObj()
	{
		 return loginObj=new LoginClass(driver);
	}

	public SearchClass getSearchObj()
	{
		return searchObj=new SearchClass(driver);
	}
	public CartClass getCartObj()
	{
		
		return cartObj=new CartClass(driver);

	}
}
