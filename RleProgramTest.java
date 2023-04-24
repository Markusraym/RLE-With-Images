import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RleProgramTest {

    @Test
 /*
    //Test method 4, //sums up all numbers at even indices
    public void getDecodeLengthTest() {
        byte[] input = {3, 15, 6, 4}; //the input
        int expectedresult = 9;
        assertEquals(expectedresult, RleProgram.getDecodedLength(input));

   }

    //Test method 2, //finds the amount of groups of numbers
    //Passes Tests
    public void countRunsTest() {
        byte[] input = {1,2,3,4,5,1,2,3,4,5,1,2,3,4,5,1,2,3,4,5,1,2,3,4,5};
        int expectedresult = 25;
        assertEquals(expectedresult, RleProgram.countRuns(input));
    }

    //Test method 5, //Does opposite of method 3. Duplicates the odd number depending on the previous number {3, 15, 6, 4} --> {15, 15, 15, 4, 4, 4, 4, 4, 4}
    //Passes Tests
   public void decodeRleTest() {
        byte[] input = {2,4,15,1,15,1,5,1,1,8,1,7};
        byte[] expectedResult = {4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,8,7};
        assertArrayEquals(expectedResult, RleProgram.decodeRle(input));
   }

   //Test method 6, // Converts hexadecimal into byte array (3f64) --> {3, 15, 6, 4}
    //Passes Tests
    public void stringToDataTest() {
        String input = "36f4FB";
        byte[] expectedResult = {3, 6, 15, 4, 15, 11};
        assertArrayEquals(expectedResult, RleProgram.stringToData(input));

    }

    //Test method 1,  //Takes byte array and translates into hexadecimal string {3, 15, 6, 4} --> 3f64
    //Passes Tests
    public void toHexStringTest() {
        byte[] input = {3, 15, 6, 4, 14, 10, 11, 8};
        String expectedResult = "3f64eab8";
        assertEquals(expectedResult, RleProgram.toHexString(input));
    }

    //Test method 3,  //Counts how many duplicates of a number and then the number. {15, 15, 15, 4, 4, 4, 4, 4,} --> { 3, 15, 6, 4}
    //Passes Tests
    public void encodeRleTest() {
        byte[] input = {4,4,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,8,7};
        byte[] expectedResult = {2,4,15,1,15,1,5,1,1,8,1,7};
        byte[] fetchTruth = RleProgram.encodeRle(input);
        assertArrayEquals(expectedResult, fetchTruth);
    }

    //Test method 7, //Replaces the odd index's with its hexadecimal equivalent, adds a : every two numbers {15, 15, 6, 4} --> 15f:64
    public void toRleStringTest() {
        byte[] input = {1, 1, 6, 4, 15, 15, 6, 4};
        String expectedResult = "11:64:15f:64";
        assertEquals(expectedResult, RleProgram.toRleString(input));
    } */

    // Test method 8, // Does the inverse of method 7, takes the RLE format and turns it back into a byte array. ("15f:64) --> {15, 15, 6, 4}
    public void stringToRleTest() {
        String input = "15f:64";
        byte[] expectedResult = {15, 15, 6, 4};
        assertArrayEquals(expectedResult, RleProgram.stringToRle(input));

    }




}

