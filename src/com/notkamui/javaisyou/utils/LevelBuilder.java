package com.notkamui.javaisyou.utils;

import com.notkamui.javaisyou.engine.babaoperator.BabaOperator;
import com.notkamui.javaisyou.engine.boardelement.Direction;
import com.notkamui.javaisyou.engine.boardelement.element.*;
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

    public static LevelManager buildLevelFromFile(String fname) {
        Objects.requireNonNull(fname);
        try {
            var lines = Files.readAllLines(
                    FileSystems.getDefault().getPath("resources", "levels", fname)
            );
            var size = lines.get(0).split(" ");
            var width = Integer.parseInt(size[0]);
            var height = Integer.parseInt(size[1]);
            var wordWrapper = new WordWrapper(new ImageIcon(""));
            var entityWrappers = parseBoardElements(lines, wordWrapper);
            return new LevelManager(width, height, entityWrappers, wordWrapper);
        } catch (IOException e) {
            System.out.println("Error while opening or reading file");
            return null;
        }
    }

    private static List<EntityWrapper> parseBoardElements(List<String> lines, WordWrapper wordWrapper) {
        Objects.requireNonNull(lines);
        var entityWrappers = new ArrayList<EntityWrapper>();
        for (var i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            if (!line.isEmpty()) {
                switch (line.charAt(0)) {
                    case 'n' -> entityWrappers.add(parseNounsEntities(lines, i, wordWrapper));
                    case 'o', 'p' -> parseOpProps(lines, i, wordWrapper);
                }
            }
        }
        return entityWrappers;
    }

    private static EntityWrapper parseNounsEntities(List<String> lines, int index, WordWrapper wrapper) {
        Objects.requireNonNull(lines);
        Objects.requireNonNull(wrapper);
        var assets = lines.get(index).split(" ");
        var entityWrapper = new EntityWrapper(new ImageIcon(assets[1]), new ImageIcon(assets[2]));
        for (var i = index+1; i < lines.size() && !lines.get(i).isEmpty(); i++) {
            var split = lines.get(i).split(" ");
            var dir = Direction.values()[Integer.parseInt(split[4])];
            var x = Integer.parseInt(split[2]);
            var y = Integer.parseInt(split[3]);
            if (split[1].equals("e")) {
                entityWrapper.addEntity(new Entity(dir, x, y));
            } else if (split[1].equals("t")) {
                wrapper.addWord(new Noun(dir, x, y, entityWrapper));
            }
        }
        return entityWrapper;
    }

    private static void parseOpProps(List<String> lines, int index, WordWrapper wrapper) {
        Objects.requireNonNull(lines);
        Objects.requireNonNull(wrapper);
        for (var i = index+1; i < lines.size() && !lines.get(i).isEmpty(); i++) {
            var split = lines.get(i).split(" ");
            var dir = Direction.values()[Integer.parseInt(split[3])];
            var x = Integer.parseInt(split[1]);
            var y = Integer.parseInt(split[2]);
            switch (lines.get(index).split(" ")[1]) {
                case "IS" -> wrapper.addWord((new TextualOperator(dir, x, y, new BabaOperator.Is())));

                case "YOU" -> wrapper.addWord(new TextualProperty(dir, x, y, new PassiveProperty.You()));
                case "DEFEAT" -> wrapper.addWord(new TextualProperty(dir, x, y, new PassiveProperty.Defeat()));
                case "SINK" -> wrapper.addWord(new TextualProperty(dir, x, y, new PassiveProperty.Sink()));
                case "HOT" -> wrapper.addWord(new TextualProperty(dir, x, y, new PassiveProperty.Hot()));
                case "MELT" -> wrapper.addWord(new TextualProperty(dir, x, y, new PassiveProperty.Melt()));
                case "WIN" -> wrapper.addWord(new TextualProperty(dir, x, y, new PassiveProperty.Win()));
                case "PUSH" -> wrapper.addWord(new TextualProperty(dir, x, y, new MovementProperty.Push()));
                case "STOP" -> wrapper.addWord(new TextualProperty(dir, x, y, new MovementProperty.Stop()));
            }
        }
    }

}
