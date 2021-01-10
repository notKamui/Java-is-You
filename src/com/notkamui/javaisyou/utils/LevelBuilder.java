package com.notkamui.javaisyou.utils;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.LevelManager;
import com.notkamui.javaisyou.engine.rule.RulePart;
import com.notkamui.javaisyou.engine.rule.rulepart.IsOperator;
import com.notkamui.javaisyou.engine.rule.rulepart.Type;
import com.notkamui.javaisyou.engine.rule.rulepart.property.Property;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class LevelBuilder {
  private final static Map<String, RulePart> NAME_TO_RULE_PART = Map.of(
      "IS", new IsOperator(),

      "YOU", new Property.You(),
      "DEFEAT", new Property.Defeat(),
      "SINK", new Property.Sink(),
      "HOT", new Property.Hot(),
      "MELT", new Property.Melt(),
      "WIN", new Property.Win(),
      "PUSH", new Property.Push(),
      "STOP", new Property.Stop()
  );
  private static long currentID;

  public static LevelManager buildLevelFromFile(String filename) throws IOException {
    Objects.requireNonNull(filename);
    final var path = Path.of("resources/levels", filename);
    try (var buffer = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
      final var lines = buffer.lines().collect(Collectors.toList());
      var size = lines.get(0).split(" ");
      var width = Integer.parseInt(size[0]);
      var height = Integer.parseInt(size[1]);
      var elemIcons = new HashMap<Long, ImageIcon>();
      var boardElements = parseBoardElements(lines, elemIcons);
      return new LevelManager(width, height, boardElements, elemIcons);
    }
  }

  private static List<BoardElement> parseBoardElements(List<String> lines, Map<Long, ImageIcon> elemIcons) {
    Objects.requireNonNull(lines);
    var boardElements = new ArrayList<BoardElement>();
    currentID = 0;
    for (var i = 0; i < lines.size(); i++) {
      var line = lines.get(i);
      if (!line.isEmpty()) {
        switch (line.charAt(0)) {
          case 'n' -> boardElements.addAll(parseNounsEntities(lines, i, elemIcons));
          case 'o', 'p' -> boardElements.addAll(parseOpProps(lines, i));
        }
      }
    }
    return boardElements;
  }

  private static List<BoardElement> parseNounsEntities(List<String> lines, int index, Map<Long, ImageIcon> elemIcons) {
    Objects.requireNonNull(lines);
    currentID++;
    var boardElements = new ArrayList<BoardElement>();
    var assets = lines.get(index).split(" ");
    var dir = "resources/assets/nouns/" + assets[1] + "/";
    var nounPic = new ImageIcon(dir + "Text_" + assets[1] + "_0.gif");
    var elemPic = new ImageIcon(dir + assets[1] + "_0.gif");
    var type = new Type(currentID, nounPic, elemPic);
    elemIcons.put(currentID, elemPic);
    for (var i = index + 1; i < lines.size() && !lines.get(i).isEmpty(); i++) {
      var split = lines.get(i).split(" ");
      var x = Integer.parseInt(split[2]);
      var y = Integer.parseInt(split[3]);
      if (split[1].equals("e")) {
        boardElements.add(new BoardElement(x, y, currentID, RulePart.NULL_RULE_PART, type));
      } else if (split[1].equals("t")) {
        boardElements.add(new BoardElement(x, y, currentID, type, type));
      }
    }
    return boardElements;
  }

  private static List<BoardElement> parseOpProps(List<String> lines, int index) {
    Objects.requireNonNull(lines);
    var boardElements = new ArrayList<BoardElement>();
    currentID++;
    var rulePart = NAME_TO_RULE_PART.getOrDefault(lines.get(index).split(" ")[1], RulePart.NULL_RULE_PART);
    var type = new Type(currentID, rulePart.image(), rulePart.image());
    for (var i = index + 1; i < lines.size() && !lines.get(i).isEmpty(); i++) {
      var split = lines.get(i).split(" ");
      var x = Integer.parseInt(split[1]);
      var y = Integer.parseInt(split[2]);
      boardElements.add(new BoardElement(x, y, currentID, rulePart, type));
    }
    return boardElements;
  }

}
