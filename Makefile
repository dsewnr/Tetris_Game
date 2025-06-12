JUNIT=/opt/gradle/lib/junit-4.13.2.jar
HAMCREST=/opt/gradle/lib/hamcrest-core-1.3.jar

build:
	javac -d out/ src/*.java

test: build
	javac -cp $(JUNIT):$(HAMCREST):out -d out/ src/test/java/*.java
	java -cp $(JUNIT):$(HAMCREST):out org.junit.runner.JUnitCore \
	    ActionManagerMoveTest ActionManagerEliminateTest ActionManagerScoreTest \
	        ActionManagerRotateWallTest

package:
	jar -cvfm Tetris_Game.jar out/MANIFEST.MF -C out/ .

clean:
	rm -f out/*.class && rm -f *.jar
