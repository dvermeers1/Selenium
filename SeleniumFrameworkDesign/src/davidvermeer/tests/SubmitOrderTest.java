package davidvermeer.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import davidvermeer.TestComponents.BaseTest;
import davidvermeer.pageobjects.CartPage;
import davidvermeer.pageobjects.CheckoutPage;
import davidvermeer.pageobjects.ConfirmationPage;
import davidvermeer.pageobjects.LandingPage;
import davidvermeer.pageobjects.OrderPage;
import davidvermeer.pageobjects.ProductCat;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(String email, String password, String productName)
			throws IOException, InterruptedException {

		LandingPage landingPage = launchApplication();
		ProductCat productCat = landingPage.loginApplication(email, password);
		List<WebElement> products = productCat.getProductList();
		productCat.addProductToCart(productName);
		CartPage cartPage = productCat.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistroyTest() {
		ProductCat productCat = landingPage.loginApplication("dvermeers11@gmail.com", "Test1234");
		OrderPage orderPage = productCat.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));

	}

	@DataProvider
	public Object[][] getData() {

		return new Object[][] { { "dvermeers11@gmail.com", "Test1234", "ZARA COAT 3" },
				{ "shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL" } };

	}

}
