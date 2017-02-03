package edu.oregonstate.cs361.battleship;

/**
 * Created by RohanMac on 1/31/17.
 */
public class Battleship {
    String name;
    int length;
    Start start;
    End end;

    public Battleship(String s, int b, Start s1, End e){
        name = s;
        length =b;
        start = s1;
        end = e;
    }
}
