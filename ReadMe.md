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
mvn -U clean test -Dcucumber.filter.tags="{@SCENARIO_TAG}" -Dthreads={THREADS_COUNT} -Durl={AUT_URL} -Dretry={RETRY_COUNT}

Example # 1: 
mvn -U clean test -Dcucumber.filter.tags="@sanity" -Dthreads=3 -Durl=https://bbc.co.uk -Dretry=1

Example # 2: Generate Allure-Report locally
mvn -U clean test -Dcucumber.filter.tags="@sanity" -Dthreads=3 -Durl=https://bbc.co.uk -Dretry=1 allure:serve
```

###### **AUT : _Application Under Test_**