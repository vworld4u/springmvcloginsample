# Spring MVC Classic Sample Application
This is a sample application which provides a simple login scenario using following technologies
1. Spring Framework 4
2. Spring boot 
3. Thymeleaf Template Engine for views
4. Spring Hibernate Entities

## What does it contain?
It is similar to [Spring MVC Rest API Sample](https://github.com/vworld4u/springmvcsample/blob/master/README.md) that I did few days back. But it varies in following new features added:
1. Email Verification of the Account (upon registration)
2. Google Re-Captcha Integration

### Email Verification
Once a new user is registered, his email gets verified by sending an Email to his registered email address. The Email consists of a link which verifies the user on click. The Verification code is valid for one day from the registration. If the verification is done after a single day, a new verification is sent to the user.

#### Email Sending with Google Email Id
There was a gotcha of email sending using Google id. So I had to lower the security standards for Google Email sending using links below
1. https://www.google.com/settings/security/lesssecureapps
2. https://accounts.google.com/displayunlockcaptcha
Configure **username** and **password** for google email id inside **application.properties** file

### Google Re-Captcha Integration
You need to register for Google Re-Captcha Service and get its secrets used and configure them in following places
1. **application.properties** - Configure *recaptcha.url*  and  *recaptcha.secret* with url and server secret
2. **register.html** - Configure the client secret key into the recaptcha div inside this page

## How to run the application?
1. It is a standard maven application with spring-boot-starter-web as the starting point.
2. Compile application commandline with mvn compile
3. Package the application into single jar file using mvn package
4. Run the single jar file as java -jar target/<jar-file-name>.jar. It starts the application in port 8080 on your machine.

## Why Thymeleaf?
After working with JSP pages few years back and working on template engines like Handlebars/Mustache, I was searching for something like them and found thymeleaf very promising! I decided to give it a try.

## Which database does it use?
It uses mysql database which can be configured in `src/main/resources/application.properties`. If you want to use HSQLDB for testing, just comment out all properties inside the properties file specified.

 
## What is pending yet?
1. More styling and other pages 
2. Preferably user logged in page should be a single page application with other functionalities