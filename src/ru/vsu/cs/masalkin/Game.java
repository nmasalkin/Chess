package ru.vsu.cs.masalkin;

import ru.vsu.cs.masalkin.Pieces.*;

import java.util.Scanner;

public class Game {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private ChessFigure[][] chessBoard = createChessBoard();

    private Type currentMoveType = Type.BLUE;
    private int kingCount = 2;

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Меню:\n" +
                        "1. Начать игру\n" +
                        "2. Завершить программу");
        switch (scanner.nextLine()) {
            case "1":
                play();
            case "2":
                System.exit(0);
            default:
                System.out.println(
                        "-----------------\n" +
                                "Неверное значение\n" +
                                "-----------------");
                menu();
        }
    }

    private void play() {
        kingCount = 2;
        printChessBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Сейчас ход " + (currentMoveType.equals(Type.BLUE) ? "синих" : "красных"));
        System.out.println("Введите откуда и куда вы хотите сходить(например, A2-A3)");
        String step = scanner.nextLine();
        int inIndex1 = 8 - Integer.parseInt(step.substring(1, 2));
        int inIndex2 = convertToNumber(step.substring(0, 1));
        int outIndex1 = 8 - Integer.parseInt(step.substring(step.length() - 1));
        int outIndex2 = convertToNumber(step.substring(step.length() - 2, step.length() - 1));
        ChessFigure[][] cb = new ChessFigure[chessBoard.length][chessBoard[0].length];
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[0].length; j++) {
                cb[i][j] = chessBoard[i][j];
            }
        }
        if (0 <= inIndex1 && inIndex1 <= 7) {
            if (0 <= inIndex2 && inIndex2 <= 7) {
                if (0 <= outIndex1 && outIndex1 <= 7) {
                    if (0 <= outIndex2 && outIndex2 <= 7) {
                        if (chessBoard[inIndex1][inIndex2].getType().equals(currentMoveType)) {
                            chessBoard[inIndex1][inIndex2].step(chessBoard, inIndex1, inIndex2, outIndex1, outIndex2);
                        }
                    }
                }
            }
        }
        boolean hasChanges = true;
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[0].length; j++) {
                if (!chessBoard[i][j].equals(cb[i][j])) {
                    hasChanges = false;
                }
                if (chessBoard[i][j].getIcon() == "\u001B[31m" + " ♚ " + "\u001B[0m" || chessBoard[i][j].getIcon() == "\u001B[34m" + " ♚ " + "\u001B[0m") {
                    kingCount -= 1;
                }
            }
        }
        if (kingCount != 0) {
            printChessBoard();
            System.out.println("Игра завершена!");
            System.exit(0);
        }
        if (hasChanges) {
            for (int i = 0; i < 3; i++) {
                System.out.println("Данный ход невозможен!");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                System.out.println();
            }
            if (currentMoveType == Type.BLUE) {
                currentMoveType = Type.RED;
            } else {
                currentMoveType = Type.BLUE;
            }
        }
        play();
    }

    private void printChessBoard() {
        System.out.println("       |   A   |   B   |" +
                "   C   |   D   |   E   |" +
                "   F   |   G   |   H   |");
        System.out.println("――――――――――――――――――――――――――――");
        for (int i = 0; i < chessBoard.length; i++) {
            System.out.print("   " + (8 - i) + "   ");
            for (int j = 0; j < chessBoard[i].length; j++) {
                if ((i + j) % 2 == 0) {
                    System.out.print(ANSI_RESET + "|" + ANSI_WHITE_BACKGROUND + chessBoard[i][j].getIcon());
                } else {
                    System.out.print(ANSI_RESET + "|" + ANSI_BLACK_BACKGROUND + chessBoard[i][j].getIcon());
                }
            }
            System.out.print(ANSI_RESET + "|   " + (8 - i) + "   ");
            System.out.println("\n――――――――――――――――――――――――――――");
        }
        System.out.println("       |   A   |   B   |" +
                "   C   |   D   |   E   |" +
                "   F   |   G   |   H   |");
    }

    private ChessFigure[][] createChessBoard() {
        //♔♕♖♗♘♙♚♛♜♝♞♟ 
        return new ChessFigure[][]{
                {new Rook(0), new Knight(0), new Bishop(0), new Queen(0), new King(0), new Bishop(0), new Knight(0), new Rook(0)},
                {new Pawn(0), new Pawn(0), new Pawn(0), new Pawn(0), new Pawn(0), new Pawn(0), new Pawn(0), new Pawn(0)},
                {new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty()},
                {new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty()},
                {new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty()},
                {new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty()},
                {new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1), new Pawn(1)},
                {new Rook(1), new Knight(1), new Bishop(1), new Queen(1), new King(1), new Bishop(1), new Knight(1), new Rook(1)}};
    }

    public int convertToNumber(String letter) {
        return switch (letter.toUpperCase()) {
            case "A" -> 0;
            case "B" -> 1;
            case "C" -> 2;
            case "D" -> 3;
            case "E" -> 4;
            case "F" -> 5;
            case "G" -> 6;
            case "H" -> 7;
            default -> -1;
        };
    }
}