package edu.oregonstate.cs361.battleship;

import java.beans.Statement;

/**
 * Created by RohanMac on 2/1/17.
 */
public class ComputerDestroyer {
    String name;
    int length;
    Start start;
    End end;

    public ComputerDestroyer(String s, int l, Start s2, End e){
        name =s;
        length=l;
        start=s2;
        end=e;
    }
}
