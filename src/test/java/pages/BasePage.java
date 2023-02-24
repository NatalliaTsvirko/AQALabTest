package pages;


import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public abstract class BasePage {

    public static List<String> webElements = new ArrayList<>();

    String BASE_URL = System.getenv().getOrDefault("BASE_URL", PropertyReader.getProperty("booksAll.base_url"));
    String BASE_URL_LOGIN = System.getenv().getOrDefault("BASE_URL", PropertyReader.getProperty("books.base_url"));

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    public abstract boolean isPageOpened();

    public abstract BasePage open();

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

}


