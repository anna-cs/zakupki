package pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by anna on 14.09.2018.
 */
public class Page {
    protected WebDriver driver;

    public Page(WebDriver driver){
        super();
        this.driver = driver;
    }
    public Page(){
        super();
    }
}
