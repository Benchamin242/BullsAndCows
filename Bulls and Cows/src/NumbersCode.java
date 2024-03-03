import java.util.Random;

public class NumbersCode extends SecretCode{

    @Override
    public void generateCode(){
        Random random = new Random();
        int code = random.nextInt(10000);
        decipheredCode = String.format("%04d", code);
    }

    @Override
    public void makeGuess(String userInput){
        try {
            if (userInput.length() < 4){
                throw new RuntimeException("Guess needs to be of length 4");
            }

            Integer.parseInt(userInput);
            System.out.printf("You entered: %s%n",userInput);
        } catch (NumberFormatException e) {
             throw new RuntimeException("Input is not a number");
        }

        char[] inputArray = userInput.toCharArray();
        char[] codeArray = decipheredCode.toCharArray();

        int numberOfBulls=0;
        int numberOfCows=0;

        for(int i=0; i< inputArray.length;i++){
            if(inputArray[i]==codeArray[i]){
                numberOfBulls++;
            }
        }

        System.out.printf("Number of bulls: %s%n",numberOfBulls);
    }
}
