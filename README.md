# Climate Watch - Spring Boot

Proyecto web de clima desarrollado con Spring Boot + Thymeleaf.

## Funcionalidades

- Página principal con diseño climático.
- Página de pronóstico de demostración.
- Página de novedades cargada desde `src/main/resources/data/novedades.json`.
- Endpoint JSON: `GET /api/novedades`.
- Página de contacto.

## Requisitos

- Java 17 o superior.
- Maven 3.9+.

## Ejecutar

Desde la carpeta del proyecto:

```bash
mvn spring-boot:run
```

Luego abrir:

- http://localhost:8080/
- http://localhost:8080/pronostico
- http://localhost:8080/novedades
- http://localhost:8080/contacto
- http://localhost:8080/api/novedades

## Modificar novedades

Edita el archivo:

`src/main/resources/data/novedades.json`

Puedes agregar, eliminar o cambiar objetos manteniendo los campos:

- id
- titulo
- descripcion
- fecha
- tipo
- imagen
