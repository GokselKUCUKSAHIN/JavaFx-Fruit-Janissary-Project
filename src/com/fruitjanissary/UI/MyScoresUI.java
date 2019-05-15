package com.fruitjanissary.UI;

import com.fruitjanissary.DBO;
import com.fruitjanissary.Player;
import com.fruitjanissary.ScoreRow;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyScoresUI extends Pane
{

    ArrayList<ScoreRow> rows = new ArrayList<>();
    BorderPane stage;
    Player player;

    public MyScoresUI(BorderPane stage, Player player)
    {
        initTable();
        //
        this.stage = stage;
        this.player = player;
        //
        ImageView backGround = new ImageView("file:sprites/neon.png");
        backGround.fitWidthProperty().bind(widthProperty());
        backGround.fitHeightProperty().bind(heightProperty());
        //
        ScoreRow.rankCount = 0;
        //
        Label banner = new Label("MY SCORES");
        banner.setFont(Font.loadFont("file:sprites/arcade.otf", 35));
        banner.setTextFill(Color.rgb(255, 0, 255));
        HBox labelBox = new HBox();
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setPrefSize(710, 100);
        labelBox.getChildren().add(banner);
        labelBox.setLayoutX(0);
        labelBox.setLayoutY(-20);
        //
        Label playTime = new Label();
        String str = "Total Play Time: ";
        try
        {
            str += DBO.getPlayersPlayTime(player.getPlayerId());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        playTime.setText(str);
        playTime.setFont(Font.loadFont("file:sprites/Evogria.otf", 25));
        playTime.setTextFill(Color.BLUEVIOLET.brighter());
        HBox playBox = new HBox();
        playBox.setAlignment(Pos.CENTER);
        playBox.setPrefSize(710, 100);
        playBox.getChildren().add(playTime);
        playBox.setLayoutX(0);
        playBox.setLayoutY(30);
        //
        Label totalScore = new Label();
        String tmpstr = "Total Score: ";
        try
        {
            tmpstr += DBO.getPlayersTotalScore(player.getPlayerId());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        totalScore.setText(tmpstr);
        totalScore.setFont(Font.loadFont("file:sprites/Evogria.otf", 35));
        totalScore.setTextFill(Color.BLUEVIOLET.brighter());
        HBox totalScoreBox = new HBox();
        totalScoreBox.setAlignment(Pos.CENTER);
        totalScoreBox.setPrefSize(710, 100);
        totalScoreBox.getChildren().add(totalScore);
        totalScoreBox.setLayoutX(0);
        totalScoreBox.setLayoutY(80);
        //
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(3);
        box.setLayoutX(105);
        box.setLayoutY(180);
        box.getChildren().addAll(rows);
        fillTable();
        //
        Button backButton = new Button("BACK");
        backButton.setFont(Font.loadFont("file:sprites/Evogria.otf", 18));
        backButton.setMaxSize(90, 40);
        backButton.setMinSize(90, 40);
        backButton.setLayoutX(210);
        backButton.setLayoutY(635);
        backButton.setFocusTraversable(false);
        //
        Button hallOfFame = new Button("Hall Of Fame");
        hallOfFame.setFont(Font.loadFont("file:sprites/Evogria.otf", 18));
        hallOfFame.setMaxSize(150, 40);
        hallOfFame.setMinSize(150, 40);
        hallOfFame.setLayoutX(380);
        hallOfFame.setLayoutY(635);
        hallOfFame.setFocusTraversable(false);
        //
        getChildren().addAll(backGround, labelBox, playBox,totalScoreBox, box, backButton, hallOfFame);
        //
        backButton.setOnMouseClicked(e -> {
            stage.getChildren().remove(this);
            stage.setCenter(new MainMenuUI(stage, player));
        });
        //
        hallOfFame.setOnMouseClicked(e -> {
            stage.getChildren().remove(this);
            stage.setCenter(new ScoreTableUI(stage, player));
        });
    }

    private void initTable()
    {
        rows.add(new ScoreRow());
        rows.add(new ScoreRow());
        rows.add(new ScoreRow());
        rows.add(new ScoreRow());
        rows.add(new ScoreRow());
        rows.add(new ScoreRow());
        rows.add(new ScoreRow());
        rows.add(new ScoreRow());
        rows.add(new ScoreRow());
        rows.add(new ScoreRow());
    }

    private void fillTable()
    {
        try
        {
            int cursor = 0;
            ResultSet rs = DBO.getPlayersTop10Scores(player.getPlayerId());
            while (rs.next())
            {
                rows.get(cursor).setRow(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
                cursor++;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
