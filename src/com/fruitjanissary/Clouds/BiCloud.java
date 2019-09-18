package com.fruitjanissary.Clouds;

import com.fruitjanissary.Cloud;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class BiCloud extends Cloud
{

    private double size;

    public BiCloud(double size)
    {
        this.setSize(size * 1.6, size);
        draw();
    }

    @Override
    public void draw()
    {
        //
        //setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        //
        this.setOpacity(0.87);
        //
        Arc arc1 = new Arc(getSize() * 2 / 5, getSize() * 4 / 5, getSize() * 2 / 5, getSize() * 2 / 5, 0, 180);
        arc1.setFill(Color.LIGHTBLUE);
        arc1.setType(ArcType.ROUND);
        //
        Arc arc2 = new Arc(getSize(), getSize() * 4 / 5, getSize() * 3 / 5, getSize() * 3 / 5, 0, 180);
        arc2.setFill(Color.LIGHTBLUE);
        arc2.setType(ArcType.ROUND);
        //setMinSize(200,200);
        this.getChildren().addAll(arc1, arc2);
    }
}