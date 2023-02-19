package com.softwaretestingboard.StepDefinitions.Sort;

import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyAutowired;
import com.softwaretestingboard.Configurations.CustomAnnotations.TakeScreenshot;
import com.softwaretestingboard.Pages.Sort.SortMethods;
import io.cucumber.java.en.And;
import org.testng.Assert;


public class SortStepDefs extends BasePage {

    @LazyAutowired
    SortMethods sortMethods;

    //Step checks if Sort by label is displayed
    //Based on status of the condition, error or success message is logged
    @And("I verify if sort by label is displayed")
    public void iVerifyIfSortByLabelIsDisplayed() {
        boolean status = sortMethods.isSortByLabelDisplayed();
        report.logMessageBasedOnStatus(status, "iVerifyIfSortByLabelIsDisplayed",
                "Sort By is" + (status ? " displayed" : " not displayed"));
    }

    //Step checks if Sort by drop down and sort order are displayed
    //If the step fails, test also fails at this point
    @And("I verify if sort by drop down and sort order arrow are visible")
    public void iVerifyIfSortByDropDownIsVisible() {
        sortMethods.isSortElementsVisible();
        report.logMessage("Sort by and sort order are displayed");
    }

    //Step checks if the default sort by is selected
    //Based on status of the condition, error or success message is logged
    //A basic screenshot of the visible screen is added
    @And("I verify default sort by option selected is {string} and default sort order is ascending")
    public void iVerifyDefaultSortByOptionSelectedIs(String defaultOption) {
        boolean status = sortMethods.returnSelectedSortByOption().equals(defaultOption);
        status &= sortMethods.returnSelectedSortOrder().contains("Ascending");
        report.logMessageBasedOnStatus(status, "iVerifyDefaultSortByOptionSelectedIs", "Default options are" + (status ? " correct" : " not correct"));
        report.addScreenshot("theUserIsLoggedin", driver);
    }

    //Changes sort to desired sort by and sort order
    //If the step fails, test also fails at this point
    @And("I select {string} from sort by drop down in {string} order")
    public void iSelectFromSortByDropDownInOrder(String sortBy, String sortOrder) {
        Assert.assertEquals(sortMethods.selectOptionFromSortBy(sortBy), sortBy);
        Assert.assertEquals(sortMethods.selectSortOrder(sortOrder), sortOrder);
        report.logMessage("Sort changed");
    }

    //verifies if products are sorted to desired sort by and sort order
    //If the step fails, test also fails at this point
    @TakeScreenshot
    @And("I verify if products are sorted according to {string} in {string} order")
    public void iVerifyIfProductsAreSortedAccordingToInOrder(String sortBy, String sortOrder) {
        sortMethods.verifyIfSortedAccordingTo(sortBy, sortOrder);
    }
}
