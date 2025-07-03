@echo off

:: Configuration des variables
set APP_NAME=bibliotec
set WAR_FILE=target\Film_MVC-1.0-SNAPSHOT.war
set TOMCAT_WEBAPPS="C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"

:: Compilation et création du WAR avec Maven
echo [INFO] Construction du projet Maven...
call mvn clean package
if %ERRORLEVEL% neq 0 (
    echo [ERREUR] Maven a échoué. Vérifiez la console pour les détails.
    exit /b 1
)

:: Vérification de la présence du WAR
if not exist %WAR_FILE% (
    echo [ERREUR] Le fichier WAR n'a pas été généré : %WAR_FILE%
    exit /b 1
)

:: Déploiement sur Tomcat
echo [INFO] Déploiement sur Tomcat...
copy %WAR_FILE% %TOMCAT_WEBAPPS%\%APP_NAME%.war /Y
if %ERRORLEVEL% neq 0 (
    echo [ERREUR] Échec du déploiement sur Tomcat.
    exit /b 1
)

echo [INFO] Déploiement terminé. Redémarrez Tomcat si nécessaire.
pause