package com.softwaretestingboard.StepDefinitions.MyAccount;

import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyAutowired;
import com.softwaretestingboard.Configurations.CustomAnnotations.TakeScreenshot;
import com.softwaretestingboard.Pages.MyAccount.MyAccountMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;


public class MyAccountStepDefs extends BasePage {

    @LazyAutowired
    MyAccountMethods myAccountMethods;

    //Step to check if section is present in my account page
    //If the step fails, test also fails at this point
    //shutterbug screenshot of the whole screen is taken

    @TakeScreenshot
    @Then("I verify if {string} section is present in My account Page")
    public void iVerifyIfSectionIsPresentInMyAccountPage(String sectionName) {
        Assert.assertTrue(myAccountMethods.verifySectionPresentInMyAccountPage(sectionName), sectionName);
    }

    //Step to check if subsection and all the links are present in my account page
    //If the step fails, test also fails at this point
    @Then("I verify if {string} subsection is present in My account Page")
    public void iVerifyIfSubsectionIsPresentInMyAccountPage(String subSectionName) {
        Assert.assertTrue(myAccountMethods.verifySubSectionPresentInMyAccountPage(subSectionName), subSectionName + "not present");
    }

    //Step to navigate between tabs in my account page
    //If the step fails, test also fails at this point
    //shutterbug screenshot of the whole screen is taken

    @TakeScreenshot
    @And("I click on {string} in the left navigation panel")
    public void iClickOnInTheLeftNavigationPanel(String tabName) {
        Assert.assertTrue(myAccountMethods.clickOnLeftNavigationPanelLink(tabName));
    }


    //Step to verify if user with address has a default billing and shipping address section
    //If the step fails, test also fails at this point
    //A basic screenshot of the visible screen is added
    @And("I verify the page for a user with address added")
    public void iVerifyThePageForAUserWithAddressAdded() {
        Assert.assertTrue(myAccountMethods.verifyMyAddressPageForUserWithAddress());
        report.addScreenshot("iClickOnInCustomerMenuOnTheHeader", driver);
    }
}
