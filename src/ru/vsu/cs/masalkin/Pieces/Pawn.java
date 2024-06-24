package ru.vsu.cs.masalkin.Pieces;

public class Pawn extends ChessFigure {
    private String icon = " ♙ ";

    private Type type;

    public String getIcon() {
        return icon;
    }

    public Type getType() {
        return type;
    }

    public Pawn(int i) {
        if (i == 0) {
            this.icon = "\u001B[31m" + " ♟ " + "\u001B[0m";
            this.type = Type.RED;
        } else if (i == 1) {
            this.icon = "\u001B[34m" + " ♟ " + "\u001B[0m";
            this.type = Type.BLUE;
        }
    }

    public void step(ChessFigure[][] chessBoard, int in1, int in2, int out1, int out2) {
        if (chessBoard[out1][out2].getType() == Type.EMPTY) {
            if (chessBoard[in1][in2].getType().equals(Type.BLUE)) {
                if (out1 - in1 > -3 && out1 - in1 < 0 && out2 == in2) {
                    chessBoard[out1][out2] = chessBoard[in1][in2];
                    chessBoard[in1][in2] = new Empty();
                }
            } else {
                if (out1 - in1 < 3 && out1 - in1 > 0 && out2 == in2) {
                    chessBoard[out1][out2] = chessBoard[in1][in2];
                    chessBoard[in1][in2] = new Empty();
                }
            }
        } else {
            if (chessBoard[out1][out2].getType() != type) {
                if (type == Type.BLUE) {
                    if (out1 - in1 == -1 && out2 - in2 == 1) {
                        chessBoard[out1][out2] = chessBoard[in1][in2];
                        chessBoard[in1][in2] = new Empty();
                    }
                    if (out1 - in1 == -1 && out2 - in2 == -1) {
                        chessBoard[out1][out2] = chessBoard[in1][in2];
                        chessBoard[in1][in2] = new Empty();
                    }
                } else {
                    if (out1 - in1 == 1 && out2 - in2 == 1) {
                        chessBoard[out1][out2] = chessBoard[in1][in2];
                        chessBoard[in1][in2] = new Empty();
                    }
                    if (out1 - in1 == 1 && out2 - in2 == -1) {
                        chessBoard[out1][out2] = chessBoard[in1][in2];
                        chessBoard[in1][in2] = new Empty();
                    }
                }
            }
        }
    }
}