package rahulshettyacademy.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.*;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerse page")
    public void I_landed_on_Ecommerse_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) throws Throwable {
        productCatalogue = landingPage.loginApplication(username, password);
    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_Cart(String productName) throws Throwable {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_order(String productName) throws Throwable {
        cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Indonesia");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on Confirmation Page")
    public void message_is_displayed_Confirmation_page(String string) throws Throwable {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
    }
}
