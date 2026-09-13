package org.example;

import org.junit.Test;
import static org.junit.Assert.*;


public class IsEvenTest {
    @Test
    public void testEven() {
        // 1. Проверка четных чисел
        assertTrue(BasicUtils.isEven(6));
        assertTrue(BasicUtils.isEven(0));
        assertTrue(BasicUtils.isEven(-2));

        // 2. Проверка нечетных чисел
        assertFalse(BasicUtils.isEven(3));
        assertFalse(BasicUtils.isEven(5));
        assertFalse(BasicUtils.isEven(-1));
    }
}
