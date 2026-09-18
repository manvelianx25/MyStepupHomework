package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumToNTest {
    @Test
    public void testSumToN() {
        assertEquals(0, BasicUtils.sumToN(0));
        assertEquals(1, BasicUtils.sumToN(1));
        assertEquals(3, BasicUtils.sumToN(2));
        assertEquals(15, BasicUtils.sumToN(5));
        assertEquals(5050, BasicUtils.sumToN(100));
    }
}
