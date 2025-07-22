package rahulshettyacademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

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
        landingPage.loginApplication("rahulshetty123@yopmail.com", "Password123!");

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        List<WebElement> products = productCatalogue.getProductList();

        productCatalogue.addProductToCart(productName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        productCatalogue.goToCartPage();

        //css selector: .cartSection h3 -> parent to child
        //xpath: //*[@class='cartSection']/h3 -> parent to child
        CartPage cartPage = new CartPage(driver);
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"Indonesia").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("//button[contains(@class,'ta-item')][contains(.,'Indonesia')]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".hero-primary"))));
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));



    }
}
