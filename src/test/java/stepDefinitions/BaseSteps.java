package stepDefinitions;

import pages.Sut;

import java.util.Optional;

public class BaseSteps {
    public BaseSteps() {
    }

    private static final ThreadLocal<Sut> sut = new ThreadLocal<>();
    private static final String DEFAULT_BROWSER = "chrome";
    private static final String BROWSER_NAME = "browserName";
    private static final String URL = "url";

    public static Sut getSut() {
        String url = Optional.of(System.getProperty(URL)).orElseThrow(() -> new RuntimeException("Missing application URL"));
        String browser = System.getProperty(BROWSER_NAME, DEFAULT_BROWSER);
        Sut currentSut = sut.get();
        if (currentSut == null) {
            currentSut = new Sut(url, browser);
            sut.set(currentSut);
        }
        return currentSut;
    }

    public static void stopSut() {
        Sut currentSut = sut.get();
        if (currentSut != null) {
            currentSut.quitBrowser();
            sut.remove();
        }
    }
}
