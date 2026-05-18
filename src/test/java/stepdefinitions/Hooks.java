package stepdefinitions;

import com.codeborne.selenide.Configuration;
import config.TestConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.screenshot;

public class Hooks {

    @Before
    public void setup() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.baseUrl = TestConfig.BASE_URL;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 8000;
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            screenshot(scenario.getName().replaceAll("[^a-zA-Z0-9]", "_"));
        }
        closeWebDriver();
    }
}
