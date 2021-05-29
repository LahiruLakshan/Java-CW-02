import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void dateValidate() {
        assertTrue(new Date().dateValidate("20/12/2020"));
        assertTrue(new Date().dateValidate("20/1/2020"));
        assertFalse(new Date().dateValidate("2020/12/01"));
        assertFalse(new Date().dateValidate("2020/12/1"));
        assertFalse(new Date().dateValidate("45"));
        assertFalse(new Date().dateValidate("date"));
    }
}