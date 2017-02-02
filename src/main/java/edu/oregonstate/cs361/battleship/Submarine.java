package edu.oregonstate.cs361.battleship;

/**
 * Created by RohanMac on 2/1/17.
 */
public class Submarine {
    String name;
    int length;
    Start start;
    End end;

    public Submarine(String s, int l, Start s2, End e){
        name =s;
        length=l;
        start = s2;
        end =e;
    }
}
