package com.fruitjanissary.VFX;

import com.fruitjanissary.Fruit;
import com.fruitjanissary.Ifallable;
import com.fruitjanissary.Utils;
import javafx.animation.FadeTransition;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Particles extends Circle implements Ifallable
{

    private double velocity = 0;
    private double hVelocity = 0;

    public Particles(Fruit fruit)
    {
        this.velocity = fruit.getVelocity();
        this.hVelocity = fruit.getHVelocity();
        //
        this.setCenterX(fruit.getCenterX());
        this.setCenterY(fruit.getCenterY());
        this.setFill(fruit.getJuice());
        this.setStroke(fruit.getStroke());
        this.setStrokeWidth(0.75);
        //
        setThrow(); //throw new fruit :)
        //
        //place here animation fade!! //i placed don't worry :)
        //
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), this);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(0.8);
        //fadeTransition.setCycleCount(5); //1 cycle is must be enough
        //fadeTransition.setAutoReverse(true); //not necessary
        fadeTransition.play(); //play when it's created
    }

    private void setThrow()
    {
        double r = Utils.getRandomNumber(8, 2);
        setRadius(r);
        double v = (Utils.getRandomNumber(5, 1) / r) * 3;
        double h = (Utils.getRandomNumber(5, 1) / r) * 2.8;
        if (Utils.getRandomNumber(2) == 0)
        {
            v *= -1; //rastgele yön %50 sağ %50 sol
        }
        if (Utils.getRandomNumber(2) == 0)
        {
            h *= -1; //rastgele yön %50 sağ %50 sol
        }
        setVelocity(getVelocity() + v);
        setHVelocity(getHVelocity() + h);
    }

    public void setVelocity(double velocity)
    {
        this.velocity = velocity;
    }

    public void setHVelocity(double hVelocity)
    {
        this.hVelocity = hVelocity;
    }

    public double getVelocity()
    {
        return velocity;
    }

    public double getHVelocity()
    {
        return hVelocity;
    }

    @Override
    public boolean isFall(int border)
    {
        //if (border + radius > centerY) return true else return false.
        return (this.getCenterY() > border + getRadius());
    }

    @Override
    public void fall()
    {
        //GRAVITY = 0.08 It's universal constant.
        //velocity = GRAVITY + velocity (velocity updating on every cycle.)
        this.setCenterY(this.getCenterY() + this.getVelocity());
        this.setCenterX(this.getCenterX() + this.getHVelocity());
        this.setVelocity(this.getVelocity() + GRAVITY);
    }
}