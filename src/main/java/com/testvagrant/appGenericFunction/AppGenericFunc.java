package com.testvagrant.appGenericFunction;

import com.testvagrant.utils.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppGenericFunc {
    private WebDriver driver = WebDriverManager.getDriver();
    private WebDriverWait wait;

    public void launchBrowser() {
        driver = WebDriverManager.getDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void launchUrl(String url) {
        driver.get(url);
    }

    public void clickOnAnchorTag(String text) {
        By xpath = By.xpath("//a[text()='"+text+"']");
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        driver.findElement(xpath).click();
    }

    public void setText(By xpath, String text) {
        WebElement textBox = driver.findElement(xpath);
        textBox.clear();
        textBox.sendKeys(text);
    }

    public void clickOnButton(By button) {
        driver.findElement(button).click();
    }

    public String checkElementVisibility(By ele) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
            if(driver.findElement(ele).isDisplayed()) {
                System.out.println(driver.findElement(ele).getText()+" is visible");
                return driver.findElement(ele).getText();
            }
        }
        catch(Exception e) {
            System.out.println(driver.findElement(ele).getText()+" not visible");
        }
        return driver.findElement(ele).getText();
    }
}
