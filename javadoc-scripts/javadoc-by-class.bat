REM This script takes in class names as arguments
REM and generates javadocs for the classes
REM eg. "javadoc-by-class.bat UserService ScheduleService".

@echo off
setlocal enabledelayedexpansion
set base_path=%~dp0
set base_path=%base_path:~0,-1%
set root_path=%base_path%\..
set src_path=%root_path%\src\java
set phoenix_path=%src_path%\sg\edu\nus\iss\phoenix
set classes=

for %%a in (%*) do (
	set var=
	for /f "delims=" %%i in ('dir %phoenix_path%\%%a.java /b/s') do set var=%%i
	if not [!var!]==[] set "classes=!classes! !var!"
)

if not [!classes!]==[] (
	set classes=%classes:~1%
	javadoc -d %root_path%\dist\javadoc -sourcepath %src_path% !classes!
)