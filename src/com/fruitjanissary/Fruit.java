package com.fruitjanissary;

import com.fruitjanissary.VFX.Particles;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class Fruit extends Pane implements Ifallable, Isliceable
{

    public static final int TOTAL_FRUIT_COUNT = 7;
    private double velocity = 0; //fruits have velocity (vertical velocity)
    private double hVelocity = 0; //horizontal velocity
    private int scoreValue = 50; //default
    private Color juice = Color.valueOf("FEF695");
    private Color fill;
    private Color stroke;
    private double strokeWidth;
    private double radius;
    protected ArrayList<SlicedFruit> slicedFruits = new ArrayList<>();

    public Fruit(int centerX, int centerY, double radius)
    {
        //setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        this.radius = radius;
        setSize(2 * radius, 2 * radius);
        setCenterX(centerX);
        setCenterY(centerY);
    }

    public abstract void draw();

    public abstract void setPlayAnim();

    public abstract void setPauseAnim();

    public double getDistance(double x, double y)
    {
        //I calculate delta x and y after i get distance using pythagoras theorem.
        double dx = Math.abs(getCenterX() - x); //delta x
        double dy = Math.abs(getCenterY() - y); //delta y
        return Math.round(Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2))); // sqrt(dx^2+dy^2) and rounding for get rid of unnecessary decimals
    }

    public boolean isHit(double x, double y)
    {
        if (getDistance(x, y) < 0.85 * getRadius()) //if distance less than 85% of radius
        {
            return true;
        } else
        {
            return false;
        }
    }

    @Override
    public boolean isFall(int border)
    {
        return (this.getCenterY() > border);
    }

    @Override
    public void fall()
    {
        this.setCenterY(this.getCenterY() + this.getVelocity());
        this.setCenterX(this.getCenterX() + this.getHVelocity());
        this.setVelocity(this.getVelocity() + GRAVITY);
    }

    @Override
    public abstract void slice(double sliceAngle);

    public ArrayList<SlicedFruit> getSlicedFruits()
    {
        return this.slicedFruits;
    }

    public ArrayList<Particles> splash()
    {
        ArrayList<Particles> particles = new ArrayList<>();
        for (int j = 0; j < (int) this.getRadius() / 3.5; j++)//4
        {
            particles.add(new Particles(this));
        }
        return particles;
    }

    //
    //Getter and Setter
    //
    private void setSize(double width, double height)
    {
        this.setMinHeight(height);
        this.setMaxHeight(height);
        this.setMinWidth(width);
        this.setMaxWidth(width);
    }

    public void setCenterX(double centerX)
    {
        this.setLayoutX(centerX - getRadius());
    }

    public void setCenterY(double centerY)
    {
        this.setLayoutY(centerY - getRadius());
    }

    public double getCenterX()
    {
        return this.getLayoutX() + radius;
    }

    public double getCenterY()
    {
        return this.getLayoutY() + radius;
    }

    public double getRadius()
    {
        return this.radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public double getHVelocity()
    {
        return hVelocity;
    }

    public void setHVelocity(double hVelocity)
    {
        this.hVelocity = hVelocity;
    }

    public void setVelocity(double velocity)
    {
        this.velocity = velocity;
    }

    public double getVelocity()
    {
        return this.velocity;
    }

    public int getScoreValue()
    {
        return this.scoreValue;
    }

    public void setScoreValue(int scoreValue)
    {
        this.scoreValue = scoreValue;
    }

    public Color getJuice()
    {
        return juice;
    }

    public void setJuice(Color juice)
    {
        this.juice = juice;
    }

    public Color getFill()
    {
        return fill;
    }

    public void setFill(Color fill)
    {
        this.fill = fill;
    }

    public Color getStroke()
    {
        return stroke;
    }

    public void setStroke(Color stroke)
    {
        this.stroke = stroke;
    }

    public double getStrokeWidth()
    {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth)
    {
        this.strokeWidth = strokeWidth;
    }
}