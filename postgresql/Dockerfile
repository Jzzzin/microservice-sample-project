FROM postgres:10
COPY postgresql.conf /tmp/postgresql
COPY pg_hba.conf /tmp/postgresql
COPY replication.txt /tmp/postgresql
COPY database.txt /tmp/postgresql

COPY common-schema.sql /docker-entrypoint-initdb.d/3.common-schema.sql
COPY compile-schema-per-service.sh /docker-entrypoint-initdb.d/4.compile-schema-per-service.sh
COPY template /docker-entrypoint-initdb.d/template


RUN touch /docker-entrypoint-initdb.d/5.schema-per-service.sql
RUN chown mysql -R /docker-entrypoint-initdb.d

