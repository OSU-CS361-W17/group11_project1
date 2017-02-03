package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;
import spark.Request;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import com.google.gson.Gson;


public class Main {
    public static void main(String[] args) {
        //This will allow us to server the static pages such as index.html, app.js, etc.
        staticFiles.location("/public");
        //This will listen to GET requests to /model and return a clean new model
        get("/model", (req, res) -> newModel());
        //This will listen to POST requests and expects to receive a game model, as well as location to fire to
        post("/fire/:row/:col", (req, res) -> fireAt(req));
        //This will listen to POST requests and expects to receive a game model, as well as location to place the ship
        post("/placeShip/:id/:row/:col/:orientation", (req, res) -> placeShip(req));
    }

    //This function should return a new model
    private static String newModel() {

        End end = new End(0,0);
        Start start = new Start(0,0);
        End end1 = new End(0,0);
        Start start1 = new Start(0,0);
        End end2 = new End(0,0);
        Start start2 = new Start(0,0);
        Start start3 = new Start(0,0);
        End end3 = new End(0,0);
        Start start4 = new Start(0,0);
        End end4 = new End(0,0);

        Start start5 = new Start(2,2);
        End end5 = new End(2,7);
        Start start6 = new Start(2,8);
        End end6 = new End(6,8);
        Start start7 = new Start(4,1);
        End end7 = new End(4,4);
        Start start8 = new Start(7,3);
        End end8 = new End(7,5);
        Start start9 = new Start(9,6);
        End end9 = new End(9,8);



        AircraftCarrier aircraftCarrier = new AircraftCarrier("AircraftCarrier",5,start,end);
        Battleship battleship = new Battleship("Battleship",4,start1,end1);
        Cruiser cruiser = new Cruiser("Cruiser",3,start2,end2);
        Destroyer destroyer = new Destroyer("Destroyer",2,start3,end3);
        Submarine submarine = new Submarine("Submarine",2,start4,end4);

        ComputerAircraftCarrier computer_aircraftCarrier = new ComputerAircraftCarrier("Computer_AircraftCarrier",5,start5,end5);
        ComputerBattleship computer_battleship = new ComputerBattleship("Computer_Battleship",4,start6,end6);
        ComputerCruiser computer_cruiser = new ComputerCruiser("Computer_Cruiser",3,start7,end7);
        ComputerDestroyer computer_destroyer = new ComputerDestroyer("Computer_Destroyer",2,start8,end8);
        ComputerSubmarine computer_submarine = new ComputerSubmarine("Computer_Submarine",2,start9,end9);
        ArrayList<Hits>playerHits = new ArrayList<Hits>();
        ArrayList<Misses>playerMisses = new ArrayList<Misses>();
        ArrayList<Misses>computerMisses = new ArrayList<Misses>();
        ArrayList<Hits>computerHits = new ArrayList<Hits>();

        BattleshipModel battleshipModel = new BattleshipModel(aircraftCarrier,battleship,cruiser,destroyer,submarine,
                computer_aircraftCarrier,computer_battleship,computer_cruiser,computer_destroyer,computer_submarine,
                playerHits,playerMisses,computerHits,computerMisses);

        Gson gson = new Gson();
        String battleshipModelWithJson = gson.toJson(battleshipModel);
        return battleshipModelWithJson;
    }


    //This function should accept an HTTP request and deseralize it into an actual Java object.
    private static BattleshipModel getModelFromReq(Request req){
        //retrieve json data from the request
        String jsonData = req.body;

        //create new Gson object to read json data
        Gson gson = new Gson();

        //read json data into a battleship object
        BattleshipModel battleshipModel = gson.fromJson(jsonData, BattleshipModel.class);

        //return the battleship model as current game state
        return battleshipModel;
    }

    //This controller should take a json object from the front end, and place the ship as requested, and then return the object.
   private static String placeShip(Request req) {



        return "SHIP";
    }

    //Similar to placeShip, but with firing.
    private static String fireAt(Request req) {



        return null;
    }

}
