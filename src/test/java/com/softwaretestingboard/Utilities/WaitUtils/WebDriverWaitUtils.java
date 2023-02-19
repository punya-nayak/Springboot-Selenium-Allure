package com.softwaretestingboard.Utilities.WaitUtils;


import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


@LazyComponent
public class WebDriverWaitUtils extends BasePage {

    static private final String JS_AJAX_COMPLETION =
            "var docReady = window.document.readyState == 'complete';" +
                    "var isJqueryComplete = typeof(window.jQuery) != 'function' || window.jQuery.active == 0;" +
                    "var isPrototypeComplete = typeof(window.Ajax) != 'function' || window.Ajax.activeRequestCount == 0;" +
                    "var isDojoComplete = typeof(window.dojo) != 'function' || window.dojo.io.XMLHTTPTransport.inFlight.length == 0;" +
                    "var result = docReady && isJqueryComplete && isPrototypeComplete && isDojoComplete;" +
                    "return result;";

    static private final String JS_ANGULAR_COMPLETION =
            "if (window.angular) { \n" +
                    "  var el = document.getElementsByClassName('ng-scope');\n" +
                    "  if (angular.element(el).injector()) {\n" +
                    "    return angular.element(el).injector().get('$http').pendingRequests.length;\n" +
                    "  }\n" +
                    "}\n" +
                    "return -1;";

    private static final String XPATH_ANGULAR_PLACEHOLDERS = ".//*[contains(text(), '{{')]";

    private static final String XPATH_CURRENT_PROCESS_GIF = ".//img[contains(@src, 'loading.gif')]";


    public void waitForElementIsAbsentByLocator(By locator, final int timeoutSec) {
        (new WebDriverWait(driver, timeoutSec)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }


    public void waitForPageUpdate(int timeout) {
        Wait<WebDriver> wait = new WebDriverWait(driver, timeout, 10);
        wait.until(isPageUpdated);
        wait.until(isAngularFinished);
        waitForElementIsAbsentByLocator(By.xpath(XPATH_ANGULAR_PLACEHOLDERS), 10);
        waitForElementIsAbsentByLocator(By.xpath(XPATH_CURRENT_PROCESS_GIF), 10);
    }

    public void waitForElementToBeClickable(final WebElement e, final int timeoutSec) {
        new WebDriverWait(driver, timeoutSec).until(ExpectedConditions.elementToBeClickable(e));
    }

    private final ExpectedCondition<Object> isPageUpdated = new ExpectedCondition<Object>() {
        @Override
        public Boolean apply(WebDriver webDriver) {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            return Boolean.parseBoolean(js.executeScript(JS_AJAX_COMPLETION).toString());
        }
    };


    private final ExpectedCondition<Object> isAngularFinished = new ExpectedCondition<Object>() {
        @Override
        public Boolean apply(WebDriver webDriver) {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            String result = js.executeScript(JS_ANGULAR_COMPLETION).toString();
            return "0".equals(result) || "-1".equals(result);
        }
    };

}
