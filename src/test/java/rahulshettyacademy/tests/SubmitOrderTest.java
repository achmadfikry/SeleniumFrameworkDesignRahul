package rahulshettyacademy.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.*;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase","TestMoreThan1Group"})
    public void SubmitOrderTest(String email, String password, String productName) throws InterruptedException, IOException {
        ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);

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

    @DataProvider
    public Object[][] getData(){
        return new Object[][] {{"rahulshetty123@yopmail.com", "Password123!", "ZARA COAT 3"},{"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
    }
}
