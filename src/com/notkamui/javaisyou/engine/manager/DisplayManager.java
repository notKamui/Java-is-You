
package com.notkamui.javaisyou.engine.manager;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DisplayManager implements Displayable {




  private static void drawBoard(Graphics2D graphics, int startX , int startY, int lineCount, int colCount,
                               int squareSize) {
    graphics.setColor(Color.GRAY);
    var width = colCount * squareSize;
    var height = lineCount * squareSize;
    for (int i = 0; i <= lineCount; i++) {
      graphics.fill(new Rectangle2D.Float(startX, i * squareSize, width, 1));
    }
    for (int j = 0; j <= colCount; j++) {
      graphics.fill(new Rectangle2D.Float(j * squareSize, startY, 1, height));
    }
  }

  private static int computeSquareSize(int width, int height, int lineCount, int colCount) {
    return Math.min(width / colCount, height / lineCount);

  }


  @Override
  public void display(Graphics2D graphics, int x, int y, int width, int height) {

  }
}
