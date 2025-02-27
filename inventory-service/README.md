# ECOMMERCE - INVENTORY SERVICE

## About

Software component developed to handle end user operations on their own inventory, such add a new product, remove an article or update its quantity.
   
It consists of 2 main services and a bonus:
- **Spring Boot** server application.
- **MongoDB** NoSQL database.
- **Mongo Express** GUI to manage and monitor the database server.

## Pre-requisites

### Technologies
**Docker** and **docker-compose** installed on your machine.

### Environment variables
- `APP_PORT`
- `DB_USERNAME`
- `DB_PASSWORD`

## Local deployment

The first step is run the `docker-compose build` command to build docker images from these three services. Then, starting containers executing `docker-compose up` in the CLI.