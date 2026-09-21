package org.example;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

public class ReverseTest {
    @Test
    public void testReverse() {
        // Обычный случай
        assertArrayEquals(new String[]{"Zero", "Two", "One"},
                BasicUtils.reverse(new String[]{"One", "Two", "Zero"}));

        // Один элемент
        assertArrayEquals(new String[]{"Solo"},
                BasicUtils.reverse(new String[]{"Solo"}));

        // Пустой массив
        assertArrayEquals(new String[0],
                BasicUtils.reverse(new String[0]));

        // null
        assertNull(BasicUtils.reverse(null));
    }
}
