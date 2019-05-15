package com.fruitjanissary;

import javafx.scene.layout.Pane;

public abstract class Explosion extends Pane
{

    protected double x;
    protected double y;
    //
    public Explosion(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.setMinHeight(710);
        this.setMaxHeight(710);
        this.setMaxWidth(710);
        this.setMinWidth(710);
    }

    protected abstract void draw();
    public abstract boolean isDone();
}