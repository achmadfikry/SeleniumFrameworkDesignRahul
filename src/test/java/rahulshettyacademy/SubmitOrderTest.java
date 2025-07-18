package rahulshettyacademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import rahulshettyacademy.pageobjeects.LandingPage;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        String productName = "ZARA COAT 3";
        driver.get("https://rahulshettyacademy.com/client");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.loginApplication("rahulshettyacademy@gmail.com", "Password123");

        if (driver.findElement(By.id("toast-container")).isDisplayed()){
            Assert.assertTrue(true);
        };
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//        products.stream().filter(product->product.getText().equals("ZARA COAT 3")); // it's not finish yet because we have to travel untill we find the right element
        WebElement selectedProduct = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        selectedProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        if (driver.findElement(By.id("toast-container")).isDisplayed()){
            Assert.assertTrue(true);
        };
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("[routerlink*=cart]")).click();

        //css selector: .cartSection h3 -> parent to child
        //xpath: //*[@class='cartSection']/h3 -> parent to child
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
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
