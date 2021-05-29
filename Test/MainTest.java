import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void nameValidation() {
        assertTrue(Main.nameValidation("lahiru"));
        assertFalse(Main.nameValidation("45"));
    }


    @Test
    void checkAge() {
        assertTrue(Main.checkAge(70));
        assertFalse(Main.checkAge(50));
        assertFalse(Main.checkAge(-10));
    }

    @Test
    void checkWeight() {
        assertTrue(Main.checkWeight(80));
        assertFalse(Main.checkWeight(-12));
        assertFalse(Main.checkWeight(5));
    }
}
