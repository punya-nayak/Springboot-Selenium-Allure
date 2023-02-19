package com.softwaretestingboard.StepDefinitions.Header;

import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Pages.Header.HeaderMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;


public class HeaderStepDef extends BasePage {

    @Autowired
    HeaderMethods headerMethods;


    //Step checks if user has logged in by checking if welcome message or customer menu arrow is displayed
    //Based on status of the condition, error or success message is logged
    //A basic screenshot of the visible screen is added
    @Given("the user is logged in")
    public void theUserIsLoggedin() throws InterruptedException {
        boolean status = headerMethods.isUserLoggedIn();
        report.logMessageBasedOnStatus(status, "theUserIsLoggedin",
                "User is" + (status ? " logged in" : " not logged in"));
        report.addScreenshot("theUserIsLoggedin", driver);
    }


    //Step to click any link present in the customer menu
    //If the step fails, test also fails at this point
    //A basic screenshot of the visible screen is added
    @And("I click on {string} in customer menu on the header")
    public void iClickOnInCustomerMenuOnTheHeader(String headerLink) {
        Assert.assertEquals(headerMethods.clickOnLinkInCustomerMenu(headerLink), headerLink);
        report.addScreenshot("iClickOnInCustomerMenuOnTheHeader", driver);
    }
}
