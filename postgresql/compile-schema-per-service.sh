#! /bin/bash -e

for schema in bn_auth_server bn_member_service ;
do
  user=${schema}_user
  password=${schema}_password
  cat >> /docker-entrypoint-initdb.d/30.schema-per-service.sql <<END
  CREATE USER $user WITH PASSWORD '$password';
  CREATE SCHEMA ${schema} AUTHORIZATION $user;
  GRANT ALL PRIVILEGES ON SCHEMA ${schema} TO $user;
END
    cat | sed -e s/\$\{schema\}/$schema/g /docker-entrypoint-initdb.d/template >> /docker-entrypoint-initdb.d/30.schema-per-service.sql
done