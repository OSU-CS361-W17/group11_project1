package edu.oregonstate.cs361.battleship;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by RohanMac on 2/1/17.
 */
public class AircraftTest {
     private Start start;
     private End end;
     private AircraftCarrier aircraftCarrier;

    @Test
    public void setUp()  {
        start = new Start(1,1);
        end = new End(1,1);
        aircraftCarrier = new AircraftCarrier("AircraftCarrier", 5,start,end);
        assertEquals(5,aircraftCarrier.getLength());


    }


}
