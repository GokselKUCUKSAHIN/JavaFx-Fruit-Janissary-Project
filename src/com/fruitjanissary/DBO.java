package com.fruitjanissary;

import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class DBO
{

    //DBO = DataBaseObject
    static
    {
        //RUN THESE LINE FOR ONCE
        //System.out.println("SINGLE RUN TEST."); //debug
        try
        {
            checkDB(); //check for init
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //
    private static Connection connection;
    private static Statement statement;
    private static boolean isInit = false;

    //
    //
    private static void initDatabase() throws SQLException
    {
        //String conString = "jdbc:mysql://127.0.0.1:3306/?user=root&password=goksel38&serverTimezone=UTC";//&SQL_SAFE_UPDATES = 0";
        String url = "jdbc:mysql://127.0.0.1:3306/fruitdb";
        Properties connProps = new Properties();
        connProps.setProperty("user", "root");
        connProps.setProperty("password", "goksel38");
        connProps.setProperty("useSSL", "false");
        connProps.setProperty("serverTimezone", "UTC");
        connProps.setProperty("allowPublicKeyRetrieval", "true");
        //
        connection = DriverManager.getConnection(url, connProps);
        statement = connection.createStatement();
    }

    public static int logIn(String userName, String password) throws SQLException
    {
        checkDB();
        //String query = "insert into fruitjanissary.users (nickname,password) values ('" + name + "','" + pass + "');";
        //String query2 = String.format("insert into fruitjanissary.users (nickname,password) values ('%s','%s');", name, pass);
        //String query = "select * from fruitjanissary.users where nickname ='" + userName + "' and password ='" + password + "';";
        //String query = String.format("select * from fruitjanissary.users where nickname ='%s' and password ='%s'", userName, password);
        String query = String.format("select * from fruitdb.player where nickname ='%s' and password ='%s'", userName, Utils.hashFunctionGenerator(password));
        ResultSet result = statement.executeQuery(query);
        if (result.next())
        {
            return result.getInt(1);
        } else
        {
            return -1;
        }
        //1 tane var devamı yok false
        //0 tane var devamı yok false
    }

    private static boolean isUnique(String nick, String mail) throws SQLException
    {
        checkDB();
        String query = String.format("select * from fruitdb.player where nickname ='%s' or email ='%s'", nick, mail);
        ResultSet result = statement.executeQuery(query);
        return (!result.next());
    }

    private static void signUpQuery(String nick, String pass, String name, String sn, String mail) throws SQLException
    {
        String query = String.format("insert into fruitdb.player values(null,'%s','%s','%s','%s','%s');", nick, Utils.hashFunctionGenerator(pass), name, sn, mail);
        statement.executeUpdate(query);
    }

    public static void checkDB() throws SQLException
    {
        if (!isInit)
        {
            initDatabase();
            isInit = true;
        }
    }

    public static String signUp(String nick, String pass1, String pass2, String mail, String name, String surname)
    {
        if (Utils.isEmpty(nick) || Utils.isEmpty(pass1) || Utils.isEmpty(pass2) || Utils.isEmpty(mail) || Utils.isEmpty(name) || Utils.isEmpty(surname))
        {
            return "Has Empty Fields!"; //edit
        } else
        {
            //all fields aren't empty
            if (pass1.equals(pass2))
            {
                //şifreler aynı
                if (Utils.isValidPassword(pass1))
                {
                    if (Utils.isValidName(name))
                    {
                        if (Utils.isValidName(surname))
                        {
                            if (Utils.isValidEMail(mail))
                            {
                                if (Utils.isValidNickName(nick))
                                {
                                    try
                                    {
                                        if (DBO.isUnique(nick, mail))
                                        {
                                            DBO.signUpQuery(nick, pass1, name, surname, mail);
                                            return "SignUp Successful! Let's Play.";
                                        } else
                                        {
                                            return "Nickname or Email already in use.";
                                        }
                                    }
                                    catch (SQLException ex)
                                    {
                                        return "Error code: " + ex.getErrorCode();
                                    }
                                } else
                                {
                                    return "Invalid Nickname!";
                                }
                            } else
                            {
                                return "Invalid E-Mail!";
                            }
                        } else
                        {
                            return "Invalid Surname!";
                        }
                    } else
                    {
                        return "Invalid Name!";
                    }
                } else
                {
                    return "Invalid Password!";
                }
            } else
            {
                return "Passwords aren't the Same!";
            }
        }
    }

    public static void closeConnection() throws SQLException
    {
        if (connection != null)
        {
            connection.close();
        }
    }

    public static void insertNewScore(int playerID, int score, float duration) throws SQLException
    {
        checkDB();
        String query = String.format("insert into fruitdb.board values(null,'%d','%d','%s','%d');", playerID, score, Utils.localDateToSQLDate(LocalDate.now()), (int) duration);
        statement.executeUpdate(query);
    }

    public static ResultSet getTop10Scores() throws SQLException
    {
        checkDB();
        String query = "select p.id, p.nickname, b.score, b.pDate, b.duration from fruitdb.board b inner join fruitdb.player p on p.id = b.playerid order by b.score desc limit 10;";
        return statement.executeQuery(query);
    }

    public static ResultSet getPlayersTop10Scores(int id) throws SQLException
    {
        checkDB();
        String query = String.format("select p.id, p.nickname, b.score, b.pDate, b.duration from fruitdb.board b inner join fruitdb.player p on p.id = b.playerid where p.id = '%s' order by b.score desc limit 10;", id);
        return statement.executeQuery(query);
    }

    public static int getPlayersTotalScore(int id) throws SQLException
    {
        checkDB();
        String query = String.format("select playerid, sum(board.score) as total from fruitdb.board where playerid = %d;", id);
        ResultSet rs = statement.executeQuery(query);
        if (rs.next())
        {
            return rs.getInt(2);
        } else
        {
            return 0;
        }

    }

    public static String getPlayersPlayTime(int id) throws SQLException
    {
        checkDB();
        String query = String.format("select playerid, sum(duration) as total from fruitdb.board where playerid = %d;", id);
        ResultSet rs = statement.executeQuery(query);
        if (rs.next())
        {
            return Utils.getHourMinSec(rs.getInt(2));
        } else
        {
            return Utils.getHourMinSec(0);
        }
    }

    public static Player getPlayer(int id) throws SQLException
    {
        checkDB();
        String query = String.format("select id, nickname, name, surname, email from fruitdb.player where id = %d", id);
        ResultSet rs = statement.executeQuery(query);
        while (rs.next())
        {
            return new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        }
        return null; //error
    }
}