package com.fruitjanissary.Fruits;

import com.fruitjanissary.Fruit;
import com.fruitjanissary.SlicedFruits.SlicedPeach;
import com.fruitjanissary.Utils;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Peach extends Fruit
{

    RotateTransition rt = new RotateTransition(Duration.millis(4000), this);

    public Peach(int centerX, int centerY, double radius)
    {
        super(centerX, centerY, radius + 2); //peach has -5 radius
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
        setFill(Color.valueOf("F7A78E"));
        setStroke(Color.valueOf("C0614F"));
        setStrokeWidth(4);
        Circle body = new Circle(getRadius(), getRadius(), getRadius());
        body.setStroke(getStroke());
        body.setFill(getFill());
        body.setStrokeWidth(getStrokeWidth());
        setScoreValue(this.getScoreValue() - 5);
        setJuice(Color.valueOf("FEFBC2"));
        //
        Ellipse shadow = new Ellipse(18, 5);
        shadow.setCenterX(getRadius());
        shadow.setCenterY(20);
        shadow.setFill(getFill().darker());
        //
        Rectangle peduncle = new Rectangle(6, 25, Color.DARKGREEN);
        peduncle.setX(getRadius() - 3);
        peduncle.setY(-3);
        //
        Ellipse leaf = new Ellipse(9, 3);
        leaf.setFill(Color.DARKGREEN.brighter());
        leaf.setCenterX(getRadius() - 5);
        leaf.setCenterY(5);
        leaf.setRotate(30);
        //
        Ellipse leaf1 = new Ellipse(9, 3);
        leaf1.setFill(Color.DARKGREEN);
        leaf1.setCenterX(getRadius() + 5);
        leaf1.setCenterY(12);
        leaf1.setRotate(-30);
        //
        getChildren().addAll(body, shadow, peduncle, leaf, leaf1);//, test);
    }

    @Override
    public void slice(double sliceAngle)
    {
        slicedFruits.clear();
        boolean split = sliceAngle < 180; //left always I-II region side
        slicedFruits.add(new SlicedPeach(this, sliceAngle, split));
        slicedFruits.add(new SlicedPeach(this, sliceAngle + 180, !split));
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