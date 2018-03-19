# Health Center (Work in progress)
The application is created using the following tools and technologies: Java SE, Spring, Spring MVC, Spring Security, Thymeleaf, HTML, CSS, JavaScript, JPA, MySQL, Maven, Eclipse, MySQL Workbench, Apache Tomcat 9

## Completed features
* Ability to register new user account (registration form with validation)
* Ability to logout and login for administrator, user and doctor account
* First version of GUI for users, administrator and main pages
* Ability to select web page language (Polish, English) 
* The ability to recover a forgotten password or account number by sending new data to the given email address

**Administrator:**
* Users management with ability to create, delete, update and search users
* Setting a work schedule for doctors
* News management with ability to create, delete, update and set destination.

**User:**
* Ability to change account settings
* Ability to add informations about sensitizations, chronic diseases, solid drugs
* Ability to register to a doctor in three steps by choosing a specialization, doctor and visit date.
* Ability to cancel a visit to a doctor
* Checking the state of the queue to the doctor online
* Checking the visits history
* Ability to browse doctors profiles with the ability to add ratings along with comments
* Online help (uncompleted)

**Doctor:**
* Ability to change account settings
* Ability to add a profile picture
* Possibility of admitting patients from the online queue
* Ability to view the visits history of currently admitted patients
* Patient card of current admitted patient with informations about sensitizations, chronic diseases, solid drugs and with possibility to save diagnosis, comments, recommendations and prescribed medicines.
* Checking the visits history of current admitted patient

**Automatically:**
* Creating six nearest medical duty on the basis of a work schedule for doctor
