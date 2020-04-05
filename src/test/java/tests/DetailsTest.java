package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.GamesPage;

import java.util.List;

public class DetailsTest extends BaseTest {

    @Test
    public void getAllGamesTest() {
        // Getting the total count of games
        List<WebElement> allGames = homePage.getAllGames();

        // Listing all variables used
        int total = allGames.size();
        String gameName = "";
        WebElement game;
        GamesPage gamesPage;
        String currentURL = "";
        int statusCode = 0;
        String count = "";

        // Printing the header of result
        System.out.println("#    Gamename                                     Page URL                                                         PageStatus       Tournament Count");

        for (int i = 1; i <= total; i++) {
            // Scroll to Games Section
            homePage.scrollToGamesSection();
            // Get the game's web-element
            game = homePage.getGameNumber(i);
            // Scroll to specific game
            homePage.scrollToSpecificGame(game);
            // Get the name of game
            gameName = game.getText();
            // Open it
            gamesPage = homePage.clickGame(game);
            // Fetch the URL
            currentURL = gamesPage.getURL();
            // Fetch the status code
            statusCode = gamesPage.getStatusCode(currentURL);
            // Count the tournaments
            count = gamesPage.getCount();
            // Print result
            System.out.println(i + "    " + gameName + "                 " + currentURL + "                           " + statusCode + "    " + count);
            // Return back to the home page
            homePage = gamesPage.goBack();
        }
    }
}
