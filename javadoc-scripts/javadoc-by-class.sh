#!/usr/bin/env bash

# This script takes in class names as arguments
# and generates javadocs for the classes
# eg. "./javadoc-by-class UserService ScheduleService".
# Note: You will need to grant execution permission using chmod

base_path=`dirname ${0}`
root_path=${base_path}/..
lib_path=${root_path}/lib
src_path=${root_path}/src/java
phoenix_path=${src_path}/sg/edu/nus/iss/phoenix

for var in "$@"
do
    arr+=(`find ${phoenix_path} -name ${var}.java -print -quit`)
done

if [ ${#arr[@]} -gt 0 ]; then
    javadoc -classpath "${lib_path}/*" -d "${root_path}/dist/javadoc" -sourcepath "${src_path}" ${arr[*]}
fi