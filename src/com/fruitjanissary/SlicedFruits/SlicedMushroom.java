package com.fruitjanissary.SlicedFruits;

import com.fruitjanissary.Fruit;
import com.fruitjanissary.SlicedFruit;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SlicedMushroom extends SlicedFruit
{

    public SlicedMushroom(Fruit fruit, double angle, boolean isLeft)
    {
        super(fruit, angle, isLeft);
    }

    @Override
    public void draw()
    {
        extra();
    }

    @Override
    public void extra()
    {
        if (getIsLeft())
        {
            Arc body = new Arc(getRadius(), getRadius(), getRadius() * 0.9, getRadius(), 90, 90);
            body.setFill(getFill());
            body.setStroke(getStroke().darker().darker());
            body.setStrokeWidth(getStrokeWidth());
            body.setType(ArcType.ROUND);
            setScoreValue(this.getScoreValue() + 1000);
            setJuice(Color.valueOf("FF9934"));
            //
            Rectangle bottom = new Rectangle(getRadius() - 25, getRadius() - 5, 25, 35);
            bottom.setFill(Color.valueOf("FEFFFA"));
            bottom.setStroke(Color.valueOf("FEFFFA").darker());
            bottom.setStrokeWidth(2.3);
            bottom.setArcWidth(20);
            bottom.setArcHeight(20);
            Circle dot1 = new Circle(getRadius() - 20, getRadius() - 23, 15);
            dot1.setFill(Color.valueOf("D82900"));
            getChildren().addAll(bottom, body, dot1);
            //
            setRotate(90 - getAngle());
        } else
        {
            Arc body = new Arc(getRadius(), getRadius(), getRadius() * 0.9, getRadius(), 0, 90);
            body.setFill(getFill());
            body.setStroke(getStroke().darker().darker());
            body.setStrokeWidth(getStrokeWidth());
            body.setType(ArcType.ROUND);
            setScoreValue(this.getScoreValue() + 1000);
            setJuice(Color.valueOf("FF9934"));
            //
            Rectangle bottom = new Rectangle(getRadius(), getRadius() - 5, 25, 35);
            bottom.setFill(Color.valueOf("FEFFFA"));
            bottom.setStroke(Color.valueOf("FEFFFA").darker());
            bottom.setStrokeWidth(2.3);
            bottom.setArcWidth(20);
            bottom.setArcHeight(20);
            //
            Circle dot2 = new Circle(getRadius() + 16, getRadius() - 35, 11);
            dot2.setFill(Color.valueOf("D82900"));
            //
            //Ellipse dot3 = new Ellipse(getRadius() + 30, getRadius() - 15, 9, 11);
            Circle dot3 = new Circle(getRadius() + 33, getRadius() - 13, 8);
            dot3.setFill(Color.valueOf("D82900"));
            getChildren().addAll(bottom, body, dot2, dot3);
            setRotate(getAngle() + 90);
        }

        //setRotate(180);
        /*
        if (getIsLeft())
        {
            Arc body = new Arc(getRadius(), getRadius(), getRadius() * 0.9, getRadius(), 0, 180);
            body.setFill(getFill());
            body.setStroke(getStroke().darker().darker());
            body.setStrokeWidth(getStrokeWidth());
            body.setType(ArcType.ROUND);
            setScoreValue(this.getScoreValue() + 1000);
            setJuice(Color.valueOf("FF9934"));
            //
            Ellipse dot1 = new Ellipse(getRadius() - 18, getRadius() - 23, 18, 15);
            dot1.setFill(Color.valueOf("D82900"));
            dot1.setRotate(-25);
            //
            Circle dot2 = new Circle(getRadius() + 16, getRadius() - 35, 11);
            dot2.setFill(Color.valueOf("D82900"));
            //
            Ellipse dot3 = new Ellipse(getRadius() + 30, getRadius() - 15, 9, 11);
            dot3.setFill(Color.valueOf("D82900"));
            dot3.setRotate(50);
            getChildren().addAll(body, dot1, dot2,dot3);
        }
        else
        {
            Rectangle bottom = new Rectangle(getRadius() - 25, getRadius() - 5, 50, 35);
            bottom.setFill(Color.valueOf("FEFFFA"));
            bottom.setStroke(Color.valueOf("FEFFFA").darker());
            bottom.setStrokeWidth(2.3);
            bottom.setArcWidth(20);
            bottom.setArcHeight(20);
            getChildren().addAll(bottom);
        }*/
    }

    @Override
    public void slice(double sliceAngle)
    {

    }
}
