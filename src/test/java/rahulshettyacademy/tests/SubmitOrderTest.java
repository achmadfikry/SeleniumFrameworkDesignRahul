package rahulshettyacademy.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.*;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";

    @Test
    public void SubmitOrderTest() throws InterruptedException, IOException {
        ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty123@yopmail.com", "Password123!");

        List<WebElement> products = productCatalogue.getProductList();

        productCatalogue.addProductToCart(productName);

        CartPage cartPage = productCatalogue.goToCartPage();

        //css selector: .cartSection h3 -> parent to child
        //xpath: //*[@class='cartSection']/h3 -> parent to child

        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Indonesia");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
    }

    //To verify ZARA COAT 3 is displaying in orders page
    @Test(dependsOnMethods = {"SubmitOrderTest"})
    public void OrderHistoryTest(){
        ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty123@yopmail.com", "Password123!");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }
}
