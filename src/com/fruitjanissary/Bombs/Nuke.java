package com.fruitjanissary.Bombs;

import com.fruitjanissary.Bomb;
import com.fruitjanissary.Explosion;
import com.fruitjanissary.Explosions.NuclearExplosion;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Nuke extends Bomb
{

    private int id = 1;
    private boolean isDroping = false;
    private boolean isSetAngle = false;
    private boolean isDroped = false;
    private Explosion explosion;
    private RotateTransition rt = new RotateTransition(Duration.millis(250), this);
    private EventHandler<ActionEvent> drop = e -> {
        if (this.getVelocity() > -0.5 && !isDroping)
        {
            rt.play();
            isDroping = true;
        }
        if (isSetAngle == false && getHVelocity() != 0)
        {
            isSetAngle = true;
            if (getHVelocity() > 0)
            {
                this.setRotate(15);
                rt.setByAngle(150);
            } else
            {
                this.setRotate(-15);
                rt.setByAngle(-150);
            }
        }
        if (isSetAngle && isDroping)
        {
            stopChecking();
        }
    };
    Timeline checkDrop = new Timeline(new KeyFrame(Duration.millis(10), drop));

    private void stopChecking()
    {
        checkDrop.stop();
    }

    public Nuke(int x, int y)
    {
        super(x, y);
        checkDrop.setCycleCount(Timeline.INDEFINITE);
        checkDrop.setRate(1);
        checkDrop.play();
        rt.setCycleCount(1);
        rt.setOnFinished(e -> {
            isDroped = true;
        });
    }

    @Override
    public void draw()
    {
        //setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        setSize(60); //50-65 variable size maybe :D
        Arc top = new Arc(getSize() / 2, getSize() * 1 / 3, getRadius() * 2 / 3, getRadius() * 0.9, 0, 180);
        top.setFill(Color.valueOf("202020"));
        top.setStroke(Color.valueOf("171717"));
        top.setStrokeWidth(getRadius() / 14);
        //
        Rectangle main = new Rectangle(getRadius() * 4 / 3, getRadius() * 21 / 30);
        main.setFill(Color.valueOf("202020"));
        main.setStroke(Color.valueOf("171717"));
        main.setStrokeWidth(getRadius() / 14);
        main.setLayoutY(getSize() * 1 / 3);
        main.setLayoutX((getSize() / 2) - (getRadius() * 2 / 3));
        main.setArcHeight(getRadius() * 2 / 15);
        main.setArcWidth(getRadius() * 1 / 15);
        //
        Arc tip = new Arc(getSize() / 2, getSize() * 1 / 7, getRadius() / 2.5, getRadius() / 4.8, 0, 180);
        tip.setFill(Color.RED);
        tip.setStroke(Color.valueOf("171717"));
        tip.setStrokeWidth(getRadius() / 20);
        tip.setType(ArcType.ROUND);
        //
        double[] triUp = new double[]
            {
                ((getSize() / 2) - (getRadius() * 18.5 / 30)), ((getSize() * 1 / 3) + (getRadius() * 0.7)),//1
                ((getSize() / 2) + (getRadius() * 37 / 60)), ((getSize() * 1 / 3) + (getRadius() * 0.7)),//2
                (getSize() / 2), ((getSize() * 1 / 3) + (getRadius() * 13 / 10))//3

            };
        Polygon tup = new Polygon(triUp);
        tup.setFill(Color.valueOf("202020"));
        tup.setStroke(Color.valueOf("171717"));
        //tup.setStroke(Color.RED);
        tup.setStrokeWidth(getRadius() / 14);
        //
        double[] triDown = new double[]
            {
                ((getSize() / 2) + (getRadius() * 37 / 60)), ((getSize() * 1 / 3) + (getRadius() * 3 / 2)),//1
                ((getSize() / 2) - (getRadius() * 18.5 / 30)), ((getSize() * 1 / 3) + (getRadius() * 3 / 2)),//2
                (getSize() / 2), ((getSize() * 1 / 3) + getRadius() * 59 / 70)//3
            };
        Polygon tdown = new Polygon(triDown);
        tdown.setFill(Color.valueOf("202020"));
        tdown.setStroke(Color.valueOf("171717"));
        //tdown.setStroke(Color.RED);
        tdown.setStrokeWidth(getRadius() / 14);
        //
        Rectangle bottom = new Rectangle(getRadius() * 4 / 3, getRadius() * 3.4 / 9);
        bottom.setFill(Color.valueOf("202020"));
        bottom.setStroke(Color.valueOf("171717"));
        bottom.setStrokeWidth(getRadius() / 14);
        bottom.setLayoutY((getSize() * 1 / 3) + (getRadius() * 3 / 2));
        bottom.setLayoutX((getSize() / 2) - (getRadius() * 2 / 3));
        bottom.setArcHeight(getRadius() * 1 / 6);
        bottom.setArcWidth(getRadius() * 1 / 15);
        //
        Circle logoBack = new Circle(getSize() / 2, (getSize() / 2) - getRadius() / 3.3, getRadius() * 1 / 2.3);
        logoBack.setFill(Color.YELLOW);
        logoBack.setStroke(Color.valueOf("171717"));
        logoBack.setStrokeWidth(getRadius() / 20);
        //
        Arc one = new Arc(logoBack.getCenterX(), logoBack.getCenterY(), getRadius() * 1 / 2.7, getRadius() * 1 / 2.7, 60, 60);
        one.setFill(Color.valueOf("171717"));
        one.setType(ArcType.ROUND);
        Arc two = new Arc(logoBack.getCenterX(), logoBack.getCenterY(), getRadius() * 1 / 2.7, getRadius() * 1 / 2.7, 180, 60);
        two.setFill(Color.valueOf("171717"));
        two.setType(ArcType.ROUND);
        Arc three = new Arc(logoBack.getCenterX(), logoBack.getCenterY(), getRadius() * 1 / 2.7, getRadius() * 1 / 2.7, 300, 60);
        three.setFill(Color.valueOf("171717"));
        three.setType(ArcType.ROUND);
        //
        Circle yellowDot = new Circle(logoBack.getCenterX(), logoBack.getCenterY(), getRadius() * 1 / 9);
        yellowDot.setFill(Color.YELLOW);
        Circle blackDot = new Circle(logoBack.getCenterX(), logoBack.getCenterY(), getRadius() * 1 / 15);
        blackDot.setFill(Color.valueOf("171717"));
        getChildren().addAll(top, main, tdown, tup, tip, bottom, logoBack, one, two, three, yellowDot, blackDot);
    }

    @Override
    public void setPlayAnim()
    {
        if (isDroping && !isDroped)
        {
            //sadece düşerken animasyonu devam ettir
            //eğer kontrolsüz olarak play dersem
            //düşerken duraklattıktan sonra tekrar tekrar
            //dönmeye devam ediyor
            //sadece 1 kere düşerken dönmesi için
            //kontrol ediyorum.
            rt.play();
        }
    }

    @Override
    public void setPauseAnim()
    {
        if (isDroping)
        {
            //sadece düşerken duraklatılabilir
            //diğer durumlarda zaten oynamıyor.
            rt.pause();
        }
    }

    @Override
    public void explode()
    {
        this.explosion = new NuclearExplosion(getHitCenterX(), getHitCenterY());
    }

    @Override
    public Explosion getExplosion()
    {
        return explosion;
    }

    @Override
    public void slice(double sliceAngle)
    {
        explode();
    }

    @Override
    public void fall()
    {
        this.setLayoutY(this.getLayoutY() + this.getVelocity() * 17 / 30);
        this.setLayoutX(this.getLayoutX() + this.getHVelocity() / 2);
        this.setVelocity(this.getVelocity() + GRAVITY / 2);
    }

    @Override
    public int getID()
    {
        return id;
    }
}