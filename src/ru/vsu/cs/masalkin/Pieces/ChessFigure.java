package ru.vsu.cs.masalkin.Pieces;

public class ChessFigure {
    private String icon = "";

    private Type type;

    public String getIcon() {
        return icon;
    }

    public Type getType() {
        return type;
    }

    public void step(ChessFigure[][] chessBoard, int inIndex1, int inIndex2, int outIndex1, int outIndex2) {
    }

    public int convertToNumber(String letter) {
        return switch (letter.toUpperCase()) {
            case "A" -> 1;
            case "B" -> 2;
            case "C" -> 3;
            case "D" -> 4;
            case "E" -> 5;
            case "F" -> 6;
            case "G" -> 7;
            case "H" -> 8;
            default -> 0;
        };
    }
}
