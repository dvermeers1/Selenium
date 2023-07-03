package davidvermeer.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import davidvermeer.TestComponents.BaseTest;
import davidvermeer.pageobjects.CartPage;
import davidvermeer.pageobjects.ProductCat;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCat productCat = landingPage.loginApplication("dvermeers11112321@gmail.com", "Test112321234");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());


	}
	
	@Test
	public void ProductErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";
		ProductCat productCat = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement> products = productCat.getProductList();
		productCat.addProductToCart(productName);
		CartPage cartPage = productCat.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		

	}


}
