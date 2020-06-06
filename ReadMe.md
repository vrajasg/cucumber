## Cucumber - Java

#### Features:
```
 - Dependency Injection via Pico-Container
 - Run scenarios in parallel
 - Run scenarios with a specific tag
 - Rerun failed scenarios with retry count
 - Dynamically pass AUT url at runtime
 - Hamcrest - for customized assertion matchers
 - Allure Report
 - Log4j2 logging - for each scenario 
```

#### maven command:
```
mvn -U clean test -Dcucumber.options="--tags {@SCENARIO_TAG}" -Dthreads={THREADS_COUNT} -Durl={AUT_URL} -Dretry={RETRY_COUNT}
```

###### **AUT : _Application Under Test_**