#Imagen base de mysql
FROM mysql:8.0.41

#Permisos basicos
RUN chown -R mysql:root /var/lib/mysql/

#Argumentos reueridos para la instalacion (vease .env)
ARG MYSQL_DATABASE
ARG MYSQL_USER
ARG MYSQL_PASSWORD
ARG MYSQL_ROOT_PASSWORD

#Variables de entorno de la bdd
ENV MYSQL_DATABASE=$MYSQL_DATABASE
ENV MYSQL_USER=$MYSQL_USER
ENV MYSQL_PASSWORD=$MYSQL_PASSWORD
ENV MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD

#Dump inicial a copiar
ADD dump.sql /docker-entrypoint-initdb.d

#Puerto interno a exponer
EXPOSE 3306
