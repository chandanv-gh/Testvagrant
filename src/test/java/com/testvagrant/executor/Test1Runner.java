package com.testvagrant.executor;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/Features/Test1.feature",
        glue = "com.testvagrant.stepdef",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class Test1Runner extends AbstractTestNGCucumberTests {
    // This will run Test1.feature scenarios sequentially
}

