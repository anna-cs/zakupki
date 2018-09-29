package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Export {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.zakupki.gov.ru/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testExport() throws Exception {
    driver.get(baseUrl + "/epz/main/public/home.html");
    driver.findElement(By.cssSelector("li.active")).click();
    driver.findElement(By.cssSelector("li.active")).click();
    driver.findElement(By.cssSelector("li.active")).click();
    driver.findElement(By.id("quickSearchForm_header_searchString")).clear();
    driver.findElement(By.id("quickSearchForm_header_searchString")).sendKeys("НИОКР");
    driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Расширенный\n                    поиск')]")).click();
    driver.findElement(By.cssSelector("#sizcache04579182027846236 > span.msPlaceholder")).click();
    driver.findElement(By.id("placingWay_OK")).click();
    driver.findElement(By.id("placingWaysSelectBtn")).click();
    driver.findElement(By.cssSelector("#sizcache04579182027846236 > span.msPlaceholder.choseColor")).click();
    driver.findElement(By.id("ca")).click();
    driver.findElement(By.id("pc")).click();
    driver.findElement(By.id("pa")).click();
    driver.findElement(By.cssSelector("#orderStagesSelectBtn > span.btnBtn.blueBtn")).click();
    driver.findElement(By.id("priceFromGeneral")).clear();
    driver.findElement(By.id("priceFromGeneral")).sendKeys("100000000");
    driver.findElement(By.id("priceToGeneral")).clear();
    driver.findElement(By.id("priceToGeneral")).sendKeys("300000000");
    driver.findElement(By.id("currencyChangecurrencyIdGeneral")).click();
    driver.findElement(By.id("1")).click();
    driver.findElement(By.id("currencyChangecurrencyIdGeneral")).click();
    driver.findElement(By.cssSelector("span.bigOrangeBtn.searchBtn")).click();
    driver.findElement(By.xpath("//a[contains(@href, '/epz/order/notice/printForm/list.html?regNumber=0773100000318000060')]")).click();
    driver.findElement(By.cssSelector("span.expandTr")).click();
    driver.findElement(By.cssSelector("span.closePopUp")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
