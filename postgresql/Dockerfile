FROM postgres:10
ENV PG_MAX_WAL_SENDERS 8
ENV PG_WAL_KEEP_SEGMENTS 8
COPY setup-replication.sh /docker-entrypoint-initdb.d/setup-replication.sh
COPY docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint-initdb.d/setup-replication.sh /docker-entrypoint.sh

COPY common-schema.sql /docker-entrypoint-initdb.d/10.common-schema.sql
COPY compile-schema-per-service.sh /docker-entrypoint-initdb.d/20.compile-schema-per-service.sh
COPY template /docker-entrypoint-initdb.d/template
COPY init-permissions.sh /docker-entrypoint-initdb.d/40.init-permission.sh

RUN touch /docker-entrypoint-initdb.d/30.schema-per-service.sql
RUN chown postgres -R /docker-entrypoint-initdb.d

