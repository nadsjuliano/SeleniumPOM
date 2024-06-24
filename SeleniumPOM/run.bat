set projectLocation=C:\Users\Nads\eclipse-workspace\SeleniumPOM
cd %projectLocation%
mvn clean test -DxmlFilePath=testng.xml
pause