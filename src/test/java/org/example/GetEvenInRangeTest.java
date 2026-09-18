package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetEvenInRangeTest {
    @Test
    public void testGetEvenInRange() {
        assertEquals("2 4", BasicUtils.getEvenInRange(2, 5));
        assertEquals("2 4 6", BasicUtils.getEvenInRange(1, 6));
        assertEquals("", BasicUtils.getEvenInRange(3, 3));
        assertEquals("Error!", BasicUtils.getEvenInRange(5, 2));
        assertEquals("-2 0 2", BasicUtils.getEvenInRange(-3, 3));
        assertEquals("0", BasicUtils.getEvenInRange(0, 0));
    }
}
