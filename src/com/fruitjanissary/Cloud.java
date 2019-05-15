package com.fruitjanissary;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.transform.Translate;

public abstract class Cloud extends Pane
{

    private boolean bounce = false;
    private double size = 0;
    private double widthLimit;

    //Clouds are actually two intersecting arcs
    //we need 2 or 3 arc for getting 1 cloud
    //Clouds are actually two intersecting arcs
    //we need 2 or 3 arc for getting 1 cloud

    public abstract void draw();

    public void movePivot(double x, double y)
    {
        this.getTransforms().add(new Translate(-x, -y));
        this.setTranslateX(x);
        this.setTranslateY(y);
    }

    public void setLocation(double x, double y)
    {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void setSize(double width, double height)
    {
        this.size = height;
        setMinWidth(width);
        setMaxWidth(width);
        setMaxHeight(height);
        setMaxHeight(height);
    }

    public boolean getBounce()
    {
        return this.bounce;
    }

    public void setBounce(boolean bounce)
    {
        this.bounce = bounce;
    }

    public double getSize()
    {
        return size;
    }

    public void setSize(double size)
    {
        this.size = size;
    }
}