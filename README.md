# Java is You

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a347cb7081b743c7810505bfdf38dd00)](https://app.codacy.com/gh/notKamui/Java-is-You?utm_source=github.com&utm_medium=referral&utm_content=notKamui/Java-is-You&utm_campaign=Badge_Grade)
[![CodeFactor](https://www.codefactor.io/repository/github/notkamui/java-is-you/badge/main)](https://www.codefactor.io/repository/github/notkamui/java-is-you/overview/main)

by Jimmy "notKamui" Teillard & Lorris "ZwenDo" Creantor\
greatly inspired by **Baba is You** by Arvi Teikari\
all assets are owned by Arvi Teikari and are used under fair-use.

Support the real game by actually buying it !

## Launching the game

Make sure that :
- You have the Java JRE 16 installed
- `baba.jar` and the `resources` folder are in the same directory
-   All levels are in `resources/levels`

Run the game with
```Shell
java --enable-preview -jar baba.jar [options]
```
Options can be the following:
-   nothing will default to launch default-level
-   `--level [name] {...}` with name being the name of a level
-   `--levels [dirname] {...}` with dirname being the name of a directory (world)

`--level` and `--levels` are incompatible, but you can cumulate each.

Examples of valid commands:
-   `java --enable-preview -jar baba.jar`
-   `java --enable-preview -jar baba.jar --level world1/level0 --level default-level`
-   `java --enable-preview -jar baba.jar --levels world1`

## Controls

You, as the player, can only control entities that have the property YOU.\
You can move them with the arrow keys.

For further information on the rules, check the [Wiki](https://babaiswiki.fandom.com/wiki/Baba_Is_You_Wiki)
