# User Manual for REST API and Terminal Application

## 1. Introduction
Overview of the REST API and terminal application.<br>
User management application for managing multiple units and their unitilities and renters. The idea behind is to store all the untilities (electricity, gas, water) and show the bills for each unit. The application also stores the employees and their schedules. The application also stores the renters and their employees. The application also stores the payments and the readings for each unit. <br>

 Purpose and target audience of the user manual ->  <br>
 <span style="color:pink">
Impress and get good grade UwU</span> <br>
![Alt text](anime-blonde-wink-anime-girls-wallpaper-preview-1.jpg)

## 2. Prerequisites
Required software and hardware.<br>
Java 8 or higher <br>
Mysql <br>
[tutorial for Mysql setup](https://www.youtube.com/watch?v=k5tICunelSU&ab_channel=AmitThinks)<br>


create db with name renters<br>

username = root<br>
password = root<br>


## 3. Installation and Setup
MySQL database setup [Mysql](https://mysql.com/downloads/)<br> 
clone the repository <br>
```git clone https://github.com/andreiasnico/demo.git``` <br>
alternatively with ssh <br>
```git clone git@github.com:andreiasnico/demo.git``` <br>



## 4. API Endpoints
Detailed description of each API endpoint. Required parameters and their formats. Expected responses and their formats. <br>
### 4.1. Bill
```/bill/find-all-bills```<br>
Shows all bills from the database.<br>

```bill/add-bill```<br>
deliveryMethod = enum (Email, Post)<br>
unit = unit id<br>
status = enum (Paid, Pending)<br>
Adds a bill to the database.<br>
```bill/delete-bill```<br> 
Deletes a bill from the database.<br>
billid = bill id<br>

```bill/update-bill``` <br>
Updates a bill from the database.<br>
billid = bill id<br>
delivery = enum (Email, Post)<br>
status = enum (Paid, Pending)<br>

### 4.2. Building
```building/all-buildings```<br>
Shows all buildings from the database.<br>

```building/add-building```<br>
Adds a building to the database.<br>
street = String<br>
town = String<br>
stories = int <br>
name = String <br>
```building/delete-building```<br>
Deletes a building from the database.<br>
buildingid = building id<br>
```building/update-building```<br>
Updates a building from the database.<br>
buildingid = building id<br>
street = String<br>
stories = int <br>

### 4.3. Counter
```counter/fin d-all-counters```<br>
Shows all counters from the database.<br>
```counter/add-counter```<br>
Adds a counter to the database.<br>
checkDate = String YEAR-MONTH-DAY<br>
counterType =Enum (Electricty, Gas, Water)<br>
unitprice = long <br>

```counter/delete-counter```<br>
Deletes a counter from the database.<br>
counterId = counter id<br>

```counter/update-counter```<br>
Updates a counter from the database.<br>
counterId = counter id<br>
unitPrice = long<br>
checkDate = String YEAR-MONTH-DAY<br>

### 4.4. Employee
```employee/find-all-employees```<br>
Shows all employees from the database.<br>

```employee/add-employee```<br>
Adds an employee to the database.<br>
name = String<br>
state = String<br>
title = enum(    Cleaning, Lawyer,Engineer,Accountant,Handyman)<br>
salary = long <br>

```employee/delete-employee```<br>
Deletes an employee from the database.<br>

employeeid = employee id<br>
```employee/update-employee```<br>
Updates an employee from the database.<br>
employeeid = employee id<br>
name = String<br>
state= <br>
title = enum(    Cleaning, Lawyer,Engineer,Accountant,Handyman)<br>
salary = long <br>

### 4.5. Employee Schedule
```employee-schedule/employee-schedules```<br>
Shows all employee schedules from the database.<br>
```employee-schedule/add-schedule```<br>
Adds a schedule to the database.<br>
employeeId = employee id<br>
unitId = unit id<br>
startTime = String HOUR:MINUTE<br>
endTime = String HOUR:MINUTE<br>

```employee-schedule/delete-schedule```<br>
Deletes a schedule from the database.<br>
employeeId = employee id<br>
unitId = unit id<br>

```employee-schedule/update-schedule```<br>
Updates a schedule from the database.<br>
employeeId = employee id<br>
unitId = unit id<br>
startTime = String HOUR:MINUTE<br>
endTime = String HOUR:MINUTE<br>

### 4.6. Payment
```payment/find-all-payments```<br>
Shows all payments from the database.<br>
```payment/add-payment```<br>
Adds a payment to the database.<br>
amount = long<br>
billId = bill id <br>
bank = String (USUALLY THE 2-3-4 letter abreviation of bank)<br>
deliveryMethod = emum (Email, Post)<br>
```payment/delete-payment```<br>
Deletes a payment from the database.<br>
paymentId = payment id<br>
```payment/update-payment```<br>
Updates a payment from the database.<br>
paymentId = payment id<br>
amount = long<br>
billId = bill id <br>
bank = String (USUALLY THE 2-3-4 letter abreviation of bank)<br>
deliveryMethod = emum (Email, Post)<br>
### 4.7. Reading
```reading/readings```<br>
Shows all readings from the database.<br>
```reading/add-reading```<br>
Adds a reading to the database.<br>
volume = long<br>
billId = bill id <br>
counterId = counter id<br>
```reading/delete-reading```<br>
Deletes a reading from the database.<br>
readingId = reading id<br>
```reading/update-reading```<br>
Updates a reading from the database.<br>
readingId = reading id<br>
volume = long<br>
billId = bill id <br>
counterId = counter id<br>
### 4.8. Renter
```renter/find-all-renters```<br>
Shows all renters from the database.<br>
```renter/add-renter```<br>
Adds a renter to the database.<br>
name = String<br>
email = String <br>
iban = String <br>
```renter/delete-renter```<br>
Deletes a renter from the database.<br>
renterId = renter id<br>
```renter/update-renter```<br>
Updates a renter from the database.<br>
renterId = renter id<br>
name = String<br>
email = String <br>
iban = String <br>

### 4.9. Renter Employee
```renter-employee/renter-employees```<br>
Shows all renter employees from the database.<br>
```renter-employee/add-renter-employee```<br>
name = String<br>
birthday = String YEAR-MONTH-DAY<br>
renterId = renter id<br>

Adds a renter employee to the database.<br>
```renter-employee/delete-renter-employee```<br>
Deletes a renter employee from the database.<br>
renterEployeeId = renter employee id<br>

```renter-employee/update-renter-employee```<br>
Updates a renter employee from the database.<br>
renterEployeeId = renter employee id<br>
name = String<br>
birthday = String YEAR-MONTH-DAY<br>
renterId = renter id<br>
### 4.10. Unit
```unit/units```<br>
Shows all units from the database.<br>
```unit/add-unit```<br>
Adds a unit to the database.<br>
name = String <br>
storyNumber = int <br>
surface = long <br>
buildingId = building id <br>
renterId = renter id <br>
```unit/delete-unit```<br>
Deletes a unit from the database.<br>
unitId = unit id<br>

```unit/update-unit```<br>
Updates a unit from the database.<br>
unitId = unit id<br>
name = String <br>
storyNumber = int <br>
surface = long <br>
buildingId = building id <br>
renterId = renter id <br>

All endpoints are available at [http://localhost:8080/](http://localhost:8080/)<br>



## 5. Terminal Commands
```
Bill Cli Commands
       add bill: add a bill to our database
       read bill: read a bill from our database
       bills: show all bills
       by unit: show all bills from a unit 
       delete bill: delete a bill from our database
       update bill: update a bill from our database

Building Cli Commands
       Building: list all buildings
       building info: read a building from our database
       update building: update a building from our database
       delete building: delete a building from our database
       add building: add a building to our database

Built-In Commands
       help: Display help about available commands
       stacktrace: Display the full stacktrace of the last error.
       clear: Clear the shell screen.
       quit, exit: Exit the shell.
       history: Display or save the history of previously run commands
       version: Show version info
       script: Read and execute commands from a file.

Counter Cli Commands
       delete counter: delete counter by id
       Counter: show all counters
       add Counter: create a counter
       update counter: update counter by id
       counter info: read counter by id

Employee Cli Commands
       add employee: add an employee to our database
       employee: show all employees
       update employee: update an employee from our database
       employee info: read an employee from our database
       delete employee: delete an employee from our database

Employee Schedule Commands
       see all schedules: find all schedules
       update schedule: update a scehdule plan
       add schedule: add a schedule
       delete schedule: delete a schedule

Payment Cli Commands
       Payment: show all payments
       add payment: create a payment
       delete payment: delete payment by id
       update payment: update payment by id
       payment info: read payment by id

Reading Cli Commands
       Reading: show all readings
       add reading: add a new reading
       delete reading: delete reading by id
       update reading: update reading based on id

Renter Cli Commands
       add renter: add a renter to our database
       update renter: update a renter from our database
       Renter: show all renters
       delete renter: delete a renter from our database

Renter Employee Commands
       delete renter employee: delete a renter employee
       add renter employee: add a new renter employee
       renter employees: find all renter employees
       update renter employee: update a renter employee

Unit Cli Commands
       update unit: update a unit from our database
       delete unit: delete a unit from our database
       unit info: read a unit from our database
       units: show all units
       add unit: add a unit to our database
       
```

## 6. Error Messages and Troubleshooting
Common error messages, their meanings, and solutions. Troubleshooting steps for common issues. <br>

The project should work as intened if the user follows the steps in the installation and setup section. <br>

We do not guarantee that the project will work if the user does not follow the steps in the installation and setup section. <br>





## 7. Support and Resources
No ongoing support will be provided for the terminal application or REST API. However, the following resources are available for users:
[google.com](https://www.google.com)

## 9. Glossary
Definitions of technical terms and jargon used in the manual.
Rest API - Representational State Transfer Application Programming Interface <Br>
Terminal Application - A command line interface application<br>
MySQL - An open-source relational database management system<br>


## 10. Thanks
Thanks to the following people for their contributions to the project:<br>
Andrei Nicolaescu<br>
Zsolt Rapolti <br>
