package com.kodilla.checkers.game;

import javafx.scene.image.Image;

public class Figure {
    private FigureColor color;

    public Figure(FigureColor color) {
        this.color = color;
    }

    public FigureColor getColor() {
        return color;
    }

    public Image getImage() {
        return null;
    }
}
