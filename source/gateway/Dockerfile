FROM kong:2.6.0-alpine

USER root

ENV KONG_PLUGINS=oidc

# install kong-oidc plugin from https://github.com/nokia/kong-oidc/
RUN luarocks install kong-oidc

USER kong