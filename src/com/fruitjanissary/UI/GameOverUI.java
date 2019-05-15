package com.fruitjanissary.UI;

import com.fruitjanissary.Game;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;


public class GameOverUI extends Pane
{
    private Button exit;
    private Button replay;
    private Game game;
    FadeTransition fadeTransition = new FadeTransition(Duration.millis(600));

    public GameOverUI(int type, Game game)
    {
        this.setOpacity(0);
        this.game = game;
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setNode(this);
        fadeTransition.setAutoReverse(false);
        fadeTransition.setRate(1);
        fadeTransition.setCycleCount(1);
        switch (type)
        {
            case 0:
            {
                //3 drop
                fadeTransition.setDelay(Duration.millis(500));
                break;
            }
            case 1:
            {
                //bomb
                fadeTransition.setDelay(Duration.millis(2000));
                break;
            }
            case 2:
            {
                //nuke
                fadeTransition.setDelay(Duration.millis(6000));
                break;
            }
        }
        fadeTransition.play();
        draw();
        replay.setOnMouseClicked(e -> {
            game.restart();
        });
        exit.setOnMouseClicked(e -> {
            game.exitGame();
        });
    }

    private void draw()
    {
        Rectangle background = new Rectangle(350, 200);
        background.setFill(Color.LIGHTYELLOW);
        background.setStrokeWidth(11);
        background.setStroke(Color.GOLD);
        background.setArcHeight(40);
        background.setArcWidth(40);
        background.setX(355 - background.getWidth() / 2);
        background.setY(355 - background.getHeight() / 2);
        //
        this.setMaxSize(710, 710);
        this.setMinSize(710, 710);
        //
        Label gameOverText = new Label("GAME OVER");
        gameOverText.setFont(Font.loadFont("file:sprites/Overseer.otf", 50));
        gameOverText.setLayoutY(background.getY() + 25);
        gameOverText.setLayoutX(background.getX() + 84);
        exit = new Button("Exit");
        exit.setFont(Font.loadFont("file:sprites/Evogria.otf", 20));
        exit.setPrefSize(100, 50);
        exit.setFocusTraversable(false);
        replay = new Button("Replay");
        replay.setFont(Font.loadFont("file:sprites/Evogria.otf", 20));
        replay.setPrefSize(100, 50);
        replay.setFocusTraversable(false);
        HBox box = new HBox();
        box.setMinWidth(background.getWidth());
        box.setLayoutX(background.getX());
        box.getChildren().addAll(exit, replay);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        box.setLayoutY(background.getY() + 115);
        getChildren().addAll(background, gameOverText, box);
    }
}
