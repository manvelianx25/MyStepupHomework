package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class IsPositiveTest {

    @Test
    public void isPositiveTest() {
        // Проверка позитивных чисел
        assertTrue(BasicUtils.isPositive(1));
        assertTrue(BasicUtils.isPositive(0));

        // Проверка отрицательных чисел
        assertFalse(BasicUtils.isPositive(-1));
        assertFalse(BasicUtils.isPositive(-100));
    }
}
