package com.fruitjanissary.SlicedFruits;

import com.fruitjanissary.Fruit;
import com.fruitjanissary.SlicedFruit;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class SlicedOrange extends SlicedFruit
{

    public SlicedOrange(Fruit fruit, double angle, boolean isLeft)
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
            Rectangle peduncle = new Rectangle(6, 25, Color.GREEN);
            peduncle.setX(getRadius() - 3);
            peduncle.setY(-3);
            //
            Ellipse leaf = new Ellipse(15, 4);
            leaf.setFill(Color.GREEN.brighter());
            leaf.setCenterX(getRadius() + 10);
            leaf.setCenterY(5);
            leaf.setRotate(-30);
            getChildren().addAll(shadow, peduncle, leaf);
        }
        Ellipse inside = new Ellipse(getRadius(), getRadius(), getRadius(), getRadius() * 0.2);
        inside.setFill(Color.LIGHTYELLOW);
        inside.setStrokeWidth(getStrokeWidth());
        inside.setStroke(getStroke());
        Ellipse inside2 = new Ellipse(getRadius(), getRadius(), getRadius() * 0.88, inside.getRadiusY() * 0.75);
        inside2.setFill(Color.ORANGE.brighter()); //Color.valueOf("E55B00").brighter() //old
        getChildren().addAll(inside, inside2);
    }

    @Override
    public void slice(double sliceAngle)
    {
        //slice must be implemented but we dont need for this class
    }
}