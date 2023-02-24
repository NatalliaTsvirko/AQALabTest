package tests;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertyReader;
import java.util.List;
import static endpoint.Endpoints.GET_BOOKS;
import static pages.BasePage.webElements;
@Log4j2
public class ApiTests extends BaseTest {

    @BeforeMethod
    public void openUrl() {
        bookPage.open();
    }
    @AfterMethod
    public void clearCookie() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @Test
    public void getBodyTitleAllBookso() {
        log.info("take all book from ui and make list variable");
        webElements = bookPage.getAllBook();
        log.info("take all books fom api swagger. Read and path info");
        JsonPath books = RestAssured.get(PropertyReader.getUrl() + GET_BOOKS).getBody().jsonPath();
        log.info("path info put on list and take only title");
        List<String> tittles = books.get("books.title");
        log.info("compaiable list books from answer api and list books from ui");
        Assert.assertEquals(tittles, webElements);
    }
}
