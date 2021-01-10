package com.notkamui.javaisyou.utils;

import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.manager.LevelManager;
import com.notkamui.javaisyou.engine.rule.Rule;
import com.notkamui.javaisyou.engine.rule.RulePart;
import com.notkamui.javaisyou.engine.rule.rulepart.IsOperator;
import com.notkamui.javaisyou.engine.rule.rulepart.Type;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static com.notkamui.javaisyou.engine.rule.rulepart.property.Property.*;

public class LevelBuilder {
  private final static Type TEXT_TYPE = new Type(new ImageIcon(
      "resources/assets/nouns/TEXT/Text_TEXT_0.gif"),
      new ImageIcon()
  );

  public static LevelManager buildLevelFromFile(String filename) throws IOException {
    Objects.requireNonNull(filename);
    final var path = Path.of("resources/levels", filename);
    try (var buffer = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
      final var lines = buffer.lines().collect(Collectors.toList());
      var size = lines.get(0).split(" ");
      var width = Integer.parseInt(size[0]);
      var height = Integer.parseInt(size[1]);
      var boardElements = parseBoardElements(lines);
      var defaultRules = new ArrayList<Rule>();
      defaultRules.add(new Rule(TEXT_TYPE, new IsOperator(), new Push()));
      return new LevelManager(width, height, boardElements, defaultRules);
    }
  }

  private static List<BoardElement> parseBoardElements(List<String> lines) {
    Objects.requireNonNull(lines);
    var boardElements = new ArrayList<BoardElement>();
    for (var i = 0; i < lines.size(); i++) {
      var line = lines.get(i);
      if (!line.isEmpty()) {
        switch (line.charAt(0)) {
          case 'n' -> boardElements.addAll(parseNounsEntities(lines, i));
          case 'o', 'p' -> boardElements.addAll(parseOpProps(lines, i));
        }
      }
    }
    return boardElements;
  }

  private static List<BoardElement> parseNounsEntities(List<String> lines, int index) {
    Objects.requireNonNull(lines);
    var boardElements = new ArrayList<BoardElement>();
    var assets = lines.get(index).split(" ");
    var dir = "resources/assets/nouns/" + assets[1] + "/";
    var nounPic = new ImageIcon(dir + "Text_" + assets[1] + "_0.gif");
    var elemPic = new ImageIcon(dir + assets[1] + "_0.gif");
    var type = new Type(nounPic, elemPic);
    for (var i = index + 1; i < lines.size() && !lines.get(i).isEmpty(); i++) {
      var split = lines.get(i).split(" ");
      var x = Integer.parseInt(split[2]);
      var y = Integer.parseInt(split[3]);
      if (split[1].equals("e")) {
        boardElements.add(new BoardElement(x, y, RulePart.NULL_RULE_PART, type));
      } else if (split[1].equals("t")) {
        boardElements.add(new BoardElement(x, y, type, TEXT_TYPE));
      }
    }
    return boardElements;
  }

  private static List<BoardElement> parseOpProps(List<String> lines, int index) {
    Objects.requireNonNull(lines);
    var boardElements = new ArrayList<BoardElement>();
    var rulePart = switch (lines.get(index).split(" ")[1]) {
      case "IS" -> new IsOperator();

      case "YOU" -> new You();
      case "DEFEAT" -> new Defeat();
      case "SINK" -> new Sink();
      case "HOT" -> new Hot();
      case "MELT" -> new Melt();
      case "WIN" -> new Win();
      case "PUSH" -> new Push();
      case "STOP" -> new Stop();

      default -> throw new IllegalArgumentException();
    };
    for (var i = index + 1; i < lines.size() && !lines.get(i).isEmpty(); i++) {
      var split = lines.get(i).split(" ");
      var x = Integer.parseInt(split[1]);
      var y = Integer.parseInt(split[2]);
      boardElements.add(new BoardElement(x, y, rulePart, TEXT_TYPE));
    }
    return boardElements;
  }

}
