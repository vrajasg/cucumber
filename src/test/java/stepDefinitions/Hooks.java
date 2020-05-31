package stepDefinitions;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import pages.Sut;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    public static Map<Long, Scenario> scenarioMap = new ConcurrentHashMap<>();

    private TestContext testContext;
    private Sut sut;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void before(Scenario scenario) {
        ThreadContext.put("logFileName", scenario.getName().replaceAll("[^a-zA-Z0-9]", "_"));
        logger.info(
                "Starting scenario {} on thread {} ", scenario.getName(), Thread.currentThread().getName());
        scenarioMap.put(Thread.currentThread().getId(), scenario);
    }

    @Before
    public void loadBrowser() {
        sut = BaseSteps.getSut();
        sut.loadApplicationUrl();
        testContext.setDriver(sut.getWebDriver());
        testContext.initializePageObjects();
    }

    @After
    public void closeBrowser(Scenario scenario) {
        if (scenario.isFailed())
            sut.captureScreenshot(scenario);
        BaseSteps.stopSut();
    }
}