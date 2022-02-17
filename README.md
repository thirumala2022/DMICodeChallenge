# Project name :DMI CodeChallenge
## Pre-Requisites for project execution
- Eclipse IDE
- java version "11.0.12"
- Latest Chrome Driver  98.0.4758.82

## Running Examples
- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one).
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
###### Approach 1 :
  - Run the below command in Command Prompt, change the below file path to a locally saved project file path
```
cd C:\AspireQACodeChallenge1202\AspireQACodeChallenge
mvn clean install
```
###### Approach 2 :
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the right project
- Choose the AspireQACodeChallenge file
- Right Click on the file and Run as Maven test
###### Approach 3 :
- Right-click on Run.bat file and click on Edit -> change the file path to locally saved Project file path then Save and Close
- Run the Run.bat 

## 01 - Automation Framework design Approach

###### IDE & Language
   - Eclipse IDE & Java Language
###### Automation Tool
   - Selenium WebDriver
###### Project Type
   - Maven
###### Design Pattern
   - Page Object Model (POM)
###### DataDriven
   - Apache POI (Excel)
###### Browsers
   - Chrome Driver

## 02 - Test Scenarios
 - Scenario 01 - Verify User can Add New ToDo to existing Todo Items or not 
 - Scenario 02 - Verify user can Edit the existing ToDo or not 
 - Scenario 03 - Verify user can delete the existing ToDo or not
 - Scenario 04 - Verify user can make the Active ToDo to Competed o not
 - Scenario 05 -Verify user can make Completed ToDo to Active or not
 - Scenario 06 - Verify user can clear All completed ToDos or not
 - Scenario 07 - Verify given Todo is available in existing ToDos list or not
 - 
## 03 - Failed Scenario
 - Scenario 05 -Verify user can make Completed ToDo to Active or not
   -When user checked any ToDo Item then that particular Item become strike off which should be part of completed Items List, but that particular ToDo Item name changed to "false"   
   - When user try to make that completed Item to Active, thats difficult due to all the completed Items are of same name as "false"
 
 ## 03 - Brief Description about class files
  - Reports and Logs are maintaining using TestNg and ScreenShots captured for failed scenarios
       - .\\test-output\\emailable-report.html
  - Java class file files Information :
 ```
 ```
