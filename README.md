# Project Group 6

# Description
The goal of the project is to build a website and a mobile Android application for a Vehicle Repair Shop.
The Vehicle Repair Shop system will allow customers, technicians and an administrative assistant to create an account in order to do through the website/application what was previously done through a call. The customers can take an appointment for repairing their cars, the technician can add their availabilities and the administrative assistant can take an appointment for a customer and update anything the services, calendar, etc.

# Project Reports
[Home](https://github.com/McGill-ECSE321-Winter2021/project-group-06/wiki)
## Sprint 1
### Team
This table shows an overview of the team members and their roles for Sprint 1. The work done in group appears and are detailed on the [Meeting Minutes - Sprint 1](https://github.com/McGill-ECSE321-Winter2021/project-group-06/wiki/Meeting-Minutes---Sprint-1).
|Deliverables|Aurelia Haas|Catherine Caron|Cheng Chen|James Darby|Mike Wang|
|:----------:|:----------:|:-------------:|:--------:|:---------:|:-------:|
|Deliverable 1 - Roles and Activities|Group activities - Requirements, Use Cases Diagrams, Domain Model  Individual - Use Cases descriptions and detailed specification, Umple Domain Model, Persistence Layer and tests (with Catherine), Backlog issues, Documentation|Group activities - Requirements, Use Cases Diagrams, Domain Model  Individual - Use Cases descriptions and detailed specification, Backlog Issues, Persistence Layer and tests (with Aurelia), Minutes, Documentation|Group activities - Requirements, Use Cases Diagrams, Domain Model  Individual - Use Cases descriptions and detailed specification, Project Creation, Persistence Layer and tests, Build System, Documentation|Group activities - Requirements, Use Cases Diagrams, Domain Model  Individual - Use Cases descriptions and detailed specification, Persistence Layer and tests, Minutes, Documentation|Group activities - Requirements, Use Cases Diagrams, Domain Model  Individual - Use Cases descriptions and detailed specification, Persistence Layer and tests, Build System, Documentation|
|Deliverable 1 - Efforts in hours| 22 hours| 22 hours| 22 hours| 22 hours| 22 hours|

***
## Sprint 2
### Team
This table shows an overview of the team members and their roles for Sprint 2. The work done in group and more design decisions can be found on the [Meeting Minutes - Sprint 2](https://github.com/McGill-ECSE321-Winter2021/project-group-06/wiki/Meeting-Minutes---Sprint-2).
|Deliverables|Aurelia Haas|Catherine Caron|Cheng Chen|James Darby|Mike Wang|
|:----------:|:----------:|:-------------:|:--------:|:---------:|:-------:|
|Deliverable 2 - Roles and Activities|Group activities - Fix Persistence, Domain Model, QA Plan, Code Reviews and Debugging, Code Coverage Analysis  Individual -  Fix Persistence Tests from deliverable 1, Write Service Classes and business methods, Write controller classes and integration methods, Write Dto Methods, Clean up code, Document Restful methods, Write Unit Tests, Write Integration Tests in Postman, Fill Backlog issues, General Documentation, Minutes, Design Decisions|Group activities - Fix Persistence, Domain Model, QA Plan, Code Reviews and Debugging, Code Coverage Analysis  Individual -  Fix Persistence Tests from deliverable 1, Write Service Classes and business methods, Write controller classes and integration methods, Write Dto Methods, Clean up code, Document Restful methods, Write Unit Tests, Write Integration Tests in Postman, Fill Backlog issues, General Documentation, Minutes, Design Decisions |Group activities - Fix Persistence, Domain Model, QA Plan, Code Reviews and Debugging, Code Coverage Analysis  Individual -  Fix Persistence Tests from deliverable 1, Write Service Classes and business methods, Write controller classes and integration methods, Write Dto Methods, Clean up code, Document Restful methods, Write Unit Tests, Write Integration Tests in Postman, Fill Backlog issues, General Documentation| Group activities - Fix Persistence, Domain Model, QA Plan, Code Reviews and Debugging, Code Coverage Analysis  Individual -  Fix Persistence Tests from deliverable 1, Write Service Classes and business methods, Write controller classes and integration methods, Write Dto Methods, Clean up code, Document Restful methods, Write Unit Tests, Write Integration Tests in Postman, Fill Backlog issues, General Documentation | Group activities - Fix Persistence, Domain Model, QA Plan, Code Reviews and Debugging, Code Coverage Analysis  Individual -  Fix Persistence Tests from deliverable 1, Write Service Classes and business methods, Write controller classes and integration methods, Write Dto Methods, Clean up code, Document Restful methods, Write Unit Tests, Write Integration Tests in Postman, Fill Backlog issues, General Documentation |
|Deliverable 1 - Efforts in hours| 32 hours| 32 hours| 32 hours| 22 hours (Spoke to prof about situation)| 32 hours|

### Key Design Decisions
For more details on any design decisions, please explore the wiki pages. 
* **Key Domain Model Change:** adding a token to all accounts to easily check if a user is logged in or not
* **Key Domain Model Change:** removed useless isAvailable boolean from garage class
* **Key Domain Model Change:** changed service to offered service to avoid confusion with service package and classes
* **Key Domain Model Change:** removed userAccount superclass for simplicity with databases and tests
* **Key Domain Model Change:** linked business information to admin account based on feedback
* **Key QA Decision:** Established code conventions to keep our codebase uniform. These include using javadocs, organizing code in classes in a package in the same way our domain model is organized, and using InvalidInputException to throw any errors.
* **Key QA Decision:** Restful services will be documented in the wiki and Postman integration tests will be showcased as screenshots in the repository.
* **Key Task Accomplished:** an error in the project prevented integration tests from being written or run. As a team, we chose to rebuild the project from scratch. We copied files slowly to find the error and in the end replaced the old project with the new one since it worked with Springboot and Postman.
* **Key Project Change:** in order to run service and integration tests, you must import spring-data-rest-core-3.0.7.RELEASE.jar into your library in the build path.
* **Key Project Change:** we added a Blocked column to our project to keep track of issues that are dependent on other issues before they can be completed. 

***
## Sprint 3
### Running the website
* Heroku Credentials: 
> Host: ec2-3-213-85-90.compute-1.amazonaws.com <br/>
> Database: d5c0lp280rnr23 <br/>
> User: fafwfyfrjfnhgc <br/>
> Port: 5432 <br/>
> Password: 4d426e83bc615cec96f331fcac95821b92300121157528f4c33ed13d42df5f44 <br/>
> URI: postgres://fafwfyfrjfnhgc:4d426e83bc615cec96f331fcac95821b92300121157528f4c33ed13d42df5f44@ec2-3-213-85-90.compute-1.amazonaws.com:5432/d5c0lp280rnr23 <br/>
> Heroku CLI: heroku pg:psql postgresql-closed-55802 --app vehiclerepairshop-backend-g06 <br/>
* Run the backend:
Right click on the VehicleRepairShopApplication.java and Run as on SpringBoot App
* Run the frontend:
Open the command line / terminal, cd to the VehicleRepairShop-Frontend and run "npm run dev". <br/>
You might need to install npm plugins. Follow the instructions in the command line to install the necessary plugins.


### Team
This table shows an overview of the team members and their roles for Sprint 3. The work done in group and more design decisions can be found on the [Meeting Minutes - Sprint 3](https://github.com/McGill-ECSE321-Winter2021/project-group-06/wiki/Meeting-Minutes---Sprint-3).
|Deliverables|Aurelia Haas|Catherine Caron|Cheng Chen|James Darby|Mike Wang|
|:----------:|:----------:|:-------------:|:--------:|:---------:|:-------:|
|Deliverable 3 - Roles and Activities|Group activities - Architecture Design and Documentation, Peer Programming and Debugging, UI colors and component design decisions, Overall documentation <br/>Individual - Frontend for All Accounts (with Catherine), Frontend for Navigation Bar, Frontend for Car, Frontend for Business Information, Frontend for Home page, Meeting Minutes |Group activities - Architecture Design and Documentation, Peer Programming and Debugging, UI colors and component design decisions, Overall documentation <br/>Individual - Frontend for All Accounts (with Aurelia), Frontend for Car, Frontend for Business Information, Frontend for Home page  |Group activities - Architecture Design and Documentation, Peer Programming and Debugging, UI colors and component design decisions, Overall documentation<br/>Individual - Frontend for Appointment (with Mike), Frontend for Garage (with Mike), Frontend for Car, Frontend for Timeslot (with Mike) | Group activities - Architecture Design and Documentation, Peer Programming and Debugging, UI colors and component design decisions, Overall documentation <br/>Individual - Frontend for Car, Frontend for Business Info  | Group activities - Architecture Design and Documentation, Peer Programming and Debugging, UI colors and component design decisions, Overall documentation <br/>Individual - Frontend for Appointment (with Cheng), Frontend for Garage (with Cheng), Frontend for Offered Service, Frontend for Timeslot (with Cheng)|
|Deliverable 3 - Efforts in hours| 32 hours| 32 hours| 32 hours| 32 hours| 32 hours|

### Key Design Decisions
For more details on any design decisions, please explore the wiki pages.
 
* As a team, we agreed on specific colors and UI elements to keep our app cohesive. 
* We chose to represent our project with a layered architecture because we can split it into four distinct layers that interact with each other in order. 
* This architecture was chosen because it is easy to add layers during the development process. We built our project from the bottom up, and the clearly defined layers made this a simple process. 
* The distinction between the layers makes it easier for the team to understand how the code works. We know which layer deals with what functionalities, so we can easily navigate the project.
* When we started testing our frontend error handling, we realized some errors did not have error messages, so we made modifications to our service classes to capture these errors instead of relying on the browser's error messages.
* We chose to have different vue files for each account type. This was simpler to implement, but it means we have a lot of files. We chose to continue with it despite the extra files because we could easily locate specific functions and because the views are quite different for each account anyway. 
* We agreed to have many web pages to represent the different business functionalities of our app, rather than grouping them into fewer pages. This makes our app cleaner and easier to use. The navigation bar is available to the user to jump to specific pages based on what they need to accomplish.  
* We added methods to the admin account to allow the admin to delete any user without them having to be logged in. 
* We changed our design to only allow for one garage instance to exist. This made the appointment booking process simpler because it means appointments can never overlap. 
* Since only one instance of garage can ever exist, we chose to create one for the user and only allow them to edit it. This way, we didn't need to implement creating or deleting features for the user, which would almost never be used anyway. This use case is so rare that we felt it was not detrimental to eliminate it. 
* When we started implementing the frontend, we realized there were many methods in our backend that were not useful for what we agreed to do in the frontend. We decided not to use these methods, but we kept them because, in a production setting, they could be used for future features the client may want to implement. These methods were tested using unit and integration tests, so we agreed it was safe enough to leave in the code without it generating bugs in the user experience. 

***
## Sprint 4
### Running the app
* Heroku Credentials: 
> Host: ec2-3-213-85-90.compute-1.amazonaws.com <br/>
> Database: d5c0lp280rnr23 <br/>
> User: fafwfyfrjfnhgc <br/>
> Port: 5432 <br/>
> Password: 4d426e83bc615cec96f331fcac95821b92300121157528f4c33ed13d42df5f44 <br/>
> URI: postgres://fafwfyfrjfnhgc:4d426e83bc615cec96f331fcac95821b92300121157528f4c33ed13d42df5f44@ec2-3-213-85-90.compute-1.amazonaws.com:5432/d5c0lp280rnr23 <br/>
> Heroku CLI: heroku pg:psql postgresql-closed-55802 --app vehiclerepairshop-backend-g06 <br/>
* Run the backend:
Right-click on the VehicleRepairShopApplication.java and Run as on SpringBoot App
* Run the frontend:
1. Navigate to the orphan Branch "android" 
2. Use either an emulator or an android phone which you will connect to your laptop
3. Verify that you can see your device before running (you might need to put your device in developer mode)
4. Launch the code 
5. Check your emulator/android device and then follow the [User Documentation Android](https://github.com/McGill-ECSE321-Winter2021/project-group-06/wiki/User-Documentation---Android) while using the app





### Team
This table shows an overview of the team members and their roles for Sprint 4. The work done in group and more design decisions can be found on the [Meeting Minutes - Sprint 4](https://github.com/McGill-ECSE321-Winter2021/project-group-06/wiki/Meeting-Minutes-Sprint-4).
|Deliverables|Aurelia Haas|Catherine Caron|Cheng Chen|James Darby|Mike Wang|
|:----------:|:----------:|:-------------:|:--------:|:---------:|:-------:|
|Deliverable 4 - Roles and Activities|Group activities - Peer Programming and Debugging, UI colors and component design decisions, Overall documentation <br/>Individual - Login, Reminders, View Account, Manage Account, Navigation (all with Catherine), Report, Meeting Minutes|Group activities - Peer Programming and Debugging, UI colors and component design decisions, Overall documentation <br/>Individual - Login, Reminders, View Account, Manage Account, Navigation (all with Aurelia), Report, Meeting Minutes|Group activities - Peer Programming and Debugging, UI colors and component design decisions <br/>Individual - Appointment, Offered Service, Receipts (all with Mike)| Group activities - Peer Programming and Debugging, UI colors and component design decisions, Navigation around app <br/>Individual - Business Information page, View Car page | Group activities - Peer Programming and Debugging, UI colors and component design decisions <br/>Individual - Appointment, Offered Service, Receipts (all with Cheng) |
|Deliverable 4 - Efforts in hours| 23 hours| 23 hours| 23 hours| 23 hours| 23 hours|

### Key Design Decisions
For more details on any design decisions, please explore the wiki pages.
* We decided to only have the customer account available for the application. We imagined the customer would find the app the most useful.
* We chose the features to implement for the app. Since the customer will be using it, we decided most of the features should be view only. This allows them to view the business information, their cars, the offered services, and their upcoming appointments much more easily than if they had to use the website. This decision also simplified our work by not having to implement the appointment booking feature in the app, which is a very complicated method to do. We did decide to have the customer log in, and we let them modify their account. This was a simpler feature to implement, especially once login was implemented. 
* We chose features such that we would have more time to implement two new features: view receipts and get reminders. We implemented the feature to view the receipts for past appointments, and we added a reminder that appears when the customer is due for another appointment for a specific offered service. 
* When implementing the android frontend, we decided to use the Volley library instead of AsyncHttpClient. This was more straightforward for the team, had more documentation available online, and offered more features that we could take advantage of. 
* As a team, we decided to keep the UI colors from our webpage and use them for our app as well. We also only tested on light mode, which as a team we thought was acceptable. 
