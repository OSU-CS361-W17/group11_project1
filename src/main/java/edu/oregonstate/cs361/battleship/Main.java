package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;
import java.util.Random;
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
        BattleshipModel currentModel = getModelFromReq(req);

        int rows = Integer.parseInt(req.params(row));
        int col = Integer.parseInt(req.params(col));

        BattleshipModel placeModel = getModelFromReq(req);



        return "SHIP";
    }

    //Similar to placeShip, but with firing.
    private static String fireAt(Request req) {

        //read in where user is trying to fire form the url
        int row = Integer.parseInt(req.params(row));
        int col = Integer.parseInt(req.params(col));

        //get current model from the request
        BattleshipModel currentModel = getModelFromReq(req);


        //determine whether it is a hit or a miss
        boolean hit = false;

        //check if it is a hit on the computer's aircraft carrier
        if ((currentModel.computerAircraftCarrier.start.Across <= col) && (col <= currentModel.computerAircraftCarrier.end.Across)){
            if ((currentModel.computerAircraftCarrier.start.Down <= row) && (row <= currentModel.computerAircraftCarrier.start.Down))
            {
                //it's a hit on the aircraft carrier
                hit = true;
            }
        }

        //check if it's a hit on the battleship
        if ((currentModel.computerBattleship.start.Across <= col) && (col <= currentModel.computerBattleship.end.Across)){
            if ((currentModel.computerBattleship.start.Down <= row) && (row <= currentModel.computerBattleship.start.Down))
            {
                //it's a hit on the battleship
                hit = true;
            }
        }

        //check if it's a hit on the cruiser
        if ((currentModel.computerCruiser.start.Across <= col) && (col <= currentModel.computerCruiser.end.Across)){
            if ((currentModel.computerCruiser.start.Down <= row) && (row <= currentModel.computerCruiser.start.Down))
            {
                //it's a hit on the cruiser
                hit = true;
            }
        }

        //check if it's a hit on the destroyer;
        if ((currentModel.computerDestroyer.start.Across <= col) && (col <= currentModel.computerDestroyer.end.Across)){
            if ((currentModel.computerDestroyer.start.Down <= row) && (row <= currentModel.computerDestroyer.start.Down))
            {
                //it's a hit on the destroyer
                hit = true;
            }
        }

                //check if it's a hit on th submarine
        if ((currentModel.computerSubmarine.start.Across <= col) && (col <= currentModel.computerSubmarine.end.Across)){
            if ((currentModel.computerSubmarine.start.Down <= row) && (row <= currentModel.computerSubmarine.start.Down))
            {
                //it's a hit on the submarine
                hit = true;
            }
        }

        if (!hit){
           //record as a miss
            Misses miss = new Misses(row, col);
            currentModel.computerMisses.add(miss);
            System.out.println("You missed!");
        } else if (hit) {
            Hits hit1 = new Hits(row, col);
            currentModel.computerHits.add(hit1);
            System.out.println("That was a hit");
        }

        //Hit for AI to user
        int airow, aicol;
        boolean aihit = false;

        airow = rn.nextInt(10) + 1;
        aicol = rn.nextInt(10) + 1;

        //check if it is a hit on the computer's aircraft carrier
        if ((currentModel.AircraftCarrier.start.Across <= aicol) && (aicol <= currentModel.AircraftCarrier.end.Across)){
            if ((currentModel.AircraftCarrier.start.Down <= airow) && (airow <= currentModel.AircraftCarrier.start.Down))
            {
                //it's a hit on the aircraft carrier
                aihit = true;
            }
        }

        //check if it's a hit on the battleship
        if ((currentModel.Battleship.start.Across <= aicol) && (aicol <= currentModel.Battleship.end.Across)){
            if ((currentModel.Battleship.start.Down <= airow) && (airow <= currentModel.Battleship.start.Down))
            {
                //it's a hit on the battleship
                aihit = true;
            }
        }

        //check if it's a hit on the cruiser
        if ((currentModel.Cruiser.start.Across <= aicol) && (aicol <= currentModel.Cruiser.end.Across)){
            if ((currentModel.Cruiser.start.Down <= airow) && (airow <= currentModel.Cruiser.start.Down))
            {
                //it's a hit on the cruiser
                aihit = true;
            }
        }

        //check if it's a hit on the destroyer;
        if ((currentModel.Destroyer.start.Across <= aicol) && (aicol <= currentModel.Destroyer.end.Across)){
            if ((currentModel.Destroyer.start.Down <= airow) && (airow <= currentModel.Destroyer.start.Down))
            {
                //it's a hit on the destroyer
                aihit = true;
            }
        }

                //check if it's a hit on th submarine
        if ((currentModel.Submarine.start.Across <= aicol) && (aicol <= currentModel.Submarine.end.Across)){
            if ((currentModel.Submarine.start.Down <= airow) && (airow <= currentModel.Submarine.start.Down))
            {
                //it's a hit on the submarine
                aihit = true;
            }
        }

        if (!aihit){
           //record as a miss
            Misses aimiss = new Misses(airow, aicol);
            currentModel.Misses.add(aimiss);
            System.out.println("The computer missed!");
        } else if (aihit) {
            Hits aihit1 = new Hits(airow, aicol);
            currentModel.Hits.add(aihit1);
            System.out.println("The computer hit");
        }

        //return the updated battleship model as a string (json)
        Gson gson = new Gson();
        String CurrentStateJson = gson.toJson(currentModel);

        return CurrentStateJson;
    }

}
