public abstract class SecretCode {
    public String decipheredCode;

    abstract void generateCode();

    abstract void makeGuess(String userInput);

    private void test(){}
}
