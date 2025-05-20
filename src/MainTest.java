import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testAddSimpleSum() {
        assertEquals(3, Main.Add("1,2"));
    }

    @Test
    public void testAddCustomSemicolonDelimiter() {
        assertEquals(4, Main.Add("//;\n1;3"));
    }

    @Test
    public void testAddCustomDelimiter() {
        assertEquals(6, Main.Add("//|\n1|2|3"));
    }

    @Test
    public void testAddTextDelimiter() {
        assertEquals(7, Main.Add("//sep\n2sep5"));
    }

    @Test
    public void testAddSeparatorError() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> Main.Add("1,2,"));
        assertTrue(ex.getMessage().contains("Separators are not allowed at the end of String"));
    }

    @Test
    public void testAddErrorUnexpectedDelimiterMixedError() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> Main.Add("//|\n1|2,3"));
        assertTrue(ex.getMessage().contains("'|' expected but ',' found"));
    }

    @Test
    public void testAddErrorSingleNegativeNumber() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> Main.Add("1,-2"));
        assertTrue(ex.getMessage().contains("Negative number(s) not allowed: -2"));
    }

    @Test
    public void testAddErrorMultipleNegatives() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> Main.Add("2,-4,-9"));
        assertTrue(ex.getMessage().contains("Negative number(s) not allowed: -4, -9"));
    }

    @Test
    public void testAddErrorCustomDelimiterAndNegative() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> Main.Add("//|\n1|2,-3"));
        assertTrue(ex.getMessage().contains("Negative number(s) not allowed: -3"));
        assertTrue(ex.getMessage().contains("'|' expected but ',' found"));
    }

    @Test
    public void testAddIgnoreNumbersGreaterThanThousand() {
        assertEquals(2, Main.Add("1001,2"));
    }
}