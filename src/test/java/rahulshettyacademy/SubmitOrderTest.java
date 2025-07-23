package rahulshettyacademy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import rahulshettyacademy.pageobjects.*;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {
    public static void main(String[] args) throws InterruptedException {
        String productName = "ZARA COAT 3";
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty123@yopmail.com", "Password123!");

        List<WebElement> products = productCatalogue.getProductList();

        productCatalogue.addProductToCart(productName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

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
}
