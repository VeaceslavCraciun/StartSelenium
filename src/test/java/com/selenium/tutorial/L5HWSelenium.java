package com.selenium.tutorial;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class L5HWSelenium {
    @Test
    public void addRemoveElements () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        By addManually = By.xpath("//button[text()='Add Element']");
        driver.findElement(addManually).click();
        Thread.sleep(1000);
        driver.findElement(addManually).click();
        Thread.sleep(1000);
        driver.findElement(By.className("added-manually")).click();
        int deletedButtonsQuantity = driver.findElements(By.className("added-manually")).size();
        Thread.sleep(1000);
        assertEquals(deletedButtonsQuantity, 1, "One button is remaining");
        driver.quit();
    }

    @Test
    public void fileUploadTest(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/upload");
        driver.findElement(By.id("file-upload")).sendKeys("C:\\Users\\Owner\\Desktop\\IMG_E0312.JPG");
        driver.findElement(By.id("file-submit")).submit();
        if(driver.getPageSource().contains("File Uploaded!")) {
            System.out.println("file uploaded");
        }
        else{
            System.out.println("file not uploaded");
        }
        driver.quit();
    }
    @Test
    public void fileDownloadTest(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/download");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Test.txt")).click();
        driver.quit();
    }

    @Test
    public void inputsTest(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/inputs");
        driver.findElement(By.xpath("//input")).sendKeys(Keys.ARROW_UP);
        String upKeyResult = driver.findElement(By.xpath("//input")).getAttribute("value");
        assertEquals(upKeyResult, "1");
        driver.findElement(By.xpath("//input")).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN);
        String downKeyResult = driver.findElement(By.xpath("//input")).getAttribute("value");
        assertEquals(downKeyResult, "-3");

        driver.navigate().refresh();
        driver.findElement(By.xpath("//input")).sendKeys("www");
        String alphaResult = driver.findElement(By.xpath("//input")).getAttribute("value");
        assertEquals(alphaResult, "");

        driver.navigate().refresh();
        driver.findElement(By.xpath("//input")).sendKeys("12");
        String numericResult = driver.findElement(By.xpath("//input")).getAttribute("value");
        assertEquals(numericResult, "12");
        driver.quit();
    }
}
