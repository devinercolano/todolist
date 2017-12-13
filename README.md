# What to install to use the To Do List:

-Install Java and Tomcat on your machine
* **Java**
* https://www.java.com/en/download/help/download_options.xml

* **Apache Tomcat Server**
* Use this tutorial to install tomcat 8 on your local ~/Applications directory:
* https://www.ntu.edu.sg/home/ehchua/programming/howto/Tomcat_HowTo.html

-unzip and move the todoservlet directory into the webapps directory 

-start the tomcat server from /Applications/tomcat/bin

-run (without quotes) “chmod 777 ./catalina.sh”
and
“./catalina.sh run”

-navigate to http://localhost:8080/todoservlet/form_input.html to fill out the form

	--The “delete” button will remove the To Do item from the To Do List
	--The “show” button will expand the To Do item to show the “more details” section
