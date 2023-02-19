package com.softwaretestingboard.StepDefinitions.NavigationMenuBar;

import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Configurations.CustomAnnotations.TakeScreenshot;
import com.softwaretestingboard.Pages.NavigationMenuBar.NavigationMenuBarMethods;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.testng.Assert;

import java.util.List;


public class NavigationMenuBarStepDefs extends BasePage {

    @Autowired
    @Lazy
    NavigationMenuBarMethods navigationMenuBarMethods;


    //Checks whether all the sections mentioned are displayed
    //Based on status of the condition, error or success message is logged
    //A basic screenshot of the visible screen is added
    @Then("the navigation menu bar is displayed with the following category menus:")
    public void theNavigationMenuBarIsDisplayedWithTheFollowingMenus(List<String> expectedMenus) {
        boolean status = navigationMenuBarMethods.verifyAvailableMenusInNavigationBar(expectedMenus);
        report.logMessageBasedOnStatus(status, "theNavigationMenuBarIsDisplayedWithTheFollowingMenus",
                "Menus are" + (status ? " displayed" : " not displayed"));
        report.addScreenshot("theNavigationMenuBarIsDisplayedWithTheFollowingMenus", driver);

    }


    //Checks whether all the links mentioned are displayed
    //Based on status of the condition, error or success message is logged
    //A basic screenshot of the visible screen is added
    @Then("the navigation menu bar has the following links:")
    public void theNavigationMenuBarHasTheFollowingLinks(List<String> expectedLinks) {
        boolean status = navigationMenuBarMethods.verifyAvailableLinksInNavigationBar(expectedLinks);
        report.logMessageBasedOnStatus(status, "theNavigationMenuBarHasTheFollowingLinks",
                "Links are" + (status ? " displayed" : " not displayed"));
        report.addScreenshot("theNavigationMenuBarHasTheFollowingLinks", driver);
    }


    //Checks whether all the sections mentioned are displayed in the category
    //Based on status of the condition, error or success message is logged
    //A basic screenshot of the visible screen is added
    @Then("I verify that {string} category has the following sections:")
    public void iVerifyThatCategoryHasTheFollowingSubsections(String categoryName, List<String> sections) {
        boolean status = navigationMenuBarMethods.checkSubsectionsOf(categoryName, sections);
        report.logMessageBasedOnStatus(status, "iVerifyThatCategoryHasTheFollowingSubsections",
                "Links are" + (status ? " displayed" : " not displayed"));
        report.addScreenshot("iVerifyThatCategoryHasTheFollowingSubsections", driver);
    }


    //Step to navigate to subsections
    //If the step fails, test also fails at this point
    //shutterbug screenshot of the whole screen is added
    @TakeScreenshot
    @Then("I navigate to {string} subsection in {string} section of {string} category")
    public void iNavigateToSubsectionInSectionOfCategory(String SubSection, String Section, String Category) {
        Assert.assertTrue(navigationMenuBarMethods.navigateToSubsection(SubSection, Section, Category),
                "Navigation to " + SubSection + " of " + Section + "section in " + Category + " category not successful");
    }


    //Step to navigate to sections
    //If the step fails, test also fails at this point
    //shutterbug screenshot of the whole screen is added
    @TakeScreenshot
    @Then("I navigate to {string} section of {string} category")
    public void iNavigateToSectionOfCategory(String Section, String Category) {
        Assert.assertTrue(navigationMenuBarMethods.navigateToSection(Section, Category),
                "Navigation to " + Section + "section in " + Category + " category not successful");
    }
}
