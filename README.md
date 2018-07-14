# ATM

##My Springboot + Maven + Mysql with Resftful API project.

#Download

`git https://github.com/mengyangbai/ATM.git`

To check the project, just set the database using application.properties .

And run the atm.sql to setup a database for this project

And run on the project directory.

`mvn clean package -DskipTests`

Why skip tests? 

`https://stackoverflow.com/questions/16420935/a-required-class-was-missing-while-executing-org-apache-maven-pluginsmaven-sure`

And go to target, run

`java -jar .\atm-0.1.0.jar`
