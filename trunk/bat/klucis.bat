@echo off
@REM Script to run a command

if NOT "%KLUCIS_HOME%" == "" goto :okRoot
echo KLUCIS_HOME not set
exit /B

:okRoot
call %KLUCIS_HOME%\bat\make_classpath.bat %KLUCIS_HOME%

java -cp %CP% lv.webkursi.klucis.KlucisMain %1 %2 %3 %4 %5 %6 %7 %8 %9
exit /B
