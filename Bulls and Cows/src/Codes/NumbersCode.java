package Codes;

import java.util.Random;

public class NumbersCode extends SecretCode{
    public NumbersCode() {
        Random rand = new Random();
        super.deciferedString = "%05d".formatted(rand.nextInt(100000));
    }
}
