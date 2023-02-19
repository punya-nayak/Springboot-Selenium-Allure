package com.softwaretestingboard.TestRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = "src/test/resources",
        glue = {"com.softwaretestingboard"},
        plugin = {"pretty",
                "usage:target/usage-reports/usage.json",
                "html:target/cucumber-reports/cucumberReportHTML.html",
                "json:target/cucumber-reports/cucumberReportJson.json",
                "junit:target/cucumber-reports/cucumberReportXML.xml",
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
        },
        tags = "@NavigationMenuBar_02"
)


public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}

