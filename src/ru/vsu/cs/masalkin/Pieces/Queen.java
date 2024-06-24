package ru.vsu.cs.masalkin.Pieces;

public class Queen extends ChessFigure {
    private String icon = " ♕ ";

    private Type type;

    public String getIcon() {
        return icon;
    }

    public Type getType() {
        return type;
    }

    public Queen(int i) {
        if (i == 0) {
            this.icon = "\u001B[31m" + " ♛ " + "\u001B[0m";
            this.type = Type.RED;
        } else {
            this.icon = "\u001B[34m" + " ♛ " + "\u001B[0m";
            this.type = Type.BLUE;
        }
    }

    public void step(ChessFigure[][] chessBoard, int in1, int in2, int out1, int out2) {
        if (chessBoard[out1][out2].getType() != type) {
            if (out1 - in1 != 0 && out2 - in2 == 0) {
                if (out1 - in1 > 0) {
                    for (int i = 1; i < out1 - in1; i++) {
                        if (chessBoard[in1 + i][in2].getType() != Type.EMPTY) {
                            return;
                        }
                    }
                } else {
                    for (int i = 1; i < in1 - out1; i++) {
                        if (chessBoard[in1 - i][in2].getType() != Type.EMPTY) {
                            return;
                        }
                    }
                }
            } else if (out1 - in1 == 0 && out2 - in2 != 0) {
                if (out2 - in2 > 0) {
                    for (int i = 1; i < out2 - in2; i++) {
                        if (chessBoard[in1][in2 + i].getType() != Type.EMPTY) {
                            return;
                        }
                    }
                } else {
                    for (int i = 1; i < in2 - out2; i++) {
                        if (chessBoard[in1][in2 - i].getType() != Type.EMPTY) {
                            return;
                        }
                    }
                }
            }
        }
        if (Math.abs(out1 - in1) != Math.abs(out2 - in2)) {
            if (chessBoard[out1][out2].getType() != type) {
                if (out1 - in1 < 0 && out2 - in2 > 0) {
                    for (int i = 1; i < out2 - in2; i++) {
                        if (chessBoard[in1 - i][in2 + i].getType() != Type.EMPTY) {
                            return;
                        }
                    }
                }
                if (out1 - in1 < 0 && out2 - in2 < 0) {
                    for (int i = 1; i < in1 - out1; i++) {
                        if (chessBoard[in1 - i][in2 - i].getType() != Type.EMPTY) {
                            return;
                        }
                    }
                }
                if (out1 - in1 > 0 && out2 - in2 > 0) {
                    for (int i = 1; i < out2 - in2; i++) {
                        if (chessBoard[in1 + i][in2 + i].getType() != Type.EMPTY) {
                            return;
                        }
                    }
                }
                if (out1 - in1 > 0 && out1 - in1 < 0) {
                    for (int i = 1; i < out2 - in2; i++) {
                        if (chessBoard[in1 + i][in2 - i].getType() != Type.EMPTY) {
                            return;
                        }
                    }
                }
            }
        }
        chessBoard[out1][out2] = chessBoard[in1][in2];
        chessBoard[in1][in2] = new Empty();
    }
}