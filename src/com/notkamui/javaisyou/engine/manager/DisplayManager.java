
package com.notkamui.javaisyou.engine.manager;

import com.notkamui.javaisyou.engine.boardelement.Displayable;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Objects;


public class DisplayManager {

  private final Model model;
  private final int boardWidth;
  private final int boardHeight;

  DisplayManager(Model model, int boardWidth, int boardHeight) {
    Objects.requireNonNull(model);
    if (boardWidth <= 0 || boardHeight <= 0) {
      throw new IllegalArgumentException("boardWidth <= 0 || boardHeight <= 0");
    }
    this.model = model;
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
  }


  private void drawBoard(Graphics2D graphics, int startX , int startY, int squareSize) {
    Objects.requireNonNull(graphics);
    graphics.setColor(Color.GRAY);
    var width = boardWidth * squareSize;
    var height = boardHeight * squareSize;
    for (int i = 0; i <= boardHeight; i++) {
      graphics.fill(new Rectangle2D.Float(startX, i * squareSize, width, 1));
    }
    for (int j = 0; j <= boardWidth; j++) {
      graphics.fill(new Rectangle2D.Float(j * squareSize, startY, 1, height));
    }
  }

  private void drawElements(Graphics2D graphics, List<Displayable> elements, int x, int y, int squareSize) {
    for (var element : elements) {
      graphics.drawImage(element.image().getImage(),
              x + (element.x() * squareSize) + 1, y + (element.y() * squareSize) + 1,
              squareSize - 1, squareSize - 1, null);
    }
  }

  void display(Graphics2D graphics, int x, int y, int width, int height) {
    Objects.requireNonNull(graphics);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("board < 0 || board < 0");
    }
    var squareSize =  Math.min(width / boardWidth, height / boardHeight);
    drawBoard(graphics, x, y, squareSize);
    drawElements(graphics, model.displayableElements(), x, y, squareSize);
  }
}
