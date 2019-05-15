package com.fruitjanissary.SlicedFruits;

import com.fruitjanissary.Fruit;
import com.fruitjanissary.SlicedFruit;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;

public class SlicedLemon extends SlicedFruit
{

    public SlicedLemon(Fruit fruit, double angle, boolean isLeft)
    {
        super(fruit, angle, isLeft);
    }

    @Override
    public void extra()
    {
        Arc top = new Arc(getRadius(),getRadius(),getRadius()*0.8,getRadius()*1.4,30,120);
        top.setStroke(getStroke());
        top.setFill(getFill());
        top.setStrokeWidth(getStrokeWidth());
        Ellipse inside = new Ellipse(getRadius(),getRadius(),getRadius(),getRadius()*0.15);
        inside.setFill(getFill().brighter());
        inside.setStrokeWidth(getStrokeWidth());
        inside.setStroke(getStroke());
        getChildren().addAll(top,inside);//, test);
    }

    @Override
    public void slice(double sliceAngle)
    {
        //null
    }
}