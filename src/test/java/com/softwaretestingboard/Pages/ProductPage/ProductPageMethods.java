package com.softwaretestingboard.Pages.ProductPage;

import com.softwaretestingboard.Base.BasePage;
import com.softwaretestingboard.Configurations.CustomAnnotations.LazyComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


@LazyComponent
public class ProductPageMethods extends BasePage {

    //Page Objects
    @FindBy(xpath = "//h1[@id='page-title-heading']/span")
    WebElement productPageTitleElement;

    //Initializes page objects
    public ProductPageMethods() {
        PageFactory.initElements(driver, this);
    }


    /**
     * Verifies title of product page
     *
     * @return String containing title of product page
     */
    public String verifyTitleOfProductPage() {
        return productPageTitleElement.getText();
    }
}
