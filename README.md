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

### Environment Set up

For this project, we are using [Java(TM) SE Runtime Environment (build 20+36-2344)](https://www.oracle.com/java/technologies/downloads/)   

To run the application:   
First, clone the repository into local device from github.   
Next, compile all files in terminal:
```
javac -cp . main\Pages\*.java
javac -cp . main\Models\*.java
javac - cp . main\Utils\*.java
javac -cp . main\*.java
```  
Finally, run `MainApp`:
```
java main/MainApp
```


---

### Report Summary
The main objective was to design the Final Year Project Management System (FYPMS), where students can register and deregister projects as well as send requests to supervisors/coordinators for approval. The overall design is based on Object-Oriented Programming (OOP) principles, namely encapsulation, abstraction, inheritance and polymorphism which is used in our menus for the entire application. Another guiding element for the design is the usage of SOLID design principles. The SOLID design principles are composed of single responsibility principle, open-closed principle, Liskov substitution principle, interface segregation principle, and dependency inversion principle. 

Students are able to view all available projects (view if their registered project fails), request for registering project, request of changing of title of project, and view request of history and status. Additionally, the student can request to deregister for FYP. After the FYP coordinator approves, the student won't be able to view the project.

The supervisor can view information of projects submitted by students, request FYP coordinator to transfer student, modify the title of projects, view student pending, ingoing and outgoing requests, and accepting and rejecting requests.

The Projects are on a first come first serve basis. Users can view the status of a project (reserved or not), as well as view all relevant information regarding the project. The FYP coordinator can submit a project and approve the request, as well as deregister a student. It should be noted that only the coordinator can change supervisors and allocate projects. 

In addition to the above, the following new features were also added:

1. Hashing of password turns users’ password into a short string of letters or numbers using an encryption algorithm. If a website is hacked, cybercriminals don't get access to your password. Instead, they just get access to the encrypted “hash” created by your password.
2. Hide password in console to provide a safer way for the user to securely enter a password.
3. Functionality to sort projects and requests by a particular feature. Projects and requests could be sorted via status, student name, ID, etc. This would improve the convenience of the user experience and allow users to organize students/projects as per their preference. 
4. When project and request id are generated, these long-term can be based on date/time.
5. We added another feature to prevent the same id being generated if two students register at same time, since a very large number of ids can be generated at a single time (so it is unlikely that two same IDs will be generated at once).

Some difficulties encountered include hurdles that arose in the OOP design, difficulty in implementing the request class due to the complexity in the accountability (whether supervisor or coordinator approves them) and execution of requests, the need to find ways to avoid tight-coupling between classes, and increased complexity in the UML relationships. To solve the same, a thorough analysis of the relationship between objects was undertaken. SOLID principles were applied to manage classes and design principles were followed in UML class diagram creation. Additionally, debugging and revision were performed to refine the function.
Furthermore, specifically for the request class, we decided to change our approach. Initially, we had decided to implement the four different request types as separate utils classes (RequestType1, RequestType2, etc.). However, this was proving too complex and inefficient. Instead, the request class was implemented as a method. We added an abstract processRequest() method in the Request class, then the four child request classes will implement the processRequest() method. The CoordinatorViewRequest method was also changed to align with this, and further checks were put in place.

---

*Submission: This repository is submitted to Nanyang Technological University Singapore as a project for module SC2002 AY2023*

<h3 align="center">Reference</h3>
