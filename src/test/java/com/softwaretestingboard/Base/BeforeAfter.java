package com.softwaretestingboard.Base;

import com.softwaretestingboard.Pages.Header.HeaderMethods;
import com.softwaretestingboard.Pages.LoginPage;
import com.softwaretestingboard.Utilities.ReportUtils.Report;
import com.softwaretestingboard.Utilities.UserUtilities.UserManagement;
import com.softwaretestingboard.Utilities.UserUtilities.UserUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.TimeUnit;


public class BeforeAfter extends UserUtils {

    @Autowired
    public LoginPage loginPage;

    @Autowired
    public UserManagement userManagement;

    @Autowired
    private ApplicationContext applicationContext;

    private Scenario scenario;

    @Autowired
    HeaderMethods headerMethods;

    /**
     * Here the report is initialized and login is done
     *
     * @param scenario Current Scenario
     */
    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("before");
        try {
            this.scenario = scenario;
            report = new Report(scenario);
            driver.manage().timeouts().implicitlyWait(ReadAppProperties.TIMEOUT, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(ReadAppProperties.URL);
            if (!specificUserTagExist(scenario)) {
                loginPage.loginWithRegularUser();
            } else {
                report.logMessage("Logging in with a specific User");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Checks whether scenario has @specific_user tag
     *
     * @param scenario Current Scenario
     * @return true or false baed on whether scenario has @specific_user tag
     */
    private boolean specificUserTagExist(Scenario scenario) {
        System.out.println(scenario.getSourceTagNames());
        return scenario.getSourceTagNames().contains("@specific_user");
    }


    /**
     * Here ajcorefiles are deleted,screenshot is attached to report if scenario has failed
     * Users are signed out and removed from busy state and finally driver is quit
     *
     * @param scenario Current scenario
     */
    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("After");
        deleteAJcoreFiles();
        try {
            if (scenario.isFailed()) {
                scenario.attach(this.applicationContext.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.BYTES), "image/png", scenario.getName());
            }
            synchronized (this) {
                String userToBeReset = this.userManagement.getBusyUserFromMap(Thread.currentThread().getName());
                if (userToBeReset != null) {
                    this.resetUserToFree(userToBeReset);
                }
            }
            headerMethods.signOut();
            this.applicationContext.getBean(WebDriver.class).quit();
        } catch (Exception e) {
            if (driver != null) {
                headerMethods.signOut();
                synchronized (this) {
                    String userToBeReset = this.userManagement.getBusyUserFromMap(Thread.currentThread().getName());
                    if (userToBeReset != null) {
                        this.resetUserToFree(userToBeReset);
                    }
                }
                this.applicationContext.getBean(WebDriver.class).quit();
                e.printStackTrace();
            }
        }
    }

}
