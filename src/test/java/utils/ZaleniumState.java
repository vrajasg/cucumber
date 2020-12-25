package utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class ZaleniumState {
    private static final String ZALENIUM_TEST_PASSED = "zaleniumTestPassed";

    public static void setStatus(Scenario scenario, WebDriver driver) {
        Cookie cookie = new Cookie(ZALENIUM_TEST_PASSED, scenario.isFailed() ? String.valueOf(Boolean.FALSE) : String.valueOf(Boolean.TRUE));
        driver.manage().addCookie(cookie);
    }
}
