package com.fruitjanissary.VFX;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Trace extends Circle
{
    FadeTransition fadeTransition = new FadeTransition(Duration.millis(400), this);
    EventHandler<ActionEvent> vanish = e -> {
        this.setRadius(this.getRadius() - 0.24);
    };
    Timeline shrink = new Timeline(new KeyFrame(Duration.millis(10), vanish));
    private boolean isFinished = false;

    public Trace(double size, Color color)
    {
        this.setRadius(size);
        this.setFill(color);
        fadeTransition.setFromValue(0.5);
        fadeTransition.setToValue(0);
        fadeTransition.setAutoReverse(false);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
        shrink.setCycleCount(50);
        shrink.play();
        fadeTransition.setOnFinished(e -> {
            isFinished = true;
            shrink.stop();
        });
    }

    public boolean isDone()
    {
        return this.isFinished;
    }

    public void setLocation(double x, double y)
    {
        this.setCenterX(x);
        this.setCenterY(y);
    }
}