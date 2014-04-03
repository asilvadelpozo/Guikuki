#!/bin/sh

#First we drop the database guikuki to start from scratch
~/Documents/mongodb/mongodb-osx-x86_64-2.4.3/bin/mongo guikuki --eval "db.dropDatabase()"

#After that the restaurants are imported in the mongo collection restaurants from guikuki database
~/Documents/mongodb/mongodb-osx-x86_64-2.4.3/bin/mongoimport --db guikuki --collection restaurants --type json --file restaurants.json --jsonArray

#Then we move into pictures folder
cd pictures

#After that pictures are inserted in mongodb as well using gridFS with mongofiles utility
for file in *
do
  ~/Documents/mongodb/mongodb-osx-x86_64-2.4.3/bin/mongofiles -d guikuki -t image/jpeg put $file;
done

#To finish we turn back to our previous path
cd ..
