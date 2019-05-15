package com.fruitjanissary;

import com.fruitjanissary.Bombs.BombDefault;
import com.fruitjanissary.Bombs.Nuke;
import com.fruitjanissary.Clouds.BiCloud;
import com.fruitjanissary.Clouds.TriCloud;
import com.fruitjanissary.Fruits.*;
import com.fruitjanissary.PowerUps.Mushroom;
import com.fruitjanissary.PowerUps.RainbowFruit;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Random;

public class Utils
{

    //
    private static Random random = new Random(); //static random generator //one generator for everything!

    //
    public static double getRandomNumber(int end)
    {
        return random.nextInt(end);
    }

    //
    public static double getRandomNumber(int end, double offset)
    {
        return random.nextInt(end) + offset;
    }

    //
    static double calculateAngle(double p1X, double p1Y, double p2X, double p2Y)
    {
        double dx = Math.abs(p1X - p2X);
        double dy = Math.abs(p1Y - p2Y);
        //System.out.println(dx+": "+dy);
        double a = Math.round(radianToAngle(Math.atan(dy / dx)));
        if (p1X - p2X >= 0)
        {
            //II - III
            if (p1Y - p2Y >= 0)
            {
                //II Region
                return 180 - a;
            } else
            {
                //III Region
                return 180 + a;
            }
        } else
        {
            // I - IV
            if (p1Y - p2Y >= 0)
            {
                //I Region
                return a;
            } else
            {
                //IV Region
                return 360 - a;
            }
        }
    }

    //
    static double radianToAngle(double radian)
    {
        return radian * (180 / Math.PI);
    }

    //
    public static boolean isValidEMail(String mail)
    {
        mail = mail.toLowerCase();
        String regex = "^[a-z0-9.]+@[a-z0-9.]+.(com|org|net|edu|gov)(.[a-z]{2})?$";
        return mail.matches(regex);
    }

    //
    public static boolean isValidNickName(String nickName)
    {
        if (nickName == null || nickName.length() <= 1)
        {
            return false;
        } else
        {
            String isAllNumber = "[0-9]+";
            if (nickName.matches(isAllNumber))
            {
                return false;
            } else
            {
                String regex = "[a-zA-Z0-9]+";
                return nickName.matches(regex);
            }
        }
    }

    //
    public static boolean isValidName(String name)
    {
        if (name == null || name.length() <= 1)
            return false;
        else
        {
            String regex = "[a-zA-ZğĞüÜıİöÖçÇşŞ]+";
            return name.matches(regex);
        }
    }

    //
    public static boolean isValidPassword(String password)
    {
        if (password == null || password.length() < 6)
        {
            return false;
        } else
        {
            String regex = "[A-Za-z0-9@!#]{6,20}";
            return password.matches(regex);
        }
    }

    //
    public static String localDateToSQLDate(LocalDate date)
    {
        //return "year-month-day"
        return (date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth()); //format = year-month-day
    }

    //
    //Hash fonksiyonu lazım: md5 veya base64
    //"Gazozuna kod yazıyor olsanız bile Şifreleri Plain text olarak tutmayın!"
    //
    public static String hashFunctionGenerator(String stringForEncode)
    {
        MessageDigest digest = null;
        try
        {
            digest = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace(); //it means somethings went really wrong.
        }
        byte[] hash = digest.digest(stringForEncode.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    //
    static Fruit pickRandomFruit(int x, int y, int radius)
    {
        int random = (int) getRandomNumber(Fruit.TOTAL_FRUIT_COUNT);
        switch (random)
        {
            //bigger fruit lesser score.
            case 0:
            {
                //orange
                return new Orange(x, y, radius);
            }
            case 1:
            {
                //apple
                return new Apple(x, y, radius);
            }
            case 2:
            {
                //peach
                return new Peach(x, y, radius);
            }
            case 3:
            {
                //lemon
                return new Lemon(x, y, radius);
            }
            case 4:
            {
                //mango
                return new Mango(x, y, radius);
            }
            case 5:
            {
                //green apple
                return new GreenApple(x, y, radius);
            }
            case 6:
            {
                //watermelon
                return new Watermelon(x, y, radius);
            }
            //can add more fruits
            default:
            {
                return null;
            }
        }
    }

    //
    static Bomb pickRandomBomb(int x, int y)
    {
        int random = (int) getRandomNumber(Bomb.NUMBER_OF_BOMBS);
        switch (random)
        {
            case 0:
            {
                //Regular Bomb
                return new BombDefault(x, y);
            }
            case 1:
            {
                //Nuclear Bomb
                return new Nuke(x, y);
            }
            //add more bombs maybe
            default:
            {
                return null;

            }
        }
    }

    //
    static PowerUp pickRandomPowerUps(int x, int y, int radius)
    {
        int random = (int) getRandomNumber(PowerUp.NUMBER_OF_POWERUPS);
        switch (random)
        {
            case 0:
            {
                //Regular Bomb
                return new RainbowFruit(x, y, radius);
            }
            case 1:
            {
                //Nuclear Bomb
                return new Mushroom(x, y, radius);
            }
            //add more powerups maybe
            default:
            {
                return null;
            }
        }
    }

    //
    static Cloud pickRandomCloud()
    {
        int random = (int) getRandomNumber(2);
        switch (random)
        {
            case 0:
            {
                return new BiCloud(100);
            }
            case 1:
            {
                return new TriCloud(80);
            }
            default:
            {
                return null;
            }
        }
    }

    //
    public static boolean isEmpty(String value)
    {
        return (value.length() == 0);
    }

    //
    public static String getHourMinSec(int time)
    {
        String out = "";
        int hour = (time - time % 3600) / 3600;
        int min = (time - 3600 * hour) / 60;
        int sec = time - (3600 * hour + 60 * min);
        if (hour != 0)
        {
            out += hour + " hours ";
        }
        if (min != 0)
        {
            out += min + " minutes ";
        }
        if (sec != 0)
        {
            out += sec + " seconds";
        }
        return out;
    }
    //
    public static String getHMS(int time)
    {
        String out = "";
        int hour = (time - time % 3600) / 3600;
        int min = (time - 3600 * hour) / 60;
        int sec = time - (3600 * hour + 60 * min);
        if (hour != 0)
        {
            out += hour + "h";
        }
        if (min != 0)
        {
            out += min + "m";
        }
        if (sec != 0)
        {
            out += sec + "s";
        }
        return out;
    }
}
