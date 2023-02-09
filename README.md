# Employee_Portal_Application(EPA)
 - This is a application created for the Employees and HR's.
 - This application lets the Hr to register themselves with their details.
 - And allows to Add, Update the employee details.
 
## EPA main features 
 - Provides authentication for the HR and Employee.
 - The application allows to login anytime with the credentials.
 - Application then allows Hr to add the employee details.
 - Hr can also use an option to update the Employee details.
 - Multiple Hr's can use their credentials to add the new employees.
 - At last Hr can be able to logout, for security reasons.
 - Employee can login and check the profile page.
 - Roles are given according to their registration ie either Hr or Employee.
 
## Prerequisites 
 - Java version 17.
 - Mysql running on port 3306.


## Installation  
### 1) Import on STS 
 - Go to file -> Import
 - Choose Git -> Projects from Git and click Next
 - Chose CloneURL and click Next
 - Copy paste the git hub url

### 2) DataBase 
 - Start Mysql on port no 3306
 - Create a database named "employeeportal"
 - Update database username and password in "application.properties" file in src/main/resources.


## Run Application  
 - Run the Application as Java Application.
 - Tables will be created in your database.


## Login/Register  
 - For the Hr to register for the first time , click on Register here.
 - Enter the details for the HR -> submit.
 - Try to login for the same using the given credentials.
 - After login is successful -> Add Employee page can be opened.
 - Add Employee details in the form -> Save Employee Details.
 - You can also Update the Details of the Employee.
 - After this Employee can also login with the credentials and check the profile page.


## DataBase  
 - After some entries are done, open the data base.
 - You can check the entries in the user table.
 - You can check the role of that entry in the user_role table.
 - You can check if the entry is an HR or an user(employee) in the role table.
