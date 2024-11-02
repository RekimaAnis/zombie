
all : run

run : clean compile exec

compile:
	javac -sourcepath src src/projet/*.java -d classes
	
exec:
	java -classpath classes projet.Game

train: 
	java -classpath classes projet.GameTrainer
	
test-c:
	javac -classpath junit-console.jar:classes src/test/projet/*.java
	
teste:
	java -jar junit-console.jar -classpath test:classes -scan-classpath

doc:
	javadoc -sourcepath src -subpackages projet -d docs

game-c:
	jar cvfe game.jar projet.Game classes  -C classes projet

game: 
	java -jar game.jar

game_trainer-c:
	jar cvfe game_trainer.jar projet.GameTrainer classes  -C classes projet

game_trainer:
	java -jar game_trainer.jar


clean:
	rm -rf classes
