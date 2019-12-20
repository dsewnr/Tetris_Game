build:
	javac -d out/ src/*.java

package:
	jar -cvfm Tetris_Game.jar out/MANIFEST.MF -C out/ .

clean:
	rm -f out/*.class && rm -f *.jar
