package com.fruitjanissary.Explosions;

import com.fruitjanissary.Explosion;
import com.fruitjanissary.VFX.ShockWave;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class RegularBombExplosion extends Explosion
{

    private int eLimit = 19;
    private int eCount = 0;
    private boolean isDone = false;
    //
    FadeTransition fd = new FadeTransition(Duration.millis(1500), this);
    //
    ArrayList<ShockWave> waves = new ArrayList<>();
    //
    EventHandler<ActionEvent> handler = e -> {
        if (eCount != eLimit)
        {
            ShockWave shockWave = new ShockWave(x, y);
            waves.add(shockWave);
            getChildren().add(shockWave);
            eCount++;
        } else
        {
            for (int i = 0; i < waves.size(); i++)
            {
                ShockWave sw = waves.get(i);
                if (sw.isDone())
                {
                    waves.remove(sw);
                    getChildren().remove(sw);
                }
            }
            if (waves.size() == 0)
            {
                fd.play();
            }
        }
    };
    Timeline boom = new Timeline(new KeyFrame(Duration.millis(50), handler));

    //
    public RegularBombExplosion(double x, double y)
    {
        super(x, y);
        //
        draw();
    }

    @Override
    protected void draw()
    {
        boom.setCycleCount(Timeline.INDEFINITE);
        boom.setRate(1);
        boom.setAutoReverse(false);
        this.setLayoutX(0);
        this.setLayoutY(0);
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        fd.setCycleCount(1);
        fd.setToValue(0);
        fd.setFromValue(1);
        fd.setAutoReverse(false);
        boom.play();
        fd.setOnFinished(e -> {
            this.isDone = true;
        });
    }

    @Override
    public boolean isDone()
    {
        return this.isDone;
    }
}