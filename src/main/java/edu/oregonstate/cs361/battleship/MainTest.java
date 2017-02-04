/**
 * Created by RohanMac on 2/1/17.
 */
package edu.oregonstate.cs361.battleship;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static spark.Spark.awaitInitialization;


/**
 * Created by michaelhilton on 1/26/17.
 */
class MainTest {

    @BeforeAll
    public static void beforeClass() {
        Main.main(null);
        awaitInitialization();
    }

    @AfterAll
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void testGetModel() {
        TestResponse res = request("GET", "/model");
        assertEquals(200, res.status);
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
        ArrayList<Hits> playerHits = new ArrayList<Hits>();
        ArrayList<Misses>playerMisses = new ArrayList<Misses>();
        ArrayList<Misses>computerMisses = new ArrayList<Misses>();
        ArrayList<Hits>computerHits = new ArrayList<Hits>();

        BattleshipModel battleshipModel = new BattleshipModel(aircraftCarrier,battleship,cruiser,destroyer,submarine,
                computer_aircraftCarrier,computer_battleship,computer_cruiser,computer_destroyer,computer_submarine,
                playerHits,playerMisses,computerHits,computerMisses);

        Gson gson = new Gson();
        String battleshipModelWithJson = gson.toJson(battleshipModel);

        assertEquals(battleshipModelWithJson,res.body);
    }

    @Test
    /*public void testPlaceShip() {
        TestResponse res = request("POST", "/placeShip/aircraftCarrier/1/1/horizontal");
        assertEquals(200, res.status);
        assertEquals("SHIP",res.body);
    }*/

    private TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

        public Map<String,String> json() {
            return new Gson().fromJson(body, HashMap.class);
        }
    }


    @Test
    public void testGetModelFromReq(Request req){
        req.body="{\n" +
                "    \"aircraftCarrier\": {\n" +
                "        \"name\": \"AircraftCarrier\",\n" +
                "        \"length\": 5,  \n" +
                "        \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
                "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
                "    },\n" +
                "    \"battleship\": {\n" +
                "        \"name\": \"Battleship\",\n" +
                "        \"length\": 4,\n" +
                "        \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
                "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
                "    },\n" +
                "    \"cruiser\": {\n" +
                "        \"name\": \"Cruiser\",\n" +
                "        \"length\": 3,\n" +
                "        \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
                "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
                "    },\n" +
                "    \"destroyer\": {\n" +
                "        \"name\": \"Destroyer\",\n" +
                "        \"length\": 2,\n" +
                "        \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
                "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
                "    },\n" +
                "    \"submarine\": {\n" +
                "        \"name\": \"Submarine\",\n" +
                "        \"length\": 2,\n" +
                "       \"start\": { \"Across\": 0,\"Down\": 0 },\n" +
                "        \"end\": {\"Across\": 0, \"Down\": 0}\n" +
                "    },\n" +
                "    \"computer_aircraftCarrier\": {\n" +
                "        \"name\": \"Computer_AircraftCarrier\",\n" +
                "        \"length\": 5,\n" +
                "        \"start\": { \"Across\": 2,\"Down\": 2 },\n" +
                "        \"end\": {\"Across\": 2, \"Down\": 7}\n" +
                "    },\n" +
                "    \"computer_battleship\": {\n" +
                "        \"name\": \"Computer_Battleship\",\n" +
                "        \"length\": 4,\n" +
                "        \"start\": { \"Across\": 2,\"Down\": 8 },\n" +
                "        \"end\": {\"Across\": 6, \"Down\": 8}\n" +
                "    },\n" +
                "    \"computer_cruiser\": {\n" +
                "        \"name\": \"Computer_Cruiser\",\n" +
                "        \"length\": 3,\n" +
                "        \"start\": { \"Across\": 4, \"Down\": 1 },\n" +
                "        \"end\": {\"Across\": 4, \"Down\": 4}\n" +
                "    },\n" +
                "    \"computer_destroyer\": {\n" +
                "        \"name\": \"Computer_Destroyer\",\n" +
                "        \"length\": 2,\n" +
                "        \"start\": { \"Across\": 7, \"Down\": 3 },\n" +
                "        \"end\": {\"Across\": 7, \"Down\": 5}\n" +
                "    },\n" +
                "    \"computer_submarine\": {\n" +
                "        \"name\": \"Computer_Submarine\",\n" +
                "        \"length\": 2,\n" +
                "        \"start\": { \"Across\": 9, \"Down\": 6 },\n" +
                "        \"end\": {\"Across\": 9, \"Down\": 8}\n" +
                "    },\n" +
                "    \"playerHits\": [],\n" +
                "    \"playerMisses\": [],\n" +
                "    \"computerHits\": [],\n" +
                "    \"computerMisses\": []\n" +
                "}";

        BattleshipModel battleshipModel = createDefaultModel();

        assertEquals(getModelFromReq(req),battleshipModel);

        return;
    }

    @Test
    public void testFiring() {
        BattleshipModel sampleModel = createDefaultModel();
        // checkHit(int row, int col, BattleshipModel currentModel)
        // hit on the aircraft carrier
        assertEquals(Main.checkHit(2,3,sampleModel), true);

        // hit on the battleship
        assertEquals(Main.checkHit(5,8,sampleModel), true);

        // hit on the cruiser
        assertEquals(Main.checkHit(4,2,sampleModel), true);

        // hit on the destroyer
        assertEquals(Main.checkHit(7,3,sampleModel), true);

        // hit on the submarine
        assertEquals(Main.checkHit(9,7,sampleModel), true);

        // miss
        assertEquals(Main.checkHit(1,1,sampleModel), false);

    }

    public BattleshipModel createDefaultModel(){
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
        ArrayList<Hits> playerHits = new ArrayList<Hits>();
        ArrayList<Misses>playerMisses = new ArrayList<Misses>();
        ArrayList<Misses>computerMisses = new ArrayList<Misses>();
        ArrayList<Hits>computerHits = new ArrayList<Hits>();

        BattleshipModel battleshipModel = new BattleshipModel(aircraftCarrier,battleship,cruiser,destroyer,submarine,
                computer_aircraftCarrier,computer_battleship,computer_cruiser,computer_destroyer,computer_submarine,
                playerHits,playerMisses,computerHits,computerMisses);

        return battleshipModel;
    }
}