javac -classpath .;msbase.jar;mssqlserver.jar;msutil.jar sqltest.java
pause
java -classpath .;msbase.jar;mssqlserver.jar;msutil.jar sqltest >>sql.html
