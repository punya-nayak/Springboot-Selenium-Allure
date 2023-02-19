package com.softwaretestingboard.Pages.Header;


import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyAutowired;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyComponent;
import com.softwaretestingboard.Utilities.WaitUtils.WebDriverWaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@LazyComponent
public class HeaderMethods extends BasePage {

    //Instance Variables of other components
    @LazyAutowired
    WebDriverWaitUtils webDriverWaitUtils;


    //Page Objects
    @FindBy(xpath = "(//li[@class='greet welcome']/span[@class='logged-in'])[1]")
    protected WebElement welcomeMessage;

    @FindBy(xpath = "//button[@data-action='customer-menu-toggle']")
    WebElement customerMenu;

    @FindBy(xpath = "//h1[@class='page-title']/span")
    WebElement pageTitle;


    //Xpath pattern to find multiple elements in page
    public static String XPATH_CUSTOMER_MENU_LINK = "(//div[@class='customer-menu']//a[contains(.,'%s')])[1]";


    //Initializes page objects
    public HeaderMethods() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Method checks if user has logged in by checking if welcome message or customer menu arrow is displayed
     *
     * @return Boolean value of whether welcome message of the customerMenu arrow is displayed
     */
    public boolean isUserLoggedIn() throws InterruptedException {
        Thread.sleep(3000);
        return isElementPresent(welcomeMessage) || isElementPresent(customerMenu);
    }

    /**
     * @param headerLink Pass the String of the link in customer menu drop down as it appears in UI
     * @return The title of the page we navigated to
     */
    public String clickOnLinkInCustomerMenu(String headerLink) {
        customerMenu.click();
        returnWebElementFromXpathTemplate(XPATH_CUSTOMER_MENU_LINK, headerLink).click();
        webDriverWaitUtils.waitForPageUpdate(10);
        return pageTitle.getText();
    }

    /**
     * Signs out current user
     */
    public void signOut() {
        try {
            if (isUserLoggedIn()) {
                if (clickOnLinkInCustomerMenu("Sign Out").contains("signed out"))
                    report.logMessage("Logged Out Succesfully");
            }
        } catch (Exception e) {
            if (clickOnLinkInCustomerMenu("Sign Out").equals("You are signed out")) {
                report.logMessage("Logged Out Succesfully");
            }
        }
    }
}
