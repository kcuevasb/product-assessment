## Product-assessment 

- El ejercicio se ha desarrollado intentando seguir arquitectura hexagonal, por esto, se ha dividido en 3 secciones:
  - **Infrastructure**
  - **Application**
  - **Domain**

- Se ha creado un script (schema.sql) que inserta los datos necesarios al arrancar el proyecto.

## Arranque

- La aplicación expondrá un endpoint al que atacar, /product-assessment, además de varios adicionales gracias a la librería de OpenAPI.

- Como se cargan datos al iniciar el proyecto, se puede lanzar una primera prueba directamente:
  - GET -> http://localhost:8080/product-assessment/search?productId=35455&brandId=1&appDate=2020-06-16T21:00:00


## Test

- Se han creado tanto test unitarios como de integración y e2e.