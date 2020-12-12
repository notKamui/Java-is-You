package com.notkamui.javaisyou.engine.manager;

import java.awt.*;

public interface Displayable {
  void display(Graphics2D graphics, int x, int y, int width, int height);
}
