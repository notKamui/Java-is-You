package com.notkamui.javaisyou.parser;

import com.notkamui.javaisyou.engine.Direction;
import com.notkamui.javaisyou.engine.GameManager;
import com.notkamui.javaisyou.engine.boardelement.BoardElement;
import com.notkamui.javaisyou.engine.boardelement.IsOperator;
import com.notkamui.javaisyou.engine.boardelement.Operator;
import com.notkamui.javaisyou.engine.boardelement.TextualProperty;
import com.notkamui.javaisyou.engine.property.Property;
import com.notkamui.javaisyou.engine.type.WordWrapper;
import com.notkamui.javaisyou.engine.type.Wrapper;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class LevelBuilder {
    private LevelBuilder() {}

    public static GameManager buildLevelFromFile(String fname) {
        try {
            var lines = Files.readAllLines(
                    FileSystems.getDefault().getPath("resources", "levels", fname)
            );

            var size = lines.get(0).split(" ");
            var width = Integer.parseInt(size[0]);
            var height = Integer.parseInt(size[1]);
            var boardElements = parseBoardElements(lines);
            return new GameManager(width, height, boardElements);
        } catch (IOException e) {
            System.out.println("Error while opening or reading file");
            return null;
        }
    }

    public static List<BoardElement> parseBoardElements(List<String> lines) {
        var boardElements = new ArrayList<BoardElement>();
        for (var i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            switch (line.charAt(0)) {
                case 'n' -> boardElements.addAll(parseNouns(lines, i));
                case 'o', 'p' -> boardElements.addAll(parseOpProps(lines, i));
            }
        }
        return boardElements;
    }

    public static List<BoardElement> parseNouns(List<String> lines, int index) {

    }

    public static List<BoardElement> parseOpProps(List<String> lines, int index) {
        var newElements = new ArrayList<BoardElement>();
        var wrapper = new WordWrapper();
        for (var i = index+1; i < lines.size() && !lines.get(i).isEmpty(); i++) {
            var split = lines.get(i).split(" ");
            var dir = Direction.values()[Integer.parseInt(split[3])];
            var x = Integer.parseInt(split[1]);
            var y = Integer.parseInt(split[2]);
            switch (lines.get(index).split(" ")[1]) {
                case "IS" -> newElements.add(new IsOperator(wrapper, dir, x, y));
                case "YOU" -> newElements.add(new TextualProperty(wrapper, dir, x, y, new Property.You()));
            }
        }
        return newElements;
    }

}
