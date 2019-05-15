package com.fruitjanissary;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class ScoreRow extends Pane
{

    public static int rankCount = 0;
    private int rank = 0;
    private int score = 0;
    private int duration = 0;
    // private String date = "2018-05-11";
    private String date = "----------";
    private String nickname = "-------------";

    public ScoreRow()
    {
        rankCount++;
        this.rank = rankCount;
        //setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY))); //background
        draw();
    }

    public ScoreRow(String nickname, int score, String date, int duration)
    {
        this();
        setRow(nickname, score, date, duration);
    }

    public void setRow(String nickname, int score, String date, int duration)
    {
        this.nickname = nickname;
        this.score = score;
        this.date = date;
        this.duration = duration;
        draw();
    }

    private void draw()
    {
        getChildren().clear();
        Rectangle field = new Rectangle(0, 0, 500, 40);
        if (rank % 2 == 0)
        {
            field.setFill(Color.valueOf("D0B3E3"));
            field.setStroke(Color.valueOf("D0B3E3").darker());
        } else
        {
            field.setFill(Color.valueOf("7171C4"));
            field.setStroke(Color.valueOf("7171C4").darker());
        }
        field.setStrokeWidth(3);
        field.setArcHeight(40);
        field.setArcWidth(40);
        //
        this.setMinSize(field.getWidth(), field.getHeight());
        this.setMaxSize(field.getWidth(), field.getHeight());
        //
        Label ranklbl = new Label();
        ranklbl.setText(String.format("#%2d", rank));
        ranklbl.setPrefSize(50, 30);
        ranklbl.setFont(Font.loadFont("file:sprites/Evogria.otf", 25));
        ranklbl.setTextFill(Color.WHITE);
        ranklbl.setLayoutY(3);
        ranklbl.setLayoutX(10);
        //
        Label nicklbl = new Label();
        nicklbl.setText(String.format("%-20s", nickname));
        nicklbl.setPrefSize(125, 30);
        nicklbl.setMaxSize(125, 30);
        nicklbl.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        nicklbl.setTextFill(Color.WHITE);
        nicklbl.setLayoutY(3);
        nicklbl.setLayoutX(65);
        nicklbl.setTextAlignment(TextAlignment.LEFT);
        //
        Label scorelbl = new Label();
        scorelbl.setText(String.format("%08d", score));
        scorelbl.setPrefSize(100, 30);
        scorelbl.setMinSize(100, 30);
        scorelbl.setFont(Font.loadFont("file:sprites/Evogria.otf", 15));
        scorelbl.setTextFill(Color.WHITE);
        scorelbl.setLayoutY(3);
        scorelbl.setLayoutX(200);
        scorelbl.setTextAlignment(TextAlignment.LEFT);
        //
        Label datelbl = new Label();
        datelbl.setText(String.format("%s", date));
        datelbl.setPrefSize(80, 30);
        datelbl.setMaxSize(80, 30);
        datelbl.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        datelbl.setTextFill(Color.WHITE);
        datelbl.setLayoutY(3);
        datelbl.setLayoutX(300);
        datelbl.setTextAlignment(TextAlignment.LEFT);
        //
        Label durationlbl = new Label();
        durationlbl.setText(Utils.getHMS(duration));
        durationlbl.setPrefSize(100, 30);
        durationlbl.setMaxSize(100, 30);
        durationlbl.setMinSize(100, 30);
        durationlbl.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        durationlbl.setTextFill(Color.WHITE);
        durationlbl.setLayoutY(3);
        durationlbl.setLayoutX(395);
        durationlbl.setTextAlignment(TextAlignment.LEFT);
        //
        getChildren().addAll(field, ranklbl, nicklbl, scorelbl, datelbl, durationlbl);
    }
}
