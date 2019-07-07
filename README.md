# Gastos dos deputados federais [![Build Status](https://travis-ci.org/edgarpf/gastos-deputados-federais.svg?branch=master)](https://travis-ci.org/edgarpf/gastos-deputados-federais) [![Coverage Status](https://coveralls.io/repos/github/edgarpf/gastos-deputados-federais/badge.svg?branch=master)](https://coveralls.io/github/edgarpf/gastos-deputados-federais?branch=master) [![Maintainability](https://api.codeclimate.com/v1/badges/2902440b44466c6c9ffa/maintainability)](https://codeclimate.com/github/edgarpf/gastos-deputados-federais/maintainability)
A REST API made with Spring Boot to monitor brazilian deputies spending.

## Docker
```
docker container run -p 8098:8098 edgarpf/gastosdeputadosfederais
```

Access http://localhost:8098 and you will see brazilian deputies spending in the year in JSON format like the image below.

![JSON](https://i.ibb.co/RBfB8Qd/Capturar.png)

# FAQ

## Where does the data come from?

[Dados abertos](https://www.camara.leg.br/transparencia/gastos-parlamentares)

## Some data are shown as null

Some data are not displayed. Try to contact Dados Abertos to request a update.

## How does the application work?

1 - When the application starts a sraper will read each deputy`s page and store the data in a memory database(h2).

2 - If a cliente makes a request to the application this database is read and the data are returned in JSON format.

3 - At midnight the scraper will run again and the data will de updated.




