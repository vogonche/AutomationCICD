package jovicakuresevic.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import jovicakuresevic.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}


	@FindBy(css=".cartSection h3")
	List<WebElement> cartChechoutProducts;
	@FindBy(xpath  ="//button[contains(text(), 'Checkout')]")
	WebElement chechOutButton;
	
	By productsBy = By.cssSelector(".mb3");
	By addToChart = By.cssSelector(".card-body button:last-of-type");	
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList()
	{
		return cartChechoutProducts;
	}

	public Boolean VerifyProductDisplay(String productName)
	{
		boolean match = cartChechoutProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;

	}

	public CheckOutPage goToCheckoutPage() {
		
		chechOutButton.click();
		
		return new CheckOutPage(driver);
		
	}
	
	
}	