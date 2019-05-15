package com.fruitjanissary;

public abstract class PowerUp extends Fruit
{
    public static final int NUMBER_OF_POWERUPS = 2;
    private int id;

    public PowerUp(int centerX, int centerY, double radius)
    {
        super(centerX, centerY, radius);
    }

    public int getID()
    {
        return id;
    }

    public void setID(int id)
    {
        this.id = id;
    }
}
