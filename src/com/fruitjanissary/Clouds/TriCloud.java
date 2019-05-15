package com.fruitjanissary.Clouds;

import com.fruitjanissary.Cloud;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class TriCloud extends Cloud
{

    public TriCloud(double size)
    {
        this.setSize(size*1.6,size);
        draw();
    }

    @Override
    public void draw()
    {
        //setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

        //
        Arc arc1 = new Arc(getSize()/3, getSize(), getSize()/3, getSize()/3, 0, 180);
        arc1.setFill(Color.LIGHTBLUE);
        arc1.setType(ArcType.ROUND);
        //
        Arc arc2 = new Arc(getSize()*5/6, getSize(), getSize()/2, getSize()/2, 0, 180);
        arc2.setFill(Color.LIGHTBLUE);

        arc2.setType(ArcType.ROUND);
        //
        Arc arc3 = new Arc(getSize()*4/3, getSize(), getSize()*2/3, getSize()*2/3, 0, 180);
        arc3.setFill(Color.LIGHTBLUE);
        arc3.setType(ArcType.ROUND);
        //setMinSize(200,200);
        this.getChildren().addAll(arc1, arc2, arc3);
    }
}