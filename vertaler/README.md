Voorbeeld opstart-commando voor het vertalen naar een nieuw bestand wat bij UM aangeboden kan worden:

java -jar ./bin/UmVertaler.jar -filename="./input/SonarDashboardExample.xlsx" -format="SonarExcelImport"


Voorbeeld opstart-commando voor het vertalen en daarna direct aanbieden bij de UM-API:

java -jar ./bin/UmVertaler.jar -format="SONAREXCELIMPORT" -filename="input/SonarDashboardExample.xlsx" -oin="00000001002220647000" -username="username" -password="password" -authurl="http://localhost:8080/auth/realms/poc-vng-realm/protocol/openid-connect/token" -url="http://localhost:8081/werkzoekende/lijst"
