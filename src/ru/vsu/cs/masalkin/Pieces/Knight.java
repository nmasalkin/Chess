package ru.vsu.cs.masalkin.Pieces;

public class Knight extends ChessFigure {
    private String icon = " ♘ ";
    private Type type;

    public String getIcon(){
        return icon;
    }

    public Type getType(){
        return type;
    }
    public Knight(int i) {
        if (i == 0){
            this.icon = "\u001B[31m" + " ♞ " + "\u001B[0m";
            this.type = Type.RED;
        }else {
            this.icon = "\u001B[34m" + " ♞ " + "\u001B[0m";
            this.type = Type.BLUE;
        }
    }
    public void step(ChessFigure[][] chessBoard, int in1, int in2, int out1, int out2) {
        if (chessBoard[out1][out2].getType() != type) {
            if (Math.abs(out1 - in1) == 2 && Math.abs(out2 - in2) == 1){
                chessBoard[out1][out2] = chessBoard[in1][in2];
                chessBoard[in1][in2] = new Empty();
            }
            if (Math.abs(out1 - in1) == 1 && Math.abs(out2 - in2) == 2){
                chessBoard[out1][out2] = chessBoard[in1][in2];
                chessBoard[in1][in2] = new Empty();
            }
        }
    }
}