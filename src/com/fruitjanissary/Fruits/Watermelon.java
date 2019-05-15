package com.fruitjanissary.Fruits;

import com.fruitjanissary.Fruit;
import com.fruitjanissary.SlicedFruits.SlicedWatermelon;
import com.fruitjanissary.Utils;
import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

public class Watermelon extends Fruit
{

    RotateTransition rt = new RotateTransition(Duration.millis(4000), this);

    public Watermelon(int centerX, int centerY, double radius)
    {
        super(centerX, centerY, radius + 10); //watermelon has +10 radius
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
        setFill(Color.GREEN);
        setStroke(Color.valueOf("275809"));
        setStrokeWidth(7);
        setScoreValue(this.getScoreValue() - 20);
        setJuice(Color.RED);
        //
        Ellipse body = new Ellipse(getRadius(),getRadius(),getRadius(),getRadius()*0.8);
        body.setStroke(getStroke());
        body.setFill(getFill());
        body.setStrokeWidth(getStrokeWidth());
        //
        Arc strip1 = new Arc(getRadius(),getRadius(),getRadius(),getRadius()*0.2,0,180);
        strip1.setFill(null);
        strip1.setStrokeWidth(5);
        strip1.setStroke(getStroke().darker());
        //
        Arc strip2 = new Arc(getRadius(),getRadius(),getRadius(),getRadius()*0.4,0,180);
        strip2.setFill(null);
        strip2.setStrokeWidth(5);
        strip2.setStroke(getStroke());
        //
        Arc strip3 = new Arc(getRadius(),getRadius(),getRadius(),getRadius()*0.6,0,180);
        strip3.setFill(null);
        strip3.setStrokeWidth(5);
        strip3.setStroke(getStroke().darker());
        //
        Arc strip4 = new Arc(getRadius(),getRadius(),getRadius(),getRadius()*0.8,0,180);
        strip4.setFill(null);
        strip4.setStrokeWidth(5);
        strip4.setStroke(getStroke());
        //
        Arc strip5 = new Arc(getRadius(),getRadius(),getRadius(),getRadius()*0.2,180,180);
        strip5.setFill(null);
        strip5.setStrokeWidth(5);
        strip5.setStroke(getStroke().darker());
        //
        Arc strip6 = new Arc(getRadius(),getRadius(),getRadius(),getRadius()*0.4,180,180);
        strip6.setFill(null);
        strip6.setStrokeWidth(5);
        strip6.setStroke(getStroke());
        //
        Arc strip7 = new Arc(getRadius(),getRadius(),getRadius(),getRadius()*0.6,180,180);
        strip7.setFill(null);
        strip7.setStrokeWidth(5);
        strip7.setStroke(getStroke().darker());
        //
        Arc strip8 = new Arc(getRadius(),getRadius(),getRadius(),getRadius()*0.8,180,180);
        strip8.setFill(null);
        strip8.setStrokeWidth(5);
        strip8.setStroke(getStroke());
        //
        getChildren().addAll(body,strip1,strip2,strip3,strip4,strip5,strip6,strip7,strip8);
    }

    @Override
    public void slice(double sliceAngle)
    {
        slicedFruits.clear();
        boolean split = sliceAngle < 180; //left always I-II region side
        slicedFruits.add(new SlicedWatermelon(this, sliceAngle, split));
        slicedFruits.add(new SlicedWatermelon(this, sliceAngle + 180, !split));
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
