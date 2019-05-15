package com.fruitjanissary.Explosions;

import com.fruitjanissary.Explosion;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class NuclearExplosion extends Explosion
{

    private boolean isDone = false;
    ImageView backGround = new ImageView("file:sprites/explosion.jpg");
    ImageView pipBoy = new ImageView("file:sprites/falloutboy.png");
    FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), backGround);
    EventHandler<ActionEvent> handler = e -> {
        pipBoy.setY(pipBoy.getY() - 4);
    };
    Timeline falloutboy = new Timeline(new KeyFrame(Duration.millis(10), handler));
    FadeTransition fd = new FadeTransition(Duration.millis(1200), this);

    public NuclearExplosion(double x, double y)
    {
        super(x, y);
        //
        draw();
    }

    @Override
    protected void draw()
    {
        this.setLayoutX(0);
        this.setLayoutY(0);
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        backGround.fitWidthProperty().bind(widthProperty());
        backGround.fitHeightProperty().bind(heightProperty());
        //
        pipBoy.setFitHeight(250);
        pipBoy.setFitWidth(250);
        pipBoy.setX(450);
        pipBoy.setY(750);
        //
        falloutboy.setCycleCount(70);
        falloutboy.setRate(1);
        falloutboy.setAutoReverse(false);
        //
        fadeTransition.setCycleCount(1);
        fadeTransition.setToValue(1);
        fadeTransition.setFromValue(0);
        fadeTransition.play();
        //
        fd.setFromValue(1);
        fd.setToValue(0);
        fd.setDelay(Duration.millis(850));
        fd.setCycleCount(1);
        //
        fadeTransition.setOnFinished(e -> {
            falloutboy.play();
        });
        falloutboy.setOnFinished(e -> {
            fd.play();
        });
        fd.setOnFinished(e -> {
            isDone = true;
        });
        getChildren().addAll(backGround, pipBoy);
    }

    @Override
    public boolean isDone()
    {
        return this.isDone;
    }
}