package com.fruitjanissary.Bombs;

import com.fruitjanissary.Bomb;
import com.fruitjanissary.Explosion;
import com.fruitjanissary.Explosions.RegularBombExplosion;
import com.fruitjanissary.Utils;
import com.fruitjanissary.VFX.Spark;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class BombDefault extends Bomb
{

    private int id = 0;
    Explosion explosion;
    private double tipX;
    private double tipY;
    ArrayList<Spark> sparks = new ArrayList<>();
    //
    RotateTransition rt = new RotateTransition(Duration.millis(4000), this);
    EventHandler<ActionEvent> sparkFX = e -> {
        Spark spark = new Spark(this);
        sparks.add(spark);
        this.getChildren().add(spark);
        //
        for (int j = 0; j < sparks.size(); j++)
        {
            Spark spark1 = sparks.get(j);
            spark1.spread();
            if (spark1.isDone())
            {
                sparks.remove(spark1);

                getChildren().remove(spark1);
            }
        }
    };
    Timeline bombSpark = new Timeline(new KeyFrame(Duration.millis(100), sparkFX));

    //
    public BombDefault(int x, int y)
    {
        super(x, y);
        tipX = (getSize() / 2);
        tipY = ((getSize() / 2) - (getRadius() * 19 / 12));
        double angle = Utils.getRandomNumber(600, 400);
        if (Utils.getRandomNumber(2) == 0)
        {
            angle *= -1;
        }
        rt.setByAngle(angle);
        rt.setCycleCount(2);
        rt.play();
        //
        //Bomb Spark
        //
        bombSpark.setCycleCount(100);
        bombSpark.setRate(5);
        bombSpark.setAutoReverse(false);
        bombSpark.play();
        bombSpark.setOnFinished(e -> {
            //System.out.print("Before " + getChildren().size());
            getChildren().removeAll(sparks);
            sparks.clear();
            //System.out.println(": is Deleted ?" + getChildren().size());
        });
    }

    @Override
    public void draw()
    {
        //setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        setSize(40);
        Circle main = new Circle(getSize() / 2, getSize() / 2, getRadius());
        main.setFill(Color.valueOf("070707"));
        main.setStroke(Color.valueOf("333333"));
        main.setStrokeWidth(getRadius() / 8);
        //
        Circle shade1 = new Circle((getSize() / 2) * 1.01, getSize() / 2, getRadius() * 0.87);
        shade1.setFill(Color.WHITE);
        //
        Circle shade2 = new Circle((getSize() / 2) * 1.06, getSize() / 2, getRadius() * 0.835);
        shade2.setFill(Color.valueOf("070707"));
        //
        Rectangle top = new Rectangle(getRadius() / 1.25, getRadius() / 2.5);
        top.setFill(Color.valueOf("070707"));
        top.setStroke(Color.valueOf("333333"));
        top.setStrokeWidth(getRadius() / 10);
        top.setArcHeight(getRadius() / 4);
        top.setArcWidth(getRadius() / 4);
        top.setLayoutY((getSize() / 2) - getRadius() * 6 / 5);
        top.setLayoutX((getSize() / 2) - (getRadius() / 2.5));
        //
        Rectangle tip = new Rectangle(getRadius() / 8, getRadius() / 2);
        tip.setFill(Color.valueOf("FFC266"));
        tip.setStroke(Color.valueOf("333333"));
        tip.setStrokeWidth(getRadius() / 25);
        tip.setArcWidth(getRadius() / 10);
        tip.setArcHeight(getRadius() / 10);
        tip.setLayoutX((getSize() / 2) - getRadius() / 16);
        tip.setLayoutY(((getSize() / 2) - (getRadius() * 19 / 12)));
        //
            /*
        Circle hit = new Circle(getCenterX(),getCenterY(),getRadius());
        hit.setStrokeWidth(3);
        hit.setStroke(Color.RED);
        hit.setFill(Color.rgb(0,0,0,0));
*/
        //
        //
        getChildren().addAll(tip, main, shade1, shade2, top);
    }

    public double getTipX()
    {
        return this.tipX;
    }

    public double getTipY()
    {
        return this.tipY;
    }

    @Override
    public void setPauseAnim()
    {
        rt.pause();
        bombSpark.pause();
    }

    @Override
    public void setPlayAnim()
    {
        rt.play();
        bombSpark.play();
    }

    public ArrayList<Spark> sparkEffect()
    {
        ArrayList<Spark> sparkArrayList = new ArrayList<>();
        for (int j = 0; j < 5; j++)//17
        {
            sparks.add(new Spark(this));
        }
        return sparkArrayList;
    }

    @Override
    public void explode()
    {
        explosion = new RegularBombExplosion(getHitCenterX(), getHitCenterY());
    }

    @Override
    public Explosion getExplosion()
    {
        return this.explosion;
    }

    @Override
    public void slice(double sliceAngle)
    {
        explode();
    }

    @Override
    public int getID()
    {
        return id;
    }
}