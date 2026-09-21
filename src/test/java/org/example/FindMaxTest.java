package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindMaxTest {
    @Test
    public void testFindMaxPositiveNumbers() {
        assertEquals(5, BasicUtils.findMax(new int[]{1, 3, 5, 2}));
    }

    @Test
    public void testFindMaxNegativeNumbers() {
        assertEquals(-2, BasicUtils.findMax(new int[]{-5, -2, -9, -3}));
    }

    @Test
    public void testFindMaxMixedNumbers() {
        assertEquals(10, BasicUtils.findMax(new int[]{-10, 0, 5, 10, -1}));
    }

    @Test
    public void testFindMaxSingleElement() {
        assertEquals(42, BasicUtils.findMax(new int[]{42}));
    }

    @Test
    public void testFindMaxAllSameValues() {
        assertEquals(7, BasicUtils.findMax(new int[]{7, 7, 7, 7}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMaxNullArray() {
        BasicUtils.findMax(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMaxEmptyArray() {
        BasicUtils.findMax(new int[]{});
    }
}
