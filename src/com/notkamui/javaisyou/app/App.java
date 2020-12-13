package com.notkamui.javaisyou.app;

import com.notkamui.javaisyou.parser.LevelBuilder;
import fr.umlv.zen5.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class App {

  private static ImageIcon img;
  private static int squareSize;

  private final static int boardLines = 7;
  private final static int boardCols = 10;

  public static void main(String[] args) {
    var game = LevelBuilder.buildLevelFromFile("default-level.txt");
    System.out.println(game);

    img = new ImageIcon("resources/assets/operators/IS/Op_IS.gif");

    Application.run(Color.BLACK, context -> {
      var screenInfo = context.getScreenInfo();
      var width = (int) screenInfo.getWidth();
      var height = (int) screenInfo.getHeight();


      while (true) {
        var event = context.pollOrWaitEvent(100);
        if (event != null) {
          context.exit(0);
          return;
        }
        context.renderFrame(graphics -> {
          assert game != null;
          game.displayGame(graphics, 0, 0, width, height);
        });

      }
    });

  }


}

