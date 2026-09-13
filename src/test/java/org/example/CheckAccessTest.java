package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckAccessTest {

    @Test
    public void testCheckAccess() {
        assertEquals("Allow", BasicUtils.checkAccess(19));
        assertEquals("Allow", BasicUtils.checkAccess(75));
        assertEquals("Denied", BasicUtils.checkAccess(18));
        assertEquals("Denied", BasicUtils.checkAccess(3));
    }
}
