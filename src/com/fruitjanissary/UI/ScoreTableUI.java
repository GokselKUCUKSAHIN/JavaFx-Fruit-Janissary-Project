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

public class ScoreTableUI extends Pane
{

    ArrayList<ScoreRow> rows = new ArrayList<>();
    BorderPane stage;
    Player player;

    public ScoreTableUI(BorderPane stage, Player player)
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
        /*Rectangle background = new Rectangle(0,0,710,710);
        background.setFill(Color.rgb(51,51,51));*/
        //
        ScoreRow.rankCount = 0;
        //
        Label banner = new Label("HALL OF FAME");
        banner.setFont(Font.loadFont("file:sprites/arcade.otf", 45));
        banner.setTextFill(Color.rgb(255, 0, 255));
        HBox labelBox = new HBox();
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setPrefSize(710, 100);
        labelBox.getChildren().add(banner);
        labelBox.setLayoutX(0);
        labelBox.setLayoutY(60);
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
        Button myScores = new Button("My Scores");
        myScores.setFont(Font.loadFont("file:sprites/Evogria.otf", 18));
        myScores.setMaxSize(120, 40);
        myScores.setMinSize(120, 40);
        myScores.setLayoutX(380);
        myScores.setLayoutY(635);
        myScores.setFocusTraversable(false);
        //
        getChildren().addAll(backGround, labelBox, box, backButton, myScores);
        //
        backButton.setOnMouseClicked(e -> {
            stage.getChildren().remove(this);
            stage.setCenter(new MainMenuUI(stage, player));
        });
        //
        myScores.setOnMouseClicked(e->{
            stage.getChildren().remove(this);
            stage.setCenter(new MyScoresUI(stage, player));
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
            ResultSet rs = DBO.getTop10Scores();
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
