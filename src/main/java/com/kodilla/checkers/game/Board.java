package com.kodilla.checkers.game;

import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<BoardRow> rows = new ArrayList<>();
    private GridPane grid;
    private int oldX = -1;
    private int oldY = -1;
    private FigureColor whoseMove = FigureColor.WHITE;
    private Stage stage;


    public Board(GridPane grid, Stage stage) {
        for(int n = 0; n<8; n++)
            rows.add(new BoardRow());
        this.grid = grid;
        this.stage = stage;
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getCols().get(col);
    }

    public void setFigure(int col, int row, Figure figure) {
        rows.get(row).getCols().add(col, figure);
        rows.get(row).getCols().remove(col + 1);
    }

    public void initBoard() {
        setFigure(0,0, new Pawn(FigureColor.BLACK));
        setFigure(1,1, new Pawn(FigureColor.BLACK));
        setFigure(2,0, new Pawn(FigureColor.BLACK));
        setFigure(3,1, new Pawn(FigureColor.BLACK));
        setFigure(4,0, new Pawn(FigureColor.BLACK));
        setFigure(5,1, new Pawn(FigureColor.BLACK));
        setFigure(6,0, new Pawn(FigureColor.BLACK));
        setFigure(7,1, new Pawn(FigureColor.BLACK));
        setFigure(0,2, new Pawn(FigureColor.BLACK));
        setFigure(2,2, new Pawn(FigureColor.BLACK));
        setFigure(4,2, new Pawn(FigureColor.BLACK));
        setFigure(6,2, new Pawn(FigureColor.BLACK));

        setFigure(0,6, new Pawn(FigureColor.WHITE));
        setFigure(1,7, new Pawn(FigureColor.WHITE));
        setFigure(2,6, new Pawn(FigureColor.WHITE));
        setFigure(3,7, new Pawn(FigureColor.WHITE));
        setFigure(4,6, new Pawn(FigureColor.WHITE));
        setFigure(5,7, new Pawn(FigureColor.WHITE));
        setFigure(6,6, new Pawn(FigureColor.WHITE));
        setFigure(7,7, new Pawn(FigureColor.WHITE));
        setFigure(1,5, new Pawn(FigureColor.WHITE));
        setFigure(3,5, new Pawn(FigureColor.WHITE));
        setFigure(5,5, new Pawn(FigureColor.WHITE));
        setFigure(7,5, new Pawn(FigureColor.WHITE));
    }

    public void showBoard() {
        grid.getChildren().clear();
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++) {
                Figure f = getFigure(col, row);
                grid.add(new ImageView(f.getImage()), col, row);
            }
        stage.setTitle("Your turn: " + whoseMove);
    }

    public void move(int col1, int row1, int col2, int row2) {
        FigureColor figureColor = getFigure(col1, row1).getColor();
        boolean moveIsOk = figureColor == whoseMove;
        boolean moveOver = isMoveOver(col1, row1, col2, row2, figureColor);
        if (!moveOver) {
            moveIsOk = moveIsOk && oneStepDiagonalMove(col1, row1, col2, row2, figureColor);
        }
        if (moveIsOk) {
            setFigure(col2, row2, getFigure(col1, row1));
            setFigure(col1, row1, new None());
            if (moveOver)
                removeOpponentFigure(col1, row1, col2, row2);
            whoseMove = oppositeColor(whoseMove);
            showBoard();
            checkGameOver();
        }
    }

    private void checkGameOver() {
        int blackCnt = 0;
        int whiteCnt = 0;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        for (int row=0; row<8; row++)
            for (int col=0; col<8; col++) {
                Figure f = getFigure(col, row);
                if (f.getColor() == FigureColor.WHITE)
                    whiteCnt++;
                else if (f.getColor() == FigureColor.BLACK)
                    blackCnt++;
        }
        if (blackCnt == 0) {
            alert.setTitle("End of the game");
            alert.setHeaderText("Well done player! White pawns win!");
            alert.setContentText("Play again and try to win one more time :)");
            alert.showAndWait();
        }else if (whiteCnt == 0) {
            alert.setTitle("End of a game");
            alert.setHeaderText("Well done player! Black pawns win!");
            alert.setContentText("Play again and try to win one more time :)");
            alert.showAndWait();
        }
    }

    private FigureColor oppositeColor(FigureColor whoseMove) {
        if (whoseMove == FigureColor.WHITE) {
            return FigureColor.BLACK;
        }else{
            return FigureColor.WHITE;
        }
    }

    private void removeOpponentFigure(int col1, int row1, int col2, int row2) {
        if (row2 > row1) {
            if (col2 > col1) {
                setFigure(col1 + 1, row1 + 1, new None());
            }else{
                setFigure(col1 - 1, row1 + 1, new None());
            }
        }else{
            if (col2 > col1) {
                setFigure(col1 + 1, row1 - 1, new None());
            }else{
                setFigure(col1 - 1, row1 -1, new None());
            }
        }
    }

    private boolean isMoveOver(int col1, int row1, int col2, int row2, FigureColor figureColor) {
        boolean result = true;
        result = result && ((Math.abs(row2 - row1) == 2) && (Math.abs(col2 - col1) == 2));
        result = result && ((figureColor == FigureColor.WHITE && row2 < row1) || row2 > row1);
        result = result && getFigure(col2, row2) instanceof None;
        Figure jumpedFigure = findJumpedFigure(col1, row1, col2, row2);
        result = result && isOppositeColor(jumpedFigure.getColor(), figureColor);
        return result;
    }

    private Figure findJumpedFigure(int col1, int row1, int col2, int row2) {
        Figure jumpedFigure;
        if (row2 > row1) {
            if (col2 > col1) {
                jumpedFigure = getFigure(col1 + 1, row1 + 1);
            }else{
                jumpedFigure = getFigure(col1 - 1, row1 + 1);
            }
        }else{
            if (col2 > col1) {
                jumpedFigure = getFigure(col1 + 1, row1 - 1);
            }else{
                jumpedFigure = getFigure(col1 - 1, row1 -1);
            }
        }
        return jumpedFigure;
    }

    private boolean isOppositeColor(FigureColor color1, FigureColor color2) {
        return color1 != color2;
    }

    private boolean oneStepDiagonalMove(int col1, int row1, int col2, int row2, FigureColor figureColor) {
        boolean result = true;
        if (figureColor == FigureColor.WHITE) {
            result = (row2 == row1 - 1);
        }else if(figureColor == FigureColor.BLACK) {
            result = (row1 == row2 - 1);
        }else{
            result = false;
        }
        result = result && Math.abs(col2 - col1) == 1;
        return result;
    }

    public void doClick(int x, int y) {
        if (oldX == -1) {
            oldX = x;
            oldY = y;
        }else{
            move(oldX, oldY, x, y);
            oldX = -1;
            oldY = -1;
        }
    }
}
