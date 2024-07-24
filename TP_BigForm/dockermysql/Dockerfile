FROM mysql:8.0

# Définir les variables d'environnement pour MySQL
ENV MYSQL_ROOT_PASSWORD=admin
ENV MYSQL_DATABASE=bigform
ENV MYSQL_USER=john
ENV MYSQL_PASSWORD=admin

# Exposer le port 3306 pour accéder à MySQL
EXPOSE 3306

COPY init.sql /docker-entrypoint-initdb.d/