package Tests;

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import Codes.*;

public class CodesTest {

    @Test
    public void testGenerateLetterCode(){
        SecretCode code = new LettersCode("WordList.csv");

        assertNotNull(code);

        code.generateCode();

        assertNotNull(code.decipheredCode);
    }

    @Test
    public void testGenerateLetterCodesWithoutFile(){
        SecretCode code = new LettersCode("Does not exits.csv");

        assertNotNull(code);

        code.generateCode();

        assertNull(code.decipheredCode);
    }

    @Test
    public void testGenerateNumberCode(){
        SecretCode code = new NumbersCode();

        assertNotNull(code);

        code.generateCode();

        assertNotNull(code.decipheredCode);
    }
}
