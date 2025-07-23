package rahulshettyacademy.TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public void initializeDriver() throws IOException {

        //properties class
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("/Users/telkomdev/Java/SeleniumFrameworkDesignRahul/src/main/java/rahulshettyacademy/resources/GlobalData.properties");
        prop.load(fis);

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }
}
