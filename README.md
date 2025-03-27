# user-registration-api

API RESTful para registro de usuarios desarrollada con Spring Boot.

## Requisitos

- Java 17
- Maven

## Ejecución

1. Clonar el repositorio
2. Ejecutar: `mvn spring-boot:run`
3. La aplicación estará disponible en `http://localhost:8080`

## Endpoints

- POST `/api/users`: Registrar un nuevo usuario

## Ejemplo de solicitud

```json
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "Hunter2!",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
