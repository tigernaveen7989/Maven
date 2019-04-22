This document provides information on the framework structure, features included and also keep providing 
the changes to the framework

----------------------
Installations required
----------------------
Following need to be setup to be able to use this automation framework effectively
1)Maven
2)JDK 8.0
3)Eclipse
4)Jenkins & associated Plugins

------------------------------------------------------------------------------
Brief about the framework structure
------------------------------------------------------------------------------

-------------
Base Project :
-------------
This will contain all the reusable components needed for all the automation projects
1)Common utilites like JSON parsers, File Handlers
2)Driver object creation and capability management
3)Test data management utilities
4)Base objects of page and test which will be consumed by child projects
5)Resources contain the drivers , log utilties needed for log management
6)Utilities needed for Galen UX validation.

------------------------------------------------------------
Child projects
------------------------------------------------------------
AHQ -  AHQ page objects and automation scripts will be part of this project
EAHELP -  EAHELP page objects and automation scripts will be part of this project
OMEGA -  OMEGA page objects and automation scripts will be part of this project
SALESFORCE -  SALESFORCE page objects and automation scripts will be part of this project
THOR -  THOR page objects and automation scripts will be part of this project
SOVEREIGN -  SOVEREIGN page objects and automation scripts will be part of this project
INTEGRATION -  This project will contain test artifacts related integration tests spanning across the above mentioned projects


