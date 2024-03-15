package Tests;

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import Codes.*;

import java.io.IOException;

/*
public class CodesTest {

    @Test
    public void testGenerateLetterCode() throws IOException{
        SecretCode code = new LettersCode("WordList.csv");

        assertNotNull(code);

        code.generateCode();

        assertNotNull(code.decipheredCode);
    }

    @Test
    public void testGenerateLetterCodesWithoutFile(){
        SecretCode code = new LettersCode("Does not exits.csv");

        assertNotNull(code);

        assertThrows(IOException.class,()-> code.generateCode());

        assertNull(code.decipheredCode);
    }

    @Test
    public void testGenerateNumberCode() throws IOException {
        SecretCode code = new NumbersCode();

        assertNotNull(code);

        code.generateCode();

        assertNotNull(code.decipheredCode);
    }
}
*/
