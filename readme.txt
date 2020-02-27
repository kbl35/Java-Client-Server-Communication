Project 1

Student: Ky Le

=======================================================================================================
In this project, you are going to write a client-server program using TCP. The client sends requests to the server and the server sends the reply back 
after processing the requests. The client can send the following requests to manage a simple student database on the server. You can randomly use a 
6-digit numerical number as a student's ID. We assume a student's first and last names are less than 10 charactersâ€™ long. A student's information 
includes his/her ID, first and last names, and his/her score (0-100) for the network course. Run your server program in zeus and your client program in eros.  

1. add(ID, Fname, Lname, score): this request adds a new student's information into the database.

2. display(ID): this request sends the ID of a student to the server and the server returns the information of the student.

2. display(score): this request sends a score to the server and the server returns the information of all the students whose scores are above the sent score.

3. display_all: this request displays the information of all the students currently in the database. 

4. delete(ID): this request deletes the student entry with that ID.
===============================================================================================================
* this project is written in Java programming language. Therefore, in order to run this project on Unix server, you need to:
NOTE: this program includes 3 .java program:
						javac GreetingClient.java
						javac GreetingServer.java
						Student.java
	and a text file as a storage called studentInfor.txt

Procedure to run:

1. compile two .java files using command line:
	javac GreetingClient.java
	javac GreetingServer.java

2. run server file first using command line (Run your server program in zeus):
	java GreetingServer 

3. run client file using command line (Run your client program in eros):
	java GreetingClient

4. Client terminal will display a menu of all the opotions as: a, b, c, d, e, q.
5. The program will run in a do-while loop with some input validations. it only exits the loop once you enter 'q' as a choice.


* if there is any problem, please let me know at kbl35@txstate.edu. thanks

	