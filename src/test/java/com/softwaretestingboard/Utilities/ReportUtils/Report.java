package com.softwaretestingboard.Utilities.ReportUtils;


import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
public class Report {

    Scenario reportScenario;
    public Logger logger = LogManager.getLogger(this.getClass().getName());

    public Report() {
        System.out.println("Report created in BasePage");
    }

    public Report(Scenario scenario) {
        System.out.println("Report for: " + scenario.getName());
        reportScenario = scenario;
    }


    /**
     * If the test condition fails, error is logged in the log file.However the step will be mark as passed.
     * Use only when:
     * <p>1) tests are very low priority</p>
     * <p>2) Passing of the test condition is not necessary to execute the upcoming tests.</p>
     *
     * @param flag       boolean value indicating test status
     * @param methodName Pass the name of current method or step here. Useful for debugging in log file.
     * @param message    Message to be logged or printed in report.
     */
    public void logMessageBasedOnStatus(boolean flag, String methodName, String message) {
        if (!flag) {
            logger.error("Error in scenario: " + reportScenario.getName() + "\nIn Step: " + methodName + "\nError Message: " + message);
            reportScenario.log(flag + " = " + message);
        } else {
            logger.info(flag + " = " + message);
            reportScenario.log(flag + " = " + message);
        }
    }

    /**
     * A simple method to attach a text message to report and to log
     *
     * @param message Message to be attached to report and log
     */
    public void logMessage(String message) {
        try {
            logger.info(message);
            reportScenario.log(message);
        } catch (Exception e) {
            System.out.println(message);
        }
    }

    /**
     * A simple method to attach a error message to report and to log
     *
     * @param message Error message to be attached to report and log
     */
    public void logError(String message) {
        try {
            logger.error(message);
            reportScenario.log(message);
        } catch (Exception e) {
            System.out.println(message);
        }
    }

    /**
     * This screenshot method uses Allure.addAttachment method to attach a screenshot taken from TakesScreenshot interface of selenium
     *
     * @param screenshotName Name of the screenshot.
     * @param driver         Base page driver
     */

    public void addScreenshot(String screenshotName, WebDriver driver) {
        Allure.addAttachment(screenshotName, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

}
