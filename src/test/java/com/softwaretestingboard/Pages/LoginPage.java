package com.softwaretestingboard.Pages;

import com.softwaretestingboard.Configurations.CustomAnnotations.LazyAutowired;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyComponent;
import com.softwaretestingboard.Configurations.CustomAnnotations.TakeScreenshot;
import com.softwaretestingboard.Pages.Header.HeaderMethods;
import com.softwaretestingboard.Utilities.UserUtilities.UserUtils;
import com.softwaretestingboard.Utilities.WaitUtils.WebDriverWaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


@LazyComponent
public class LoginPage extends UserUtils {

    //Instance Variables of other components
    @LazyAutowired
    WebDriverWaitUtils webDriverWaitUtils;

    @LazyAutowired
    HeaderMethods headerMethods;


    //Page Objects
    @FindBy(xpath = "//div[@class='panel header']//li[@class='authorization-link']/a[contains(.,'Sign In')]")
    private WebElement signInButton;

    @FindBy(id = "email")
    private WebElement emailInputBox;

    @FindBy(id = "pass")
    private WebElement passwordInputBox;

    @FindBy(xpath = "//button[@class='action login primary']")
    private WebElement loginSubmitButton;


    /**
     * Simple login method which waits for page to be updated after logging in
     *
     * @param username Username for login
     * @param password password for that user
     */
    @TakeScreenshot
    private void performLogin(String username, String password) {
        System.out.println("inside performLogin and the driver instance is >>> " + driver);
        webDriverWaitUtils.waitForElementToBeClickable(signInButton, 10);
        clickElementJS(signInButton);

        webDriverWaitUtils.waitForPageUpdate(10);

        emailInputBox.clear();
        emailInputBox.click();
        emailInputBox.sendKeys(username);

        passwordInputBox.clear();
        passwordInputBox.click();
        passwordInputBox.sendKeys(password);

        loginSubmitButton.click();

        webDriverWaitUtils.waitForPageUpdate(10);
    }

    /**
     * Login method for logging in with a regular user with no special privileges.
     * Users which are regular have usertype in user.csv as "regular"
     * This method is marked as synchronized so that only 1 thread has access to this method at a time.
     * This type of login is especially useful for websites which do not allow multiple logins from same user id.
     */
    public synchronized void loginWithRegularUser() {
        driver.manage().deleteAllCookies();
        String userName = this.getUser("regular");
        String password = this.getPassword(userName);
        System.out.println("Credentials from User Database >>> " + userName + "  " + password);
        performLogin(userName, password);
    }

    /**
     * Login method for scenarios marked with @specific_user
     * Such scenarios pass usertype in feature file
     * we should have a user in user.csv with that usertype
     * This method is marked as synchronized so that only 1 thread has access to this method at a time.
     * This type of login is especially useful for websites which do not allow multiple logins from same user id.
     *
     * @param userType Type of user which is passed in step in feature file
     */
    public synchronized void loginWithSpecificUser(String userType) {
        try {
            String userName = this.getUser(userType);
            String passwd = this.getPassword(userName);
            report.logMessage("Username: " + userName + ", Password: " + passwd);
            performLogin(userName, passwd);
            if (!headerMethods.isUserLoggedIn()) {
                headerMethods.signOut();
                performLogin(userName, passwd);
            }
        } catch (Exception ex) {
            report.logError("Error while logging in with specific user");
        }

    }

}
