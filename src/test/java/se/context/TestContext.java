package se.context;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import webdriver.WebDriverProvider;

@Getter
@Setter
public class TestContext {
    private WebDriver webDriver;
    private WebDriverProvider webDriverProvider;
}
