##Cucucmber - Java
#### Features:
```
 - Run scenarios with a specific tag
 - Suuport to run scenarios in parallel
 - Rerun failed scenarios with retry count
 - Dynamically pass AUT url at runtime 
```

#### maven command:
```
mvn -U clean test -Dcucumber.options="--tags @smoke" -Dthreads=3 -Durl="http://bbc.co.uk" -Dretry=1
```

###### **AUT : _Application Under Test_**
