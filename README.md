# CSX42: Assignment 3
**Name:** Ashmeet kaur Chhabra

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [studentskills/src](./studentskills/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile studentskills/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile studentskills/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile src/build.xml run -Dinput="input.txt" -Dout1="output1.txt" -Dout2="output2.txt" -Dout3="output3.txt" -Dmodify="modify.txt" -Derror="error.txt" -Ddebug="debug.txt"
```
Note: Arguments accept the absolute path of the files.


## Description:

Classes:
1. Driver.java
2. StudentRecord.java
3. StudentRecords.java
4. TreeHelper.java
5. FileProcessor.java
6. LineHandler.java
7. MyLogger.java
8. Results.java

Interfaces:
1. ObserverI.java
2. SubjectI.java
3. StdoutDisplayInterface.java

Enum:
1. StudentDetails.java

Description:
1. The StudentRecord is a node of the Tree, StudentRecords. The Tree, T0 has the 2 replicas T1,T2. 
2. Every time the node is inserted in the tree, the nodes are also inserted in the replicas. Every node in one tree is the observer of every node of other tree in same heirarchy.Ex: Left child of T1 is oberver of left child of T2 and T3, Left child of T2 is oberver of left child of T1 and T3 and Left child of T3 is oberver of left child of T1 and T2.
3. StudentRecords is the tree which has insert, search and printTree functionality
4. From Driver the execution of the assignment starts.
5. With the first argument as input file: First the lineInputProcessor() in LineHandler class and creates the map of the values.
6. The insert() of Tree Helper class will then be called to insert the nodes or the studentReccord in the trees and replicate it to other trees.
7. The Replica of the trees are made in Constructor of the TreeHelper Class.
8. Now the modify file is encountered, which is again processed by lineModifyProcessor() in LineHandler class and creates the map of the values.
9. using the bNumber value in modify file we will search a record to modify in TreeHelper class.
10. Now that node is modified in StudentRecord class, of a particular tree, the other 2 nodes of different trees will be the oberver and now that nodes will be modified when notifyAll() is called. 
11. Here Observer Pattern is used.
12. All the errors are printed to the error file. 
13. The Tree1 nodes are inserted to output1.txt,Tree2 nodes are inserted to output2.txt, Tree3 nodes are inserted to output3.txt
15. The Debug level is given by the user and on that level the lines are printed to console.  


## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 10 July 2020


