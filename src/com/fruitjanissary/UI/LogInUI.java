package com.fruitjanissary.UI;

import com.fruitjanissary.DBO;
import com.fruitjanissary.Player;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.sql.SQLException;

public class LogInUI extends Pane
{

    private BorderPane stage;

    public LogInUI(BorderPane stage)
    {
        this.stage = stage;
        draw();
    }

    private void draw()
    {
        //
        ImageView backGround = new ImageView("file:sprites/mainMenuBG.png");
        backGround.fitWidthProperty().bind(widthProperty());
        backGround.fitHeightProperty().bind(heightProperty());
        //710 * 710
        Rectangle field = new Rectangle(355 - (420 / 2), 120, 420, 480);
        field.setFill(Color.LIGHTGREEN.brighter());
        field.setStroke(Color.GOLD);
        field.setStrokeWidth(5);
        field.setArcWidth(88);
        field.setArcHeight(88);
        //
        Label fruitJanissary = new Label("Welcome to Fruit Janissary");
        fruitJanissary.setFont(Font.loadFont("file:sprites/lsun.otf", 38));
        fruitJanissary.setTextFill(Color.BLACK);
        fruitJanissary.setLayoutX(field.getX() + 30);
        fruitJanissary.setLayoutY(field.getY() + 35);
        //
        Label logIn = new Label("LogIn");
        logIn.setFont(Font.font("Century Gothic", 28));
        logIn.setTextFill(Color.RED.brighter());
        logIn.setLayoutX(field.getX() + 35);
        logIn.setLayoutY(field.getY() + 125);
        //
        HBox userbox = new HBox();
        Label userLabel = new Label("Nickname: ");
        userLabel.setMinSize(100, 20);
        userLabel.setFont(Font.font("Arial", 20));
        TextField userTf = new TextField();
        userTf.setFont(Font.font("Arial", 16));
        userTf.setMinSize(120, 20);
        userbox.setSpacing(20);
        userbox.setAlignment(Pos.CENTER_LEFT);
        userbox.getChildren().addAll(userLabel, userTf);
        userbox.setLayoutX(field.getX() + 40);
        userbox.setLayoutY(field.getY() + 200);
        //
        HBox passbox = new HBox();
        Label passLabel = new Label("Password: ");
        passLabel.setFont(Font.font("Arial", 20));
        passLabel.setMinSize(100, 20);
        PasswordField passTf = new PasswordField();
        passTf.setFont(Font.font("Arial", 16));
        passTf.setMinSize(120, 20);
        passbox.setSpacing(20);
        passbox.setAlignment(Pos.CENTER_LEFT);
        passbox.getChildren().addAll(passLabel, passTf);
        passbox.setLayoutX(field.getX() + 40);
        passbox.setLayoutY(field.getY() + 250);
        //
        HBox infoBox = new HBox();
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setLayoutX(field.getX());
        infoBox.setLayoutY(field.getY() + 300);
        infoBox.setMinSize(field.getWidth(), 30);
        Label info = new Label("Enter Nickname and Password");
        info.setFont(Font.font("Century Gothic", 18));
        info.setTextFill(Color.RED);
        infoBox.getChildren().add(info);
        //
        Button buttonLogIn = new Button("Log In");
        buttonLogIn.setFont(Font.font("Century Gothic", 20));
        buttonLogIn.setMinSize(80, 20);
        buttonLogIn.setLayoutX(315);
        buttonLogIn.setLayoutY(470);
        //
        Hyperlink link = new Hyperlink("Haven't any Accounts? Sign Up Now!");
        link.setUnderline(false);
        link.setVisited(false);
        link.setTextFill(Color.ORANGERED);
        link.setFont(Font.font("Arial", 18));
        link.setLayoutX(field.getX() + 60);
        link.setLayoutY(540);
        //
        //
        getChildren().addAll(backGround, field, fruitJanissary, logIn, userbox, passbox, infoBox, buttonLogIn, link);
        //
        buttonLogIn.setOnMouseClicked(e -> {
            try
            {
                int pid = DBO.logIn(userTf.getText(), passTf.getText());
                if (pid != -1)
                {
                    //ok
                    Player player = DBO.getPlayer(pid);
                    info.setText("Welcome " + player.getName() + " " + player.getSurname());
                    stage.getChildren().remove(this);
                    stage.setCenter(new MainMenuUI(stage,player));
                } else
                {
                    info.setText("Check Nickname and Password.");
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        });
        //
        link.setOnMouseClicked(e -> {
            stage.getChildren().remove(this);
            stage.setCenter(new SignUpUI(stage));
        });
    }
}
