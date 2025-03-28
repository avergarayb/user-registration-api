# User Registration API  

API RESTful para registro de usuarios desarrollada en **Spring Boot 3**, **Java 17** y base de datos **H2**.  

## Características  
✔ **Registro de usuarios** con validación de formato de email y contraseña.  
✔ **Generación automática de UUID, fechas**.  
✔ **Manejo de errores** con respuestas JSON estandarizadas.  
✔ **Swagger UI** para documentación interactiva de la API.  
✔ **Tests unitarios** con JUnit 5 y Mockito.

## Diagrama de la solución

Acceder: `https://www.mermaidchart.com/raw/391cc297-723e-4e47-a600-54ce6ae83130?theme=light&version=v0.1&format=svg`

## Requisitos

- Java 17
- Maven
- H2

## Ejecución

1. Clonar el repositorio

	- git clone `https://github.com/avergarayb/user-registration-api.git`
	- `cd user-registration-api`

2. Ejecutar: 
	- `mvn clean install`
	- `mvn spring-boot:run`
	
3. La aplicación estará disponible en: `http://localhost:8080`

## Acceso consola H2

Acceder a la base de datos H2: `http://localhost:8080/h2-console`

Credenciales:

	JDBC URL: `jdbc:h2:mem:userdb`

	User: `sa`

	Password: `sa`

## Endpoints

- POST `/api/users`: Registrar un nuevo usuario

## Tests Unitarios

Ejecutar todos los tests con: `mvn test`

## Swagger

Acceder a la interfaz en: `http://localhost:8080/swagger-ui/index.html`

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
