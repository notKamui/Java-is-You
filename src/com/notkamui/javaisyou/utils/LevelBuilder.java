package com.notkamui.javaisyou.utils;

import com.notkamui.javaisyou.engine.babaoperator.BabaOperator;
import com.notkamui.javaisyou.engine.boardelement.Direction;
import com.notkamui.javaisyou.engine.boardelement.element.*;
import com.notkamui.javaisyou.engine.boardelement.element.data.Entity;
import com.notkamui.javaisyou.engine.boardelement.element.data.Noun;
import com.notkamui.javaisyou.engine.boardelement.element.data.TextualOperator;
import com.notkamui.javaisyou.engine.boardelement.element.data.TextualProperty;
import com.notkamui.javaisyou.engine.manager.LevelManager;
import com.notkamui.javaisyou.engine.property.MovementProperty;
import com.notkamui.javaisyou.engine.property.PassiveProperty;
import com.notkamui.javaisyou.engine.type.EntityWrapper;
import com.notkamui.javaisyou.engine.type.WordWrapper;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class LevelBuilder {
    private LevelBuilder() {}

    public static LevelManager buildLevelFromFile(String fname) {
        Objects.requireNonNull(fname);
        try {
            var lines = Files.readAllLines(
                    FileSystems.getDefault().getPath("resources", "levels", fname)
            );
            var size = lines.get(0).split(" ");
            var width = Integer.parseInt(size[0]);
            var height = Integer.parseInt(size[1]);
            var boardElements = parseBoardElements(lines);
            return new LevelManager(width, height, boardElements);
        } catch (IOException e) {
            System.out.println("Error while opening or reading file");
            return null;
        }
    }

    private static List<BoardElement> parseBoardElements(List<String> lines) {
        Objects.requireNonNull(lines);
        var boardElements = new ArrayList<BoardElement>();
        var textIcon = new ImageIcon("");
        Objects.requireNonNull(textIcon);
        var wordWrapper = new WordWrapper(textIcon);
        for (var i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            if (!line.isEmpty()) {
                switch (line.charAt(0)) {
                    case 'n' -> boardElements.addAll(parseNounsEntities(lines, i, wordWrapper));
                    case 'o', 'p' -> boardElements.addAll(parseOpProps(lines, i, wordWrapper));
                }
            }
        }
        return boardElements;
    }

    private static List<BoardElement> parseNounsEntities(List<String> lines, int index, WordWrapper wrapper) {
        Objects.requireNonNull(lines);
        Objects.requireNonNull(wrapper);
        var newElements = new ArrayList<BoardElement>();
        var assets = lines.get(index).split(" ");
        var entityWrapper = new EntityWrapper(new ImageIcon(assets[1]), new ImageIcon(assets[2]));
        for (var i = index+1; i < lines.size() && !lines.get(i).isEmpty(); i++) {
            var split = lines.get(i).split(" ");
            var dir = Direction.values()[Integer.parseInt(split[4])];
            var x = Integer.parseInt(split[2]);
            var y = Integer.parseInt(split[3]);
            if (split[1].equals("e")) {
                newElements.add(new Entity(entityWrapper, dir, x, y));
            } else if (split[1].equals("t")) {
                newElements.add(new Noun(wrapper, dir, x, y, entityWrapper));
            }
        }
        return newElements;
    }

    private static List<BoardElement> parseOpProps(List<String> lines, int index, WordWrapper wrapper) {
        Objects.requireNonNull(lines);
        Objects.requireNonNull(wrapper);
        var newElements = new ArrayList<BoardElement>();
        for (var i = index+1; i < lines.size() && !lines.get(i).isEmpty(); i++) {
            var split = lines.get(i).split(" ");
            var dir = Direction.values()[Integer.parseInt(split[3])];
            var x = Integer.parseInt(split[1]);
            var y = Integer.parseInt(split[2]);
            switch (lines.get(index).split(" ")[1]) {
                case "IS" -> newElements.add(new TextualOperator(wrapper, dir, x, y, new BabaOperator.Is()));

                case "YOU" -> newElements.add(new TextualProperty(wrapper, dir, x, y, new PassiveProperty.You()));
                case "DEFEAT" -> newElements.add(new TextualProperty(wrapper, dir, x, y, new PassiveProperty.Defeat()));
                case "SINK" -> newElements.add(new TextualProperty(wrapper, dir, x, y, new PassiveProperty.Sink()));
                case "HOT" -> newElements.add(new TextualProperty(wrapper, dir, x, y, new PassiveProperty.Hot()));
                case "MELT" -> newElements.add(new TextualProperty(wrapper, dir, x, y, new PassiveProperty.Melt()));
                case "WIN" -> newElements.add(new TextualProperty(wrapper, dir, x, y, new PassiveProperty.Win()));
                case "PUSH" -> newElements.add(new TextualProperty(wrapper, dir, x, y, new MovementProperty.Push()));
                case "STOP" -> newElements.add(new TextualProperty(wrapper, dir, x, y, new MovementProperty.Stop()));
            }
        }
        return newElements;
    }

}
