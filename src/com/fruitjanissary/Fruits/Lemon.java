package com.fruitjanissary.Fruits;

import com.fruitjanissary.Fruit;
import com.fruitjanissary.SlicedFruits.SlicedLemon;
import com.fruitjanissary.Utils;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

public class Lemon extends Fruit
{

    RotateTransition rt = new RotateTransition(Duration.millis(4000), this);

    public Lemon(int centerX, int centerY, double radius)
    {
        super(centerX, centerY, radius - 8); //lemon has -8 radius
        draw();
        double angle = Utils.getRandomNumber(600, 400);
        if (Utils.getRandomNumber(2) == 0)
        {
            angle *= -1;
        }
        rt.setByAngle(angle);
        rt.setCycleCount(2);
        rt.play();
    }

    @Override
    public void draw()
    {
        setFill(Color.valueOf("FFFF77"));
        setStroke(Color.valueOf("FFCC12"));
        setStrokeWidth(5);
        setScoreValue(this.getScoreValue() + 30);
        setJuice(Color.YELLOW.brighter());
        //
        /*
        Circle body = new Circle(getRadius(), getRadius(), getRadius());
        body.setStroke(getStroke());
        body.setFill(getFill());
        body.setStrokeWidth(getStrokeWidth());*/
        Arc rightPart = new Arc(getRadius(), getRadius(), getRadius(), getRadius(), 310, 100);
        Arc leftPart = new Arc(getRadius(), getRadius(), getRadius(), getRadius(), 130, 100);
        rightPart.setStroke(getStroke());
        rightPart.setFill(getFill());
        rightPart.setStrokeWidth(getStrokeWidth());
        leftPart.setStroke(getStroke());
        leftPart.setFill(getFill());
        leftPart.setStrokeWidth(getStrokeWidth());
        //
        Ellipse top = new Ellipse(getRadius(), getRadius(), getRadius() * 0.8, getRadius() * 1.4);
        top.setStroke(getStroke());
        top.setFill(getFill());
        top.setStrokeWidth(getStrokeWidth());
        getChildren().addAll(top, leftPart, rightPart);//, test);
    }

    @Override
    public void slice(double sliceAngle)
    {
        slicedFruits.clear();
        boolean split = sliceAngle < 180; //left always I-II region side
        slicedFruits.add(new SlicedLemon(this, sliceAngle, split));
        slicedFruits.add(new SlicedLemon(this, sliceAngle + 180, !split));
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
    public boolean isHit(double x, double y)
    {
        if (getDistance(x, y) < 0.95 * getRadius()) //if distance less than 85% of radius
        {
            return true;
        } else
        {
            return false;
        }
    }
}