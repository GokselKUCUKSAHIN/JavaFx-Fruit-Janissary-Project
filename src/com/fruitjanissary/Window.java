package com.fruitjanissary;

import com.fruitjanissary.UI.LogInUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Window extends Application
{

    public static Image mainMenuBG;
    public static Image neon;
    public static Image clearSky;
    public static Image explosionImg;
    public static Image fallOutImg;
    //
    public static Font lsunFont70;
    public static Font lsunFont45;
    public static Font lsunFont38;
    public static Font evogriaFont35;
    public static Font evogriaFont30;
    public static Font evogriaFont25;
    public static Font evogriaFont20;
    public static Font evogriaFont18;
    public static Font evogriaFont15;
    public static Font arcadeFont45;
    public static Font arcadeFont35;
    public static Font overseerFont50;


    LogInUI logIn;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            //try load image
            mainMenuBG = new Image(String.valueOf(this.getClass().getResource("/IMG/mainMenuBG.png")));
            neon = new Image(String.valueOf(this.getClass().getResource("/IMG/neon.png")));
            clearSky = new Image(String.valueOf(this.getClass().getResource("/IMG/clear_sky.png")));
            explosionImg = new Image(String.valueOf(this.getClass().getResource("/IMG/explosion.jpg")));
            fallOutImg = new Image(String.valueOf(this.getClass().getResource("/IMG/falloutboy.png")));
            //
            lsunFont70 = Font.loadFont(getClass().getResourceAsStream("/Font/lsun.otf"), 70);
            lsunFont45 = Font.loadFont(getClass().getResourceAsStream("/Font/lsun.otf"), 45);
            lsunFont38 = Font.loadFont(getClass().getResourceAsStream("/Font/lsun.otf"), 38);
            //
            evogriaFont35 = Font.loadFont(getClass().getResourceAsStream("/Font/Evogria.otf"), 35);
            evogriaFont30 = Font.loadFont(getClass().getResourceAsStream("/Font/Evogria.otf"), 30);
            evogriaFont25 = Font.loadFont(getClass().getResourceAsStream("/Font/Evogria.otf"), 25);
            evogriaFont20 = Font.loadFont(getClass().getResourceAsStream("/Font/Evogria.otf"), 20);
            evogriaFont18 = Font.loadFont(getClass().getResourceAsStream("/Font/Evogria.otf"), 18);
            evogriaFont15 = Font.loadFont(getClass().getResourceAsStream("/Font/Evogria.otf"), 15);
            //
            arcadeFont45 = Font.loadFont(getClass().getResourceAsStream("/Font/arcade.otf"), 45);
            arcadeFont35 = Font.loadFont(getClass().getResourceAsStream("/Font/arcade.otf"), 35);
            //
            overseerFont50 = Font.loadFont(getClass().getResourceAsStream("/Font/Overseer.otf"), 50);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        try
        {
            DBO.checkDB(); //init db when it's starts (for quick response)
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        BorderPane root = new BorderPane(); //root node
        //
        logIn = new LogInUI(root);
        root.setCenter(logIn);
        //root.setCenter(new ScoreTableUI());
        //
        primaryStage.setTitle("Fruit Janissary Jellybeanci©");//başlık
        Scene scene = new Scene(root, 700, 700); //700x700 pixel fixed single
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);//sabit penecere boyutu
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception
    {
        super.stop();
        DBO.closeConnection(); //close connection while closing.
    }
}
