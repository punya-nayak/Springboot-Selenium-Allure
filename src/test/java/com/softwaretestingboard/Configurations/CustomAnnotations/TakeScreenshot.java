package com.softwaretestingboard.Configurations.CustomAnnotations;

import java.lang.annotation.*;

/**
 * User Defined annotation which can be added before methods
 * indicating that a screenshot will be taken.
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TakeScreenshot {
}
