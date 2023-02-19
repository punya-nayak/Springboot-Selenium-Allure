package com.softwaretestingboard.Utilities.ScreenshotUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *Aspect class which calls ScreenshotUtil's attachScreenShot or attachScreenShotAndSaveToDisk method
 * after every method annotated with TakeScreenshot completed
 * regardless of whether the outcome is successful or not
 *
 */
@Aspect
@Component
public class ScreenshotAspect {

    @Autowired
    private ScreenshotUtil screenshotUtil;

    @After("@annotation(com.softwaretestingboard.Configurations.CustomAnnotations.TakeScreenshot)")
    public void callScreenshotUtil(JoinPoint joinPoint) throws IOException {
        this.screenshotUtil.attachScreenShotAndSaveToDisk(joinPoint.getSignature().getName());
    }
}
