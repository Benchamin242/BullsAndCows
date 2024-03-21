package Codes;

import java.io.IOException;
import java.util.ArrayList;

public abstract class SecretCode {
    public String decipheredCode;
    public int currentNumOfBulls;
    public int currentNumOfCows;

    //abstract constructor is only used for read in, may as well just act as a field malloc
    public SecretCode(String code, int b, int c) {
        decipheredCode = code;
        currentNumOfBulls = b;
        currentNumOfCows = c;
    }


    public abstract void makeGuess(String userInput) throws IllegalArgumentException;


    protected void getBullsAndCows(String userInput) {
        if (userInput.length() != 8){
            throw new IllegalArgumentException("Guess needs to be of length 8");
        }

        char[] inputArray = userInput.toCharArray();
        char[] codeArray = decipheredCode.toCharArray();

        int numberOfCows = 0;
        ArrayList<Integer> bullsIndexList = new ArrayList<>();
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == codeArray[i]) {
                bullsIndexList.add(i);
            }
        }

        for (int i = 0; i < inputArray.length; i++) {
            if (!bullsIndexList.contains(i)) {
                for (int j = 0; j < codeArray.length; j++) {
                    if (!bullsIndexList.contains(j) & codeArray[j] == inputArray[i]) {
                        numberOfCows++;
                    }
                }
            }
        }

        currentNumOfBulls = bullsIndexList.size();
        currentNumOfCows = numberOfCows;
    }

    public String getCode() {
        return decipheredCode;
    }
}