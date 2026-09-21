package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlastOffTest {
    @Test
    public void testBlastOff() {
        assertEquals("5 4 3 2 1 Поехали!", BasicUtils.blastOff(5));
        assertEquals("1 Поехали!", BasicUtils.blastOff(1));
        assertEquals("Поехали!", BasicUtils.blastOff(0));
        assertEquals("Поехали!", BasicUtils.blastOff(-3));
    }
}
