package jovicakuresevic.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import jovicakuresevic.TestComponents.BaseTest;
import jovicakuresevic.pageobjects.CartPage;
import jovicakuresevic.pageobjects.CheckOutPage;
import jovicakuresevic.pageobjects.ConfirmationPage;
import jovicakuresevic.pageobjects.LandingPage;
import jovicakuresevic.pageobjects.OrderPage;
import jovicakuresevic.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	/*
	 * @Test(dataProvider = "getDAta", groups = "Purchase") public void
	 * SubmitOrder(String email, String password, String productName) throws
	 * InterruptedException {
	 * 
	 * ProductCatalog productCatalogue = landingPage.LoginApplication(email,
	 * password);
	 * 
	 * List<WebElement> products = productCatalogue.getProductList();
	 * 
	 * productCatalogue.addProductToCart(productName);
	 * 
	 * CartPage cartPage = productCatalogue.goToCartPage();
	 * 
	 * Boolean match = cartPage.VerifyProductDisplay(productName);
	 * 
	 * Assert.assertTrue(match);
	 * 
	 * CheckOutPage checkOut = cartPage.goToCheckoutPage();
	 * checkOut.selectCountry(); ConfirmationPage confirmationPage =
	 * checkOut.SubmitOrder();
	 * 
	 * String confirmationMessage = confirmationPage.GetConfirmationMessage();
	 * Assert.assertTrue(confirmationMessage.
	 * equalsIgnoreCase("Thankyou for the order."));
	 * System.out.println("I am executed first"); driver.quit();
	 * 
	 * }
	 */

	@Test(dataProvider = "getData", groups = "Purchase")
	public void SubmitOrder(HashMap<String, String> input) throws InterruptedException {

		ProductCatalog productCatalogue = landingPage.LoginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(input.get("product"));

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));

		Assert.assertTrue(match);

		CheckOutPage checkOut = cartPage.goToCheckoutPage();
		checkOut.selectCountry();
		ConfirmationPage confirmationPage = checkOut.SubmitOrder();

		String confirmationMessage = confirmationPage.GetConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println("I am executed first");
		driver.quit();

	}

	// to verify ZARA COAT 3 is displaying in orders page
	@Test(dependsOnMethods = "SubmitOrder()")
	public void OrderHistoryTest() throws InterruptedException {

		ProductCatalog productCatalogue = landingPage.LoginApplication("jovica.kuresevic@gmail.com", "Vlahovic2000");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

		System.out.println("I am executed second");

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		/*
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
		 * "jovica.kuresevic@gmail.com"); map.put("password", "Vlahovic2000");
		 * map.put("product", "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "jovica.kuresevic@gmail.com"); map1.put("password",
		 * "Vlahovic2000"); map1.put("product", "ADIDAS ORIGINAL");
		 */

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\jovicakuresevic\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
