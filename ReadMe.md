## Cucumber - Java

#### Framework Features:
```
 - Dependency Injection via Pico-Container
 - Executes each scenario in parallel
 - Run scenarios based on specific tag(s)
 - Ability to rerun failed scenarios (with retry count)
 - Reporting:
     - ReportPortal: An AI-powered Test Automation Dashboard
     - Cucumber Reports
     - Cucumber-html-reports
     - Allure Reports
     - Extent Reports (created inside 'test-output' folder)
 - Cross browser - Supports Chrome and Firefox for now (straightforward for other browsers when required)
 - Dynamically gets browser drivers via webdrivermanager dependency 
 - Scalable Infrastrcture: ability to run on Grid (VM or docker or Zalenium) 
     - Grid 3 via Docker: Refer comment at top in docker-compose-v2.yml
     - Grid 4 via Docker: Refer comment at top in docker-compose-v3.yml   
     - Zalenium: Refer comment at top in docker-compose-zalenium.yml
 - Log4j2 logging - for each scenario
```

#### Tech-stack:
```
 - java
 - cucumber-jvm + cucumber-testng
 - lombok
 - Hamcrest
 - Log4j2
```

#### Examples: maven command
```
mvn -U clean test -Dcucumber.filter.tags="{@SCENARIO_TAG}" -Dthreads={THREADS_COUNT} -Durl={AUT_URL} -Dretry={RETRY_COUNT}

Example # 1: Run Tests
mvn -U clean test -Dcucumber.filter.tags="@sanity" -Dthreads=3 -Durl=https://google.co.uk -Dretry=1

Example # 2: To generate Allure-Report locally
mvn -U clean test -Dcucumber.filter.tags="@sanity" -Dthreads=3 -Durl=https://www.google.co.uk -Dretry=1 allure:serve

Example # 3: To generate Cucumber-Html-Report locally (i.e change maven phae to 'verify')
mvn -U clean verify -Dcucumber.filter.tags="@sanity" -Dthreads=3 -Durl=https://www.google.co.uk -Dretry=1

Example # 4: To generate Allure-Reports & Cucumber-Html-Report locally (i.e change maven phase to 'verify')
mvn -U clean verify -Dcucumber.filter.tags="@sanity" -Dthreads=3 -Durl=https://www.google.co.uk -Dretry=1 allure:serve

Exmaple # 5: Grid (VM/Docker/Zalenium)
mvn -U clean test -Dcucumber.filter.tags="@sanity" -Dthreads=3 -Durl=https://www.google.co.uk -Dretry=1 -DgridUrl=http://localhost:4444/wd/hub -Dbrowser=chrome_remote
            Note: replace `localhost` with valid ip-address if grid set-up is other than on local machine. 
```
###### **AUT : _Application Under Test_**

###### **ReportPortal : https://reportportal.io **