package com.softwaretestingboard.Pages.MyAccount;

import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@LazyComponent
public class MyAccountMethods extends BasePage {

    //Page Objects
    @FindBy(xpath = "//span[.='Contact Information']//..//following-sibling::div[@class='box-content']/p")
    WebElement emailInAccountPage;

    @FindBy(xpath = "//span[.='Contact Information']//..//following-sibling::div[@class='box-actions']/a[contains(@class,'change-password')]")
    WebElement editPassWord;

    @FindBy(xpath = "//ul[@class='nav items']//li[@class='nav item current']")
    WebElement selectedTabInNavigationPanel;

    @FindBy(xpath = "//h1[@class='page-title']/span")
    WebElement pageTitle;

    @FindBy(xpath = "//span[.='Default Billing Address']")
    WebElement billingAddressTitleInAddressPage;

    @FindBy(xpath = "//span[.='Default Shipping Address']")
    WebElement shippingAddressTitleInAddressPage;


    //Xpath pattern to find multiple elements in page
    public static String XPATH_ACCOUNTPAGE_SECTION = "//div[@class='block-title']/strong[.='%s']";
    public static String XPATH_ACCOUNTPAGE_SUBSECTION_TITLE = "//div[contains(@class,'box')]//span[.='%s']";
    public static String XPATH_ACCOUNTPAGE_EDIT_SUBSECTION = "//span[.='%s']//..//following-sibling::div[@class='box-actions']/a[@class='action edit']";
    public static String XPATH_ACCOUNTPAGE_SUBSECTION_CONTENT = "//span[.='%s']//..//following-sibling::div[@class='box-content']";
    public static String XPATH_ACCOUNTPAGE_LEFTNAV_LINKS = "//ul[@class='nav items']//a[.='%s']";

    //Initializes page objects
    public MyAccountMethods() {
        PageFactory.initElements(driver, this);
    }


    /**
     * Method to check if section is present in my account
     *
     * @param sectionName Name of the section
     * @return string of the section present in my account page
     */
    public boolean verifySectionPresentInMyAccountPage(String sectionName) {
        return returnWebElementFromXpathTemplate(XPATH_ACCOUNTPAGE_SECTION, sectionName).isDisplayed();
    }


    /**
     * Checks if subsection with all the text and links are displayed
     *
     * @param subSectionName Name of the subsection in mu account
     * @return true or false based on whether all the conditions are satisfied
     */
    public boolean verifySubSectionPresentInMyAccountPage(String subSectionName) {
        boolean status = returnWebElementFromXpathTemplate(XPATH_ACCOUNTPAGE_SUBSECTION_TITLE, subSectionName).isDisplayed();
        WebElement editInfo = returnWebElementFromXpathTemplate(XPATH_ACCOUNTPAGE_EDIT_SUBSECTION, subSectionName);
        switch (subSectionName) {
            case "Contact Information":
                status &= emailInAccountPage.isDisplayed() && emailInAccountPage.getText().length() > 0;
                status &= editInfo.isDisplayed() && editInfo.getText().contains("Edit");
                status &= editPassWord.isDisplayed() && editPassWord.getText().contains("Change Password");
                break;
            case "Newsletters":
            case "Default Billing Address":
            case "Default Shipping Address":
                WebElement subsectionInfo = returnWebElementFromXpathTemplate(XPATH_ACCOUNTPAGE_SUBSECTION_CONTENT, subSectionName);
                status &= subsectionInfo.isDisplayed() && subsectionInfo.getText().length() > 0;
                status &= editInfo.isDisplayed() && editInfo.getText().contains("Edit");
                break;
            default:
                report.logError("Wrong choice");
                status = false;
        }
        return status;
    }

    public boolean clickOnLeftNavigationPanelLink(String tabNameToBeSelected) {
        WebElement tabToClick = returnWebElementFromXpathTemplate(XPATH_ACCOUNTPAGE_LEFTNAV_LINKS, tabNameToBeSelected);
        tabToClick.click();
        return selectedTabInNavigationPanel.getText().equals(tabNameToBeSelected);
    }

    public boolean verifyMyAddressPageForUserWithAddress() {
        return pageTitle.getText().equals("Address Book") &&
                billingAddressTitleInAddressPage.isDisplayed() &&
                shippingAddressTitleInAddressPage.isDisplayed();
    }
}
