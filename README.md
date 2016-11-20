# Spring MVC Classic Sample Application
This is a sample application which provides a simple login scenario using following technologies
1. Spring Framework 4
2. Spring boot 
3. Thymeleaf Template Engine for views
4. Spring Hibernate Entities

## What does it contain?
It is same as [Spring MVC Rest API Sample](https://github.com/vworld4u/springmvcsample/blob/master/README.md) that I did two days back.

## How to run the application?

1. It is a standard maven application with spring-boot-starter-web as the starting point.
2. Compile application commandline with mvn compile
3. Package the application into single jar file using mvn package
4. Run the single jar file as java -jar target/<jar-file-name>.jar. It starts the application in port 8080 on your machine.

## Why Thymeleaf?
After working with JSP pages few years back and working on template engines like Handlebars/Mustache, I was searching for something like them and found thymeleaf very promising! I decided to give it a try.

## What is pending yet?
1. MySQL integration 