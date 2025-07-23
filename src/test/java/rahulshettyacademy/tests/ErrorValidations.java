package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import java.io.IOException;

public class ErrorValidations extends BaseTest {
    @Test
    public void SubmitOrderTest() throws InterruptedException, IOException {
        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshe@yopmail.com", "Password123!");

        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());



    }
}
