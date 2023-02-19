package com.softwaretestingboard.StepDefinitions.BreadCrumbs;

import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyAutowired;
import com.softwaretestingboard.Pages.BreadCrumbs.BreadCrumbsMethods;
import io.cucumber.java.en.And;
import org.testng.Assert;


public class BreadCrumbsStepDef extends BasePage {

    @LazyAutowired
    BreadCrumbsMethods breadCrumbsMethods;

    //Step to go back to any section from breadcrumbs panel
    //If the step fails, test also fails at this point
    //A basic screenshot of the visible screen is added
    @And("I navigate to {string} via breadcrumbs and verify if navigation is successful")
    public void iNavigateToViaBreadcrumbs(String pageName) {
        Assert.assertEquals(breadCrumbsMethods.navigateToSectionViaBreadCrumbs(pageName), pageName);
        report.addScreenshot("iNavigateToViaBreadcrumbs", driver);
    }
}
