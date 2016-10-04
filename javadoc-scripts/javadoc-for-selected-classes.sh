#!/usr/bin/env bash

# This script generates the javadocs for the required classes
# by calling the javadoc-by-class script

classes=(UserService ReviewSelectUserService ReviewSelectPresenterProducerService\
 ScheduleService ReviewSelectScheduledProgramService)
source "`dirname ${0}`/javadoc-by-class.sh" ${classes[*]}