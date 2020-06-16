FROM postgres:10-alpine
RUN apk add --no-cache su-exec
RUN apk add --no-cache --virtual .build-deps gcc git make musl-dev pkgconf \
    && git clone https://github.com/eulerto/wal2json.git -b master --single-branch \
    && cd /wal2json && make && make install \
    && cd / && rm -rf wal2json \
    && apk del .build-deps

COPY docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh

COPY common-schema.sql /docker-entrypoint-initdb.d/10.common-schema.sql
COPY compile-schema-per-service.sh /docker-entrypoint-initdb.d/20.compile-schema-per-service.sh
COPY template /docker-entrypoint-initdb.d/template
COPY create-replication-slot.sql /docker-entrypoint-initdb.d/40.create-replication-slot.sql
COPY init-permissions.sh /docker-entrypoint-initdb.d/50.init-permission.sh

RUN touch /docker-entrypoint-initdb.d/30.schema-per-service.sql
RUN chown postgres -R /docker-entrypoint-initdb.d

