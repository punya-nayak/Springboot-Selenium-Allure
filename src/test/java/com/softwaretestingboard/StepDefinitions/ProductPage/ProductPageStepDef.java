package com.softwaretestingboard.StepDefinitions.ProductPage;

import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyAutowired;
import com.softwaretestingboard.Pages.ProductPage.ProductPageMethods;
import io.cucumber.java.en.Then;


public class ProductPageStepDef extends BasePage {

    @LazyAutowired
    ProductPageMethods productPageMethods;


    //Verifies the header of the product page is the same as provided
    //Based on status of the condition, error or success message is logged
    //A basic screenshot of the visible screen is added
    @Then("I verify if {string} title is displayed")
    public void iVerifyIfTitleIsDisplayed(String productPageTitle) {
        boolean status = productPageMethods.verifyTitleOfProductPage().equals(productPageTitle);
        report.logMessageBasedOnStatus(status, "iVerifyIfTitleIsDisplayed",
                "Title " + productPageTitle + " is displayed" + (status ? " correctly!" : " incorrectly!"));
        report.addScreenshot("iVerifyIfTitleIsDisplayed", driver);
    }
}
