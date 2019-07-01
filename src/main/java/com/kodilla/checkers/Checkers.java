package com.kodilla.checkers;

import com.kodilla.checkers.game.Board;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Checkers extends Application {

    private Image imageback = new Image("file:C:\\Users\\pc\\Documents\\Development\\Projects\\checkers\\src\\main\\resources\\gameboard.png");
    private Image blackPawn = new Image("file:C:\\Users\\pc\\Documents\\Development\\Projects\\checkers\\src\\main\\resources\\pawnblack.png");
    private Image whitePawn = new Image("file:C:\\Users\\pc\\Documents\\Development\\Projects\\checkers\\src\\main\\resources\\pawnwhite.png");
    private FlowPane pawns = new FlowPane(Orientation.HORIZONTAL);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100,100,true,true,true,false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(-4.8, 0, 0, 0));
        grid.setHgap(1);
        grid.setVgap(-4);
        grid.setBackground(background);

        Board board = new Board(grid, primaryStage);
        board.initBoard();

        for(int n = 0; n<8; n++) {
            grid.getColumnConstraints().add(new ColumnConstraints(75));
        }
        for(int n = 0; n<8; n++) {
            grid.getRowConstraints().add(new RowConstraints(75));
        }

        ImageView img = new ImageView(blackPawn);
        ImageView img1 = new ImageView(whitePawn);
        pawns.getChildren().add(img);
        pawns.getChildren().add(img1);
        grid.add(pawns, 0, 0, 3, 1);

        Scene scene = new Scene(grid, 752, 700, Color.BLACK);
        //primaryStage.setTitle("CHECKERS");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        board.showBoard();
        primaryStage.show();

        grid.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            int x = (int)(e.getX() - 90) / 70;
            int y = (int)(e.getY() - 70) / 70;
            board.doClick(x, y);
        });
    }
}