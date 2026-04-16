package jovicakuresevic.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jovicakuresevic.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	// Page Factory
	@FindBy(id = "userEmail")
	WebElement userEmail;

	// WebElement userPassword = driver.findElement(By.id("userPassword"));
	@FindBy(id = "userPassword")
	WebElement userPassword;

	// driver.findElement(By.id("login"))
	@FindBy(id = "login")
	WebElement loginButton;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public String getErrorMessage()
	{
		WaitWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public ProductCatalog LoginApplication(String email, String password) throws InterruptedException {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();

		ProductCatalog prod = new ProductCatalog(driver);
		return prod;

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
