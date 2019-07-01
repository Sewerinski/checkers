package com.kodilla.checkers.game;

import javafx.scene.image.Image;

public class Queen extends Figure {

    private Image gueenwhite = new Image("file:C:\\Users\\pc\\Documents\\Development\\Projects\\checkers\\src\\main\\resources\\queenwhite.png");
    private Image gueenblack = new Image("file:C:\\Users\\pc\\Documents\\Development\\Projects\\checkers\\src\\main\\resources\\queenblack.png");

    public Queen(FigureColor color) {
        super(color);
    }

    @Override
    public Image getImage() {
        if (getColor() == FigureColor.BLACK) {
            return gueenwhite;
        }if (getColor() == FigureColor.WHITE) {
            return gueenblack;
        } else {
            return null;
        }
    }
}