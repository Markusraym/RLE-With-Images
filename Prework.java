public class Prework {



    // 1.
    //input: {5, 12, 8, 5, 3, 11, 7, 2, 3, 16, 4}
    //output: {30, 46}
    //This adds up all the elements at even indices and adds up all the elements in odd indices.

    public static int[] sumByParity(int[] arr) {
        int[] res = new int[2]; //this is the result array we should get. its [2] cause it should be {30, 46}
        // check if its at an even index or odd index
        for (int i =0; i < arr.length; i++) {

            if (i % 2 == 0) //This is even index
                res[0] += arr[i]; //will add all of the even elements of the array to the first index of new array
            else  //we are at odd indices
                res[1] += arr[i];
        }
        return res;
    }



    // 2. useful for method 2 & 3
    //input: {3,2,5,4,4,4,5,5,5}, output: false
    //input: {3,2,5,4,4,4,5,5,5,5} output: true
    //Checks an array and returns a true value if the array has at least 4 of the same numbers consecutively in a row
    public static boolean consecutiveFours(int[] arr) {
        int count = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) { // This checks if the first index has the same number as the second index
               count++; //If two same values in a sequence, it adds one to the count.
            }
            else {
                count = 1; //If the next two don't match, the count is reset back to 1. It needs to get up to 4 before resetting to return a true value.
            }
            if (count >= 4)
                return true;
        }

        return false;
    }


    //3.
    //input: {2, 1, 3}
    //output: {0, 0, 1, 2, 2, 2}
    //Expands the array. The value in the original array represents how many times that index will appear in the new array
    public static int[] expandByIndex(int[] arr) {
        // size of the output: if you add up all the elements in the input you get the length of the output
        int size = 0;
        int index = 0;
        for (int item : arr) { // for each loop, goes through each element one at a time and adds it
            size += item;
        }
        int[] res = new int[size]; //a new array that will be the size that was just found
        for (int i = 0; i < arr.length; i++) {
            int value = i; // this is the value that is at the current index, i.
            int repeats = arr[i]; // this is the value that shows how many times to repeat
            for (int j = 0; j < repeats; j++ ) {
                res[index] = value;
                index++;
            }
        }
        return res;
    }

    //4. Equivalent for method 6
    // input: "abcd3fgh1"
    // output: 2
    // Reads a string and counts how many numbers are in the string, ignoring any letters
    public static int numericalCount(String string) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt((i))))
                count++;
        }
        return count;
        }

    }
