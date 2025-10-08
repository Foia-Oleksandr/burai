@echo off
setlocal

rem Get the directory where this script is located
set "DIR=%~dp0"

rem Remove trailing backslash
if "%DIR:~-1%"=="\" set "DIR=%DIR:~0,-1%"

rem Launch the application using -jar
java -jar "%DIR%\burai-1.3.3-SNAPSHOT.jar" %*

endlocal