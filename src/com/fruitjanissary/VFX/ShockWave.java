package com.fruitjanissary.VFX;

import com.fruitjanissary.Utils;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class ShockWave extends Circle
{

    private Color color;
    private boolean isDone = false;
    EventHandler<ActionEvent> explode = e -> {
        this.setRadius(this.getRadius() + 25);
    };
    Timeline explosion = new Timeline(new KeyFrame(Duration.millis(10), explode));
    FadeTransition fadeTransition = new FadeTransition(Duration.millis(700),this);
    public ShockWave()
    {
        int randColor = (int) Utils.getRandomNumber(20, 25);
        color = Color.hsb(randColor, 0.95, 1);
        this.setFill(color);
        this.setRadius(10);
        this.setStroke(color.darker());
        this.setStrokeWidth(2);
        explosion.setCycleCount(50);
        explosion.setRate(1);
        explosion.setOnFinished(e -> {
            this.isDone = true;
        });
        explosion.play();
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();

    }

    public ShockWave(double x, double y)
    {
        this();
        setLocation((int)x, (int)y);
    }

    public boolean isDone()
    {
        return this.isDone;
    }

    public void setLocation(int x, int y)
    {
        this.setCenterX(x);
        this.setCenterY(y);
    }
}