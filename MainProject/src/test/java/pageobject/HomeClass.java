package pageobject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeClass {

	private WebDriver driver;
	private WebDriverWait wait;
	
	// Webelements locators
	private By documentationLink = By.xpath("//a[@class='text-white']");
	private By testingguideLink = By.xpath("//button[@class='testing-guide-btn']");
	private By bughuntingLink = By.xpath("//button[@class='bug-hunting-btn']");
	private By closesplitWin = By.xpath("//button[contains(text(),'✕ Close Split Screen')]");
	private By homeLink=By.xpath("//a[@class='nav-link active']");
	private By categoriesLink = By.cssSelector(".nav-link.dropdown-toggle.show");
    private By dropdownMenu = By.xpath("//a[normalize-space()='Categories']");
	private By handtools=By.xpath("//a[normalize-space()='Hand Tools']");
	private By powertools=By.xpath("//a[normalize-space()='Power Tools']");
	private By other=By.xpath("//a[normalize-space()='Power Tools']");
	private By specialtool=By.xpath("//a[normalize-space()='Special Tools']");
	private By rentals=By.xpath("//a[normalize-space()='Rentals']");
	private By langsel=By.xpath("//button[@id='language']");
	private By langdropdown=By.id("dropdown-animated");
	private By contact=By.xpath("//a[normalize-space()='Contact']");
	private By contactfirstname=By.xpath("//input[@id='first_name']");
	private By contactlastname=By.xpath("//input[@id='last_name']");
	private By emailaddr=By.xpath("//input[@id='email']");
	private By subjectsend=By.xpath("//select[@id='subject']");
	private By messagesend=By.xpath("//textarea[@id='message']");
	private By sendbuttoncontact=By.xpath("//input[@value='Send']");
	private By dropdownMenucategory = By.cssSelector("ul.dropdown-menu");
    private By categoryItems = By.cssSelector("ul.dropdown-menu li");
    private By handToolsLink = By.cssSelector("a[data-test='nav-hand-tools']");
	
	//Constructor
	public HomeClass(WebDriver driver)
	{
		this.driver=driver;
		wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	public void clickDocumentationLink()
	{
        driver.findElement(documentationLink).click();
    }
	public void clickTestingLink()
	{
        driver.findElement(testingguideLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(closesplitWin));

	}
	public void clickBugLink()
	{
        driver.findElement(bughuntingLink).click();
        

	}
	public void clickHomeLink()
	{
		//driver.findElement(homeLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(homeLink)).click();
      

		
	}
	public void clickCategoriesLink() {
        //driver.findElement(categoriesLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownMenu)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(handToolsLink)).click();

    }
	public  List<WebElement> getCategoryItems() {
        return driver.findElements(categoryItems);
    }

    public boolean isDropdownVisible() {
        return driver.findElement(dropdownMenu).isDisplayed();
    }
	
	public void clickLan() {
        //driver.findElement(categoriesLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(langsel)).click();
        driver.findElement(langdropdown);

    }
	public List<WebElement> getLangOptions()
	{
		return driver.findElements(langdropdown);
	}
	public void clickcontact()
	{
		wait.until(ExpectedConditions.elementToBeClickable(contact)).click();
		System.out.println("Inside Home class Current uRL is"+driver.getCurrentUrl());

	}
	public void firstNameContactForm(String name)
	{
		driver.findElement(contactfirstname).sendKeys(name);
	}
	public void lastNameContactForm(String last)
	{
		driver.findElement(contactlastname).sendKeys(last);
	}
	public void emailContactForm(String email)
	{
		driver.findElement(emailaddr).sendKeys(email);
	}
	public void subjectContactForm()
	{
		//locating dropdown
		WebElement dropdown=driver.findElement(subjectsend);
		Select dropdownmenu=new Select(dropdown);
		dropdownmenu.selectByIndex(1); //selecting first item in the dropdown
	}
	public void messageContactForm(String message)
	{
		driver.findElement(messagesend).sendKeys(message);
	}
	public void sendContactForm()
	{
		driver.findElement(sendbuttoncontact).click();
	}
	public void pagination()
	{
		driver.manage().window().maximize();
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		// Wait until the pagination UL is actually visible on the screen
		WebElement paginationUl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.pagination")));
		
		// Count page items (including Next/Prev)
        //List<WebElement> pages = elements.get(0).findElements(By.tagName("li"));
		List<WebElement> pages = paginationUl.findElements(By.tagName("li"));
		System.out.println("Success! Found " + pages.size() + " pagination items.");
        //System.out.println("Total pagination items: " + pages.size());
		
	}
	
	
}
