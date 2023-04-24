import java.math.BigInteger;
import java.util.Scanner;

import static java.lang.Character.getNumericValue;

public class RleProgram {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println(" Welcome to the RLE image encoder!");
        System.out.println(" ");
        System.out.println("Displaying Spectrum Image:");
        ConsoleGfx.displayImage(ConsoleGfx.testRainbow);

        byte[] imageData = null;
        boolean Program = true;

        while (Program) {

        System.out.println("RLE Menu");
        System.out.println("--------");
        System.out.println("0. Exit");
        System.out.println("1. Load File");
        System.out.println("2. Load Test Image");
        System.out.println("3. Read RLE String");
        System.out.println("4. Read RLE Hex String");
        System.out.println("5. Read Data Hex String");
        System.out.println("6. Display Image");
        System.out.println("7. Display RLE String");
        System.out.println("8. Display Hex RLE Data");
        System.out.println("9. Display Hex Flat Data");
        System.out.println(" ");
        System.out.println("Select a Menu Option: ");
        int menuInput = scanner.nextInt();

        if (menuInput == 1) { //Load File
            System.out.println("Enter name of file to load: "); //Asks user to input what file it wants to load
            String fileInput = scanner.next();
           // ConsoleGfx.loadFile(fileInput);
            imageData = ConsoleGfx.loadFile(fileInput); //This loads the file into the imageData bite array


        }
        if (menuInput ==2) { // Load Test Image
            imageData = ConsoleGfx.testImage;
            System.out.println("Test image data loaded."); // This tells the user the image was successfully loaded


        }
        if (menuInput == 3){ //Read RLE String
            System.out.println("Enter an RLE string to be decoded: "); // Asks the user for input
            String rleString = scanner.next(); // Allows user for input and stores it
            imageData = stringToRle(rleString); // Converts string to rle byte array

        }

        if (menuInput == 4){ // Read HLE Hex String
            System.out.println("Enter the hex string holding RLE data: ");
            String RleHexString = scanner.next(); // Allows user for input and stores it
            imageData = stringToData(RleHexString); // Converts string to rle byte array

        }

        if (menuInput == 5) { // Read Data Hex String
            System.out.println("Enter the hex string holding flat data: "); // Asks the user for input
            String RleHexFlat = scanner.next(); //Allows user for input and stores it
            imageData = encodeRle(stringToData(RleHexFlat)); // Converts into the array


        }

        if (menuInput == 6) { // Display Image
            System.out.println("Displaying image..."); // Tells the user the image is currently being displayed
            ConsoleGfx.displayImage(imageData); // Displays the image that imageData is assigned to for the user
        }

        if (menuInput == 7) { // Display RLE String
            System.out.println(" RLE representation: " + toRleString(imageData)); // Takes the data and converts to Rle String and displays it

        }

        if (menuInput == 8) { //Display Hex RLE Data10
            System.out.println("RLE hex values: " + toHexString(encodeRle(imageData))); // Takes the data and converts to hex string and displays it

        }

        if (menuInput == 9 ) { // Display Hex Flat Data
            System.out.println("Flat hex values: " + toHexString(decodeRle(imageData)));

        }
        if (menuInput == 0) { // Ends the program if user inputs 0, the option to exit
            break;
        }

        else if (menuInput < 0 || menuInput > 9) { // If the user inputs a number that is not applicable to the menu
            System.out.println("Error! Invalid input.");
        }
        }


    }

    // Method 1
    public static String toHexString(byte[] data) { //Takes byte array and translates into hexadecimal string {3, 15, 6, 4} --> 3f64
        StringBuilder expectedOutput = new StringBuilder();
        for (byte x : data) { //Looks at each element of the array at a time
            if (x < 10) { // If the value is under 10, it will do nothing to it
                expectedOutput.append(x);
            }
            else { // If value is 10+ it will append it to its hex value
                switch (x) {
                    case 10:
                        expectedOutput.append('a');
                        break;
                    case 11:
                        expectedOutput.append('b');
                        break;
                    case 12:
                        expectedOutput.append('c');
                        break;
                    case 13:
                        expectedOutput.append('d');
                        break;
                    case 14:
                        expectedOutput.append('e');
                        break;
                    case 15:
                        expectedOutput.append('f');
                        break;
                }
            }
        }

    return expectedOutput.toString(); } // Returns the finished product




    // Method 2
    public static int countRuns(byte[] flatData) {  // Checks how many groups of numbers you have. {15, 15, 15, 4, 4, 4, 4, 4, 4} = 2
        int count = 1;
        int groups = 1;
        for (int i = 0; i < flatData.length - 1; i++ ) { // For loop that looks at each element of the array
            if (flatData[i] == flatData[i+1]) { // If the value at one index is the same to the value of the next it adds one to count
                count++;
            }
            else {
                count = 1; // If the two elements at each index are not equal to eachother it resets the count back to 1 and adds one to groups
                groups++;
            }
            if (count >= 15) { // If there has been a repeat of the same value over 15 times, it calls it a single group and sets the count back to one
                groups++;
                count = 1;
            }
        }
        return groups; } // Returns the finished product




   // Method 3
    public static byte[] encodeRle(byte[] flatData) { //Counts how many duplicates of a number and then the number. {15, 15, 15, 4, 4, 4, 4, 4,} --> { 3, 15, 6, 4}
       int size = countRuns(flatData) * 2; // Fetches the countRuns method in order to get the size of the new array
       byte[] expectedOutput = new byte[size];
       int numberofRepeats = 1;
       int maximumNum = 0;
       int indexValue = 0;
       int forLength = flatData.length - 1;

       for (int i = 0; i < flatData.length; i++) { //For loop that goes through and encodes with each iteration

           if (i == forLength) { // If i reaches the maximum point
               expectedOutput[indexValue] = (byte) numberofRepeats; //Assigns the value of numberofRepeats to the array at index indexValue
               indexValue++;
               expectedOutput[indexValue] = flatData[i];
           }
           else if (flatData[i] == flatData[i+1] && maximumNum == 15) { //Checks if they match AND it has a maximum repeat of 15
               expectedOutput[indexValue] = (byte) maximumNum;
               indexValue++;
               expectedOutput[indexValue] = flatData[i];
               indexValue++;
               maximumNum = 1; //resets the count back to one
               numberofRepeats = 1; //resets the counter for the repeats back to one
           }
           else if (flatData[i] == flatData[i+1]) { // Checks if they match
               maximumNum++; // Supplements maximumNum by 1
               numberofRepeats++; // Increases number of repeat counter by 1.
           }
           else { //If the two do not match and the maximumNum hasn't reached 15
               expectedOutput[indexValue] = (byte) numberofRepeats; //Assigns the value of numberofRepeats to the array at index indexValue
               indexValue++; // Adds plus 1 to the indexValue
               expectedOutput[indexValue] = flatData[i];
               indexValue++; // Adds plus 1 again to the index value
               maximumNum = 1; // maximumNum is set back to 1 as there was no matches
               numberofRepeats = 1; //numberofRepeats is set back to 1 as there was no matches
           }
       }
        return expectedOutput; }



    // Method 4
    public static int getDecodedLength(byte[] rleData) { //Sum of all the numbers at even indices
       int size = 0;
       for(int i = 0; i < rleData.length; i += 2) { //Will only go on even numbers
        byte value = rleData[i]; // Assigns the value at the index i
        size = value + size; // Adds that value from previous step and repeats on every even index
       }
       return size;  } //Returns the final product



    //Method 5
    public static byte[] decodeRle(byte[] rleData) { //Does opposite of method 3. Duplicates the odd number depending on the previous number {3, 15, 6, 4} --> {15, 15, 15, 4, 4, 4, 4, 4, 4}
        //Use getDecodedLength to get the size of the new array and use the int[] res = new int [size] from expandbyIndex
        int size = getDecodedLength(rleData); //gets the length from the getDecodedLength method
        byte[] expectedResult = new byte[size]; //This will be the size of the sum of all even spots.
        int index = 0;
        for (int i = 0; i < rleData.length; i += 2) {
           byte repeatAmount = rleData[i]; //This gives the value at even index, which gives the amount of repeats the actualValue would have
           byte actualValue = rleData[i+1]; //This gives the actual value that will be repeated from the repeatAmount.
            for (int k = 0; k < repeatAmount; k++ ) {
                expectedResult[index] = actualValue;
                index++;
            }

        }
        return expectedResult; // Returns the final product
    }


    // Method 6
    public static byte[] stringToData(String dataString) { // Converts hexadecimal into byte array (3f64) --> {3, 15, 6, 4}
        int size = dataString.length();
        byte[] expectedResult = new byte[size];
        //For loop with a switch statement will be used for each index/element
        for (int i = 0; i < dataString.length(); i++ ) {
            switch (dataString.charAt(i)) { // Switch case that converts each hexadecimal char into its decimal equivalent
                case 'A':
                case 'a':
                    expectedResult[i] = 10;
                    break;
                case 'B':
                case 'b':
                    expectedResult[i] = 11;
                    break;
                case 'C':
                case 'c':
                    expectedResult[i] = 12;
                    break;
                case 'D':
                case 'd':
                    expectedResult[i] = 13;
                    break;
                case 'E':
                case 'e':
                    expectedResult[i] = 14;
                    break;
                case 'F':
                case 'f':
                    expectedResult[i] = 15;
                    break;
                default:
                    int conversion = getNumericValue(dataString.charAt(i)); // If it is not 10+, it just fetches the numerical value of the character
                    expectedResult[i] = (byte) conversion;

            }
        }
    return expectedResult; // Returns the final product
    }


    //Method 7
    public static String toRleString(byte[] rleData) { //Replaces the odd index's with its hexadecimal equivalent, adds a ":" every two numbers {15, 15, 6, 4} --> 15f:64
        String expectedOutputString = ""; //Empty string to be filled-in in loop
        String[] expectedOutput = new String[rleData.length]; //String[] with the length of rleData
        for (int i = 0; i < rleData.length; i++) {
            switch (rleData[i]) { //As it counts up, it replaces any byte integer into its string
                case 15:
                    expectedOutput[i] = "15";
                    break;
                case 14:
                    expectedOutput[i] = "14";
                    break;
                case 13:
                    expectedOutput[i] = "13";
                    break;
                case 12:
                    expectedOutput[i] = "12";
                    break;
                case 11:
                    expectedOutput[i] = "11";
                    break;
                case 10:
                    expectedOutput[i] = "10";
                    break;
                case 9:
                    expectedOutput[i] = "9";
                    break;
                case 8:
                    expectedOutput[i] = "8";
                    break;
                case 7:
                    expectedOutput[i] = "7";
                    break;
                case 6:
                    expectedOutput[i] = "6";
                    break;
                case 5:
                    expectedOutput[i] = "5";
                    break;
                case 4:
                    expectedOutput[i] = "4";
                    break;
                case 3:
                    expectedOutput[i] = "3";
                    break;
                case 2:
                    expectedOutput[i] = "2";
                    break;
                case 1:
                    expectedOutput[i] = "1";
                    break;
                case 0:
                    expectedOutput[i] = "0";
                    break;


            }
            if ( i % 2 == 1) {
                switch(rleData[i]) { //Switch statement where if i lands on an odd number, and it's an integer above 9, it replaces it into its hexadecimal format.
                    case 15:
                        expectedOutput[i] = "f";
                        break;
                    case 14:
                        expectedOutput[i] = "e";
                        break;
                    case 13:
                        expectedOutput[i] = "d";
                        break;
                    case 12:
                        expectedOutput[i] = "c";
                        break;
                    case 11:
                        expectedOutput[i] = "b";
                        break;
                    case 10:
                        expectedOutput[i] = "a";
                        break;
                }
            }

        }
        for (int j = 0; j < rleData.length; j++) { //This takes the empty string made earlier and puts in all of what was in the String[].
            expectedOutputString = expectedOutputString + expectedOutput[j];
            if ( j % 2 == 1) { //This checks if the i lands on an odd number, it will add an ":" after it.
                expectedOutputString += ":";
            }

        }
        if (expectedOutputString.endsWith(":")) // This removes the ":" at the end of the last run.
            expectedOutputString = expectedOutputString.substring(0, expectedOutputString.length() - 1);

        return expectedOutputString; } // Returns the final product



    // Method 8 // Does the inverse of method 7, takes the RLE format and turns it back into a byte array. ("15f:64) --> {15, 15, 6, 4}
    public static byte[] stringToRle(String rleString) {

        int counter = 0; //counter to count up once after every conversion
        String [] arrayNoColon = rleString.split(":"); //removes the colon, makes a String[] without the ":"
        byte [] expectedOutput = new byte[2 * arrayNoColon.length]; //byte[] that doubles the length of arrayNoColon, it will have the necessary amount of space

        for (int i = 0; i < arrayNoColon.length; i++) { //cycles through each iteration making a change

            String bothHalves = arrayNoColon[i]; // String that will have all the first characters including up to the :
            int lengthOfBothHalves = bothHalves.length(); //Integer to keep track of the length of bothHalves
            String onlyOneHalves = bothHalves.substring(0, (-1 + lengthOfBothHalves)); //String that will be like bothHalves, except the 3rd index is removed
            int valueConversionCharToInt = Integer.valueOf(onlyOneHalves); // Takes onlyOneHaves and converts from char to int
            expectedOutput[counter] = (byte) valueConversionCharToInt; // Puts the converted chars into expectedOutput
            int convertHex = bothHalves.charAt(-1 + lengthOfBothHalves);

            switch (convertHex) { // switch statement to convert hexadecimal into decimal
                case 'f':
                case 'F':
                    valueConversionCharToInt = 15;
                    break;
                case 'e':
                case 'E':
                    valueConversionCharToInt = 14;
                    break;
                case 'd':
                case 'D':
                    valueConversionCharToInt = 13;
                    break;
                case 'c':
                case 'C':
                    valueConversionCharToInt = 12;
                    break;
                case 'b':
                case 'B':
                    valueConversionCharToInt = 11;
                    break;
                case 'a':
                case 'A':
                    valueConversionCharToInt = 10;
                    break;
                default:  // if the value is not 10+
                    valueConversionCharToInt = getNumericValue(bothHalves.charAt(-1 + lengthOfBothHalves));
            }
            counter = counter + 1; // adds 1 to the counter before assigning the value to the index of counter's value

            expectedOutput[counter] = (byte)valueConversionCharToInt;

            counter = counter + 1; // after assigning the value, counter is added by 1.
        }

    return expectedOutput; }  // Returns the final product
}