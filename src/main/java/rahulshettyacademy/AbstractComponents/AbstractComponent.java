package rahulshettyacademy.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink*=cart]")
    WebElement cartHeader;
    @FindBy(css = "[routerlink*=myorders]")
    WebElement ordersHeader;

    public void waitForElementToAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToDisappear(WebElement element) throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.invisibilityOf(element));
        Thread.sleep(1000);
    }

    public CartPage goToCartPage(){
        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage goToOrdersPage(){
        ordersHeader.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }
}
