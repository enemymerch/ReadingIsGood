FROM openjdk:11-jdk
MAINTAINER mcan

ARG APPUSER_NAME=appuser
ARG APPUSER_USER_ID=1579
ARG APPUSER_GROUP_ID=1357

RUN addgroup --gid "${APPUSER_GROUP_ID}" --system "${APPUSER_NAME}"
RUN adduser -u "${APPUSER_USER_ID}" --disabled-password --system --shell /bin/bash --group "${APPUSER_NAME}"

WORKDIR /app

COPY target/*.jar rig-application.jar

RUN chown -R ${APPUSER_NAME}:${APPUSER_NAME} /app

USER ${APPUSER_NAME}
ENTRYPOINT ["java", "-jar", "rig-application.jar"]