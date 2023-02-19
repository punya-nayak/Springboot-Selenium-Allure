<h1>Description:</h1>
<ul><li>This is a sample test automation project created using Java Selenium, SpringBoot, Cucumber, TestNG and Allure.  
<li>Selenium is used as the automation framework, Cucumber to support Behavior Driven Development , TestNG and AssertJ for assertions and Allure for reporting.
<li>Log4j is also used for logging.   
<li>Spring boot features used in the project are Dependency Injection, Spring profiles, Aspect-Oriented Programming (AOP) and Spring Data JPA.</li></ul>

<h1>Steps to run</h1>
<ol><li>Give the tags you want to run in Testrunner.java (src/test/java/com/softwaretestingboard/TestRunners/TestRunner.java)
<li>Thread count for parallel execution can be given in value for dataproviderthreadcount in pom.xml
<li>Run the command: mvn clean test</li></ol>

<h1>Report Generation:</h1>
Run the command:
<ul><li>allure serve target/allure-report</li></ul>

or
<ul><li>allure generate allure-results --clean -o allure-report</li></ul>
<ul><li>allure open allure-report</li></ul>

<h1>Logs</h1>
Log file can be checked in log4j.log in logs folder after run. 
This will have all the failures marked as error.

<h1>Screenshots</h1>
<ul><li>Screenshots taken using @TakeScreenshot are saved in screenshots folder and also attached to the allure report.</li></ul>
<ul><li>We can stop saving the screenshots to disk by changing attachScreenShotAndSaveToDisk method in ScreenshotAspect.java to attachScreenShot. </li></ul>

