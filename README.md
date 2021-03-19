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
