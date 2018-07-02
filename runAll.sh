set -e

mvn clean install

java -jar Client/target/Client-0.1.0.jar &

java -jar Server/target/Server-0.1.0-jar-with-dependencies.jar &
