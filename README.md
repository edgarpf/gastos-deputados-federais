# Gastos dos deputados federais [![Build Status](https://travis-ci.org/edgarpf/gastos-deputados-federais.svg?branch=master)](https://travis-ci.org/edgarpf/gastos-deputados-federais) [![Maintainability](https://api.codeclimate.com/v1/badges/2902440b44466c6c9ffa/maintainability)](https://codeclimate.com/github/edgarpf/gastos-deputados-federais/maintainability)
Uma API REST feita em Spring Boot para monitorar os gastos deputados federais

## Docker
```
docker container run -p 8098:8098 edgarpf/gastosdeputadosfederais
```

Acesse http://localhost:8098 e você verá os gastos dos deputados no ano corrente retornados em JSON conforme figura abaixo.

![JSON](https://i.ibb.co/RBfB8Qd/Capturar.png)

# FAQ

## Que dados são exibidos no JSON?

Gastos dos deputados federais no ano atual.

## De onde vem os dados dos gastos dos deputados?

[Dados abertos](https://www.camara.leg.br/transparencia/gastos-parlamentares)

## Alguns dados estão vindo null

Confira na página específica do deputado em questão. Alguns dados não estão disponíveis e pode levar algum tempo até que estejam.

## Como funciona a aplicação?

1 - Quando a aplicação inicia ela starta um srapper na página de gastos de cada deputado e armazena esse dados em um banco na mémoria(h2)

2 - Caso algum cliente faça uma requisição esse banco é consultado e todos os deputados e dados são retornados em JSON.

3 - Á meia-noite o scrapper é acionado novamente e regrava os dados na memória mantendo-os atualizados.




