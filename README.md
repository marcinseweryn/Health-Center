# Health Center (Work in progress)
The application is created using the following tools and technologies: Java SE, Spring, Spring MVC, Spring Security, Thymeleaf, HTML, CSS, JavaScript, JPA, MySQL, Maven, Eclipse, MySQL Workbench, Apache Tomcat 9

## Completed features
* Ability to register new user account
* Ability to logout and login for administrator, user and doctor account
* First version of GUI for users, administrator and main pages
* Ability to select web page language (Polish, English) 

**Administrator:**
* Users management with ability to create, delete, update and search users
* Setting a work schedule for doctors

**User:**
* Ability to change account settings
* Ability to register to a doctor in three steps by choosing a specialization, doctor and visit date.
* Ability to cancel a visit to a doctor
* Checking the state of the queue to the doctor online

**Doctor:**
* Ability to change account settings
* Possibility of admitting patients from the online queue

**Automatically:**
* Creating six nearest medical duty on the basis of a work schedule for doctor
