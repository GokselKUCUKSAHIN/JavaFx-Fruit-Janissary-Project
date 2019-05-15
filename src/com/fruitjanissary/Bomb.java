package com.fruitjanissary;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public abstract class Bomb extends Pane implements Ifallable, Isliceable
{

    private double radius;
    private double size;
    private double velocity = 0;
    private double hVelocity = 0;
    public static final int NUMBER_OF_BOMBS = 2;

    public Bomb(int x, int y)
    {
        this.setLocation(x, y);
        draw();
    }

    public abstract void draw();

    public void setSize(double radius)
    {
        this.radius = radius;
        this.size = radius * 3.5;
        setMinHeight(size);
        setMaxHeight(size);
        setMinWidth(size);
        setMaxWidth(size);
    }

    public double getSize()
    {
        return this.size;
    }

    public void setLocation(double x, double y)
    {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public double getCenterX()
    {
        return getSize() / 2;
    }

    public void setCenterX(double x)
    {
        setLayoutX(x + getSize() / 2);
    }

    public double getCenterY()
    {
        return getSize() / 2;
    }

    public double getHitCenterX()
    {
        return getLayoutX() + getCenterX();
    }

    public double getHitCenterY()
    {
        return getLayoutY() + getCenterY();
    }

    public void setCenterY(double y)
    {
        setLayoutY(y + getSize() / 2);
    }

    public double getRadius()
    {
        return this.radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
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

    public abstract void setPlayAnim();

    public abstract void setPauseAnim();

    //
    public double getDistance(double x, double y)
    {
        //I calculate delta x and y after i get distance using pythagoras theorem.
        double dx = Math.abs(this.getHitCenterX() - x); //delta x
        double dy = Math.abs(this.getHitCenterY() - y); //delta y
        return Math.round(Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2))); //sqrt(dx^2+dy^2) and rounding for get rid of unnecessary decimals
    }

    public boolean isHit(double x, double y)
    {
        if (getDistance(x, y) < getRadius() * 0.90)
        {
            return true;
        } else
        {
            return false;
        }
    }

    @Override
    public boolean isFall(int border)
    {
        return (this.getLayoutY() > border);
    }

    @Override
    public void fall()
    {
        this.setLayoutY(this.getLayoutY() + this.getVelocity());
        this.setLayoutX(this.getLayoutX() + this.getHVelocity());
        this.setVelocity(this.getVelocity() + GRAVITY);
    }

    public abstract void explode(); //

    public abstract int getID();

    public abstract Explosion getExplosion();
}