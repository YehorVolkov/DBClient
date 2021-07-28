# DBClient

A simple database client with console interface

## Run

1. Clone this repository

2. Open console and navigate to project's root

3. Execute command: "mvn clean compile assembly:single"

4. After command successfully executed, run "java -jar target\DBClient-1.0-SNAPSHOT-jar-with-dependencies.jar"

5. You can also specify configuration via system properties (e.g. -Duser=root, -Dpassword=admin, etc.) or using application.properties file located at \src\main\resources. You can also see all available configs in that file.

6. The application is now listening for your SQL input

## Usage

1. After application startup, enter SQL command.

2. If you're querying database, the application will try to output the resulting table in a form of pseudographics and make an HTML report.

3. If you're executing other commands, the application will write the number of rows affected to console.
