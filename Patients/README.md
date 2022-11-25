# Mediscreen-Patients

##### Creating the docker network

```
docker network create mysql
```

##### Launch container

```
docker-compose up -d
```



##### Creating the database

The database script is in data.sql file.
It comes with 3 example patients.

##### Editing properties

You can edit the datasource or the server port by editing the application.properties file. 