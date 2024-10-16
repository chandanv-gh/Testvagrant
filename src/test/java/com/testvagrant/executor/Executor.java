package com.testvagrant.executor;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/main/resources/Features",
        glue = "com.testvagrant.stepdef",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class Executor extends AbstractTestNGCucumberTests {
    // This class remains empty, the execution is handled by TestNG
    @Override
    @DataProvider(parallel=false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
