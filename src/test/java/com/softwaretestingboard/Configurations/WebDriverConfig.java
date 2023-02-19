package com.softwaretestingboard.Configurations;


import com.softwaretestingboard.Configurations.CustomAnnotations.CustomWebdriverThreadScopeBean;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;

import java.io.File;
import java.util.HashMap;

@Profile("local")
@LazyConfiguration
public class WebDriverConfig {

    private static File file;

    @CustomWebdriverThreadScopeBean
    @ConditionalOnMissingBean
    public WebDriver chromeDriver(){
        file = new File("tools/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
            HashMap prefs = new HashMap();
            prefs.put("download.prompt_for_download", Boolean.valueOf(true));
            options.setExperimentalOption("prefs", prefs);
            caps.setCapability("chromeOptions", options);
            System.out.println("CALLED WEBDRIVER");
        WebDriver driver=new ChromeDriver(options);
        return  driver;
    }
}
