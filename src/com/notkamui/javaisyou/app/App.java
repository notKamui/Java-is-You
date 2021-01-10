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

public final class App {
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

  public static List<LevelManager> parseCommandLine(String[] args) throws IOException {
    if (args.length % 2 != 0 ||
        (Arrays.asList(args).contains("--level") && Arrays.asList(args).contains("--levels"))) {
      quitWithErrorUsage();
    }
    if (args.length == 0) {
      return List.of(Objects.requireNonNull(LevelBuilder.buildLevelFromFile("default-level.txt")));
    }
    var levels = new ArrayList<LevelManager>();
    for (var i = 0; i < args.length; i += 2) {
      switch (args[i]) {
        case "--level" -> levels.add(Objects.requireNonNull(LevelBuilder.buildLevelFromFile(args[i + 1] + ".txt")));
        case "--levels" -> levels.addAll(listOfLevelsFromDir(args[i + 1]));
        default -> quitWithErrorUsage();
      }
    }
    return levels;
  }

  public static void quitWithErrorUsage() {
    System.out.println("Invalid command\nUsage:\njava -jar baba.jar [options]\n\nOptions:\n> --level [name]\n--levels [folder name]\n--level and --levels are incompatible");
    System.exit(1);
  }

  public static List<LevelManager> listOfLevelsFromDir(String dirName) throws IOException {
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
      levels.add(Objects.requireNonNull(LevelBuilder.buildLevelFromFile(dirName + "/" + f)));
    }
    return levels;
  }
}

