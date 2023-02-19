package com.softwaretestingboard.Pages.NavigationMenuBar;


import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyAutowired;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyComponent;
import com.softwaretestingboard.Pages.BreadCrumbs.BreadCrumbsMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@LazyComponent
public class NavigationMenuBarMethods extends BasePage {

    //Instance Variables of other components
    @LazyAutowired
    BreadCrumbsMethods breadCrumbsMethods;

    //Page Objects
    @FindBy(xpath = "//a[contains(@class,'level-top')]//span[@class='ui-menu-icon ui-icon ui-icon-carat-1-e']//following-sibling::span")
    List<WebElement> menusInNavigationBar;

    @FindBy(xpath = "//a[not(@aria-haspopup) and @class='level-top ui-corner-all']//span")
    List<WebElement> linksInNavigationBar;

    @FindBy(xpath = "//nav//a[contains(@class,'focus')]//following-sibling::ul/li[contains(@class,'level1')]/a/span[not(@class)]")
    List<WebElement> subSectionsWebElements;


    //Xpath pattern to find multiple elements in page
    public static String XPATH_NAVIGATION_MAIN_LINK = "//nav//a[contains(@class,'level-top')]//span[.='%s']";
    public static String XPATH_NAVIGATION_SECTION_LINK = "//span[.='%s']//..//following-sibling::ul[contains(@class,'level0 submenu')]//span[.='%s']";
    public static String XPATH_NAVIGATION_SUBSECTION_LINK = "//span[.='%s']//..//following-sibling::ul[contains(@class,'level0 submenu')]//span[.='%s']//..//following-sibling::ul[contains(@class,'level1 submenu')]//span[.='%s']";

    //Initializes page objects
    public NavigationMenuBarMethods() {
        PageFactory.initElements(driver, this);
    }


    /**
     * Compares the expected list of Categories with actual list of Categories present in Navigation Bar of UI
     *
     * @param expectedMenus List of Categories present in Navigation Bar
     * @return true or false based on the satisfaction of the condition
     */
    public boolean verifyAvailableMenusInNavigationBar(List<String> expectedMenus) {
        List<String> actualMenus = textFromWebelementList(menusInNavigationBar);
        return compareListsOfStrings(expectedMenus, actualMenus);
    }

    /**
     * Compares the expected list of links with actual list of links present in Navigation Bar of UI
     *
     * @param expectedLinks List of links present in Navigation Bar
     * @return true or false based on the satisfaction of the condition
     */
    public boolean verifyAvailableLinksInNavigationBar(List<String> expectedLinks) {
        List<String> actualLinks = textFromWebelementList(linksInNavigationBar);
        return compareListsOfStrings(expectedLinks, actualLinks);
    }

    /**
     * Compares the expected list of subsections with actual list of subsection present in given category
     *
     * @param categoryName     Name of category in navigation bar
     * @param expectedSections expected List of sections in the category mentioned
     * @return true or false based on the satisfaction of the condition
     */
    public boolean checkSubsectionsOf(String categoryName, List<String> expectedSections) {
        WebElement categoryElement = driver.findElement(By.xpath(String.format(XPATH_NAVIGATION_MAIN_LINK, categoryName)));
        moveToElement(driver, categoryElement);
        List<String> actualSubsections = textFromWebelementList(subSectionsWebElements);
        return compareListsOfStrings(expectedSections, actualSubsections);
    }

    /**
     * Navigates to the subSection of a particular section and category
     *
     * @param subSection Name of the subsection as displayed in UI
     * @param section    Name of the section as displayed in UI
     * @param category   Name of the category as displayed in UI
     * @return true or false based on the satisfaction of the condition
     */
    public boolean navigateToSubsection(String subSection, String section, String category) {
        List<String> expectedBreadCrumbList = Arrays.asList(category, section, subSection);
        List<String> actualBreadCrumbList = new ArrayList<>();
        try {
            WebElement categoryElement = driver.findElement(By.xpath(String.format(XPATH_NAVIGATION_MAIN_LINK, category)));
            moveToElement(driver, categoryElement);
            WebElement sectionElement = driver.findElement(By.xpath(String.format(XPATH_NAVIGATION_SECTION_LINK, category, section)));
            moveToElement(driver, sectionElement);
            WebElement subSectionElement = driver.findElement(By.xpath(String.format(XPATH_NAVIGATION_SUBSECTION_LINK, category, section, subSection)));
            moveToElement(driver, sectionElement);
            clickElementJS(subSectionElement);
            List<WebElement> breadcrumbLinksList = breadCrumbsMethods.getBreadcrumbLinksList();
            actualBreadCrumbList = textFromWebelementList(breadcrumbLinksList);
        } catch (NoSuchElementException npe) {
            report.logError("Failed to find element");
            report.addScreenshot("navigateToSubsection", driver);
            npe.printStackTrace();
        } catch (Exception e) {
            report.logError("Exception Occured!");
            report.addScreenshot("navigateToSubsection", driver);
            e.printStackTrace();
        }
        return compareListsOfStrings(expectedBreadCrumbList, actualBreadCrumbList);
    }

    /**
     * Navigates to the subSection of a particular section and category
     *
     * @param section  Name of the section as displayed in UI
     * @param category Name of the category as displayed in UI
     * @return true or false based on the satisfaction of the condition
     */
    public boolean navigateToSection(String section, String category) {
        List<String> expectedBreadCrumbList = Arrays.asList(category, section);
        List<String> actualBreadCrumbList = new ArrayList<>();
        try {
            WebElement categoryElement = driver.findElement(By.xpath(String.format(XPATH_NAVIGATION_MAIN_LINK, category)));
            moveToElement(driver, categoryElement);
            WebElement sectionElement = driver.findElement(By.xpath(String.format(XPATH_NAVIGATION_SECTION_LINK, category, section)));
            moveToElement(driver, sectionElement);
            clickElementJS(sectionElement);
            List<WebElement> breadcrumbLinks = breadCrumbsMethods.getBreadcrumbLinksList();
            actualBreadCrumbList = textFromWebelementList(breadcrumbLinks);
        } catch (NullPointerException npe) {
            report.logError("Failed to find element");
            report.addScreenshot("navigateToSubsection", driver);
            npe.printStackTrace();
        } catch (Exception e) {
            report.logError("Exception occured");
            report.addScreenshot("navigateToSubsection", driver);
            e.printStackTrace();
        }
        return compareListsOfStrings(expectedBreadCrumbList, actualBreadCrumbList);
    }
}
