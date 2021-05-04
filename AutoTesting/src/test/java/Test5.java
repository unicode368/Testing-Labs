import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;

public class Test5 {

    protected WebDriver driver;

    //driver options
    private String property = "webdriver.chrome.driver";
    private String path = "src/main/resources/chromedriver.exe";

    //downloadZip variables
    private String getURLDownload = "https://animationscreencaps.com/4k-inside-out-2015/";
    private By buttonXpath = By.xpath("//*[contains(text(), ' Download Gallery As Zip File')]");
    private String newTabTitle = "Inside Out (2015) [4K] - Animation Screencaps";

    //wrong credentials variables
    private String getURLWrong = "https://animationscreencaps.com/toy-story-2-1999-4k";
    private By commentForm = By.cssSelector("iframe[title='Comment Form']");
    private By comment = By.name("comment");
    private String exactComment = "Cool!^^";
    private By email = By.name("email");
    private String exactEmail = "gbyeger";
    private By author = By.name("author");
    private String exactAuthor = "rtdjterhs";
    private By submit = By.name("submit");
    private By inputActive = By.cssSelector("[class=\"comment-form-input active\"]");

    //send comment variables
    private String getURLComment = "https://animationscreencaps.com/toy-story-2-1999-4k";
    private By viewComments = By.id("dcl_comment_btn");
    private By disqus = By.cssSelector("iframe[title='Disqus']");
    private By authorization = By.cssSelector("[class=\"auth-disqus\"]");
    private By username = By.id("username");
    private String exactUsername = "example@gmail.com";
    private By password = By.name("password");
    private String exactPassword = "example_password";
    private By commentArea = By.cssSelector("[class=\"textarea\"]");
    private String myComment = "Cool!^^";
    private By submitButton = By.cssSelector("[class=\"btn post-action__button full-size-button\"]");
    private By myName = By.xpath("//*[text()='Jane']");
    private By commentInfo = By.xpath("//*[text()='Cool!^^']");

    //search negative variables
    private String home = "https://animationscreencaps.com/";
    private By searchField = By.cssSelector("[class=\"search-trigger\"]");
    private By searchButton = By.cssSelector("[href=\"#\"]");
    private By searchInput = By.cssSelector("[class=\"search-input  js-search-input\"]");
    private String searchParameters = "Мавка";
    private By searchSubmit = By.cssSelector("[class=\"search-button\"]");
    private By searchResults = By.cssSelector("[class=\"page-content  " +
            "blog-archive blog-archive--masonry-full\"]");
    private By tag = By.tagName("p");
    private String expectedSearchResults = "Sorry, but nothing matched your search terms. " +
            "Please try again with different keywords.";

    //follow the link variables
    private String startLink = "https://animationscreencaps.com/";
    private By link = By.linkText("Howl's Moving Castle (2004)");
    private String expectedLink = "https://animationscreencaps.com/howls-moving-castle-2004/";

    @Before
    public void SetUp() {
        System.setProperty(property, path);
        driver = new ChromeDriver();
        System.out.println("Test start");
    }

   @Test
    public void downloadZip() {
        driver.get(getURLDownload);
        driver.findElement(buttonXpath).click();
        String title = driver.getTitle();
        Assert.assertEquals(title, newTabTitle);
    }

    @Test
    public void wrongCredentials() throws InterruptedException {
        driver.get(getURLWrong);
        WebElement form = driver.findElements(commentForm).get(0);
        driver.switchTo().frame(form);
        driver.findElement(comment).sendKeys(exactComment);
        driver.findElement(email).sendKeys(exactEmail);
        driver.findElement(author).sendKeys(exactAuthor);
        Thread.sleep(8000);
        driver.findElement(submit).click();
        driver.findElement(inputActive);
    }

    /*@Test
    public void sendComment() throws InterruptedException {
        driver.get(getURLComment);
        String parentWindow = driver.getWindowHandle();
        WebElement comments = driver.findElement(viewComments);
        comments.click();
        Thread.sleep(5000);
        WebElement commentSection = driver.findElements(disqus).get(1);
        driver.switchTo().frame(commentSection);
        driver.findElement(authorization).click();
        Thread.sleep(5000);
        Set<String> allWindows = driver.getWindowHandles();
        for(String curWindow : allWindows){
            if (!curWindow.equals(parentWindow))
            driver.switchTo().window(curWindow);
        }
        Thread.sleep(5000);
        driver.findElement(username).sendKeys(exactUsername);
        driver.findElement(password).sendKeys(exactPassword);
        Thread.sleep(120000);
        driver.switchTo().window(parentWindow);
        Thread.sleep(5000);
        driver.switchTo().frame(commentSection);
        driver.findElement(commentArea).sendKeys(myComment);
        Thread.sleep(3000);
        WebElement post = driver.findElement(submitButton);
        post.click();
        driver.findElement(myName);
        driver.findElement(commentInfo);
    }*/

    @Test
    public void searchNegative() {
        driver.get(home);
        WebElement search = driver.findElement(searchField);
        search.findElement(searchButton).click();
        driver.findElement(searchInput).sendKeys(searchParameters);
        driver.findElement(searchSubmit).click();
        String result = driver.findElement(searchResults).findElement(tag).getText();
        System.out.println(result);
        Assert.assertEquals(expectedSearchResults, result);
    }

    @Test
    public void followTheLink() {
        driver.get(startLink);
        WebElement film = driver.findElement(link);
        film.click();
        String title = driver.getCurrentUrl();
        Assert.assertEquals(expectedLink, title);
    }

   @After
    public void close() {
        System.out.println("Test end");
        driver.quit();
    }

}
