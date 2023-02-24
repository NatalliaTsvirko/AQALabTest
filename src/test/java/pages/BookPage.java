package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
@Log4j2
public class BookPage extends BasePage {

    private static final By LOGOUT_BUTTON = By.xpath("//button[@id = 'submit' and text()='Log out']");
    private static final By LIST_BOOKS = By.xpath("//span[@class='mr-2']/a");
    String listBooks = "//span[@class='mr-2']/a";


    public BookPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return isElementPresent(LOGOUT_BUTTON);
    }

    @Override
    public BasePage open() {
        driver.get(BASE_URL + "/books");
        return this;
    }
    @Step("Take all list books on page")
    public List<String> getAllBook() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LIST_BOOKS));
        log.info("Take all books");
        List<WebElement> elements = driver.findElements(By.xpath((listBooks)));
        for (int i = 0; i < elements.size(); i++){
            webElements.add(elements.get(i).getText());
        }
        System.out.println(webElements);
       return webElements;
    }


}