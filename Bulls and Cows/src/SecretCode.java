import java.util.HashMap;
import java.util.Map;

public abstract class SecretCode {
    public String decipheredCode;

    abstract void generateCode();

    abstract Map<String,Integer> makeGuess(String userInput) throws IllegalArgumentException;

     protected Map<String,Integer> getBullsAndCows(String userInput) {
        char[] inputArray = userInput.toCharArray();
        char[] codeArray = decipheredCode.toCharArray();

        int numberOfBulls=0;
        int numberOfCows=0;

        for(int i=0; i< inputArray.length;i++){
            if(inputArray[i]==codeArray[i]){
                numberOfBulls++;
            }
        }

         Map<String, Integer> dictionary = new HashMap<>();

         dictionary.put("Bulls",numberOfBulls);
         dictionary.put("Cows",numberOfCows);

         return dictionary;
    }
}
