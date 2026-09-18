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

        // Проверка, что исходный массив не изменился (важно для чистоты теста)
        String[] original = {"A", "B", "C"};
        String[] copy = Arrays.copyOf(original, original.length);
        original = BasicUtils.reverse(original);
        // должны быть равны — оригинал не тронут
        assertArrayEquals(copy, original);
    }
}
