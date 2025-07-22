package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) { //driver ini hanhya memiliki scope untuk method ini saja
        super(driver);
        //initialization
        this.driver = driver; //this.driver is belong to WebDriver driver
        PageFactory.initElements(driver, this); //That means this will trigger initializing all the elements. So the construction of this will be triggered when you call this method

    }

//            List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); //instead of using this, then using PageFactory

    //PageFactory
    @FindBy(css = ".mb-3")
    List<WebElement> products;
    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.id("toast-container");

    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement selectedProduct = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return selectedProduct;

    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }

}
