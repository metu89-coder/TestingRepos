package testobject;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseobject.BaseClass;

public class SearchTest extends BaseClass{

	@Test(enabled=true, priority=1)
	public void sortAZTest()
	{
		getSearchObj().sortNameAZDropdown();

	}
	@Test(enabled=true, priority=2)
	public void sortZATest()
	{
		getSearchObj().sortNameZADropdown();

	}
	@Test(enabled=true, priority=3)
	public void sortpricehighTest()
	{
		getSearchObj().sortpricehighDropdown();

	}
	@Test(enabled=true, priority=4)
	public void sortpricelowTest()
	{
		getSearchObj().sortpricelowDropdown();

	}
	@Test(enabled=true, priority=5)
	public void sortco2highTest()
	{
		getSearchObj().sortco2highDropdown();

	}
	@Test(enabled=true, priority=6)
	public void sortco2lowTest()
	{
		getSearchObj().sortco2lowDropdown();

	}
	@Test(enabled=true, priority=7)
	public void sliderselectTest()
	
	{
		getSearchObj().sliderSelect(20,200);
		
	}
	//Searching in the search box and closing the search
	@Test(enabled=true, priority=8)
	public void searchBoxTest()
	{
		getSearchObj().searchBoxText("saw");
	}
	
}
