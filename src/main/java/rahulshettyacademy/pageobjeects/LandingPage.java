package rahulshettyacademy.pageobjeects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LandingPage {

    WebDriver driver;

    public LandingPage(WebDriver driver) { //driver ini hanhya memiliki scope untuk method ini saja
        //initialization
        this.driver = driver; //this.driver is belong to WebDriver driver

    }

    WebElement userEmail = driver.findElement(By.id("userEmail"));

}
