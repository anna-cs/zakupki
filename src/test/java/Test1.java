import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import pages.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Test1 {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions().setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
        System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        System.setProperty("phantomjs.binary.path", "drivers\\phantomjs.exe");
        //File profileDir = new File("firefox_profile\\7jbadf22.test");
        File profileDir = new File("firefox_profile\\mkf50o2d.test1");
        FirefoxProfile firefoxProfile = new FirefoxProfile(profileDir);
        FirefoxOptions firefoxOptions = new FirefoxOptions().setProfile(firefoxProfile);
        this.driver = new FirefoxDriver(firefoxOptions);
        //driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        //this.driver = new PhantomJSDriver();
        driver.manage().window().maximize();
        String baseUrl = "http://www.zakupki.gov.ru/epz/main/public/home.html";
        driver.get(baseUrl);
    }

    @Test
    public void findZakupki(){
        mainPage = new MainPage(driver);
        mainPage.clickMainSearch();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainPage.clickAdvancedSearch();
        Zakupki zakupki = mainPage.advancedSearch.goSearch();
        zakupki.write();
    }

    @After
    public void tearDown() {;
        driver.quit();
    }
}
