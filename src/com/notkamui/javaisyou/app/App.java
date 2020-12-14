package com.notkamui.javaisyou.app;

import com.notkamui.javaisyou.utils.LevelBuilder;

import java.util.List;

public final class App {
  public static void main(String[] args) {
    var level = LevelBuilder.buildLevelFromFile("world1/level0.txt");

    assert level != null;
    var game = new GameController(List.of(level));
    game.run();
  }

}

