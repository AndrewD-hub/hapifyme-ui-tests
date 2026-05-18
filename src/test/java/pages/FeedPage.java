package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FeedPage {
    private final By postTextArea = By.name("post_text");
    private final By postButton = By.name("post");

    public void openFeed() {
        open("/index.php");
    }

    public void createPost(String message) {
        $(postTextArea).shouldBe(visible).setValue(message);
        $(postButton).shouldBe(visible).click();
    }

    public void verifyPostIsDisplayed(String message) {
        $("body").shouldHave(text(message));
    }
}
