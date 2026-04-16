package jovicakuresevic.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import jovicakuresevic.abstractcomponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css="input[placeholder='Select Country")
	WebElement enterCountry;
	
	@FindBy(css=".ta-results.list-group.ng-star-inserted")
	WebElement countriesElement;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement submitButton;
	//@FindBy(css="button.ta-item:nth-child(3)")
	//WebElement myCountry;
	
	
	By enteredCountryList = By.cssSelector(".ta-results.list-group.ng-star-inserted");
	By myCountry = By.cssSelector("button.ta-item:nth-child(3)");
	

	
	public void selectCountry() throws InterruptedException
	{
		enterCountry.sendKeys("Ind");
		
		Thread.sleep(5000);
		
		WaitElementToAppear(enteredCountryList);
		
		WebElement country = countriesElement;
		
		country.findElement(myCountry).click();
		
		SubmitOrder();
		
		//driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");

		//wait.until(ExpectedConditions
			//	.visibilityOfAllElementsLocatedBy(By.cssSelector("")));

		//WebElement country = driver.findElement(By.cssSelector(".ta-results.list-group.ng-star-inserted"));

		//country.findElement(By.cssSelector("button.ta-item:nth-child(3)")).click();

		//driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
		
	}
	
	public ConfirmationPage SubmitOrder()
	{
		submitButton.click();
		//driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
		return new ConfirmationPage(driver);
	}

}








