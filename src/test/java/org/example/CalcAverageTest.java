package org.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CalcAverageTest {

    @Test
    public void testCalcAverage() {
        assertEquals(3.0, BasicUtils.calcAverage(List.of(1, 2, 3, 4, 5)), 1e-9);
        assertEquals(10.0, BasicUtils.calcAverage(List.of(10)), 1e-9);
        // пустой список
        assertEquals(0.0, BasicUtils.calcAverage(List.of()), 1e-9);
        // null
        assertEquals(0.0, BasicUtils.calcAverage(null), 1e-9);
        // отрицательные числа
        assertEquals(-2.0, BasicUtils.calcAverage(List.of(-5, 1)), 1e-9);

        // Для больших чисел: проверяем, что нет переполнения
        List<Integer> bigList = List.of(Integer.MAX_VALUE, Integer.MAX_VALUE);
        double expected = Integer.MAX_VALUE;
        assertEquals(expected, BasicUtils.calcAverage(bigList), 1e-9);
    }
}
