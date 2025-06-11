# Tetris Game

This project is a simple Tetris clone implemented with Java Swing.
It now includes a scoreboard that increases when you clear rows and
each tetromino is drawn in its own color.

## Building

Compile all sources to the `out/` directory using:

```bash
make build
```

## Packaging

Create an executable JAR after building:

```bash
make package
```

## Cleaning

Remove compiled classes and packaged JAR files with:

```bash
make clean
```

## Running

Run the packaged game with:

```bash
java -jar Tetris_Game.jar
```

The project was tested with JDK 8, but any recent JDK should work.
