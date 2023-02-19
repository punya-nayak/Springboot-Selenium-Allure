package com.softwaretestingboard.Base;

import com.softwaretestingboard.Utilities.ReportUtils.Report;
import com.softwaretestingboard.Utilities.UserUtilities.User;
import com.softwaretestingboard.Utilities.UserUtilities.UserRepository;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

/* BasePage is made abstract as it contains a lot of useful methods which is used by other components
So instead of instantiating this class everywhere we can inherit from it to create a concrete useful implementation.*/
public abstract class BasePage {

    //driver object which will be used by all the components
    @Autowired
    protected WebDriver driver;

    @Autowired
    UserRepository userRepository;

    //Report is declared as static as we only need single copy of it
    public static Report report;

    //Called just after the initialization of beans.
    @PostConstruct
    public void init() {
        loadUsersToRepository();
        PageFactory.initElements(driver, this);
    }


    /**
     * Reads user.csv and saves it into userRepository
     */
    public void loadUsersToRepository() {
        if (userRepository.count() == 0) {
            try {
                int row = 0;
                try (BufferedReader br = new BufferedReader(new FileReader("./src/test/java/com/softwaretestingboard/Utilities/UserUtilities/user.csv"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (row > 0) {
                            String[] values = line.split(",");
                            User user = new User();
                            user.setUsertype(values[0].toLowerCase());
                            user.setUsername(values[1]);
                            user.setPassword(values[2]);
                            user.setStatus(values[3].toLowerCase());
                            userRepository.save(user);
                        }
                        row++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Click an element using Actions class. If exception occurs, click is reattempted using JSexecutor
     *
     * @param element Webelement which needs to be clicked
     */
    protected void clickElement(WebElement element) {
        Actions actions = new Actions(driver);
        try {
            actions.moveToElement(element).click().perform();
            report.logMessage("Clicked on given Element");
        } catch (Exception e) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                report.logMessage("Clicked on given Element");
            } catch (Throwable e1) {
                report.logMessage("Exception occured while clicking " + e1.getMessage());
            }
        }

    }

    /**
     * Click an element using JSexecutor. If exception occurs, click is reattempted using Actions Class
     *
     * @param element Webelement which needs to be clicked
     */
    protected void clickElementJS(WebElement element) {
        Actions actions = new Actions(driver);
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            report.logMessage("Clicked on given Element");
        } catch (Exception e) {
            try {
                actions.moveToElement(element).click().perform();
                report.logMessage("Clicked on given Element");
            } catch (Throwable e1) {
                report.logMessage("Exception occured while clicking " + e1.getMessage());
            }
        }

    }

    /**
     * Checks if element is present and enabled
     *
     * @param element Webelement whose presence needs to valdiated
     * @return true or false based on whether the element is present or not
     */
    public boolean isElementPresent(WebElement element) {
        boolean flag = false;
        try {
            if (element.isDisplayed()
                    || element.isEnabled())
                flag = true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * This method takes a list of webelements and gives the text inside each of those elements
     *
     * @param webElementList List of webelements whose text needs to be extracted
     * @return List of strings of the text inside each of the webelements
     */
    public List<String> textFromWebelementList(List<WebElement> webElementList) {
        return webElementList.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    /**
     * Compares 2 lists of strings and gives a detailed log of what is missed and what is extra if list is not equal
     *
     * @param expectedList List which is expected
     * @param actualList   List which is actually present
     * @return true or false based on whether lists are equal
     */
    public boolean compareListsOfStrings(List<String> expectedList, List<String> actualList) {
        boolean flag = false;
        if (expectedList.size() > actualList.size()) {
            report.logError("Actual List is missing some elements");
            report.logMessage("Expected List: \n" + expectedList);
            report.logMessage("Actual List: \n" + actualList);
            report.logMessage("Missed elements:\n" + expectedList.removeAll(actualList));
        } else if (expectedList.size() < actualList.size()) {
            report.logError("Actual List has more elements");
            report.logMessage("Expected List: \n" + expectedList);
            report.logMessage("Actual List: \n" + actualList);
            report.logMessage("Extra elements:\n" + actualList.removeAll(expectedList));
        } else {
            report.logMessage("Lists are of same size");
            if (expectedList.equals(actualList)) {
                flag = true;
            } else {
                report.logError("Lists are not the same");
                report.logMessage("Expected List: \n" + expectedList);
                report.logMessage("Actual List: \n" + actualList);
                report.logMessage("Extra elements:\n" + actualList.removeAll(expectedList));
                report.logMessage("Missed elements:\n" + expectedList.removeAll(actualList));
            }
        }
        return flag;
    }

    /**
     * Handy method to modify a common xpath with format specifier and return the Webelement
     *
     * @param xpath             xpath with format specifier
     * @param replacementString string to replace the forma specifier
     * @return Webelement found after modifying xpath with format specifier and replacement string
     */
    public WebElement returnWebElementFromXpathTemplate(String xpath, String replacementString) {
        return driver.findElement(By.xpath(String.format(xpath, replacementString)));
    }

    /**
     * Move to an element using actions class. If exception occurs, retry with JSexecutor
     *
     * @param driver  driver Object
     * @param element element to be moved to
     */
    public void moveToElement(WebDriver driver, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
        } catch (Exception e) {
            report.logMessage("Exception in moveToElement. In catch");
            mouseHoverJScript(element, driver);
        }
    }

    /**
     * Move to an element using JSexecutor. If exception occurs, retry with actions class
     *
     * @param HoverElement element to be moved to
     * @param driver       driver Object
     */
    public void mouseHoverJScript(WebElement HoverElement, WebDriver driver) {
        try {
            if (isElementPresent(HoverElement)) {
                String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
                ((JavascriptExecutor) driver).executeScript(mouseOverScript,
                        HoverElement);
            } else {
                report.logError("Element is not visible");
            }
        } catch (Exception e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(HoverElement).build().perform();
            e.printStackTrace();
        }
    }


    /**
     * simple method to delete the AspectJ dump files created. Used in @after method
     */
    public void deleteAJcoreFiles() {
        File directory = new File(System.getProperty("user.dir"));
        File[] files = directory.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.getName().startsWith("ajcore")) {
                    boolean status = f.delete();
                    if (status) {
                        report.logMessage("Deleted an ajcorefile");
                    }
                }
            }
        }
    }
}
