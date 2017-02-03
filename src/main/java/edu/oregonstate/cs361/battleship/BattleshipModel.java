package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;


/**
 * Created by RohanMac on 1/29/17.
 */
public class BattleshipModel {
    AircraftCarrier aircraftCarrier;
    Battleship battleship;
    Cruiser cruiser;
    Destroyer destroyer;
    Submarine submarine;
    ComputerCruiser computerCruiser;
    ComputerSubmarine computerSubmarine;
    ComputerDestroyer computerDestroyer;
    ComputerAircraftCarrier computerAircraftCarrier;
    ComputerBattleship computerBattleship;

    ArrayList<Hits>playerHits;
    ArrayList<Misses>playerMisses;
    ArrayList<Hits>computerHits;
    ArrayList<Misses>computerMisses;

    public BattleshipModel(AircraftCarrier aircraftCarrier, Battleship battleship, Cruiser cruiser, Destroyer destroyer, Submarine submarine
    , ComputerAircraftCarrier computerAircraftCarrier, ComputerBattleship computerBattleship, ComputerCruiser computerCruiser, ComputerDestroyer computerDestroyer
    , ComputerSubmarine computerSubmarine, ArrayList<Hits>playerHits, ArrayList<Misses>playerMisses,
                           ArrayList<Hits>computerHits, ArrayList<Misses>computerMisses){
        this.aircraftCarrier=aircraftCarrier;
        this.battleship = battleship;
        this.cruiser = cruiser;
        this.destroyer = destroyer;
        this.submarine = submarine;
        this.computerAircraftCarrier = computerAircraftCarrier;
        this.computerBattleship = computerBattleship;
        this.computerCruiser = computerCruiser;
        this.computerDestroyer = computerDestroyer;
        this.computerSubmarine = computerSubmarine;
        this.playerHits = playerHits;
        this.playerMisses = playerMisses;
        this.computerHits=computerHits;
        this.computerMisses=computerMisses;

    }

    public AircraftCarrier getAircraftCarrier() {
        return aircraftCarrier;
    }

    public Cruiser getCruiser(){
        return cruiser;
    }

    public void setAircraftCarrier(AircraftCarrier aircraftCarrier) {
        this.aircraftCarrier = aircraftCarrier;
    }

    public Battleship getBattleship() {
        return battleship;
    }

    public void setBattleship(Battleship battleship) {
        this.battleship = battleship;
    }

    public void setCruiser(Cruiser cruiser) {
        this.cruiser = cruiser;
    }

    public Destroyer getDestroyer() {
        return destroyer;
    }

    public void setDestroyer(Destroyer destroyer) {
        this.destroyer = destroyer;
    }

    public Submarine getSubmarine() {
        return submarine;
    }

    public void setSubmarine(Submarine submarine) {
        this.submarine = submarine;
    }

    public ComputerAircraftCarrier getComputerAircraftCarrier() {
        return computerAircraftCarrier;
    }

    public void setComputerAircraftCarrier(ComputerAircraftCarrier computerAircraftCarrier) {
        this.computerAircraftCarrier = computerAircraftCarrier;
    }

    public ComputerBattleship getComputerBattleship() {
        return computerBattleship;
    }

    public void setComputerBattleship(ComputerBattleship computerBattleship) {
        this.computerBattleship = computerBattleship;
    }

    public ComputerCruiser getComputerCruiser() {
        return computerCruiser;
    }

    public void setComputerCruiser(ComputerCruiser computerCruiser) {
        this.computerCruiser = computerCruiser;
    }

    public ComputerDestroyer getComputerDestroyer() {
        return computerDestroyer;
    }

    public void setComputerDestroyer(ComputerDestroyer computerDestroyer) {
        this.computerDestroyer = computerDestroyer;
    }

    public ComputerSubmarine getComputerSubmarine() {
        return computerSubmarine;
    }

    public void setComputerSubmarine(ComputerSubmarine computerSubmarine) {
        this.computerSubmarine = computerSubmarine;
    }

    public ArrayList<Hits> getPlayerHits() {
        return playerHits;
    }

    public ArrayList<Hits> getComputerHits() {
        return computerHits;
    }

    public ArrayList<Misses> getComputerMisses() {
        return computerMisses;
    }

    public ArrayList<Misses> getPlayerMisses() {
        return playerMisses;
    }

    public void setComputerHits(ArrayList<Hits> computerHits) {
        this.computerHits = computerHits;
    }

    public void setComputerMisses(ArrayList<Misses> computerMisses) {
        this.computerMisses = computerMisses;
    }

    public void setPlayerHits(ArrayList<Hits> playerHits) {
        this.playerHits = playerHits;
    }

    public void setPlayerMisses(ArrayList<Misses> playerMisses) {
        this.playerMisses = playerMisses;
    }
}









