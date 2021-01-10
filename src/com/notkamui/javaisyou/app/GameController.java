package com.notkamui.javaisyou.app;

import com.notkamui.javaisyou.engine.Direction;
import com.notkamui.javaisyou.engine.manager.LevelManager;
import com.notkamui.javaisyou.utils.GameStatus;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Objects;

public final class GameController {
  private final List<LevelManager> levels;

  public GameController(List<LevelManager> levels) {
    Objects.requireNonNull(levels);
    if (levels.isEmpty()) {
      throw new IllegalArgumentException("At least one level must be selected");
    }
    this.levels = List.copyOf(levels);
  }

  public void run() {
    levels.forEach(level -> Application.run(Color.BLACK, context -> {
      var screenInfo = context.getScreenInfo();
      var width = (int) screenInfo.getWidth();
      var height = (int) screenInfo.getHeight();
      level.update();
      do {
        var event = context.pollOrWaitEvent(300);
        if (event != null && event.getKey() != null && event.getAction() == Event.Action.KEY_PRESSED) {
          switch (event.getKey()) {
            case UP -> level.moveYou(Direction.NORTH);
            case DOWN -> level.moveYou(Direction.SOUTH);
            case LEFT -> level.moveYou(Direction.WEST);
            case RIGHT -> level.moveYou(Direction.EAST);
            case P -> {
              System.out.println("Force quitting game...");
              System.exit(0);
            }
          }
          level.update();
        }
        render(context, level, width, height);
      } while (level.checkGameStatus() == GameStatus.ONGOING);
      if (level.checkGameStatus() == GameStatus.WIN) {
        System.out.println("Level cleared !");
      } else {
        System.out.println("Level failed...\nExiting game.");
        System.exit(0);
      }
    }));
    System.out.println("All levels cleared !");
    System.exit(0);
  }

  private void render(ApplicationContext context, LevelManager level, int width, int height) {
    context.renderFrame(graphics -> {
      assert level != null;
      graphics.setColor(Color.BLACK);
      graphics.fill(new Rectangle2D.Float(0, 0, width, height));
      level.displayGame(graphics, 0, 0, width, height);
    });
  }
}
