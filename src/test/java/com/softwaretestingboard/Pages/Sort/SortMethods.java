package com.softwaretestingboard.Pages.Sort;


import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyAutowired;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyComponent;
import com.softwaretestingboard.Utilities.WaitUtils.WebDriverWaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@LazyComponent
public class SortMethods extends BasePage {

    //Instance Variables of other components
    @LazyAutowired
    WebDriverWaitUtils webDriverWaitUtils;

    //Page Objects
    @FindBy(xpath = "//div[@class='toolbar-sorter sorter']/label")
    WebElement sortByLabel;

    @FindBy(xpath = "//div[@class='toolbar-sorter sorter']/select")
    WebElement sortByDropDownWebElement;

    @FindBy(xpath = "//div[@class='toolbar-sorter sorter']/a")
    WebElement sortOrderArrow;

    @FindBy(xpath = "//a[@class='product-item-link']")
    List<WebElement> productElements;

    @FindBy(xpath = "//div[@class='product-item-info']//span[@class='price']")
    List<WebElement> productPriceElements;

    //Initializes page objects
    public SortMethods() {
        PageFactory.initElements(driver, this);
    }


    /**
     * Step checks if sort by drop down and sort order arrow are displayed
     */
    public void isSortElementsVisible() {
        Assert.assertTrue(sortByDropDownWebElement.isDisplayed(), "Sort by not displayed");
        Assert.assertTrue(sortOrderArrow.isDisplayed(), "Sort order arrow not displayed");
    }


    /**
     * @return currently selected sort by option
     */
    public String returnSelectedSortByOption() {
        Select select = new Select(sortByDropDownWebElement);
        return select.getFirstSelectedOption().getText().trim();
    }

    /**
     * @return currently selected sort order option
     */
    public String returnSelectedSortOrder() {
        if (sortOrderArrow.getAttribute("class").contains("asc"))
            return "Ascending";
        else
            return "Descending";
    }

    /**
     * @return true or false based on whether sort by label is displayed
     */
    public boolean isSortByLabelDisplayed() {
        return sortByLabel.getText().equals("Sort By");
    }


    /**
     * @param sortBy Desired Sort By
     * @return Selected sort by after selection is performed
     */
    public String selectOptionFromSortBy(String sortBy) {
        sortBy = sortBy.toLowerCase();
        if (sortBy.equals("product name"))
            sortBy = "name";
        Select select = new Select(sortByDropDownWebElement);
        select.selectByValue(sortBy);
        webDriverWaitUtils.waitForPageUpdate(10);
        return returnSelectedSortByOption();
    }


    /**
     * @param sortOrderToBeSelected Desired Sort order
     * @return Selected sort order after selection is performed
     */
    public String selectSortOrder(String sortOrderToBeSelected) {
        String sortOrderCurrentlySelected = returnSelectedSortOrder();
        if (sortOrderToBeSelected.equalsIgnoreCase("Ascending")) {
            if (sortOrderCurrentlySelected.equalsIgnoreCase("Descending")) {
                sortOrderArrow.click();
            }
        } else {
            if (sortOrderCurrentlySelected.contains("Ascending")) {
                System.out.println("Here");
                sortOrderArrow.click();
            }
        }
        webDriverWaitUtils.waitForPageUpdate(10);
        if (!returnSelectedSortOrder().equals(sortOrderToBeSelected)) {
            clickElementJS(sortOrderArrow);
        }
        webDriverWaitUtils.waitForPageUpdate(10);
        return returnSelectedSortOrder();
    }

    /**
     * @param sortBy    sortBy expected
     * @param sortOrder sort order expected
     */

    public void verifyIfSortedAccordingTo(String sortBy, String sortOrder) {

        switch (sortBy.toLowerCase()) {
            case "product name":
                List<String> productNames = textFromWebelementList(productElements);
                if (sortOrder.equalsIgnoreCase("ascending")) {
                    assertThat(productNames).isSorted();
                } else {
                    assertThat(productNames).isSortedAccordingTo(Comparator.reverseOrder());
                }
                report.logMessage("Sorted according to " + sortBy + "in order " + sortOrder + "successfully");
                break;
            case "price":
                List<String> productPrices = textFromWebelementList(productPriceElements);
                if (sortOrder.equalsIgnoreCase("ascending")) {
                    assertThat(productPrices).isSorted();
                } else {
                    assertThat(productPrices).isSortedAccordingTo(Comparator.reverseOrder());
                }
                report.logMessage("Sorted according to " + sortBy + "in order " + sortOrder + "successfully");
                break;
            default:
                report.logError("Wrong sort by option");
        }
    }
}
