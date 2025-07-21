package rahulshettyacademy.pageobjeects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) { //driver ini hanhya memiliki scope untuk method ini saja
        super(driver);
        //initialization
        this.driver = driver; //this.driver is belong to WebDriver driver
        PageFactory.initElements(driver, this); //That means this will trigger initializing all the elements. So the construction of this will be triggered when you call this method

    }

//    WebElement userEmail = driver.findElement(By.id("userEmail")); //instead of using this, then using PageFactory

    //PageFactory
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement passwordEle;
    @FindBy(id = "login")
    WebElement submit;

    public void loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }
}
