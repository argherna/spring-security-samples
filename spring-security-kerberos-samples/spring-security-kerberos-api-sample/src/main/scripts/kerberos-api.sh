#!/bin/bash

# ------------------------------------------------------------------------------
#
# kerberos-api.sh
#
# Controls running the Spring Security Kerberos API Sample.
#
# Arguments:
# 
#   help
#     Prints a short help message and exits with code 2.
#
#   restart
#     Stops the app if running, then starts it.
#
#   start
#     Starts the app if not running, else exit with code 1.
#
#   stop
#     Stops the app if running, else exit with code 1.   
#
# author: Andy Gherna (agherna@illinois.edu)
#
# ------------------------------------------------------------------------------

# Print a short, helpful usage message to stderr.
#
usage() {
  cat <<-EOF >&2
    Usage: $(basename $) [help|restart|start|stop]

        help		Print this message
        restart		Restart the running process
        start		Start the process
        stop		Stop the process
EOF
}

# Verify arguments were entered, else print the usage and exit with code 2.
#
if [[ $# -lt 1 ]]; then
    echo "Missing required argument!" >&2
    usage
    exit 2
fi

COMMAND=$1

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

# Set the name of the PID file
#
SCRIPT_NAME=$(basename $0)
PID_FILE=$DIR/${SCRIPT_NAME%.*}.pid

# Set the name of the jar file
#
JAR_FILE=$DIR/spring-security-kerberos-api-sample-1.0.0.jar

case $COMMAND in

  help)
    usage
    exit 2
    ;;

  restart)
    echo "Stopping app..."
    IS_RUNNING=1
    if [[ -f $PID_FILE ]]; then
        kill -0 $(<$PID_FILE) >/dev/null 2>&1
        IS_RUNNING=$?
    fi

    if [[ $IS_RUNNING ]]; then
        kill $(<$PID_FILE) >/dev/null 2>&1 
        rm -f $PID_FILE 
        sleep 3
        echo "OK"
    else
        echo "App is not running!"
    fi

    echo "Starting app..."

    /usr/bin/java -jar $JAR_FILE >/dev/null 2>&1 &
    APP_PID=$!
    echo $APP_PID > $PID_FILE
    echo "OK"
    ;;

  start)
    if [[ -f $PID_FILE ]]; then
        kill -0 $(<$PID_FILE) >/dev/null 2>&1
        IS_RUNNING=$?

        if [[ $IS_RUNNING ]]; then
            echo "App is already running!"
            exit 1
        else
            rm -f $PID_FILE
        fi
    fi

    /usr/bin/java -jar $JAR_FILE >/dev/null 2>&1 &
    APP_PID=$!
    echo $APP_PID > $PID_FILE
    ;;

  stop)
    IS_RUNNING=1
    if [[ -f $PID_FILE ]]; then
        kill -0 $(<$PID_FILE) >/dev/null 2>&1
        IS_RUNNING=$?
    fi

    if [[ $IS_RUNNING ]]; then
        kill $(<$PID_FILE) >/dev/null 2>&1 
        rm -f $PID_FILE 
    else
        echo "App is not running!"
        exit 1
    fi
    ;;

  *)
    echo "Unknown command: $COMMAND"
    usage
    exit 1
    ;;
esac
