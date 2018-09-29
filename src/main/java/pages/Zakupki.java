package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.Input;

import java.io.FileWriter;
import java.util.*;

public class Zakupki extends Page{
    //private WebDriver driver;

    public int lotCount;
    FileWriter writer;

    public Zakupki(WebDriver driver){
        super(driver);
    }

    public void write() {

        WebElement count = driver.findElement(By.xpath("//p[contains(text(), 'Всего записей')]/strong"));
        lotCount = Integer.parseInt(count.getText());
        //System.out.println("!!!"+lotCount);
        driver.findElement(By.xpath("//label[contains(text(), 'Развернуть информацию о лотах')]/preceding-sibling::input")).click();

        try {

        writer = new FileWriter("output.txt");

        } catch (java.io.IOException e) {
            System.out.println(e);
        }

        try {
        for(int i=1; i<=lotCount; i++) {
            WebElement element;
            element = driver.findElement(By.xpath(".//label[contains(text(), 'Развернуть информацию о лотах')]//ancestor::div/ancestor::div/div[@class='registerBox registerBoxBank margBtm20']["+i+"]"));
            writer.write("Название закупки:\n");
            writer.write(element.findElement(By.xpath("(.//dd[contains(text(), 'Заказчик:')]/following-sibling::dd)")).getText());
            writer.write("\n");
            writer.write("Сумма лотов:\n");
            List<WebElement> lots = element.findElements(By.xpath(".//i[contains(text(), 'Начальная цена лота:')]/strong"));
            if (lots.size()==0) Assert.fail("Object \"lots price\" not found");
            Iterator<WebElement> iterator = lots.iterator();
            while(iterator.hasNext()) {
                WebElement lot = iterator.next();
                writer.write(lot.getText());
                writer.write("\n");
            }
            writer.write("дата окончания подачи заявки:\n");
            String mainTab = driver.getWindowHandle();
            //driver.findElement(By.xpath("(//div[@class='reportBox']//a[contains(text(),'Сведения')])["+i+"]")).click();
            element.findElement(By.xpath(".//a[contains(text(),'Сведения')]")).click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (String windowHandel : driver.getWindowHandles()) {
                driver.switchTo().window(windowHandel);
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writer.write(driver.findElement(By.xpath("//td[contains(text(), 'Дата и время окончания подачи заявок')]/following-sibling::td")).getText());
            //driver.close();
            driver.switchTo().window(mainTab);
            writer.write("\n");
            writer.write("ФИО подписанта:\n");


            driver.findElement(By.xpath("(//a[contains(@class, 'cryptoSignLink')][1])["+i+"]")).click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writer.write(driver.findElement(By.cssSelector(".ok")).getText());
            writer.write("\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.cssSelector("span.expandTr")).click();
            writer.write("текст электронной подписи:\n");
            writer.write(driver.findElement(By.className("elSignBox")).getText());
            writer.write("\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.cssSelector("span.closePopUp")).click();
            writer.write("\n");
        }
        } catch (java.io.IOException e1) {
        System.out.println(e1);
        }
        finally {
            try {
                writer.close();
            }
            catch(java.io.IOException e2){}
        }

        try {

        writer.close();

        } catch (java.io.IOException e) {
            System.out.println(e);
        }


    }


}

