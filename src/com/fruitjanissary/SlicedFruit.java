package com.fruitjanissary;

import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public abstract class SlicedFruit extends Fruit
{

    private double angle;
    RotateTransition rt = new RotateTransition(Duration.millis(3000), this);
    private boolean isLeft;

    public SlicedFruit(Fruit fruit, double angle, boolean isLeft)
    {
        super((int) fruit.getCenterX(), (int) fruit.getCenterY(), fruit.getRadius());
        setFill(fruit.getFill());
        setStroke(fruit.getStroke());
        setJuice(fruit.getJuice());
        setStrokeWidth(fruit.getStrokeWidth());
        setVelocity(fruit.getVelocity());
        setHVelocity(fruit.getHVelocity());
        this.angle = angle;
        this.isLeft = isLeft;
        draw();

        if (isLeft)
        {
            rt.setByAngle(-200); //200 degree Counter-Clockwise
        } else
        {
            rt.setByAngle(200); //200 degree Clockwise
        }
        if (isLeft)
        {
            //Left Slice
            setHVelocity(getHVelocity() - Utils.getRandomNumber(5, 2.3) / 3);
            setVelocity(getVelocity() + Utils.getRandomNumber(3, 3) / 5);
        } else
        {
            //Right Slice
            setHVelocity(getHVelocity() + Utils.getRandomNumber(5, 2.3) / 3);
            setVelocity(getVelocity() - Utils.getRandomNumber(3, 3) / 5);
        }
        rt.setCycleCount(1);
        rt.play();
    }

    public abstract void extra();

    @Override
    public void draw()
    {
        Arc body = new Arc(getRadius(), getRadius(), getRadius(), getRadius(), 0, 180);
        body.setFill(getFill());
        body.setStroke(getStroke());
        body.setStrokeWidth(getStrokeWidth());
        getChildren().addAll(body);
        extra();
        this.setRotate(-angle);
    }

    @Override
    public void setPauseAnim()
    {
        rt.pause();
    }

    @Override
    public void setPlayAnim()
    {
        rt.play();
    }

    @Override
    public boolean isFall(int border)
    {
        return (this.getCenterY() > border + getRadius());
    }

    public boolean getIsLeft()
    {
        return this.isLeft;
    }

    public double getAngle()
    {
        return this.angle;
    }

}