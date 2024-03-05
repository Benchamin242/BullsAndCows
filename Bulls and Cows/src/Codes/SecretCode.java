package Codes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class SecretCode {
    public String decipheredCode;

    public abstract void generateCode();

    public abstract Map < String, Integer > makeGuess(String userInput) throws IllegalArgumentException;

    protected Map < String, Integer > getBullsAndCows(String userInput) {
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
                for(int j = 0; j < codeArray.length; j++){
                    if (!bullsIndexList.contains(j) & codeArray[j]==inputArray[i]) {
                        numberOfCows++;
                    }
                }
            }
        }

        Map < String, Integer > dictionary = new HashMap < > ();

        dictionary.put("Bulls", bullsIndexList.size());
        dictionary.put("Cows", numberOfCows);

        return dictionary;
    }
}