import java.util.Random;

public class NumbersCode extends SecretCode{

    @Override
    public void generateCode(){
        Random random = new Random();
        int min=0;
        int max=9999;
        int code = random.nextInt(max - min + 1) + min;
        String paddedCode = String.format("%04d", code);
        decipheredString = paddedCode;
    }
}
