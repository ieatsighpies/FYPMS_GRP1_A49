# SC2002 Assignment AY2023

## NTU Final Year Project Management System
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)    
![cover](https://drive.google.com/uc?export=view&id=1GX_HnXsmmYM1JdbecMcJFP4w3QGlWqCs)   
### Team Member:
- [Oscar Qian](https://github.com/oscarqjh)
- [Asher Lim](https://github.com/ieatsighpies)
- [Zhaoding Chew](https://github.com/ZDchew)
- [Prateek Potdar](https://github.com/PRATEEKA001)
- [Khanh Nguyen](https://github.com/ngk2305)
---

### Environment Set up

This project is developed with [Java(TM) SE Runtime Environment (build 20+36-2344)](https://www.oracle.com/java/technologies/downloads/) and tested on [Visual Studio Code](https://code.visualstudio.com/).

**To run the application:**   
First, clone the repository into local device from github and ensure that directory is properly set up:
```
├── src   
|   ├── Data copy  
|   ├── main
|   |   ├── Data
|   |   ├── Models
|   |   ├── Pages
|   |   ├── Utils
``` 
Next, compile all files in terminal:
```
javac -cp . main\Pages\*.java
javac -cp . main\Models\*.java
javac -cp . main\Utils\*.java
javac -cp . main\*.java
```  
Finally, run `MainApp`:
```
java main/MainApp
```
Note: `Data copy` contains a fresh copy of the csv data files used in our app. You can easily factory reset the application by discarding the csv files in main/Data file and replace them with a copy of the csv files in `Data copy`.

---

### Section 1: Introduction
The main objective was to design the Final Year Project Management System (FYPMS), where students can register and deregister projects as well as send requests to supervisors/coordinators for approval. The overall design is based on Object-Oriented Programming (OOP) principles, namely encapsulation, abstraction, inheritance and polymorphism which is used in our menus for the entire application. Another guiding element for the design is the usage of SOLID design principles. The SOLID design principles are composed of single responsibility principle, open-closed principle, Liskov substitution principle, interface segregation principle, and dependency inversion principle.   

---

### Section 2: Design Principles
Through out this project we used SOLID principle extensively as a guideline for our designing process of the application[1].   

**Single-responsibility principle (SRP)**   
Each type of class is catered to its role. There are three types of classes, and each type of class has a single responsibility. Our system is designed such that the classes are catered to do only one job. For instance, the registering of FYP projects is separated into multiple Pages i.e the coordinator facing pages(CoordAllProject, CoordEditTitle, CoordMyPRoject, etc.). Utils i.e. the logic and data handling (Authenticator, FileHandler, ReqType3, etc.) and Models i.e. data structures that are manipulated (Student, Supervisor, Coordinator, Request, etc.) instead of a single class to handle the entire process. This not only facilitates code readability, but also allows for ease of modification to our system should there be changes to a specific part of the system.    

**Open-closed principle**   
OCP is upheld as we can make extensions to the module without modifying the source code. Request is the abstract parent class of all its child concrete classes, the other sub-classes that extend it. Thus, new requests can be added by extending the Request class without modifying the Request source code. Because we have a closed abstract class, and it's open for extension, we could extend the request class and add on more features without changing the source code of the parent class. New features added thus would not lead to a breakage in code.

**Liskov's Substitution principle**   
The Liskov Substitution Principle (LSP) states that objects of a superclass should be replaceable with objects of its subclasses without breaking the application. Such can be demonstrated by the classes User and Student. Our Login which uses the base class User still continues to function properly even if a derivation of User such as Student or Supervisor is passed into it. This is because subclasses of the User class provide no less functionalities than their base class, and the subclasses do not expect more than their base class.

**Interface Segregation principle**   
The interface segregation principle states that no code should be forced to depend on methods it does not use. We use multiple Interfaces eg. InitialiseRequest and InitialiseProject extend from the main Models, but we do not segregate the interface because they belong to different categories. For example, the supervisor class implements both IinitializeRequest and InitialiseProject.    

**Dependency Injection principle**   
The dependency injection principle states that a high level module should not depend upon low level modules, where both should depend on abstractions. In our Request class, it contains an abstract method “printInfo()” and “processRequest()”.  This acts as the abstraction layer which is implemented differently by the lower level modules (subclasses of Request) to print its own functionalities to be seen by the user, this abstraction allows for loose-coupling, low dependency of code. Ergo, if there were to be a change of code, other files would not need to be modified.

---

### Section 3: Features

In addition to the above, the following new features were also added:

1. Users’ passwords are hashed with an encryption[2] algorithm ([PBKDF2WithHmacSHA1](https://en.wikipedia.org/wiki/PBKDF2)). This ensures the password to be secured even in the event that source file is leaked.   

2. Password is hidden in console when the user is typing to provide a safer way for the user to securely enter a password.   

3. Functionality to sort projects and requests by a particular feature. Projects and requests could be sorted via status, student name, ID, etc. This would improve the convenience of the user experience and allow users to organize students/projects as per their preference. 

4. When project and request IDs are date time based unique IDs. To reduce the chance of duplicate IDs from being generated when 2 users generate a ID at the same time, the UID Generator we used is able to generate 65536 UID in the same ms.   

---

### Section 3: Difficulties Faced
Some difficulties encountered include hurdles that arose in the OOP design, difficulty in implementing the request class due to the complexity in the accountability (whether supervisor or coordinator approves them) and execution of requests, the need to find ways to avoid tight-coupling between classes, and increased complexity in the UML relationships. To solve the same, a thorough analysis of the relationship between objects was undertaken. SOLID principles were applied to manage classes and design principles were followed in UML class diagram creation. Additionally, debugging and revision were performed to refine the function.   

Furthermore, specifically for the request class, we decided to change our approach. Initially, we had decided to implement the four different request types as separate utils classes (RequestType1, RequestType2, etc.). However, this was proving too complex and inefficient. Instead, the request class was implemented as a abstract class which will be inherited by the different types of request child classes. We added an abstract processRequest() method in the Request class, which will be implemented by the four child request classes. This allows more types of request to be added to our application in the future with ease.

---

*Submission: This repository is submitted to Nanyang Technological University Singapore as a project for module SC2002 AY2023*

<h3 align="center">Reference</h3>

[1]: [*The SOLID Principles of OO Design Explained*](https://www.freecodecamp.org/news/solid-principles-single-responsibility-principle-explained/)   
[2]: [*How to Encrypt Password in Java?*](https://www.javatpoint.com/how-to-encrypt-password-in-java)   