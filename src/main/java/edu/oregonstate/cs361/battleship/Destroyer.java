package edu.oregonstate.cs361.battleship;

/**
 * Created by RohanMac on 1/31/17.
 */
public class Destroyer {
    String name;
    int length;
    Start start;
    End end;

    public Destroyer(String s, int l, Start s2, End e){
        name = s;
        length= l;
        start =s2;
        end =e;
    }
}
