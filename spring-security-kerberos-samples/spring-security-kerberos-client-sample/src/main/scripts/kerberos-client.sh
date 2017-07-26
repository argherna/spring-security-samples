#!/bin/bash

# ------------------------------------------------------------------------------
#
# kerberos-client.sh
#
# Runs the kerberos-client-sample. This is not a server process.
#
# author: Andy Gherna (agherna@illinois.edu)
#
# ------------------------------------------------------------------------------


# Find the directory we're running in.
#
SOURCE="${BASH_SOURCE[0]}"
DIR="$(dirname "$SOURCE")"
while [ -h "$SOURCE" ]
do
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE"
  DIR="$(cd -P "$(dirname "$SOURCE")" && pwd)"
done
DIR="$(cd -P "$(dirname "$SOURCE")" && pwd)"
DIR="$(dirname "$DIR")"

# Set the name of the jar file
#
JAR_FILE=$DIR/spring-security-kerberos-client-sample-1.0.0.jar

/usr/bin/java -jar $JAR_FILE