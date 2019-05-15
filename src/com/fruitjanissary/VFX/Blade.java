package com.fruitjanissary.VFX;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Blade extends Pane
{

    private double size;
    private Color color = Color.LIGHTGRAY;
    private double stroke = 1.5;

    public Blade(double size)
    {
        this.size = size;
    }

    public Blade(double size, Color color)
    {
        this.size = size;
        this.color = color;
    }

    public double getSize()
    {
        return size;
    }

    public void setSize(double size)
    {
        this.size = size;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public double getStroke()
    {
        return stroke;
    }

    public void setStroke(double stroke)
    {
        this.stroke = stroke;
    }

    public Trace setGenerate()
    {
        return new Trace(this.size, this.color);
    }
}