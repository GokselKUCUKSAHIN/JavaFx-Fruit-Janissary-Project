package com.fruitjanissary.Fruits;

import com.fruitjanissary.*;
import com.fruitjanissary.SlicedFruits.SlicedOrange;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Orange extends Fruit
{

    RotateTransition rt = new RotateTransition(Duration.millis(4000), this);

    public Orange(int centerX, int centerY, double radius)
    {
        super(centerX, centerY, radius + 7); //orange has +7 radius
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
        setFill(Color.ORANGE);
        setStroke(Color.ORANGERED);
        setStrokeWidth(4);
        Circle body = new Circle(getRadius(), getRadius(), getRadius());
        body.setStroke(getStroke());
        body.setFill(getFill());
        body.setStrokeWidth(getStrokeWidth());
        setScoreValue(this.getScoreValue() - 6);
        setJuice(Color.valueOf("FE9912").brighter());
        //
        Ellipse shadow = new Ellipse(18, 5);
        shadow.setCenterX(getRadius());
        shadow.setCenterY(20);
        shadow.setFill(getFill().darker());
        //
        Rectangle peduncle = new Rectangle(6, 25, Color.GREEN);
        peduncle.setX(getRadius() - 3);
        peduncle.setY(-3);
        //
        Ellipse leaf = new Ellipse(15, 4);
        leaf.setFill(Color.GREEN.brighter());
        leaf.setCenterX(getRadius() + 10);
        leaf.setCenterY(5);
        leaf.setRotate(-30);
        //
        getChildren().addAll(body, shadow, peduncle, leaf);//, test);
    }

    @Override
    public void slice(double sliceAngle)
    {
        slicedFruits.clear();
        boolean split = sliceAngle < 180; //left always I-II region side
        slicedFruits.add(new SlicedOrange(this, sliceAngle, split));
        slicedFruits.add(new SlicedOrange(this, sliceAngle + 180, !split));
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
}