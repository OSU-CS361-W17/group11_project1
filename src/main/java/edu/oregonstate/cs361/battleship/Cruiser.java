package edu.oregonstate.cs361.battleship;

/**
 * Created by RohanMac on 1/31/17.
 */
public class Cruiser {
    String name;
    int length;
    Start start;
    End end;

    public Cruiser(String s, int b, Start s1, End e){
        name = s;
        length =b;
        start =s1;
        end = e;
    }
}
