package com.softwaretestingboard.StepDefinitions;


import com.softwaretestingboard.Pages.LoginPage;
import com.softwaretestingboard.Utilities.UserUtilities.UserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;


@CucumberContextConfiguration
@SpringBootTest
public class LoginLogoutStepDefs extends UserUtils {


    @Autowired
    @Lazy
    public LoginPage loginPage;


    //Used for when we need to login with a specific type of user
    //Make sure user.csv has that kind of user before running
    @Given("I log in with {string} user")
    public void iLogInWithUser(String userType) {
        loginPage.loginWithSpecificUser(userType);
    }
}
