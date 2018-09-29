package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.Input;
import java.io.File;
import java.io.IOException;

public class MainPage extends Page{
    //private WebDriver driver;
    public AdvancedSearch advancedSearch;

    public MainPage(WebDriver driver){
        super(driver);
    }

    public void clickMainSearch(){
        driver.findElement(By.cssSelector("li.active")).click();
        WebElement element = driver.findElement(By.id("quickSearchForm_header_searchString"));
        element.sendKeys(Input.name);
        element.submit();
       //driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
    }

    public AdvancedSearch clickAdvancedSearch(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Расширенный\n                    поиск')]")));
        driver.findElement(By.xpath("//a[contains(text(),'Расширенный\n                    поиск')]")).click();
        return this.advancedSearch = new AdvancedSearch(driver);
    }

}
