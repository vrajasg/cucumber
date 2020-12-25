package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import pages.Sut;
import se.context.TestContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static utils.ZaleniumState.setStatus;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    public static Map<Long, Scenario> scenarioMap = new ConcurrentHashMap<>();
    private final TestContext testContext;
    private Sut sut;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before(order = 1)
    public void before(Scenario scenario) {
        ThreadContext.put("logFileName", scenario.getName().replaceAll("[^a-zA-Z0-9]", "_"));
        logger.info(
                "Starting scenario: {} on thread {} ", scenario.getName(), Thread.currentThread().getName());
        scenarioMap.put(Thread.currentThread().getId(), scenario);
    }

    @Before(order = 2)
    public void initTestSuiteContext() {
        sut = new Sut(testContext);
    }

    @After(order = 1)
    public void setZaleniumStatus(Scenario scenario) {
        boolean isZalenium = System.getProperty("isZalenium", String.valueOf(Boolean.FALSE)).equalsIgnoreCase(String.valueOf(Boolean.TRUE));
        if (isZalenium) {
            setStatus(scenario, testContext.getWebDriver());
        }
    }

    @After(order = 2)
    public void tearDownTestSuiteContext(Scenario scenario) {
        if (scenario.isFailed()) {
            sut.addScreenshotToScenario(scenario);
        }
        sut.stopSut();
        logger.info(
                "Ending scenario: {} on thread {} ", scenario.getName(), Thread.currentThread().getName());
        scenarioMap.remove(Thread.currentThread().getId(), scenario);
    }
}