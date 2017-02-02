/**
 * Created by RohanMac on 2/1/17.
 */
package edu.oregonstate.cs361.battleship;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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


}