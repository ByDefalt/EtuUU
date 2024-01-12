@echo off
set JAR_FILE=PJ.jar
set JAVA_EXECUTABLE="C:\Program Files\Java\jdk-18.0.1\bin\javaw.exe"
set JAVA_OPTS=--module-path "..\lib\javafx-sdk-21.0.1\lib" --add-modules javafx.controls,javafx.fxml

rem Exécutez le JAR avec les arguments sans ouvrir une fenêtre de console
start "MonProgrammeJavaFX" /B %JAVA_EXECUTABLE% %JAVA_OPTS% -jar %JAR_FILE%
