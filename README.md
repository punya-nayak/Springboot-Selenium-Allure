#Description:
- This is a sample test automation project created using Java Selenium, SpringBoot, Cucumber, TestNG and Allure.  
- Selenium is used as the automation framework, Cucumber to support Behavior Driven Development , TestNG and AssertJ for assertions and Allure for reporting.
- Log4j is also used for logging.   
- Spring boot features used in the project are Dependency Injection, Spring profiles, Aspect-Oriented Programming (AOP) and Spring Data JPA.

#Steps to run
1. Give the tags you want to run in Testrunner.java (src/test/java/com/softwaretestingboard/TestRunners/TestRunner.java)
2. Thread count for parallel execution can be given in value for dataproviderthreadcount in pom.xml
3. Run the command: mvn clean test

#Report Generation:
Run the command:
- allure serve target/allure-report

or
- allure generate allure-results --clean -o allure-report
- allure open allure-report

#Logs
Log file can be checked in log4j.log in logs folder after run. 
This will have all the failures marked as error.

#Screenshots
- Screenshots taken using @TakeScreenshot are saved in screenshots folder and also attached to the allure report.
- We can stop saving the screenshots to disk by changing attachScreenShotAndSaveToDisk method in ScreenshotAspect.java to attachScreenShot. 

