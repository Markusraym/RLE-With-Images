import org.junit.Test;

import static org.junit.Assert.assertArrayEquals; //imports the array equals function
import static org.junit.Assert.assertEquals;

public class PreWorkTest {
    @Test
   /* public void sumByParityTest1() {
        int[] arr = {5, 12, 8, 5, 3, 11, 7, 2, 3, 16, 4}; //input
        int [] res = {30, 46}; // expected output
        assertArrayEquals(res, Prework.sumByParity(arr)); //compares: (expected result array, method(input) )
    }

    public void consecutiveFoursTest() {
        int[] input = {15, 15, 15, 4, 4, 4, 4, 4, 4};
        boolean expectedoutput = true;
        assertEquals(expectedoutput, Prework.consecutiveFours(input));
    }

    */
    public void expandByIndexTest() { //Test for expandByIndex method
        int[] arr = {2, 1, 3};
        int[] res = {0, 0, 1, 2, 2, 2};
        assertArrayEquals(Prework.expandByIndex(arr), res);
    }
}
