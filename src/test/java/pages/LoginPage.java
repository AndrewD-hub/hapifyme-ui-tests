package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LoginPage {
    private final By emailInput = By.name("log_email");
    private final By passwordInput = By.name("log_password");
    private final By loginButton = By.name("login_button");

    public void openPage() {
        open("/login_register.php");
    }

    public void login(String email, String password) {
        $(emailInput).shouldBe(visible).setValue(email);
        $(passwordInput).shouldBe(visible).setValue(password);
        $(loginButton).shouldBe(visible).click();
    }

    public void verifyUserIsOnFeed() {
        $("body").shouldBe(visible);
        if (!url().contains("index.php")) {
            $("body").shouldHave(text("hapifyMe"));
        }
    }

    public void verifyErrorMessage(String expectedMessage) {
        $("body").shouldHave(text(expectedMessage));
    }
}
