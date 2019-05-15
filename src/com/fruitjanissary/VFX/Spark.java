package com.fruitjanissary.VFX;

import com.fruitjanissary.Bombs.BombDefault;
import com.fruitjanissary.Utils;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Spark extends Circle
{

    private double velocity = 0;
    private double hVelocity = 0;
    private Color color;
    FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), this);
    EventHandler<ActionEvent> vanish = e -> {
        this.setRadius(this.getRadius() - 0.24);
    };
    Timeline shrink = new Timeline(new KeyFrame(Duration.millis(10), vanish));
    private boolean isFinished = false;

    public Spark(BombDefault bomb)
    {
        setThrow(bomb);
        fadeTransition.setFromValue(0.9);
        fadeTransition.setToValue(0.5);
        fadeTransition.setAutoReverse(false);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();
        fadeTransition.setOnFinished(e -> {
            isFinished = true;
            shrink.stop();
        });
    }

    private void setThrow(BombDefault bomb)
    {
        color = Color.hsb(Utils.getRandomNumber(25, 30), 1, 1);
        this.setFill(color);
        this.setStroke(color.brighter());
        this.setStrokeWidth(0.5);
        this.setRadius(Utils.getRandomNumber(4, 0.4));
        //
        double v = (Utils.getRandomNumber(10, 0.2) / 7);
        double h = (Utils.getRandomNumber(10, 0.2) / 7);
        if (Utils.getRandomNumber(2) == 0)
            v *= -1; //rastgele dikey yön %50 yukarı %50 aşağı.
        if (Utils.getRandomNumber(2) == 0)
            h *= -1; //rastgele yatay yön %50 sağ %50 sol.
        setVelocity(v);
        setHVelocity(h);
        this.setCenterX(bomb.getTipX());
        this.setCenterY(bomb.getTipY());
    }

    public boolean isDone()
    {
        return this.isFinished;
    }

    public double getVelocity()
    {
        return velocity;
    }

    public void setVelocity(double velocity)
    {
        this.velocity = velocity;
    }

    public double getHVelocity()
    {
        return hVelocity;
    }

    public void setHVelocity(double hVelocity)
    {
        this.hVelocity = hVelocity;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public void setLocation(double x, double y)
    {
        this.setCenterX(x);
        this.setCenterY(y);
    }

    public void spread()
    {
        this.setCenterX(getCenterX() + getHVelocity());
        this.setCenterY(getCenterY() + getVelocity());
    }
}