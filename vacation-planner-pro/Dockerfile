# Start with OL runtime.
# tag::from[]
FROM icr.io/appcafe/open-liberty:kernel-slim-java17-openj9-ubi

# end::from[]

ARG VERSION=1.0
ARG REVISION=SNAPSHOT
# tag::label[]

LABEL \
  org.opencontainers.image.authors="CSC 205 2024" \
  org.opencontainers.image.vendor="UCCC" \
  org.opencontainers.image.url="local" \
  org.opencontainers.image.source="https://github.com/caglejohn/vacationplannerpro" \
  org.opencontainers.image.version="$VERSION" \
  org.opencontainers.image.revision="$REVISION" \
  vendor="UCCC" \
  name="system" \
  version="$VERSION-$REVISION" \
  summary="Vacation Planner" \
  # tag::description[]
  description="The image contains the MVP for the second sprint of a Vacation Planning app."
  # end::description[]
# end::label[]

# tag::user-root[]
USER root
# end::user-root[]

# tag::copy[]
COPY --chown=1001:0 src/main/liberty/config/server.xml /config/
RUN features.sh
COPY --chown=1001:0 *.war /config/apps/
# end::copy[]
RUN configure.sh
# tag::user[]
USER 1001
# end::user[]