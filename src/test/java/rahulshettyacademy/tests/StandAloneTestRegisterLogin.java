package rahulshettyacademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class StandAloneTestRegisterLogin {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
//        driver.findElement(By.xpath("//a[text()='Register here']")).click();
//        driver.findElement(By.id("firstName")).sendKeys("Rahul");
//        driver.findElement(By.id("lastName")).sendKeys("Shetty");
//        driver.findElement(By.id("userEmail")).sendKeys("rahulshetty123@yopmail.com");
//        driver.findElement(By.id("userMobile")).sendKeys("6281234567");
//        driver.findElement(By.xpath("//select[contains(@formcontrolname,'occupation')]")).click();
//        driver.findElement(By.xpath("//option[@value='3: Engineer']")).click();
//        driver.findElement(By.cssSelector("input[value='Male']")).click();
//        driver.findElement(By.id("userPassword")).sendKeys("Password123!");
//        driver.findElement(By.id("confirmPassword")).sendKeys("Password123!");
//        driver.findElement(By.cssSelector("input[type='checkbox']")).click();
//        driver.findElement(By.id("login")).click();
//        if(driver.findElement(By.cssSelector("h1[class='headcolor']")).getText().contains("Success")) {
//            Assert.assertTrue(true);
//        }
//        System.out.println(driver.findElement(By.cssSelector("h1[class='headcolor']")).getText());


        driver.findElement(By.id("userEmail")).sendKeys("rahulshetty123@yopmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Password123!");
        driver.findElement(By.id("login")).click();
        if (driver.findElement(By.id("res")).isDisplayed()){
            Assert.assertTrue(true);
        };

    }
}
