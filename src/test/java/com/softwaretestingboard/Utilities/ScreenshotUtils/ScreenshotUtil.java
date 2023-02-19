package com.softwaretestingboard.Utilities.ScreenshotUtils;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyComponent;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class takes a full page screenshot using shutterbug and attaches it to allure report
 */
@LazyComponent
public class ScreenshotUtil extends BasePage {

    @Autowired
    private ApplicationContext applicationContext;

    public void attachScreenShotAndSaveToDisk(String testName) {
        BufferedImage bufferedImage = Shutterbug.shootPage(this.applicationContext.getBean(WebDriver.class), Capture.FULL_SCROLL).withTitle(testName).getImage();

        File tempFile = new File("target/image.png");
        try {
            ImageIO.write(bufferedImage, "png", tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path path = Paths.get(System.getProperty("user.dir") + "\\screenshots\\");
        System.out.println(path);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        try {
            FileCopyUtils.copy(tempFile, path.resolve(testName + "_" + formatter.format(new Date()) + ".png").toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Allure.addAttachment(testName, new FileInputStream(tempFile));
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        }
    }


    public void attachScreenShot(String testName) {
        BufferedImage bufferedImage = Shutterbug.shootPage(this.applicationContext.getBean(WebDriver.class), Capture.FULL_SCROLL).withTitle(testName).getImage();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpeg", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Allure.addAttachment(testName, new ByteArrayInputStream(os.toByteArray()));
    }
}