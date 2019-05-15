package com.fruitjanissary;

import com.fruitjanissary.UI.LogInUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Window extends Application
{

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
