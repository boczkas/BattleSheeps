set -e

mvn clean install

screen -dmS Judge java -jar Judge/target/Judge-1.0.jar

screen -dmS ShotHandler java -jar ShotHandler/target/ShotHandler-1.0.jar

screen -dmS PlayingStateMachine java -jar PlayingStateMachine/target/PlayingStateMachine-1.0.jar

screen -dmS BoardHandler java -jar BoardHandler/target/BoardHandler-1.0.jar

screen -dmS REST java -jar REST/target/REST-1.0.jar
