package com.example.nicholas.sunshine.app.data;

import android.test.AndroidTestCase;

public class TestPractice extends AndroidTestCase {
    /*
        This gets run before every test.
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testThatDemonstratesAssertions() throws Throwable {
        int a = 5;
        int b = 3;
        int c = 5;
        int d = 10;

        assertEquals("X should be equal", a, c);
        assertTrue("Y should be true", d > a);
        assertFalse("Z should be false", a == b);

        if (b > d) {
            fail("XX should never happen");
        }
    }

    public void testThisYo() throws Throwable
    {
        int j = 2;
        int k = 5;
        int l = 90;
        int m = 56;

        assertTrue("Should be true", j < m);
        assertFalse("Should be false", m <= k);

        if (l < m)
        {
            fail("Should never have reached here");
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
