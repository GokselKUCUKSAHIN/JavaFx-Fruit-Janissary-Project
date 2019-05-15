package com.fruitjanissary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DebugClass
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("test: ");
        //System.out.println(String.format("%02d",12));

        //System.out.println(Utils.isValidNickName(text));
        //System.out.println(Utils.localDateToSQLDate(LocalDate.now()));
        //System.out.println(Utils.isValidPassword(text));
        //System.out.println(Utils.hashFunctionGenerator(text));
        //
        /*
        while (true)
        {
            String text = scanner.nextLine();
            System.out.println(Utils.isValidName(text));
            //System.out.println(Utils.isValidSurname(text));
            //System.out.println(Utils.isValidEMail(text));
        }*/

        /*try
        {
            //DBO.insertNewScore(3,761,29);
            //DBO.insertNewScore(3,1235,6548);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }*/

        try
        {
            System.out.println(DBO.getPlayersPlayTime(0));
            System.out.println(DBO.getPlayersPlayTime(2));
            System.out.println(DBO.getPlayersPlayTime(5)); //player 5 = jbn
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
