package com.fruitjanissary.Fruits;

import com.fruitjanissary.Fruit;
import com.fruitjanissary.SlicedFruits.SlicedMango;
import com.fruitjanissary.Utils;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Mango extends Fruit
{
    RotateTransition rt = new RotateTransition(Duration.millis(4000), this);

    public Mango(int centerX, int centerY, double radius)
    {
        super(centerX, centerY, radius + 3); //mango has +3 radius
        draw();
        double angle = Utils.getRandomNumber(600, 400);
        if (Utils.getRandomNumber(2) == 0)
        {
            angle *= -1;
        }
        rt.setByAngle(angle);
        rt.setCycleCount(2);
        rt.play();
    }

    @Override
    public void draw()
    {
        setFill(Color.valueOf("E8D403"));
        setStroke(Color.valueOf("F16730"));
        setStrokeWidth(4.5);
        Circle body = new Circle(getRadius(), getRadius(), getRadius());
        body.setStroke(getStroke());
        body.setFill(getFill());
        body.setStrokeWidth(getStrokeWidth());
        setScoreValue(this.getScoreValue() - 3);
        setJuice(Color.valueOf("E8D403").brighter());
        //
        Ellipse shadow = new Ellipse(18, 5);
        shadow.setCenterX(getRadius());
        shadow.setCenterY(20);
        shadow.setFill(getFill().darker());
        //
        Rectangle peduncle = new Rectangle(6, 25, Color.DARKGREEN);
        peduncle.setX(getRadius() - 3);
        peduncle.setY(-3);
        //
        Ellipse leaf = new Ellipse(28, 7);
        leaf.setFill(Color.DARKGREEN.brighter());
        leaf.setCenterX(getRadius() - 18);
        leaf.setCenterY(7);
        leaf.setRotate(-20);
        //
        getChildren().addAll(body, shadow, peduncle, leaf);//, test);
    }

    @Override
    public void slice(double sliceAngle)
    {
        slicedFruits.clear();
        boolean split = sliceAngle < 180; //left always I-II region side
        slicedFruits.add(new SlicedMango(this, sliceAngle, split));
        slicedFruits.add(new SlicedMango(this, sliceAngle + 180, !split));
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