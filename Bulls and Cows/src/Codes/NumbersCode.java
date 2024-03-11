package Codes;

import Codes.SecretCode;

import java.util.*;

public class NumbersCode extends SecretCode {

    @Override
    public void generateCode(){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < 4; i++){
            result.append(numbers.get(i).toString());
        }

        decipheredCode = result.toString();
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
