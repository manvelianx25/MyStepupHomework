package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class HasBugTest {
    @Test
    public void testHasBug() {
        // Базовый случай
        assertTrue(BasicUtils.hasBug(new String[]{"info", "bug", "warn"}));
        assertTrue(BasicUtils.hasBug(new String[]{"BUG"}));
        assertTrue(BasicUtils.hasBug(new String[]{"BuG"}));

        // Нет бага
        assertFalse(BasicUtils.hasBug(new String[]{"info", "warn", "error"}));
        assertFalse(BasicUtils.hasBug(new String[0]));

        // Граничные случаи
        assertFalse(BasicUtils.hasBug(null)); // массив равный null

        String[] withNull = new String[]{"info", null, "bug"};
        assertTrue(BasicUtils.hasBug(withNull));

        String[] onlyNulls = new String[]{null, null};
        assertFalse(BasicUtils.hasBug(onlyNulls));
    }
}
