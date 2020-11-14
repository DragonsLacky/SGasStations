#!/bin/bash

wget -O - https://download.geofabrik.de/europe/macedonia-latest.osm.bz2 | bzcat | ./getdata.sh | sed 's@^@db.gasStations.insert( @; s@$@) @' | mongo local
