package com.fruitjanissary.SlicedFruits;

import com.fruitjanissary.Fruit;
import com.fruitjanissary.SlicedFruit;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class SlicedWatermelon extends SlicedFruit
{

    public SlicedWatermelon(Fruit fruit, double angle, boolean isLeft)
    {
        super(fruit, angle, isLeft);
    }

    @Override
    public void draw()
    {
        Arc body = new Arc(getRadius(), getRadius(), getRadius(), getRadius() * 0.8, 0, 180);
        body.setFill(getFill());
        body.setStroke(getStroke());
        body.setStrokeWidth(getStrokeWidth());
        //
        getChildren().addAll(body);
        extra();
        this.setRotate(-getAngle());
    }

    @Override
    public void extra()
    {
        Arc strip1 = new Arc(getRadius(), getRadius(), getRadius(), getRadius() * 0.2, 0, 180);
        strip1.setFill(null);
        strip1.setStrokeWidth(5);
        strip1.setStroke(getStroke().darker());
        //
        Arc strip2 = new Arc(getRadius(), getRadius(), getRadius(), getRadius() * 0.4, 0, 180);
        strip2.setFill(null);
        strip2.setStrokeWidth(5);
        strip2.setStroke(getStroke());
        //
        Arc strip3 = new Arc(getRadius(), getRadius(), getRadius(), getRadius() * 0.6, 0, 180);
        strip3.setFill(null);
        strip3.setStrokeWidth(5);
        strip3.setStroke(getStroke().darker());
        //
        Arc strip4 = new Arc(getRadius(), getRadius(), getRadius(), getRadius() * 0.8, 0, 180);
        strip4.setFill(null);
        strip4.setStrokeWidth(5);
        strip4.setStroke(getStroke());
        //
        Ellipse inside = new Ellipse(getRadius(),getRadius(),getRadius(),getRadius()*0.3);
        inside.setFill(Color.LIGHTGREEN);
        inside.setStrokeWidth(6);
        inside.setStroke(getStroke());
        //
        Ellipse inside2 = new Ellipse(getRadius(),getRadius(),getRadius()*0.88,inside.getRadiusY()*0.75);
        inside2.setFill(Color.RED);
        getChildren().addAll(strip1, strip2, strip3, strip4,inside,inside2);
    }

    @Override
    public void slice(double sliceAngle)
    {

    }
}
