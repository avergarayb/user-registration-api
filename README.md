# User Registration API  

API RESTful para registro de usuarios desarrollada en **Spring Boot 3**, **Java 17** y base de datos **H2**.  

## Características  
✔ **Registro de usuarios** con validación de formato de email y contraseña.  
✔ **Generación automática de UUID, fechas**.  
✔ **Manejo de errores** con respuestas JSON estandarizadas.  
✔ **Swagger UI** para documentación interactiva de la API.  
✔ **Tests unitarios** con JUnit 5 y Mockito.

## Diagramas
  Diagrama de la solución:
- https://www.mermaidchart.com/raw/ecb31561-cde8-46ca-bb15-5e4e160e83fb?theme=light&version=v0.1&format=svg

  Diagrama de componentes:
- https://www.mermaidchart.com/raw/391cc297-723e-4e47-a600-54ce6ae83130?theme=light&version=v0.1&format=svg

## Requisitos

- Java 17
- Maven
- H2

## Script base de datos
- Al iniciar la aplicación se genera la estructura de la base de datos.

		`CREATE TABLE users (
		    id UUID PRIMARY KEY,
		    name VARCHAR(255) NOT NULL,
		    email VARCHAR(255) NOT NULL UNIQUE,
		    password VARCHAR(255) NOT NULL,
		    created TIMESTAMP NOT NULL,
		    modified TIMESTAMP NOT NULL,
		    last_login TIMESTAMP NOT NULL,
		    token VARCHAR(255) NOT NULL,
		    active BOOLEAN NOT NULL
		);
		
		CREATE TABLE phone (
		    id BIGINT AUTO_INCREMENT PRIMARY KEY,
		    number VARCHAR(20) NOT NULL,
		    city_code VARCHAR(10) NOT NULL,
		    country_code VARCHAR(10) NOT NULL,
		    user_id UUID,
		    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
		);`

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
