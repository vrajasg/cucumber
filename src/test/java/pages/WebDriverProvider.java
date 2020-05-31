package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverProvider {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String CHROME_REMOTE = "chrome_remote";
    private static final String FIREFOX_REMOTE = "firefox_remote";
    private final String browser;
    private final String webDriverVersion;
    private final DesiredCapabilities caps;


    public WebDriverProvider(String browser, String webDriverVersion, DesiredCapabilities caps) {
        this.browser = browser;
        this.webDriverVersion = webDriverVersion;
        this.caps = caps;
    }

    public WebDriver webDriver() {
        WebDriver driver = null;
        switch (browser) {
            case CHROME:
                driver = getChromeDriver();
                break;
            case FIREFOX:
            case CHROME_REMOTE:
            case FIREFOX_REMOTE:
                // ToDo:
                break;
        }
        return driver;
    }

    private WebDriver getChromeDriver() {
        synchronized (WebDriverManager.class) {
            WebDriverManager.getInstance(DriverManagerType.CHROME)
                    .browserVersion(webDriverVersion)
                    .setup();
        }
        return new ChromeDriver();
    }
}
