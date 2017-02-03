/**
 * Created by RohanMac on 1/31/17.
 */
package edu.oregonstate.cs361.battleship;


public class AircraftCarrier {
    private String name;
    private int length;
    private Start start;
    private End end;

    public AircraftCarrier(String s1, int l, Start s, End d){
        name =s1;
        length= l;
        start =s;
        end =d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

}

