package rahulshettyacademy.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"/*,"TestMoreThan1Group"*/})
//    public void SubmitOrderTest(String email, String password, String productName) throws InterruptedException, IOException {
    public void SubmitOrderTest(HashMap<String,String> input) throws InterruptedException, IOException {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalogue.getProductList();

        productCatalogue.addProductToCart(input.get("product"));

        CartPage cartPage = productCatalogue.goToCartPage();

        //css selector: .cartSection h3 -> parent to child
        //xpath: //*[@class='cartSection']/h3 -> parent to child

        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Indonesia");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
    }

    //To verify ZARA COAT 3 is displaying in orders page
    @Test(dependsOnMethods = {"SubmitOrderTest"})
    public void OrderHistoryTest(){
        ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty123@yopmail.com", "Password123!");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
//        return new Object[][] {{"rahulshetty123@yopmail.com", "Password123!", "ZARA COAT 3"},{"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};

//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("email","rahulshetty123@yopmail.com");
//        map.put("password","Password123!");
//        map.put("product","ZARA COAT 3");
//
//        HashMap<String,String> map1 = new HashMap<String,String>();
//        map1.put("email","rahulshetty12@yopmail.com");
//        map1.put("password","Password123!");
//        map1.put("product","ADIDAS ORIGINAL");
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }

}
