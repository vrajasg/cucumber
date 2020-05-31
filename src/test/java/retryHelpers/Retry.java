package retryHelpers;


import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import stepDefinitions.Hooks;

public class Retry implements IRetryAnalyzer {

    private static final Logger logger = LogManager.getLogger(Retry.class);
    public static Map<String, Integer> scenarioRerunMap = new ConcurrentHashMap();
    private static BufferedWriter bufferedWriter;

    private static synchronized BufferedWriter getWriter() {
        try {
            if (bufferedWriter == null) {
                bufferedWriter = new BufferedWriter(new FileWriter("target/flaky_tests.txt", true));
            }
            return bufferedWriter;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean retry(ITestResult result) {
        int maxRetryCount =
                (System.getProperty("retry") == null) ? 0 : Integer.parseInt(System.getProperty("retry"));
        Scenario scenario = Hooks.scenarioMap.get(Thread.currentThread().getId());
        int retryCount =
                (scenarioRerunMap.get(scenario.getName()) == null)
                        ? 0
                        : scenarioRerunMap.get(scenario.getName());
        if (retryCount < maxRetryCount) {
            retryCount++;
            String retriesForScenario =
                    "Feature: "
                            + Hooks.scenarioMap.get(Thread.currentThread().getId()).getUri()
                            + " Scenario: "
                            + Hooks.scenarioMap.get(Thread.currentThread().getId()).getName()
                            + " Retry #: "
                            + retryCount;
            logger.info(retriesForScenario);
            writeToFile(retriesForScenario);
            scenarioRerunMap.put(scenario.getName(), retryCount);
            return true;
        }
        return false;
    }

    private void writeToFile(String textToWrite) {
        try {
            getWriter().write(textToWrite);
            getWriter().newLine();
            getWriter().flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
