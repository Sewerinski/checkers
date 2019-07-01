package com.kodilla.checkers.game;

import javafx.scene.image.Image;

public class Pawn extends Figure {

    private Image blackPawn = new Image("file:C:\\Users\\pc\\Documents\\Development\\Projects\\checkers\\src\\main\\resources\\pawnblack.png");
    private Image whitePawn = new Image("file:C:\\Users\\pc\\Documents\\Development\\Projects\\checkers\\src\\main\\resources\\pawnwhite.png");

    public Pawn(FigureColor color) {
        super(color);
    }

    @Override
    public Image getImage() {
        if (getColor() == FigureColor.BLACK) {
            return blackPawn;
        }if (getColor() == FigureColor.WHITE) {
            return whitePawn;
        } else {
            return null;
        }
    }
}