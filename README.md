# P000177CSITCP-Weka-in-AWS-Cloud

WEKA (Waikato Environment for Knowledge Analysis) is a free open-source software that was developed at the University of Waikato, New Zealand.
The current user interface of WEKA doesn’t make it any easier for students to use it. It is a complex software which is not very user-friendly especially for students with no technical experience.
Our team together with Professor James Harland (Director, RMIT STEM Centre for Digital Innovation), Dr.Robert Shen (Director, RMIT RACE Hub) and Dr.Haytham Fayek (AI/ML Lecturer at RMIT) have created an easy-to-use version of WEKA, with the same behaviour and features, and with an improved user-interface.
Except our version of WEKA is a website you can use on any browser.

### Our team
* **Sai Sreshtaa Turaga** - Frontend and Backend Developer - sreshtaa.t@gmail.com
* **Yitao Ma** - Backend Developer and Tester - ethanyitaoma2001@gmail.com
* **Zhihong Deng** - Frontend and Backend Developer, Tester 
* **Zehu Liu** - Backend Developer and Tester 
* **Christoper Adrianus Sindarto** - Frontend and Backend Developer 

### Instructions to run this application:
1. Inside every microservice folder in the Backend folder (classifyMicroservice, clusterMicroservice, filterMicroservice, loadDataMicroservice),
   follow these steps:
   1. right click on the 'pom.xml' file
   2. click on 'add as maven project'
2. Make sure you are inside the project folder (P000177CSITCP-Weka-in-AWS-Cloud) in your terminal/command line 
3. Enter this command: cd Frontend/react_app/
4. Now run: npm install
5. Try running: npm start
6. If you face any errors after you perform Step 4, run the command "npm list", 
and check the following packages are present inside the output list 
(If these are not present, install them while you are still inside the 'Frontend/react_app/' folder):
   1. bootstrap@5.2.1
   2. react-bootstrap@2.5.0
   3. react-dom@18.2.0
   4. react-router-dom@6.3.0
   5. react-scripts@2.1.3
   6. react@18.2.0
   7. axios@0.27.2
   8. web-vitals@2.1.4
7. After running 'npm start', the website should automatically open in your browser.
8. Now you will need to change the paths present in the following classes inside the 'Backend' folder, 
to your own absolute paths (Further instructions on how to do this have been provided as comments in the files):
   1. Line 26 inside ClassifyController.java
   2. Lines 30, 33 inside ClusterController.java
   3. Line 21 inside FilterController.java
   4. Lines 26, 59, 65, 84 inside LoadDataService.java
9. Run the following classes inside the backend folder:
   1. ClassifyMicroserviceApplication.java
   2. ClusterMicroserviceApplication.java
   3. FilterMicroserviceApplication.java
   4. LoadDataMicroserviceApplication.java
10. Now the website should function as expected. Enjoy!

### All tests are present inside the 'final-tests' branch