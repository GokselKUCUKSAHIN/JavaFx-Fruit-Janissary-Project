package com.fruitjanissary;

public class Player
{
    private int playerId;
    private String nickname;
    private String name;
    private String surname;
    private String email;

    public int getPlayerId()
    {
        return playerId;
    }

    public String getNickname()
    {
        return nickname;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getEmail()
    {
        return email;
    }

    public Player(int id, String nickname, String name, String surname, String email)
    {
        this.playerId = id;
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
