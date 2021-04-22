# __Magneto Mutants__
Challenge MercadoLibre

### Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.
### Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.

<img style="display:block; margin: 0 auto;" alt="Magneto" src="https://user-images.githubusercontent.com/58950018/115470578-ef1e9c00-a20c-11eb-974d-810d81e114d0.png">

---

## __Tecnologías__
- Java 8
- Spring Boot
- MongoDB
- Maven
- AWS

## __Lanzar la aplicación__
Deberá clonarse el repositorio y, situado sobre la raíz del proyecto, lanzar el comando mvn spring-boot:run

### Consideraciones
- Deberá tener:
  - Una instancia MongoDB en su ambiente, los parámetros de conexión a la base puede encontrarlos en el archivo de configuración application.properties
  - Java 8 o superior instalada en su entorno
  - Maven
  - Conexión a internet para bajar las librerías de los repositorios remotos que les sean necesarias para compilar y ejecutar el proyecto

---

## __API URLs__

### Desarrollo: http://localhost:8080/v1/api
### Producción: http://ec2-3-141-1-63.us-east-2.compute.amazonaws.com:8080/v1/api

## __Servicios publicados__

### /mutant - Verifica si un humano es mutante

``` 
POST /v1/api/mutants 
```
#### Body (ADN Humano)
```
{"dna":["AATACT", "CCCAGA", "GGGATT", "AATTCC", "GGATCG", "TCACTG"]}
```
#### Response
``` 
403 Forbidden 
```

#### Body (ADN Mutante)
```
{"dna":["ATGCGA", "CAGGGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]}
```
#### Response
``` 
200 OK 
```

### /atats - Retorna las estadísticas de los humanos verificados, dando cantidades y el ratio de mutantes encontrados
``` 
GET /v1/api/atats 
``` 

#### Response
``` 
{
    "ratio": 6.0,
    "count_mutant_dna": 6,
    "count_human_dna": 0
}
```
