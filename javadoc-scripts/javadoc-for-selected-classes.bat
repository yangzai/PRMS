@echo off
REM This script generates the javadocs for the required classes
REM by calling the javadoc-by-class script

set base_path=%~dp0
set base_path=%base_path:~0,-1%
set classes=UserService ReviewSelectUserService ReviewSelectPresenterProducerService^
 ScheduleService ReviewSelectScheduledProgramService
call "%base_path%\javadoc-by-class.bat" %classes%