package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class SearchPage {
    private final By searchInput = By.cssSelector("input[name='q'], #search_text_input, .search_text_input");
    private final By liveResults = By.cssSelector(".search_results, .search_results_footer");

    public void searchForUser(String userName) {
        $(searchInput).shouldBe(visible).setValue(userName);
    }

    public void verifyLiveResultsContain(String expectedName) {
        $(liveResults).shouldBe(visible).shouldHave(text(expectedName));
    }

    public void openSearchPage(String query) {
        open("/search.php?q=" + query);
    }

    public void openProfileFromResults(String expectedName) {
        $(By.partialLinkText(expectedName)).shouldBe(visible).click();
    }

    public void verifyProfilePageIsOpened() {
        $("body").shouldBe(visible);
        if (!url().contains("profile.php")) {
            $("body").shouldHave(text("Profile"));
        }
    }
}
