package Codes;

import Codes.SecretCode;

import java.io.IOException;
import java.util.*;

public class NumbersCode extends SecretCode {

    public NumbersCode(int b, int c) {
        super(generateCode(), b, c);
    }

    //added for reading in codes
    public NumbersCode(String code, int b , int c) {
        super(code, b, c);
    }

    private static String generateCode(){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < 8; i++){
            result.append(numbers.get(i).toString());
        }

       return result.toString();
    }

    @Override
    public void makeGuess(String userInput){
        for (char c : userInput.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Input is not a number");
            }
        }

        super.getBullsAndCows(userInput);
    }
}
