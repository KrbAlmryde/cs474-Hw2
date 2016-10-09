# README #

This README documents the installation and usage for the cs474 HW2 tasked with using the [SciTools](https://scitools.com) 
Understand API and [JGraphT](https://github.com/jgrapht/jgrapht) library 

### What is this repository for? ###

* Using the [Scala language](http://www.scala-lang.org/) this application parses two **Java** projects, using the 
[SciTools](https://scitools.com) Understand API to generate Dependency and Call graphs with which to determine their 
**Subgraph Isomorphic relationship** to their sibling application and Compute the subgraph's **Transitive Closure**.


### Open-Source Application
This project utilizes the [SMSSync](https://github.com/ushahidi/SMSSync), a SMS gateway for android powered phones, application.
More information about the project can be found at their [website](http://smssync.ushahidi.com/)


### How do I get set up? ###
To run the application, (assuming you are using Intellij) simply execute the SBT task **'run'**. You will then be prompted
to enter the name (re: fully qualified file path) of each of your Understand.udb projects. If your file does not exist,
the application will simply ask you to try again until you get it right. Lets see an example:
 
 
 **<start the application>**
 
 **<JVM does some stuff>**
 
 \**----------------------------------------------------------------------------\** 
 
 *Please enter File#0: \>* **/some/path/to/your/file.udb** 
 
 *Please enter File#0: \>* **/some/path/to/your/other/file.udb** 

**NOTE: Quotation is not necessary. The application assumes you are passing a string.** 

If you would like to run my test application files, make it easy on yourself and type the following at the prompt:
 
 "./src/main/resources/SMSSync-2.7.udb" 
 
 "./src/main/resources/SMSSync-3.0.5.udb"
                    
####Setting up from IntelliJ ####

1) If no project is currently open in IntelliJ IDEA, click **Import Project** on the Welcome screen. Otherwise, select **File | New | Project from Existing Sources**.

2) In the dialog that opens, select the directory that contains the project to be imported, or a file that contains an appropriate project description. Click **OK**.

3) On the first page of the **Import Project** wizard, select SBT, and click **Next**. (This page is not shown if IntelliJ IDEA has guessed what you are importing.)

4) On the next page of the wizard, specify SBT project settings and global SBT settings, click **Finish**.
         
         
### Development Testing
For testing purposes, I utilized the latest release, ***version 3.0.5*** and ***version 2.7***

*Be aware, these projects are pretty big and will take some time to parse. 

To expedite the development, and to test the functionality of the application and the **Understand API** I used a 
handful of very small projects I developed in past courses. Those files are used for the actual Unit Tests proper. 
  
  
### Unit Testing ###
For Unit-testing I utilized [ScalaTest](http://www.scalatest.orge) using the [FunSuite](http://doc.scalatest.org/3.0.0/#org.scalatest.FunSuite). Its fun and was surprisingly simple to get it up and running. 

Go figure. 

To run the tests, in Intellij simplly select the **SBT** task "test" and youll be up and running! Que sera

### Who do I talk to? ###

* If you have any specific questions contact me via [kyle.almryde@gmail.com](kyle.almryde@gmail.com)
* If you have any complaints, please direct them to this [Handsome devil](drmark@uic.edu) 

![drmark@uic.edu](https://www.cs.uic.edu/~drmark/index_htm_files/3017.jpg)