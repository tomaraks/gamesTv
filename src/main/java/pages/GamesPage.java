package pages;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;

public class GamesPage {
    private WebDriver driver;
    private WebDriver.Navigation navigate;

    public GamesPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public HomePage goBack() {
        navigate = driver.navigate();
        navigate.back();
        return new HomePage(driver);
    }

    public int getStatusCode(String currentURL) {
        LogEntries logs = driver.manage().logs().get("performance");

        int status = -1;

        for (Iterator<LogEntry> it = logs.iterator(); it.hasNext(); ) {
            LogEntry entry = it.next();

            try {
                JSONObject json = new JSONObject(entry.getMessage());

                JSONObject message = json.getJSONObject("message");
                String method = message.getString("method");

                if (method != null
                        && "Network.responseReceived".equals(method)) {
                    JSONObject params = message.getJSONObject("params");

                    JSONObject response = params.getJSONObject("response");
                    String messageUrl = response.getString("url");

                    if (currentURL.equals(messageUrl)) {
                        status = response.getInt("status");
                    }
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return status;
    }

    public String getCount() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".count-tournaments")));
        } catch (TimeoutException te) {
            return "0";
        }
        return driver.findElement(By.cssSelector(".count-tournaments")).getText();
    }

}

