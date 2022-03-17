#!/bin/sh

cd /Users/ronny/Desktop/UM-Vertaler

java -jar ./bin/UmTranslator.jar -filename="./input/SonarDashboardExample.xlsx" -format="SonarExcelImport"

read -p "*****      Druk op de [Enter] toets_om dit scherm te sluiten    *****"

