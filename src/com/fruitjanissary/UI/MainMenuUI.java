package com.fruitjanissary.UI;

import com.fruitjanissary.Game;
import com.fruitjanissary.Player;
import com.fruitjanissary.Window;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class MainMenuUI extends Pane
{

    Player player;

    public MainMenuUI(BorderPane stage, Player player)
    {
        //InputStream isEvogria = getClass().getResourceAsStream("/Evogria.otf");
        //InputStream isLsun = getClass().getResourceAsStream("/lsun.otf");
        this.player = player;
        //
        ImageView backGround = new ImageView(Window.mainMenuBG);
        backGround.fitWidthProperty().bind(widthProperty());
        backGround.fitHeightProperty().bind(heightProperty());
        //
        Label fruitJanissary = new Label("FRUIT JANISSARY");
        fruitJanissary.setFont(Window.lsunFont70);
        HBox fruitBox = new HBox();
        fruitBox.setPrefSize(710, 100);
        fruitBox.setAlignment(Pos.CENTER);
        fruitBox.setSpacing(20);
        fruitBox.setPadding(new Insets(0, 100, 0, 100));
        fruitBox.setLayoutX(0);
        fruitBox.setLayoutY(140);
        fruitBox.getChildren().add(fruitJanissary);
        //
        Button buttonPlay = new Button("PLAY");

        buttonPlay.setFont(Window.evogriaFont30);
        buttonPlay.setFocusTraversable(false);
        buttonPlay.setPrefSize(150, 40);
        buttonPlay.setLayoutX(280);
        buttonPlay.setLayoutY(260);
        //
        Button scoreBoard = new Button("Score Board");

        scoreBoard.setFont(Window.evogriaFont30);
        scoreBoard.setFocusTraversable(false);
        scoreBoard.setPrefSize(250, 40);
        scoreBoard.setLayoutX(230);
        scoreBoard.setLayoutY(350);
        //
        Label userInfo = new Label();
        userInfo.setText("Logged as " + player.getNickname());

        userInfo.setFont(Window.evogriaFont25);
        userInfo.setTextFill(Color.ORANGE);
        HBox userInfoBox = new HBox();
        userInfoBox.setPrefSize(710, 100);
        userInfoBox.setAlignment(Pos.CENTER);
        userInfoBox.setSpacing(20);
        userInfoBox.setPadding(new Insets(0, 100, 0, 100));
        userInfoBox.setLayoutX(0);
        userInfoBox.setLayoutY(420);
        userInfoBox.getChildren().add(userInfo);
        //
        Button logOut = new Button("Log Out");

        logOut.setFont(Window.evogriaFont25);//"file:src/sprites/Evogria.otf"
        logOut.setFocusTraversable(false);
        logOut.setPrefSize(150, 50);
        logOut.setLayoutX(280);
        logOut.setLayoutY(500);
        //
        getChildren().addAll(backGround, fruitBox, buttonPlay, scoreBoard, userInfoBox, logOut);
        //
        buttonPlay.setOnMouseClicked(e -> {
            stage.getChildren().remove(this);
            stage.setCenter(new Game(stage, player));
        });
        //
        logOut.setOnMouseClicked(e -> {
            stage.getChildren().remove(this);
            stage.setCenter(new LogInUI(stage));
        });
        scoreBoard.setOnMouseClicked(e -> {
            stage.getChildren().remove(this);
            stage.setCenter(new ScoreTableUI(stage, player));
        });
    }
}
