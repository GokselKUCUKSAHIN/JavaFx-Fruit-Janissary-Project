package com.fruitjanissary.UI;

import com.fruitjanissary.DBO;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class SignUpUI extends Pane
{

    private BorderPane stage;

    public SignUpUI(BorderPane stage)
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
        Rectangle field = new Rectangle(355 - (420 / 2), 100, 420, 530);
        field.setFill(Color.LIGHTGREEN.brighter());
        field.setStroke(Color.GOLD);
        field.setStrokeWidth(5);
        field.setArcWidth(88);
        field.setArcHeight(88);
        //
        Label joinUs = new Label("Join US");
        joinUs.setFont(Font.loadFont("file:sprites/lsun.otf", 45));
        joinUs.setTextFill(Color.BLACK);
        joinUs.setLayoutX(field.getX() + 160);
        joinUs.setLayoutY(field.getY() + 20);
        //
        Label signUp = new Label("SignUp");
        signUp.setFont(Font.font("Century Gothic", 24));
        signUp.setTextFill(Color.RED.brighter());
        signUp.setLayoutX(field.getX() + 35);
        signUp.setLayoutY(field.getY() + 80);
        //
        HBox userbox = new HBox();
        Label userLabel = new Label("Nickname: ");
        userLabel.setMinSize(100, 20);
        userLabel.setFont(Font.font("Arial", 20));
        TextField userTf = new TextField();
        userTf.setFont(Font.font("Arial", 14));
        userTf.setMinSize(120, 20);
        userbox.setSpacing(20);
        userbox.setAlignment(Pos.CENTER_LEFT);
        userbox.getChildren().addAll(userLabel, userTf);
        userbox.setLayoutX(field.getX() + 36);
        userbox.setLayoutY(field.getY() + 145);
        //
        HBox passbox = new HBox();
        Label passLabel = new Label("Password: ");
        passLabel.setFont(Font.font("Arial", 20));
        passLabel.setMinSize(100, 20);
        PasswordField passTf = new PasswordField();
        passTf.setFont(Font.font("Arial", 14));
        passTf.setMinSize(120, 20);
        passbox.setSpacing(20);
        passbox.setAlignment(Pos.CENTER_LEFT);
        passbox.getChildren().addAll(passLabel, passTf);
        passbox.setLayoutX(field.getX() + 36);
        passbox.setLayoutY(field.getY() + 185);
        //
        HBox passbox2 = new HBox();
        Label passLabel2 = new Label("Password: ");
        passLabel2.setFont(Font.font("Arial", 20));
        passLabel2.setMinSize(100, 20);
        PasswordField passTf2 = new PasswordField();
        passTf2.setFont(Font.font("Arial", 14));
        passTf2.setMinSize(120, 20);
        passbox2.setSpacing(20);
        passbox2.setAlignment(Pos.CENTER_LEFT);
        passbox2.getChildren().addAll(passLabel2, passTf2);
        passbox2.setLayoutX(field.getX() + 36);
        passbox2.setLayoutY(field.getY() + 225);
        //
        HBox emailbox = new HBox();
        Label emailLabel = new Label("Email: ");
        emailLabel.setMinSize(100, 20);
        emailLabel.setFont(Font.font("Arial", 20));
        TextField emailTf = new TextField();
        emailTf.setFont(Font.font("Arial", 14));
        emailTf.setMinSize(120, 20);
        emailbox.setSpacing(20);
        emailbox.setAlignment(Pos.CENTER_LEFT);
        emailbox.getChildren().addAll(emailLabel, emailTf);
        emailbox.setLayoutX(field.getX() + 36);
        emailbox.setLayoutY(field.getY() + 265);
        //
        HBox namebox = new HBox();
        Label nameLabel = new Label("Name: ");
        nameLabel.setMinSize(100, 20);
        nameLabel.setFont(Font.font("Arial", 20));
        TextField nameTf = new TextField();
        nameTf.setFont(Font.font("Arial", 14));
        nameTf.setMinSize(120, 20);
        namebox.setSpacing(20);
        namebox.setAlignment(Pos.CENTER_LEFT);
        namebox.getChildren().addAll(nameLabel, nameTf);
        namebox.setLayoutX(field.getX() + 36);
        namebox.setLayoutY(field.getY() + 305);
        //
        HBox surnamebox = new HBox();
        Label surnameLabel = new Label("Surname: ");
        surnameLabel.setMinSize(100, 20);
        surnameLabel.setFont(Font.font("Arial", 20));
        TextField surnameTf = new TextField();
        surnameTf.setFont(Font.font("Arial", 14));
        surnameTf.setMinSize(120, 20);
        surnamebox.setSpacing(20);
        surnamebox.setAlignment(Pos.CENTER_LEFT);
        surnamebox.getChildren().addAll(surnameLabel, surnameTf);
        surnamebox.setLayoutX(field.getX() + 36);
        surnamebox.setLayoutY(field.getY() + 345);
        //
        HBox infoBox = new HBox();
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setLayoutX(field.getX());
        infoBox.setLayoutY(field.getY() + 380);
        infoBox.setMinSize(field.getWidth(), 30);
        Label info = new Label("Fill all Spaces and hit Sign Up");
        info.setFont(Font.font("Century Gothic", 18));
        info.setTextFill(Color.RED);
        infoBox.getChildren().add(info);
        //
        Button buttonSignUp = new Button("Sing Up");
        buttonSignUp.setFont(Font.font("Century Gothic", 20));
        buttonSignUp.setMinSize(80, 20);
        buttonSignUp.setLayoutX(315);
        buttonSignUp.setLayoutY(527);
        //
        Hyperlink link = new Hyperlink("Have Already an Account? Log In Here!");
        link.setUnderline(false);
        link.setVisited(false);
        link.setTextFill(Color.ORANGERED);
        link.setFont(Font.font("Arial", 18));
        link.setLayoutX(field.getX() + 45);
        link.setLayoutY(580);
        //
        //
        getChildren().addAll(backGround, field, joinUs, signUp, userbox, passbox, passbox2, emailbox, namebox, surnamebox, infoBox, buttonSignUp, link);
        //
        buttonSignUp.setOnMouseClicked(e -> {
            String nick = userTf.getText();
            String pass1 = passTf.getText();
            String pass2 = passTf2.getText();
            String mail = emailTf.getText();
            String name = nameTf.getText();
            String surname = surnameTf.getText();
            info.setText(DBO.signUp(nick, pass1, pass2, mail, name, surname));
        });
        //
        link.setOnMouseClicked(e -> {
            stage.getChildren().remove(this);
            stage.setCenter(new LogInUI(stage));
        });
    }
}
