package ru.vsu.cs.masalkin.Pieces;

public class Empty extends ChessFigure {
    private String icon = "       ";

    private Type type = Type.EMPTY;

    public String getIcon(){
        return icon;
    }

    public Type getType(){
        return type;
    }
}
