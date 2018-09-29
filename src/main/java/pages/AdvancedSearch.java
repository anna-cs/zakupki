package pages;

import org.openqa.selenium.*;
import project.Input;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class AdvancedSearch extends Page{
    //private WebDriver driver;

    public AdvancedSearch(WebDriver driver){
        super(driver);
    }


    public void sendType(){
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//label[contains(text(),'Способ определения поставщика, подрядной организации')]/following-sibling::div/div/span[@class='msExpandButton']")).click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//label[contains(text(), '"+ Input.type+"')]/preceding-sibling::input[@type='checkbox']")).click();

        driver.findElement(By.xpath("//label[contains(text(), '"+Input.type+"')]/ancestor::div/following-sibling::div/span[contains(text(), 'Выбрать')]")).click();
    }

    public void sendPhase(){
        driver.findElement(By.xpath("//label[contains(text(), 'Этап закупки')]/following-sibling::div/div/span[@class='msExpandButton']")).click();

        List<WebElement> phases = driver.findElements(By.xpath("//label[contains(text(), 'Этап закупки')]/ancestor::li//ul//input[@type='checkbox']"));///li[@class='customCheckbox']

        Iterator<WebElement> iterator = phases.iterator();

        while(iterator.hasNext()){
            WebElement phase = iterator.next();
            if (phase.isSelected()==true) phase.click();
            //if (phase.isSelected()==false&&phase.getText().equals(Input.phase)) phase.click();
        }

        driver.findElement(By.xpath("//label[contains(text(), '"+Input.phase+"')]/preceding-sibling::input[@type='checkbox']")).click();///li[@class='customCheckbox']
        driver.findElement(By.xpath("//li/label[contains(text(), '"+Input.phase+"')]/ancestor::div/following-sibling::div/span[contains(text(), 'Выбрать')]")).click();
    }

    public void sendPrice(){
        driver.findElement(By.xpath("//input[@id='priceFromGeneral']")).sendKeys(Input.priceFrom);
        driver.findElement(By.xpath("//input[@id='priceToGeneral']")).sendKeys(Input.priceTo);

        driver.findElement(By.xpath("//label[contains(text(), 'Валюта:')]/following-sibling::div/div[@class='pseudoSelect greyText']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//label[contains(text(), 'Валюта:')]/following-sibling::div//span[contains(text(), '"+Input.cur+"')]")).click();
    }

    public Zakupki goSearch(){
        sendType();
        sendPhase();
        sendPrice();
        driver.findElement(By.xpath("//span[contains(text(), 'Найти')]")).click();
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Zakupki(driver);
    }
}
