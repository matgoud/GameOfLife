make:
	javac src/main.java src/graphique/*.java src/automate/*.java src/automate/hashlife/*.java src/automate/gridlife/*.java src/lecteur_fichier/*.java -d ./build -Xlint:unchecked
	java -cp './:./build:./lib/junit.jar' GameOfLife.main -Xmx8g

execute:
	java -cp './:./build:./lib/junit.jar' GameOfLife.main -Xmx8g

test:
	javac src/maintest.java src/graphique/*.java src/automate/*.java src/automate/hashlife/*.java src/automate/gridlife/*.java src/test/*.java src/lecteur_fichier/*.java -d ./build  -cp './:./lib/junit.jar' 
	java -cp './:./build:./lib/junit.jar' GameOfLife.maintest -Xmx8g
docs:
	rm -rf doc/
	javadoc -d doc src/*.java src/graphique/*.java src/automate/*.java src/automate/hashlife/*.java src/automate/gridlife/*.java src/test/*.java src/lecteur_fichier/*.java -cp './:./lib/junit.jar'
	firefox doc/index.html
opendocs:
	firefox doc/index.html
generate:
	rm -f GameOfLife.jar
	rm -f ressource.jar
	jar cf ressource.jar ressource
	mv ressource.jar build/ressource.jar
	javac src/main.java src/graphique/*.java src/automate/*.java src/automate/hashlife/*.java src/automate/gridlife/*.java src/lecteur_fichier/*.java -d ./build -Xlint:unchecked
	cd build/ ; jar cfve GameOfLife.jar GameOfLife.main .
	mv build/GameOfLife.jar GameOfLife.jar
	chmod +x GameOfLife.jar
	java -jar GameOfLife.jar

