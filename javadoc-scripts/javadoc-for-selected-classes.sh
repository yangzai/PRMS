#!/usr/bin/env bash

# This script generates the javadocs for the required classes
# by calling the javadoc-by-class script

classes=(UserService ScheduleService)
source `dirname ${0}`/javadoc-by-class.sh ${classes[*]}