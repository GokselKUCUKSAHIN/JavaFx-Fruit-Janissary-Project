package com.fruitjanissary.PowerUps;

import com.fruitjanissary.PowerUp;
import com.fruitjanissary.SlicedFruits.SlicedApple;
import com.fruitjanissary.Utils;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RainbowFruit extends PowerUp
{

    Color rainbow = Color.RED;
    int rColor = (int) Utils.getRandomNumber(2500);
    Circle body;
    Ellipse shadow;
    RotateTransition rt = new RotateTransition(Duration.millis(4000), this);
    EventHandler<ActionEvent> fx = e -> {
        //easy way to create rainbow effect
        //Hue Saturation Brightness :)

        rainbow = Color.hsb(rColor, 0.90, 1);
        rColor++;
        setFill(rainbow);
        setStroke(rainbow.darker());
        body.setFill(rainbow);
        shadow.setFill(rainbow.darker());
        body.setStroke(rainbow.darker().darker());
        setJuice(rainbow);
    };
    Timeline rbow = new Timeline(new KeyFrame(Duration.millis(100), fx));

    public RainbowFruit(int centerX, int centerY, double radius)
    {
        super(centerX, centerY, radius);
        draw();
        double angle = Utils.getRandomNumber(600, 400);
        if (Utils.getRandomNumber(2) == 0)
        {
            angle *= -1;
        }
        rbow.setRate(25);
        rbow.setCycleCount(Timeline.INDEFINITE);
        rbow.play();
        rt.setByAngle(angle);
        rt.setCycleCount(2);
        rt.play();
        rt.setOnFinished(e -> {
            rbow.stop();
        });
        setID(0);
        //rainbowDash.setCycleCount(Timeline.INDEFINITE);
    }

    @Override
    public void draw()
    {
        setFill(Color.valueOf("D02A1C"));
        setStroke(Color.valueOf("781714"));
        setStrokeWidth(5.5);
        setScoreValue(1000);
        body = new Circle(getRadius(), getRadius(), getRadius());
        body.setStroke(getStroke());
        body.setFill(getFill());
        body.setStrokeWidth(getStrokeWidth());
        //
        shadow = new Ellipse(18, 5);
        shadow.setCenterX(getRadius());
        shadow.setCenterY(20);
        shadow.setFill(getFill().darker());
        //
        Rectangle peduncle = new Rectangle(6, 25, Color.LIGHTGREEN);
        peduncle.setX(getRadius() - 3);
        peduncle.setY(-3);
        //
        Ellipse leaf = new Ellipse(15, 4);
        leaf.setFill(Color.GREEN);
        leaf.setCenterX(getRadius() - 10);
        leaf.setCenterY(5);
        leaf.setRotate(30);
        //
        getChildren().addAll(body, shadow, peduncle, leaf);//, test);
    }

    @Override
    public void slice(double sliceAngle)
    {
        slicedFruits.clear();
        boolean split = sliceAngle < 180; //left always I-II region side
        slicedFruits.add(new SlicedApple(this, sliceAngle, split));
        slicedFruits.add(new SlicedApple(this, sliceAngle + 180, !split));
    }

    @Override
    public void setPauseAnim()
    {
        rt.pause();
        rbow.pause();
    }

    @Override
    public void setPlayAnim()
    {
        rt.play();
        rbow.play();
    }
}
