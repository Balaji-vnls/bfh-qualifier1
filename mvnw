#!/bin/sh
BASE_DIR=$(dirname "$0")
WRAPPER_JAR="$BASE_DIR/.mvn/wrapper/maven-wrapper.jar"

if [ ! -f "$WRAPPER_JAR" ]; then
  echo "Wrapper jar not found. Please install Maven or add the jar."
  exit 1
fi

exec java -cp "$WRAPPER_JAR" org.apache.maven.wrapper.MavenWrapperMain "$@"
