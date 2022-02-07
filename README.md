# IS-Docker-Microprofile

The project connect two applications, a frontend and a backend. This project creates a Formula 1 rest api using microprofiles.

## First step
First of all, we need to install an Oracle database and payara image in Docker containers, in this case it is Oracle 11gR2XE.
once installed we proceed to add the tables, they are in the sql folder, where we will find the file f1.sql

## Second step
secondly we access the apps folder where we will find the docker-compose.yml file. We execute the file in the docker console in this way it will install the two applications so we can already use them.

## Use
Below is how to access the data of the application, we can access and modify the information of the drivers, teams and cars

### Cars

###### Get cars `http://localhost:(yourPort)/FrontendF1/coches`

###### Get car `http://localhost:(yourPort)/FrontendF1/coches/(id)`

###### Insert car `http://localhost:(yourPort)/FrontendF1/coches/insert` 
*(You need to insert labels modelo, cv, vmax, marca as json)*

###### Update car `http://localhost:(yourPort)/FrontendF1/coches/update/(id)` 
*(You need to insert labels modelo, cv, vmax as json)*

###### Delete car `http://localhost:(yourPort)/FrontendF1/coches/delete/(id)`

### Teams

###### Get teams `http://localhost:(yourPort)/FrontendF1/escuderias`

###### Get team `http://localhost:(yourPort)/FrontendF1/escuderias/(marca)`

###### Insert team `http://localhost:(yourPort)/FrontendF1/escuderias/insert` 
*(You need to insert labels marca, ref, id as json)*

###### Delete team `http://localhost:(yourPort)/FrontendF1/escuderias/delete/(marca)`

### Pilots

###### Get pilots `http://localhost:(yourPort)/FrontendF1/pilotos/(ref)`

###### Insert pilot `http://localhost:(yourPort)/FrontendF1/pilotos/insert`
*(You need to insert labels ref, nombre, apellidos, títulos, marca as json)*

###### Update pilot `http://localhost:(yourPort)/FrontendF1/pilotos/update/(ref)` 
*(You need to insert labels nombre, apellidos, títulos as json)*

###### Delete pilot `http://localhost:(yourPort)/FrontendF1/pilotos/delete/(ref)`
