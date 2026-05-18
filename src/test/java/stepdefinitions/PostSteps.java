package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FeedPage;

public class PostSteps {
    private final FeedPage feedPage = new FeedPage();

    @When("utilizatorul creează o postare cu mesajul {string}")
    public void userCreatesPost(String message) {
        feedPage.openFeed();
        feedPage.createPost(message);
    }

    @Then("postarea cu mesajul {string} este afișată în feed")
    public void postShouldBeDisplayed(String message) {
        feedPage.verifyPostIsDisplayed(message);
    }
}
