package com.notkamui.javaisyou.app;

import com.notkamui.javaisyou.engine.boardelement.Sprites;
import com.notkamui.javaisyou.engine.manager.DisplayManager;
import fr.umlv.zen5.Application;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class App {

  private static BufferedImage img;
  private static int squareSize;

  private final static int boardLines = 7;
  private final static int boardCols = 10;

  public static void main(String[] args) {
    try {
      img = ImageIO.read(new FileInputStream(Sprites.WALL));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Application.run(Color.BLACK, context -> {
      var screenInfo = context.getScreenInfo();
      var width = (int) screenInfo.getWidth();
      var height = (int) screenInfo.getHeight();
      squareSize = 10;

      while (true) {
        var event = context.pollOrWaitEvent(100);
        if (event != null) {
          context.exit(0);
          return;
        }
        context.renderFrame(g -> g.drawImage(img, 1, 1, squareSize - 1, squareSize - 1,
                null));

      }
    });

  }


}

