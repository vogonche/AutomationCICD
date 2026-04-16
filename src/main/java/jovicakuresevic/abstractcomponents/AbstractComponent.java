package jovicakuresevic.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jovicakuresevic.pageobjects.CartPage;
import jovicakuresevic.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartButton;
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	public void WaitElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void WaitWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void WaitElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage goToCartPage()
	{
		cartButton.click();
		
		CartPage cartPage = new CartPage(driver);
		return cartPage;
		//driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
	}
	
	public OrderPage goToOrdersPage()
	{
		orderHeader.click();
		
		OrderPage OrderPage = new OrderPage(driver);
		return OrderPage;
		//driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
	}
	
}
