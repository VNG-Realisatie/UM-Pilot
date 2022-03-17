#!/bin/sh

cd /Users/ronny/Desktop/UM-Vertaler

java -jar ./bin/UmTranslator.jar -format="SONAREXCELIMPORT" -filename="input/SonarDashboardExample.xlsx" -oin="00000001002220647000" -username="utrecht" -password="password" -authurl="http://localhost:8080/auth/realms/poc-vng-realm/protocol/openid-connect/token" -url="http://localhost:8081/werkzoekende/lijst"

read -p "*****      Druk op de [Enter] toets_om dit scherm te sluiten    *****"

