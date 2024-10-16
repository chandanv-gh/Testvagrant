package com.testvagrant.stepdef;

import com.testvagrant.appGenericFunction.DataFunc;
import com.testvagrant.utils.WebDriverManager;
import dev.failsafe.internal.util.Assert;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StepDefinition {
    DataFunc df = new DataFunc();
    private WebDriver driver;
    private WebDriverWait wait;
    private String username = "Test"+RandomStringUtils.random(5, 'a', 'z');
    private String password = RandomStringUtils.random(10, 'a', 'z');


    @Given("I launch the browser and navigate to the application url")
    public void i_launch_the_browser_and_navigate_to_the_application_url() {
        driver = WebDriverManager.getDriver();
        driver.manage().window().maximize();
        driver.get(df.getAppUrl());
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }


    @When("I click on {string}")
    public void i_click_on(String string) {
        driver.findElement(By.xpath("//a[text()='Register']")).click();
    }

    @Then("I enter all the required details")
    public void i_enter_all_the_required_details(DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<String> data = dataTable.asList();
        for(String d:data) {
            driver.findElement(By.xpath("//input[contains(@id,'"+d+"')]")).sendKeys(df.getTestData(d));
        }
        driver.findElement(By.xpath("//input[contains(@id,'username')]")).sendKeys(username);
        driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys(password);
        df.updateJsonFile("registration", "username", username);
        df.updateJsonFile("registration", "password", password);
        driver.findElement(By.xpath(df.getXpath("confirmPassword"))).sendKeys(password);
    }
    @Then("click on {string} button")
    public void click_on_button(String button) {
        driver.findElement(By.xpath(df.getXpath(button))).click();
    }
    @Then("I should see the {string} message")
    public void i_should_see_the_message(String msg) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(df.getXpath(msg.toLowerCase()))));
    }


    @Given("I enter {string} of the {string} user")
    public void i_enter_of_the_user(String input, String user) {
        String[] in = input.split(",");
        String username = "";
        String password = "";
        if(user.equals("Registered")) {
            username = df.getTestData(in[0].toLowerCase());
            password = df.getTestData(in[1].toLowerCase());
        }
        driver.findElement(By.xpath(df.getXpath(in[0].toLowerCase()))).sendKeys(username);
        driver.findElement(By.xpath(df.getXpath(in[1].toLowerCase()))).sendKeys(password);
    }

    @Then("I validate the {string} of the logged in user")
    public void i_validate_the_of_the_logged_in_user(String string) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'"+df.getTestData("firstName")+" "+df.getTestData("lastName")+"')]")));
    }

    @Given("I validate all the elements in the {string} page")
    public void i_validate_all_the_elements_in_the_page(String page) {
        List<String> testElements = df.getList(page);
        for(String ele:testElements) {
            driver.findElement(By.xpath("//a[text()='"+ele+"']")).isDisplayed();
        }
    }


    @After
    public void endTest() {
        WebDriverManager.quitDriver();
    }
}
