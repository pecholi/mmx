#!/bin/bash

# removing existing messaging db
echo
echo dropping db magnetmessagedb
mysql -uroot -e "drop database if exists magnetmessagedb";
sleep 2

# Copy standalone server
echo
echo Copying mmx standalone server...
curl -u daniel.gulko:dgulko4102 -o mmx-standalone-dist.zip http://build.magnet.com:8082/view/MMX/view/MMX%20Develop/job/mmx-develop-all-maven/lastSuccessfulBuild/artifact/tools/mmx-standalone-dist/target/mmx-standalone-dist.zip;

# unzip standalone server
echo
echo extracting mmx-standalone-dist.zip
unzip -o mmx-standalone-dist.zip
sleep 2

# remove zip package
rm -rf mmx-standalone-dist.zip
sleep 2

# Start mmx standalone server
if [[ "$?" = 0 ]]; then 
	echo
	echo Starting mmx standalone server...
	cd mmx-standalone-dist
	./mmx.sh start
	sleep 5
fi

echo
echo MMX Standalone Server Running...