package com.fruitjanissary;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class TopBar extends Pane
{

    private int life = 0;
    private int score = 0;

    ArrayList<Rectangle> cross = new ArrayList<>();
    Label scoreLabel = new Label();

    public TopBar()
    {
        draw();
    }

    private void draw()
    {
        //setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        //
        setLayoutX(0);
        setLayoutY(0);
        //top-left
        setMinHeight(75);
        setMinWidth(710);
        setMaxHeight(75);
        setMaxWidth(710);
        //
        setCross();
        //
        drawLife();
        //
        scoreLabel.setText("00000000");
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setLayoutY((getMaxHeight() / 2 - 20));
        scoreLabel.setLayoutX(25);
        scoreLabel.setFont(Font.loadFont("file:sprites/Evogria.otf", 30));
        //scoreLabel.setFont(Font.font("sprites:Evogria.otf",30));
        //
        getChildren().add(scoreLabel);
    }

    public void setLife(int life)
    {
        this.life = life;
        drawLife();
    }

    public void setScore(int score)
    {
        this.score = score;
        drawScore();
    }

    private void setCross()
    {
        Rectangle rc1 = new Rectangle(650, (getMaxHeight() / 2 - 5), 35, 10);
        rc1.setFill(Color.DARKRED);
        rc1.setRotate(45);
        cross.add(rc1);
        Rectangle rl1 = new Rectangle(650, (getMaxHeight() / 2 - 5), 35, 10);
        rl1.setFill(Color.DARKRED);
        rl1.setRotate(-45);
        cross.add(rl1);
        //
        Rectangle rc2 = new Rectangle(605, (getMaxHeight() / 2 - 5), 35, 10);
        rc2.setFill(Color.DARKRED);
        rc2.setRotate(45);
        cross.add(rc2);
        Rectangle rl2 = new Rectangle(605, (getMaxHeight() / 2 - 5), 35, 10);
        rl2.setFill(Color.DARKRED);
        rl2.setRotate(-45);
        cross.add(rl2);
        //
        Rectangle rc3 = new Rectangle(560, (getMaxHeight() / 2 - 5), 35, 10);
        rc3.setFill(Color.DARKRED);
        rc3.setRotate(45);
        cross.add(rc3);
        Rectangle rl3 = new Rectangle(560, (getMaxHeight() / 2 - 5), 35, 10);
        rl3.setFill(Color.DARKRED);
        rl3.setRotate(-45);
        cross.add(rl3);
        //
    }

    public void drawLife()
    {
        getChildren().removeAll(cross); //remove old cross
        //cross.clear(); //clear old cross list.
        switch (life)
        {
            case 0:
            {
                //none
                for (Rectangle rectangle : cross)
                {
                    rectangle.setFill(Color.DARKRED);
                }
                break;
            }
            case 1:
            {
                //only first one
                cross.get(0).setFill(Color.DARKRED);
                cross.get(1).setFill(Color.DARKRED);
                cross.get(2).setFill(Color.DARKRED);
                cross.get(3).setFill(Color.DARKRED);
                //
                cross.get(4).setFill(Color.RED);
                cross.get(5).setFill(Color.RED);
                break;
            }
            case 2:
            {
                //first and second
                cross.get(0).setFill(Color.DARKRED);
                cross.get(1).setFill(Color.DARKRED);
                //
                cross.get(2).setFill(Color.RED);
                cross.get(3).setFill(Color.RED);
                cross.get(4).setFill(Color.RED);
                cross.get(5).setFill(Color.RED);
                break;
            }
            case 3:
            {
                //all cross are red
                for (Rectangle rectangle : cross)
                {
                    rectangle.setFill(Color.RED);
                }
            }
            default:
            {
                break;
            }
        }
        getChildren().addAll(cross);
    }

    public void drawScore()
    {
        String output = String.format("%08d", score);
        this.scoreLabel.setText(output);
    }
}
