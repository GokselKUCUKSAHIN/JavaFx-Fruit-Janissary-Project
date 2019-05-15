package com.fruitjanissary.SlicedFruits;

import com.fruitjanissary.Fruit;
import com.fruitjanissary.SlicedFruit;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class SlicedMango extends SlicedFruit
{

    public SlicedMango(Fruit fruit, double angle, boolean isLeft)
    {
        super(fruit, angle, isLeft);
    }

    @Override
    public void extra()
    {
        if (getIsLeft())
        {
            Ellipse shadow = new Ellipse(18, 5);
            shadow.setCenterX(getRadius());
            shadow.setCenterY(20);
            shadow.setFill(getFill().darker());
            //
            Rectangle peduncle = new Rectangle(6, 25, Color.DARKGREEN);
            peduncle.setX(getRadius() - 3);
            peduncle.setY(-3);
            //
            Ellipse leaf = new Ellipse(28, 5);
            leaf.setFill(Color.DARKGREEN.brighter());
            leaf.setCenterX(getRadius() - 18);
            leaf.setCenterY(7);
            leaf.setRotate(-20);
            getChildren().addAll(shadow,peduncle,leaf);
        }
        Ellipse inside = new Ellipse(getRadius(),getRadius(),getRadius(),getRadius()*0.2);
        inside.setFill(Color.valueOf("FDE26C"));
        inside.setStrokeWidth(getStrokeWidth());
        inside.setStroke(getStroke());
        getChildren().addAll(inside);
    }

    @Override
    public void slice(double sliceAngle)
    {

    }
}