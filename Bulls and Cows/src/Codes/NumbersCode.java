package Codes;

import Codes.SecretCode;

import java.util.Map;
import java.util.Random;

public class NumbersCode extends SecretCode {

    @Override
    public void generateCode(){
        Random random = new Random();
        int code = random.nextInt(10000);
        decipheredCode = String.format("%04d", code);
    }

    @Override
    public Map<String,Integer> makeGuess(String userInput){
        for (char c : userInput.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Input is not a number");
            }
        }

        return super.getBullsAndCows(userInput);
    }
}
