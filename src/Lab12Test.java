import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lab12Test {
    Parser p = new Parser();

    @Test
    void nonInvalidTest() throws SyntaxError {
        assertEquals(42.0, p.calculator("12+30", 1));
        assertEquals(42.0, p.calculator("12 +30", 1));
        assertEquals(42.0, p.calculator("12+ 30", 1));
        assertEquals(1.0, p.calculator("1/1         ", 1));
        assertEquals(2000.0, p.calculator("(10+10)*(10*10)", 1));
        assertEquals(0.0, p.calculator("(10 -10)*(10*10)", 1));
        assertEquals(0.0, p.calculator("(10+10) *(0  *10)", 1));
    }

    @Test
    void invalidTest() {
        assertThrows(ArithmeticException.class, () -> p.calculator("0/0", 1));
        assertThrows(SyntaxError.class, () -> p.calculator("1234 5 +", 1));
        assertThrows(SyntaxError.class, () -> p.calculator("555+", 1));
    }
}