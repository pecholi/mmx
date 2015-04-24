#!/bin/bash

echo
echo Stopping MMX Standalone Server...
killall node app
kill -9 $(lsof -i:9090 -t)

echo
echo Removing MMX Standalone Server...
rm -rf mmx-standalone-dist*