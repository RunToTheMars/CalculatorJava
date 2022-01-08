import Exceptions.*;
import Calculator.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    public static void Do_Tests() {
        assertEquals(3., Calculator.Calculate("1 + 2").Data());
        assertEquals(9., Calculator.Calculate("(1 + 2) * 3").Data ());
        assertEquals(7., Calculator.Calculate("1 + 2 * 3").Data ());
        assertEquals(2.25, Calculator.Calculate("1.5 * 1.5").Data ());
        assertEquals(210., Calculator.Calculate("10.5 * 100 / 5").Data ());

        try {
            Calculator.Calculate("2 + (1+2))");
        } catch (RuntimeException e) {
            // Missing operator before left bracket
            assertTrue(e instanceof Exceptions.InconsistentNumberOfBracketsException);
        }
        try {
            Calculator.Calculate("(A + 1)");
        } catch (RuntimeException e) {
            // Operator at start of expression
            assertTrue(e instanceof Exceptions.InvalidTokenException);
        }
        try {
            Calculator.Calculate("1++");
        } catch (RuntimeException e) {
            // Two operators in a row
            assertTrue(e instanceof Exceptions.InvalidOperatorImplement);
        }
        try {
            Calculator.Calculate("Hello World");
        } catch (RuntimeException e) {
            assertTrue(e instanceof Exceptions.InvalidTokenException);
        }

    }
}
