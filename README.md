Cloud Workshop

This repository contains a bootstrapped Java Spring Boot application for the Cloud Workshop. The application was generated with Spring initializr and contains Spring Web, Spring Data JPA and PostgreSQL driver dependencies. To use a different database, simply replace the PostgreSQL Driver with the one of your choice (e.g., MS SQL Server Driver).

The workshop is hosted for the 4th-semester Software Engineering students at VIA University Horsens. Throughout the workshop, students will learn how to deploy applications to AWS and utilize the cloud for hosting their Semester projects online.

Architecture

To host the Spring Boot application we will use AWS Elastic Beanstalk, and AWS RDS to host the database. Both of the services are fully managed, require minimal setup and are part of the AWS Free Tier. The Spring Boot application will connect to the RDS database and will be exposed to the internet via a Web API.
