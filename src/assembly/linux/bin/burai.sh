#!/bin/bash
DIR="$(cd "$(dirname "$0")" && pwd)"
java -jar "$DIR/burai-1.3.3-SNAPSHOT.jar" "$@"