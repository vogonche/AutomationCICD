package jovicakuresevic.tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {

		String productName = "ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("jovica.kuresevic@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Vlahovic2000");

		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// Excplicit wait

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		
		List<WebElement> cartProducts =  driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.xpath("//button[contains(text(), 'Checkout')]")).click();
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results.list-group.ng-star-inserted")));
		
		WebElement country = driver.findElement(By.cssSelector(".ta-results.list-group.ng-star-inserted"));
		
		country.findElement(By.cssSelector("button.ta-item:nth-child(3)")).click();
		
		
		
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		//driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
		String result = driver.findElement(By.tagName("h1")).getText();
		
//		System.out.println();
		
		Assert.assertEquals(result, "THANKYOU FOR THE ORDER.");
		
		driver.quit();

	}

}
