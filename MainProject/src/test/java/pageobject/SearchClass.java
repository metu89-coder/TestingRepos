package pageobject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SearchClass {
	

	private WebDriver driver;
	private WebDriverWait wait;
	//Locators
	private By sortdropdown=By.xpath("//select[@aria-label='sort']");
	private By priceproduct=By.cssSelector("[data-test='product-price']");
	private By minslider=By.cssSelector(".ngx-slider-pointer-min");
	private By maxslider=By.cssSelector(".ngx-slider-pointer-max");
	private By searchinput=By.id("search-query");
	//input[@id='search-query'] search-query
	private By serachbutton=By.xpath("//button[@type='submit']");
	private By searchclose=By.xpath("//button[@type='reset']");
	//Constructor
	public SearchClass(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void sortNameAZDropdown()
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		//WebElement dropdownElement = driver.findElement(sortdropdown);
		WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(sortdropdown));
		dropdownElement.click(); 
		Select sel = new Select(dropdownElement);
		sel.selectByVisibleText("Name (A - Z)"); 
		  
		//Wait for the page to start updating (a small sleep helps here)
		try { Thread.sleep(2000); } catch (InterruptedException e) {}
		  
		//((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		int count = driver.findElements(By.className("card-title")).size();
		List<String> actualTitles = new ArrayList<>();
		
		for (int i = 0; i < count; i++) 
		{
			try
			{
				WebElement freshElement = driver.findElements(By.className("card-title")).get(i);
				String text = freshElement.getAttribute("textContent").trim();
				if (!text.isEmpty())
				{
                actualTitles.add(text);
                System.out.println("Found Product: " + text);
                
				}
			}
			catch (StaleElementReferenceException e) {
				System.out.println("Skipping element " + i + " due to refresh...");
			}
		}

		// Verify the order
	    List<String> expectedTitles = new ArrayList<>(actualTitles);
	    Collections.sort(expectedTitles);
	    System.out.println("UI List: " + actualTitles);
	    System.out.println("Sorted List: " + expectedTitles);
		Assert.assertEquals(actualTitles, expectedTitles);   

	}

public void sortNameZADropdown()
{
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(sortdropdown));
	dropdownElement.click(); Select sel = new Select(dropdownElement);
	sel.selectByVisibleText("Name (Z - A)"); 
	  
	//Wait for the page to start updating (a small sleep helps here)
	try { Thread.sleep(2000); } catch (InterruptedException e) {}
	  
	//((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	int count = driver.findElements(By.className("card-title")).size();		
	List<String> actualTitles = new ArrayList<>();
	
	for (int i = 0; i < count; i++) 
	{
		try
		{
			WebElement freshElement = driver.findElements(By.className("card-title")).get(i);
			String text = freshElement.getAttribute("textContent").trim();
			if (!text.isEmpty())
			{
            actualTitles.add(text);
            System.out.println("Found Product: " + text);
            
			}
		}
		catch (StaleElementReferenceException e) {
			System.out.println("Skipping element " + i + " due to refresh...");
		}
	}

	//Verify the order
    List<String> expectedTitles = new ArrayList<>(actualTitles);
    Collections.sort(expectedTitles);
    Collections.reverse(expectedTitles);
    System.out.println("UI List: " + actualTitles);
    System.out.println("Sorted List: " + expectedTitles);
	Assert.assertEquals(actualTitles, expectedTitles);   

}

public void sortpricehighDropdown()
{
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(sortdropdown));
	dropdownElement.click(); Select sel = new Select(dropdownElement);
	sel.selectByVisibleText("Price (High - Low)"); 
	
	//Wait for the page to start updating (a small sleep helps here)
	try { Thread.sleep(4000); } catch (InterruptedException e) {}
		  
	//WebElement price = driver.findElement(priceproduct);	
	//String priceText = price.getText(); // Results in "$89.55"
	List<WebElement> priceElements = wait.until(
		    ExpectedConditions.presenceOfAllElementsLocatedBy(priceproduct)
		);

		// Now count will be > 0
		int count = priceElements.size();
		System.out.println("Total prices found: " + count);
		List<String> actualPrices = new ArrayList<>();
		for (int i = 0; i < count; i++) 
		{
			try
			{
				WebElement freshElement = driver.findElements(priceproduct).get(i);
				String text = freshElement.getAttribute("textContent").trim();
				if (!text.isEmpty())
				{
	            actualPrices.add(text);
	            System.out.println("Found Product: " + text);
	            
				}
			}
			catch (StaleElementReferenceException e) {
				System.out.println("Skipping element " + i + " due to refresh...");
			}
		}
		List<String> expectedPrices = new ArrayList<>(actualPrices);
	    Collections.sort(expectedPrices);
	    Collections.reverse(expectedPrices);
	    System.out.println("UI List: " + actualPrices);
	    System.out.println("Sorted List: " + expectedPrices);
		Assert.assertEquals(actualPrices,expectedPrices); 
		
}
public void sortpricelowDropdown()
{
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(sortdropdown));
	dropdownElement.click(); Select sel = new Select(dropdownElement);
	sel.selectByVisibleText("Price (Low - High)"); 
	
	//Wait for the page to start updating (a small sleep helps here)
	try { Thread.sleep(2000); } catch (InterruptedException e) {}
		  
	//WebElement price = driver.findElement(priceproduct);	
	//String priceText = price.getText(); // Results in "$89.55"
	List<WebElement> priceElements = wait.until(
		    ExpectedConditions.presenceOfAllElementsLocatedBy(priceproduct)
		);

		// Now count will be > 0
		int count = priceElements.size();
		System.out.println("Total prices found: " + count);
		List<String> actualPrices = new ArrayList<>();
		for (int i = 0; i < count; i++) 
		{
			try
			{
				WebElement freshElement = driver.findElements(priceproduct).get(i);
				String text = freshElement.getAttribute("textContent").trim();
				if (!text.isEmpty())
				{
	            actualPrices.add(text);
	            System.out.println("Found Product: " + text);
	            
				}
			}
			catch (StaleElementReferenceException e) {
				System.out.println("Skipping element " + i + " due to refresh...");
			}
		}
		List<String> expectedPrices = new ArrayList<>(actualPrices);
	    Collections.sort(expectedPrices);
	    System.out.println("UI List: " + actualPrices);
	    System.out.println("Sorted List: " + expectedPrices);
		Assert.assertEquals(actualPrices,expectedPrices); 
		
}
public void sortco2highDropdown()
{
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(sortdropdown));
	dropdownElement.click(); Select sel = new Select(dropdownElement);
	sel.selectByVisibleText("CO₂ Rating (A - E)"); 
}
public void sortco2lowDropdown()
{
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(sortdropdown));
	dropdownElement.click(); Select sel = new Select(dropdownElement);
	sel.selectByVisibleText("CO₂ Rating (E - A)"); 
}
public void sliderSelect(double targetmin,double targetmax)
{
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	WebElement minsliderelement = wait.until(ExpectedConditions.presenceOfElementLocated(minslider));
	WebElement maxsliderelement = wait.until(ExpectedConditions.presenceOfElementLocated(maxslider));
	//Adjust Minimum
    adjustToValue(minsliderelement, targetmin);

    //Adjust Maximum
    adjustToValue(maxsliderelement, targetmax);
    //Wait for the page to start updating 
  	try { Thread.sleep(3000); } catch (InterruptedException e) {}
  	//Wait some more
	wait = new WebDriverWait(driver, Duration.ofSeconds(50));

  	List<WebElement> priceElements = wait.until(
		    ExpectedConditions.presenceOfAllElementsLocatedBy(priceproduct));
  	
  	// Now count will be > 0
 	int count = priceElements.size();
 	System.out.println("Total prices found: " + count);
 	//Considering prices on 1 page only
 	List<String> actualPrices = new ArrayList<>();
 	for (int i = 0; i < count; i++) 
 	{
 		try
 		{
 			WebElement freshElement = driver.findElements(priceproduct).get(i);
 			String text = freshElement.getAttribute("textContent").trim();
 			if (!text.isEmpty())
 			{
 				// Remove currency symbols ($, ₹, etc.) and commas
 	            String cleanPrice = text.replaceAll("[^\\d.]", "");
 	            double priceValue = Double.parseDouble(cleanPrice);
 	           actualPrices.add(text);
 	            //The Comparison Logic
 	            if (priceValue >= targetmin && priceValue <= targetmax) {
 	                System.out.println("PASS: " + priceValue + " is within range [" + targetmin + "-" + targetmax + "]");
 	            } else {
 	                System.out.println("FAIL: " + priceValue + " is OUTSIDE range!");
 	                Assert.fail("Price " + priceValue + " is not within the slider bounds.");
 	            }
 				//actualPrices.add(text);
 				System.out.println("Found Product: " + text);
             
 			}
 		}
 		catch (StaleElementReferenceException e) {
 		System.out.println("Skipping element " + i + " due to refresh...");
 		}
 	}
}
private void adjustToValue(WebElement handle, double target)
{
    int current = Integer.parseInt(handle.getAttribute("aria-valuenow"));
    
    while (current != target) {
        if (current < target)
        {
        		//Setting right slider using keyboard function
            handle.sendKeys(Keys.ARROW_RIGHT);
        } else
        {
        		//Setting left slider using keyboard function
            handle.sendKeys(Keys.ARROW_LEFT);
        }
        current = Integer.parseInt(handle.getAttribute("aria-valuenow"));
    }
}
public void searchBoxText(String item)
{
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	JavascriptExecutor js=(JavascriptExecutor)driver;
	WebElement searchfield=wait.until(ExpectedConditions.presenceOfElementLocated(searchinput));
	searchfield.clear();
	js.executeScript(
	        "arguments[0].value = arguments[1];" +
	        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
	        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", 
	        searchfield, item
	    );

	//driver.findElement(searchinput).sendKeys(item); //finding searchTestfield
	driver.findElement(serachbutton).click();//Clicking search button
	
	//Wait for the page to start updating (a small sleep helps here)
	try { Thread.sleep(2000); } catch (InterruptedException e) {}
	wait = new WebDriverWait(driver, Duration.ofSeconds(50));
  
	//((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	int count = driver.findElements(By.className("card-title")).size();		
	List<String> actualTitles = new ArrayList<>();
	for (int i = 0; i < count; i++) 
	{
		try
		{
			WebElement freshElement = driver.findElements(By.className("card-title")).get(i);
			String text = freshElement.getAttribute("textContent").trim();
			if (!text.isEmpty() && (text.toLowerCase().contains(item.toLowerCase())))
			{
				 System.out.println("Match Found: " + text);
		         actualTitles.add(text);
			}
			else
			{
				 // If the match is mandatory for the test to pass:
				 Assert.fail("The product text '" + text + "' did not contain the expected keyword"+item);
			}
            
		}
		catch (StaleElementReferenceException e) {
			System.out.println("Skipping element " + i + " due to refresh...");
		}
		
	}
	//Verify the close button.
	driver.findElement(searchclose).click();
	wait = new WebDriverWait(driver, Duration.ofSeconds(50));

    
}
}
