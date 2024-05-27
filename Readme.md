# Aplicativo "exercise"

Este proyecto es una aplicación Spring Boot diseñada para la creación de usuarios, utilizando Spring Security para manejo de autenticaciones y HSQLDB como base de datos en memoria.

## Requisitos

- JDK 17
- Maven 3.x

## Cómo correr la aplicación

1. **Clonar el repositorio**

   ```bash
   git clone https://github.com/Ridaniel/LoginExcercise.git
   cd LoginExcercise
   ```

2. **Construir el proyecto**

   Utiliza Maven para construir el proyecto:

   ```bash
   mvn clean install
   ```

   Esto también ejecutará las pruebas unitarias del proyecto.

3. **Ejecutar la aplicación**

   Una vez construido el proyecto, puedes iniciar la aplicación usando:

   ```bash
   mvn spring-boot:run
   ```

   Esto iniciará la aplicación en el puerto predeterminado 8080.

## Acceder a la aplicación

- **Acceso a la API**: Navega a `http://localhost:8080` en tu navegador.
- **Consola H2**: Accede a `http://localhost:8080/h2-console` para interactuar con la base de datos HSQLDB.
- **Documentación Swagger UI**: Para ver la documentación de la API y probar los endpoints, visita `http://localhost:8080/swagger-ui.html`. Aquí encontrarás todos los endpoints disponibles con la opción de ejecutarlos directamente desde la interfaz de usuario de Swagger.

## Seguridad

Las credenciales predeterminadas para acceder a la aplicación son:
- **Usuario**: user
- **Contraseña**: password