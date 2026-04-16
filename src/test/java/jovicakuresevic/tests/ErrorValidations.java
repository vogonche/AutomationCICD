package jovicakuresevic.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import jovicakuresevic.TestComponents.BaseTest;
import jovicakuresevic.TestComponents.Retry;
import jovicakuresevic.pageobjects.ProductCatalog;

public class ErrorValidations extends BaseTest {

	@Test (groups = {"Error handling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";

		// LandingPage landingPage = launchApplication();
		ProductCatalog productCatalogue = landingPage.LoginApplication("jovica.kuresevic@gmail.com", "Vlahovic200011");

		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.1");

	}

}
