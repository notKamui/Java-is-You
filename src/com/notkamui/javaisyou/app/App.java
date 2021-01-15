package com.notkamui.javaisyou.app;

import com.notkamui.javaisyou.engine.manager.LevelManager;
import com.notkamui.javaisyou.utils.LevelBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Entry point class for the application
 */
public final class App {

  /**
   * Entry point for the application
   *
   * @param args the arguments for the game
   */
  public static void main(String[] args) {
    List<LevelManager> levels;
    try {
      levels = parseCommandLine(args);
    } catch (IOException e) {
      System.err.println("Error on file : " + e.getMessage());
      return;
    }
    var game = new GameController(levels);
    game.run();
  }

  private static List<LevelManager> parseCommandLine(String[] args) throws IOException {
    if (args.length % 2 != 0 ||
        (Arrays.asList(args).contains("--level") && Arrays.asList(args).contains("--levels"))) {
      quitWithErrorUsage();
    }

    var defaultRules = parseDefaultRules(args);

    var levels = parseLevels(args, defaultRules);

    if (levels.isEmpty()) {
      return List.of(Objects.requireNonNull(LevelBuilder.buildLevelFromFile("default-level.txt", defaultRules)));
    }
    return levels;
  }

  private static List<String[]> parseDefaultRules(String[] args) {
    var defaultRules = new ArrayList<String[]>();
    for (var i = 0; i < args.length; i++) {
      if (args[i].equals("--execute")) {
        defaultRules.add(new String[]{args[i + 1], args[i + 2], args[i + 3]});
        i += 3;
      }
    }
    return defaultRules;
  }

  private static List<LevelManager> parseLevels(String[] args, List<String[]> defaultRules) throws IOException {
    var levels = new ArrayList<LevelManager>();
    for (var i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "--level" -> {
          levels.add(Objects.requireNonNull(LevelBuilder.buildLevelFromFile(args[i + 1] + ".txt", defaultRules)));
          i += 2;
        }
        case "--levels" -> {
          levels.addAll(listOfLevelsFromDir(args[i + 1], defaultRules));
          i += 2;
        }
      }
    }
    return levels;
  }

  private static void quitWithErrorUsage() {
    System.out.println("""
        Invalid command
        Usage:
        java -jar baba.jar [options]

        Options:
        > --level [name]
        > --levels [folder name]
        > --execute [left_operand] [operator] [right_operand]
        --level and --levels are incompatible""".trim());
    System.exit(1);
  }

  private static List<LevelManager> listOfLevelsFromDir(String dirName, List<String[]> defaultRules) throws IOException {
    var levels = new ArrayList<LevelManager>();
    var path = FileSystems.getDefault().getPath("resources/levels", dirName);
    File dir = new File(path.toString());
    if (!dir.isDirectory()) {
      System.out.println(dirName + " is not a directory");
      quitWithErrorUsage();
    }
    for (String f : Arrays.stream(Objects.requireNonNull(dir.listFiles()))
        .map(File::getName)
        .collect(Collectors.toList())) {
      levels.add(Objects.requireNonNull(LevelBuilder.buildLevelFromFile(dirName + "/" + f, defaultRules)));
    }
    return levels;
  }
}

