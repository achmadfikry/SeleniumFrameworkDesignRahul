package rahulshettyacademy.pageobjeects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) { //driver ini hanhya memiliki scope untuk method ini saja
        //initialization
        this.driver = driver; //this.driver is belong to WebDriver driver
        PageFactory.initElements(driver, this); //That means this will trigger initializing all the elements. So the construction of this will be triggered when you call this method

    }

//            List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); //instead of using this, then using PageFactory

    //PageFactory
    @FindBy(css = ".mb-3")
    List<WebElement> products;

}
