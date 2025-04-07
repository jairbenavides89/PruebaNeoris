# Guía de Implementación del Proyecto

Esta guía te ayudará a instalar las herramientas necesarias y ejecutar el proyecto utilizando Docker, Docker Compose y Postman.

---

## Paso 1: Instalación de herramientas

- **Instalar Docker:**  
  Descarga e instala Docker Desktop (o Docker Engine) desde [la página oficial de Docker](https://www.docker.com/). Docker incluye Docker Compose de forma nativa.

- **Instalar Docker Compose:**  
  Si tu instalación de Docker no incluye Docker Compose, sigue las [instrucciones oficiales de Docker Compose](https://docs.docker.com/compose/install/).

- **Instalar Postman:**  
  Descarga e instala [Postman](https://www.postman.com/downloads/), una herramienta para probar endpoints de APIs.

---

## Paso 2: Compilar el proyecto

Abre una terminal en la raíz del proyecto y ejecuta el siguiente comando para compilar las imágenes definidas en el archivo `docker-compose.yml`:

```bash
docker compose build
```
---

## Paso 3: Ejecutar el proyecto

Para iniciar el proyecto, ejecuta en la terminal:

```bash
docker compose up
```

## Paso 4: Descargar la colección de endpoints

Descarga el archivo de la colección de endpoints para Postman:
`PruebaNeorisCollection.postman_collection.json`

Una vez descargado, impórtalo en Postman para comenzar a realizar pruebas a los endpoints expuestos por el proyecto. 

## ¡Listo! Con estos pasos tendrás tu entorno de desarrollo configurado y el proyecto corriendo, además de poder probar los endpoints con Postman.