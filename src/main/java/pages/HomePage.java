package pages;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public GamesPage clickGame(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
        return new GamesPage(driver);
    }

    public WebElement getGameNumber(int n) {
        WebElement game = driver.findElement(By.cssSelector("ul.games-list > li:nth-child(" + n + ")"));
        return game;
    }

    public List<WebElement> getAllGames() {
        List<WebElement> allGames = driver.findElements(By.cssSelector("ul.games-list > li.games-item"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.games-list > li.games-item")));
        return allGames;
    }

    public void scrollToSpecificGame(WebElement webElement) {
        if (!webElement.isDisplayed())
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void scrollToGamesSection() {
        WebElement section = driver.findElement(By.className("available-games"));
        if (!section.isDisplayed())
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", section);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(section));
    }

}
