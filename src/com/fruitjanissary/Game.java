package com.fruitjanissary;

import com.fruitjanissary.Explosions.RegularBombExplosion;
import com.fruitjanissary.UI.GameOverUI;
import com.fruitjanissary.UI.MainMenuUI;
import com.fruitjanissary.VFX.Blade;
import com.fruitjanissary.VFX.Particles;
import com.fruitjanissary.VFX.Trace;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.ArrayList;


public class Game extends Pane
{

    //
    //region Variables
    private BorderPane stage;
    private Player player;
    //
    private double mouseAngle = 0; //Mouse Angle variable
    private double pMouseX = -10; //prevMouseX
    private double pMouseY = -10; //prevMouseY
    private double mouseX = -10;
    private double mouseY = -10;
    //
    private int missCount = 0; //miss counter
    private int hitCount = 0; //hit counter
    //
    private int score = 0; //score variablea1   Q"1
    private int multiplier = 1; //default=1 on rainbow mode=2
    //
    private boolean isPaused = true;
    //
    private boolean isGameOver = false;
    //
    private Color rainbow = Color.RED;
    private int rColor = 0;
    private boolean isRainbow = false;
    //
    private float time = 0;
    //endregion
    //

    //
    //region Objects
    //Bladeold blade = new Bladeold(7,Color.rgb(51,51,51)); //darker version of the blade
    private Blade blade = new Blade(7, Color.rgb(255, 255, 255)); //lighter //it's better for blue background
    //
    private Explosion explosion = new RegularBombExplosion(0, 0); //default null explosion. no NullPointerExecption
    //
    private Cloud cloud = Utils.pickRandomCloud();
    //
    private Label timerLabel = new Label("00:00:0,0");
    //
    private Button pausePlay = new Button();
    //
    private Button rePlay = new Button("Replay?");
    //
    private TopBar panel = new TopBar();
    //endregion
    //

    //
    //CONSTRUCTOR!
    //
    public Game(BorderPane stage, Player player)
    {
        this.stage = stage;
        this.player = player;
        draw();
        play();//instant play
    }

    //
    //ARRAYLISTS
    //
    private ArrayList<Fruit> fruits = new ArrayList<>();
    private ArrayList<Bomb> bombs = new ArrayList<>();
    private ArrayList<PowerUp> powerUps = new ArrayList<>();
    private ArrayList<SlicedFruit> slicedFruits = new ArrayList<>();
    private ArrayList<Particles> particles = new ArrayList<>();
    private ArrayList<Trace> imAlreadyTracer = new ArrayList<>();

    //
    //region DEBUG LABELS
    //
    /*
    private Label label = new Label("null");
    private Label label2 = new Label("null");
    private Label label3 = new Label("null");
    private Label label4 = new Label("null");
    private Label label5 = new Label("null");
    */
    //endregion
    //

    //
    //region Animations and Events

    //
    //GRAVITY
    //
    private EventHandler<ActionEvent> handler = e -> {
        for (int i = 0; i < fruits.size(); i++)
        {
            Fruit obj = fruits.get(i);
            if (obj.isFall(1200))
            {
                fruits.remove(i);
                getChildren().remove(obj);
                //if drop before sliced
                missCount++;
                panel.setLife(missCount);
                if (missCount >= 3)//3
                {
                    //
                    //gameOver
                    //
                    if (!isGameOver)
                    {
                        gameOver(0);
                    }
                    isGameOver = true;
                }
                //label5.setText("Score: " + score + ", hit: " + hitCount + ", miss: " + missCount); //debug
            } else
            {
                obj.fall();
            }
        }
        for (int i = 0; i < slicedFruits.size(); i++)
        {
            SlicedFruit obj = slicedFruits.get(i);
            if (obj.isFall(1200)) //drop register limit
            {
                slicedFruits.remove(i);
                getChildren().remove(obj);
            } else
            {
                obj.fall();
            }
        }
        for (int i = 0; i < particles.size(); i++)
        {
            Particles obj = particles.get(i);
            if (obj.isFall(900)) //drop register limit
            {
                particles.remove(i);
                getChildren().remove(obj);
            } else
            {
                obj.fall();
            }
        }
        for (int i = 0; i < bombs.size(); i++)
        {
            Bomb obj = bombs.get(i);
            if (obj.isFall(1200)) //drop register limit
            {
                bombs.remove(i);
                getChildren().remove(obj);
            } else
            {
                obj.fall();
            }
        }
        for (int i = 0; i < powerUps.size(); i++)
        {
            PowerUp obj = powerUps.get(i);
            if (obj.isFall(1200))
            {
                powerUps.remove(i);
                getChildren().remove(obj);
            } else
            {
                obj.fall();
            }
        }
        if (explosion.isDone())
        {
            getChildren().remove(explosion);
        }

        //
        //DEBUG LABELS
        //
        //label3.setText("p: " + particles.size() + ", s: " + slicedFruits.size()); //debug
        //label4.setText("c: " + getChildren().size()); //debug
        //
    };
    private Timeline gravity = new Timeline(new KeyFrame(Duration.millis(200), handler));

    //
    //ANGLE CALCULATOR
    //
    private EventHandler<ActionEvent> alpha = e -> {
        mouseAngle = Utils.calculateAngle(pMouseX, pMouseY, mouseX, mouseY);
        //label2.setText("Angle = " + mouseAngle); //debug
    };
    private Timeline getAngle = new Timeline(new KeyFrame(Duration.millis(100), alpha)); //100 enough

    //
    //DISPENSER ( RANDOM Object THROWER )
    //
    private EventHandler<ActionEvent> dispens = e -> {
        if (!isGameOver)
        {
            int throwX = (int) Utils.getRandomNumber(451, 125);//random throw horizontal location [125 - 575].
            double hvel = Utils.getRandomNumber(13, 3) / 10; //random horizontal velocity [3-15].
            if (throwX > 350)
            {
                //0-350 is left
                //351-700 is right
                hvel *= -1;
                //I reverse the direction of the fruit
                //if the fruit is on the right side.
            }
            double random = Utils.getRandomNumber(100);
            if (2 < random && random < 14) //2-14
            {
                //Throw Random Bomb
                //10% chance
                //[4-13]
                //
                Bomb bomb = Utils.pickRandomBomb(throwX - 50, 850);
                bomb.setVelocity(-11);
                bomb.setHVelocity(hvel);
                bombs.add(bomb);
                getChildren().add(bomb);
            } else if (random <= 2)
            {
                //[0-2] //power ups
                PowerUp powerUp = Utils.pickRandomPowerUps(throwX, 900, 50);
                powerUp.setVelocity(-11); //vertical velocity
                powerUp.setHVelocity(hvel);
                powerUps.add(powerUp);
                getChildren().add(powerUp);

            } else
            {
                //Throw Random Fruit
                //[14-99]
                //
                Fruit fruit = Utils.pickRandomFruit(throwX, 900, 50);
                fruit.setVelocity(-11); //vertical velocity
                fruit.setHVelocity(hvel);
                fruits.add(fruit);
                getChildren().add(fruit);
            }
        }
    };
    private Timeline dispenser = new Timeline(new KeyFrame(Duration.millis(1000), dispens));

    //
    //TRACER
    //
    private EventHandler<ActionEvent> bladeEffect = e -> {
        for (int i = 0; i < imAlreadyTracer.size(); i++)
        {
            Trace trace = imAlreadyTracer.get(i);
            if (trace.isDone())
            {
                imAlreadyTracer.remove(trace);
                getChildren().remove(trace);
            }
        }
    };
    private Timeline tracer = new Timeline(new KeyFrame(Duration.millis(100), bladeEffect));

    //
    //CLOUD ANIMATION
    //
    private EventHandler<ActionEvent> floating = e -> {
        if (!isGameOver)
        {
            if (cloud.getBounce())
            {
                cloud.setLayoutX(cloud.getLayoutX() - 0.1);
            } else
            {
                cloud.setLayoutX(cloud.getLayoutX() + 0.1);
            }
            if (cloud.getLayoutX() > 500)
            {
                cloud.setBounce(true);
            } else if (cloud.getLayoutX() < 40)
            {
                cloud.setBounce(false);
            }
        }
    };
    private Timeline cloudMove = new Timeline(new KeyFrame(Duration.millis(10), floating)); //100 enough

    //
    //RAINBOW
    //
    private EventHandler<ActionEvent> rainbowEffect = e -> {
        //easy way to create rainbow effect
        //Hue Saturation Brightness :)
        rainbow = Color.hsb(rColor, 0.90, 1);
        rColor++;

        //chLabel.setTextFill(rainbow);
        if (isRainbow)
        {
            blade.setColor(rainbow);
            multiplier = 2;
        } else
        {
            blade.setColor(Color.WHITE);
            multiplier = 1;
        }
        //chLabel.setText(Integer.toString(getChildren().size()));
    };
    private Timeline rainbowDash = new Timeline(new KeyFrame(Duration.millis(100), rainbowEffect));

    //
    //TIMER
    //
    private Timeline timerFruit = new Timeline(new KeyFrame(Duration.millis(100), e -> timerEvent()));

    //
    //RainbowFruit PowerUpTIMER
    //
    private Timeline rainbowFruitTimer = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
        this.isRainbow = true;
        //System.out.println("hey!"); //debug
    }));

    //endregion
    //

    private void draw()
    {
        fruits.clear();
        slicedFruits.clear();
        bombs.clear();
        particles.clear();
        imAlreadyTracer.clear();
        //
        ImageView backGround = new ImageView("file:sprites/clear_sky.png");
        backGround.fitWidthProperty().bind(widthProperty());
        backGround.fitHeightProperty().bind(heightProperty());
        //
        cloud.setLocation(40, 40);
        //
        //Real Time Debuging Labels
        //
        /*
        label.setLayoutX(50);
        label.setLayoutY(70);
        label.setFont(Font.font(20));
        label.setTextFill(Color.WHITE);
        //
        label2.setLayoutX(50);
        label2.setLayoutY(120);
        label2.setFont(Font.font(20));
        label2.setTextFill(Color.WHITE);
        //
        label3.setLayoutX(50);
        label3.setLayoutY(170);
        label3.setFont(Font.font(20));
        label3.setTextFill(Color.WHITE);
        //
        label4.setLayoutX(50);
        label4.setLayoutY(220);
        label4.setFont(Font.font(20));
        label4.setTextFill(Color.rgb(255, 255, 255));
        //
        label5.setLayoutX(50);
        label5.setLayoutY(20);
        label5.setFont(Font.font(20));
        label5.setTextFill(Color.WHITE);
        */
        //

        //
        //region Test Objects
        /*Fruit fruit = new Fruit(300, 150, 50);
        fruits.add(fruit);
        Arc arc = new Arc(300, 300, 50, 50, 270, 180);
        Arc arc2 = new Arc(300, 300, 50, 50, 90, 180);
        arc.setType(ArcType.ROUND);
        arc.setStroke(Color.BLUE);
        arc.setFill(Color.GREEN);
        arc.setStrokeWidth(5);
        arc2.setType(ArcType.ROUND);
        arc2.setStroke(Color.BLUE);
        arc2.setFill(Color.GREEN);
        arc2.setStrokeWidth(5);
        Bomb bomb = new Bomb(350, 100, 45);
        Bomb nuke = new Nuke(75,100);
        */
        //endregion
        //

        //
        //TIMER Settings
        //
        timerLabel.setFont(Font.font("Century Gothic", FontWeight.NORMAL, FontPosture.REGULAR, 25));
        timerLabel.setLayoutX(590);
        timerLabel.setLayoutY(670);
        timerLabel.setTextFill(Color.valueOf("333333"));
        timerFruit.setCycleCount(Timeline.INDEFINITE);
        timerFruit.setRate(1); //1 to 1
        //
        //GRAVITY Settings
        //
        gravity.setCycleCount(Timeline.INDEFINITE);
        gravity.setRate(30);

        //
        //ANGLE CALCULATOR Settings
        //
        getAngle.setCycleCount(Timeline.INDEFINITE);
        getAngle.setRate(10); //1000 old //100 old2//10 //10 enough //10 fine

        //
        //DISPENSER ( RANDOM Object THROWER ) Settings
        //
        dispenser.setCycleCount(Timeline.INDEFINITE);
        dispenser.setRate(1.25); //Firing can be increased. //default 1.25

        //
        //TRACER Settings
        //
        tracer.setCycleCount(Timeline.INDEFINITE);
        tracer.setRate(10);

        //
        //CLOUD ANIMATION Settings
        //
        cloudMove.setCycleCount(Timeline.INDEFINITE);
        cloudMove.setRate(1); //1000 old //100 old2//10 //10 enough //1 fine

        //
        //Rainbow Fruit Settings
        //
        rainbowFruitTimer.setRate(1);
        rainbowFruitTimer.setCycleCount(10);
        rainbowFruitTimer.setAutoReverse(false);
        rainbowFruitTimer.setOnFinished(e -> this.isRainbow = false);

        //
        //Mouse SWIPE
        //
        setOnMouseDragged(e -> {
            if (!isGameOver)
            {
                //While Dragging
                pMouseX = mouseX;
                pMouseY = mouseY;
                mouseX = e.getX();
                mouseY = e.getY();
                //label.setText(mouseX + ", " + mouseY); //debug
                for (int i = 0; i < fruits.size(); i++)
                {
                    Fruit fruit = fruits.get(i);
                    //label.setText(fruit.isHit(mouseX, mouseY) + " " + fruit.getDistance(mouseX, mouseY)); //debug
                    if (fruit.isHit(mouseX, mouseY) && !isPaused)
                    {
                        //System.out.println("hit!"); //debug
                        //System.out.println("List size = " + slicedFruits.size()); //debug
                        getChildren().remove(fruit);
                        fruits.remove(i);
                        //
                        ArrayList<Particles> fruitJuices = fruit.splash(); //particles list.
                        fruit.slice(mouseAngle); //slice the Fruit.
                        ArrayList<SlicedFruit> slices = fruit.getSlicedFruits(); //add slices to slices list.
                        //
                        particles.addAll(fruitJuices);
                        slicedFruits.addAll(slices);
                        //
                        getChildren().addAll(fruitJuices); //first particles after slice
                        getChildren().addAll(slices);      //for psudo 3D effect
                        //
                        //if fruit sliced
                        hitCount++; //increase hit count
                        score += fruit.getScoreValue() * multiplier; //update score
                        panel.setScore(score);
                        //label5.setText("Score: " + score + ", hit: " + hitCount + ", miss: " + missCount); //debug
                    }
                }
                //
                for (int i = 0; i < bombs.size(); i++)
                {
                    Bomb bomb = bombs.get(i); //get i'th index of bombs list
                    if (bomb.isHit(mouseX, mouseY) && !isPaused)
                    {
                        getChildren().remove(bomb); //delete object from observable list
                        bombs.remove(bomb); //delete from bombs list
                        bomb.slice(mouseAngle); //slice the Bomb
                        //System.out.println("BOOM"); //debug
                        bomb.explode(); //explosion Effect
                        //
                        //gameOver
                        //
                        isGameOver = true; //update game status
                        bomb.slice(mouseAngle);
                        explosion = bomb.getExplosion();
                        getChildren().removeAll(bombs);
                        getChildren().addAll(explosion);
                        gameOver(bomb.getID() + 1);
                    }
                }
                //
                for (int i = 0; i < powerUps.size(); i++)
                {
                    PowerUp powerUp = powerUps.get(i);
                    //label.setText(fruit.isHit(mouseX, mouseY) + " " + fruit.getDistance(mouseX, mouseY)); //debug
                    if (powerUp.isHit(mouseX, mouseY) && isPaused == false)
                    {
                        //System.out.println("hit!"); //debug
                        //System.out.println("List size = " + slicedFruits.size()); //debug
                        getChildren().remove(powerUp);
                        powerUps.remove(i);
                        //
                        ArrayList<Particles> fruitJuices = powerUp.splash(); //particles list.
                        powerUp.slice(mouseAngle); //slice the Fruit.
                        ArrayList<SlicedFruit> slices = powerUp.getSlicedFruits(); //add slices to slices list.
                        //
                        particles.addAll(fruitJuices);
                        slicedFruits.addAll(slices);
                        //
                        getChildren().addAll(fruitJuices); //first particles after slice
                        getChildren().addAll(slices);      //for psudo 3D effect
                        //
                        //if powerUp sliced
                        hitCount++; //increase hit count
                        score += powerUp.getScoreValue() * multiplier; //update score
                        panel.setScore(score); //redraw score table
                        switch (powerUp.getID())
                        {
                            case 0:
                            {
                                //System.out.println("rainbow");
                                if (isRainbow)
                                {
                                    rainbowFruitTimer.stop();
                                    rainbowFruitTimer.play();
                                } else
                                {
                                    this.isRainbow = true;
                                    rainbowFruitTimer.play();
                                    //System.out.println("ok");
                                }
                                break;
                            }
                            case 1:
                            {
                                //System.out.println("mushroom");
                                if (this.missCount >= 1)
                                {
                                    missCount--;
                                    panel.setLife(missCount); //update life table
                                }
                                break;
                            }
                        }
                    }
                }
                //
                if (isPaused == false)
                {
                    Trace trace = blade.setGenerate(); //create new Trace
                    trace.setLocation(mouseX, mouseY); //location
                    imAlreadyTracer.add(trace); //append trace list
                    getChildren().add(trace); //append children list
                }
                //
            }
        });

        //
        //Mouse Release
        //
        setOnMouseReleased(e -> {
            //On Mouse Release
            //label.setText("DONE"); //debug
            //Idle mouse locations. 90 degree default on idle
            mouseX = -10;
            mouseY = -20;
            pMouseX = -10;
            pMouseY = -10;
        });

        /*
        //RAINBOW CHECK BOX Settings
        //
        //Just an Experimental Stuff REMOVE LATER!
        chLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        chLabel.setTextFill(rainbow);
        checkBoxRainbow.setFont(Font.font(15));
        rainbowBox.getChildren().addAll(chLabel, checkBoxRainbow);
        rainbowBox.setLayoutX(520);
        rainbowBox.setLayoutY(140);
        rainbowBox.setSpacing(10);*/

        //
        //RAINBOW Settings
        //
        rainbowDash.setCycleCount(Timeline.INDEFINITE);
        rainbowDash.setRate(100);

        //
        //PLAY PAUSE BUTTON
        //
        pausePlay.setText("PLAY");
        pausePlay.setFont(Font.font(20));
        pausePlay.setLayoutX(15);
        pausePlay.setLayoutY(650);
        pausePlay.setFocusTraversable(false);
        pausePlay.setMinSize(90, 40);
        pausePlay.setOnMouseClicked(e -> {
            if (isPaused)
            {
                play();
            } else
            {
                pause();
            }
        });
        //
        //REPLAY BUTTON
        //
        //remove debug labels before release //removed 05 05 2019
        getChildren().addAll(backGround, panel, timerLabel, cloud, pausePlay);//, rePlay);//, rainbowBox);//, label, label2, label3, label4, label5);
    }

    private void play()
    {
        //Play State
        pausePlay.setText("PAUSE");
        isPaused = false; //update pause status
        gravity.play();
        getAngle.play();
        dispenser.play();
        tracer.play();
        rainbowDash.play();
        cloudMove.play();
        timerFruit.play();
        for (SlicedFruit slicedFruit : slicedFruits)
        {
            slicedFruit.setPlayAnim();
        }
        for (Bomb bomb : bombs)
        {
            bomb.setPlayAnim();
        }
        for (Fruit fruit : fruits)
        {
            fruit.setPlayAnim();
        }
        for (PowerUp powerUp : powerUps)
        {
            powerUp.setPlayAnim();
        }
    }

    private void pause()
    {
        //Pause State
        pausePlay.setText("PLAY");
        isPaused = true; //update pause status
        gravity.pause();
        getAngle.pause();
        dispenser.pause();
        tracer.pause();
        rainbowDash.pause();
        cloudMove.pause();
        timerFruit.pause();
        rainbowFruitTimer.stop();
        isRainbow = false; //if paused will lose rainbow powerups
        for (SlicedFruit slicedFruit : slicedFruits)
        {
            slicedFruit.setPauseAnim();
        }
        for (Bomb bomb : bombs)
        {
            bomb.setPauseAnim();
        }
        for (Fruit fruit : fruits)
        {
            fruit.setPauseAnim();
        }
        for (PowerUp powerUp : powerUps)
        {
            powerUp.setPauseAnim();
        }
    }

    private void gameOver(int type)
    {
        //oyun bitince bu method devreye girecek
        //tekrardan oynamak ister misin diye soracak :ok
        //oynanmış olan oyunun bilgilerini DB ye kayıt edecek: ok
        //yeni oyun için gerekli ön hazırlıkları yapacak :ok
        timerFruit.stop();
        GameOverUI gameOverUi = new GameOverUI(type, this);
        getChildren().addAll(gameOverUi);
        try
        {
            DBO.insertNewScore(player.getPlayerId(), this.score, time);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void restart()
    {
        //clearing all scores and changes
        score = 0;
        hitCount = 0;
        missCount = 0;
        isGameOver = false;
        isPaused = true;
        multiplier = 1;
        time = 0;
        timerFruit.stop();
        rainbowFruitTimer.stop();
        isRainbow = false;
        explosion = new RegularBombExplosion(0, 0); //default null explosion. no NullPointerExecption
        cloud = Utils.pickRandomCloud();
        panel = new TopBar();
        timerLabel = new Label("00:00:0,0");
        //stop every animation for restart
        gravity.stop();
        cloudMove.stop();
        tracer.stop();
        dispenser.stop();
        getAngle.stop();
        rainbowDash.stop();
        //clear everything
        getChildren().clear();
        //draw again
        draw();
        play(); //play instant after replay
    }

    private void timerEvent()
    {
        time += 0.1;
        int min = (int) time / 60;
        int second = (int) (time - min * 60);
        float millis = time - (min * 60 + second);
        String output = String.format("%02d:%02d:%02.1f", min, second, millis);
        timerLabel.setText(output);
    }

    public void exitGame()
    {
        stage.getChildren().remove(this);
        stage.setCenter(new MainMenuUI(stage, player));
        //setCenter main menu
    }
}