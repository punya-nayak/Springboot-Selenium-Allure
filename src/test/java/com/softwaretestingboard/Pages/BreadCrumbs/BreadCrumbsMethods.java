package com.softwaretestingboard.Pages.BreadCrumbs;

import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyAutowired;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyComponent;
import com.softwaretestingboard.Pages.ProductPage.ProductPageMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


@LazyComponent
public class BreadCrumbsMethods extends BasePage {

    //Instance Variables of other components
    @LazyAutowired
    ProductPageMethods productPageMethods;


    //Page Objects
    @FindBy(xpath = "//div[@class='breadcrumbs']//li[contains(@class,'category')]")
    public List<WebElement> breadcrumbLinksList;


    //Xpath pattern to find multiple elements in page
    public static String XPATH_BREADCRUMB_LINK = "//div[@class='breadcrumbs']//li[contains(.,'%s')]/a";


    //Initializes page objects
    public BreadCrumbsMethods() {
        PageFactory.initElements(driver, this);
    }


    /**
     * Getter for breadcrumbLinksList
     *
     * @return List of breadcrumb links
     */
    public List<WebElement> getBreadcrumbLinksList() {
        return breadcrumbLinksList;
    }


    /**
     * Clicks on the link in breadcrumbs and verifies if page has navigated correctly
     *
     * @param pageName breadcrumb section to navigate to
     * @return Page name after navigation and upon exception returns a random string which will fail the Assert
     */
    public String navigateToSectionViaBreadCrumbs(String pageName) {
        try {
            WebElement sectionToNavigate = driver.findElement(By.xpath(String.format(XPATH_BREADCRUMB_LINK, pageName)));
            clickElement(sectionToNavigate);
            if (pageName.equals("Home")) {
                if (driver.getTitle().contains("Home")) {
                    return pageName;
                }
            } else {
                return productPageMethods.verifyTitleOfProductPage();
            }
        } catch (NoSuchElementException nse) {
            report.logError("Failed to find element");
            report.addScreenshot("navigateToSectionViaBreadCrumbs", driver);
            nse.printStackTrace();
        } catch (Exception e) {
            report.logError("Exception occured!");
            report.addScreenshot("navigateToSectionViaBreadCrumbs", driver);
            e.printStackTrace();
        }
        return "Not the same";
    }
}
