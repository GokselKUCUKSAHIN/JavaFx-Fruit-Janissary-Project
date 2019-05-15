package com.fruitjanissary.PowerUps;

import com.fruitjanissary.PowerUp;
import com.fruitjanissary.SlicedFruits.SlicedMushroom;
import com.fruitjanissary.Utils;
import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Mushroom extends PowerUp
{

    RotateTransition rt = new RotateTransition(Duration.millis(4000), this);

    public Mushroom(int centerX, int centerY, double radius)
    {
        super(centerX, centerY, radius + 7); //orange has +7 radius
        draw();
        double angle = Utils.getRandomNumber(300, 250);
        if (Utils.getRandomNumber(2) == 0)
        {
            angle *= -1;
        }
        rt.setByAngle(angle);
        rt.setCycleCount(2);
        rt.play();
        setID(1);
    }

    @Override
    public void draw()
    {
        setFill(Color.valueOf("FF9934"));
        setStroke(getFill().brighter());
        setStrokeWidth(5.3);
        //Circle body = new Circle(getRadius(), getRadius(), getRadius());
        Arc body = new Arc(getRadius(), getRadius(), getRadius() * 0.9, getRadius(), 0, 180);
        body.setFill(getFill());
        body.setStroke(getStroke().darker().darker());
        body.setStrokeWidth(getStrokeWidth());
        body.setType(ArcType.ROUND);
        setScoreValue(500);
        setJuice(Color.valueOf("FF9934"));
        //
        Rectangle bottom = new Rectangle(getRadius() - 25, getRadius() - 5, 50, 35);
        bottom.setFill(Color.valueOf("FEFFFA"));
        bottom.setStroke(Color.valueOf("FEFFFA").darker());
        bottom.setStrokeWidth(2.3);
        bottom.setArcWidth(20);
        bottom.setArcHeight(20);
        //
        //Ellipse dot1 = new Ellipse(getRadius() - 18, getRadius() - 23, 18, 15);
        Circle dot1 = new Circle(getRadius() - 20, getRadius() - 23, 15);
        dot1.setFill(Color.valueOf("D82900"));
        //dot1.setRotate(-25);
        //
        Circle dot2 = new Circle(getRadius() + 16, getRadius() - 35, 11);
        dot2.setFill(Color.valueOf("D82900"));
        //
        //Ellipse dot3 = new Ellipse(getRadius() + 30, getRadius() - 15, 9, 11);
        Circle dot3 = new Circle(getRadius() + 33, getRadius() - 13, 8);
        dot3.setFill(Color.valueOf("D82900"));
        //dot3.setRotate(50);
        //
        getChildren().addAll(bottom, body, dot1, dot2, dot3);
    }

    @Override
    public void slice(double sliceAngle)
    {
        slicedFruits.clear();
        boolean split = sliceAngle < 180; //left always I-II region side
        slicedFruits.add(new SlicedMushroom(this, sliceAngle, split));
        slicedFruits.add(new SlicedMushroom(this, sliceAngle + 180, !split));
    }

    @Override
    public void setPauseAnim()
    {
        rt.pause();
    }

    @Override
    public void setPlayAnim()
    {
        rt.play();
    }
}
