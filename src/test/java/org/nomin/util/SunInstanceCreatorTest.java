package org.nomin.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests SunInstanceCreator.
 * @author Dmitry Dobrynin
 *         Date: 26.10.2010 Time: 23:04:00
 */
public class SunInstanceCreatorTest {

    @Test (expected = InstantiationException.class)
    public void testUsingClassNewInstance() throws IllegalAccessException, InstantiationException {
        WithoutDefaultConstructor.class.newInstance();
    }

    @Test
    public void testUsingInstanceCreator() throws Exception {
        WithoutDefaultConstructor wdc = new SunInstanceCreator().create(WithoutDefaultConstructor.class);
        Assert.assertNotNull(wdc);
        Assert.assertNull(wdc.name);
    }

    static class WithoutDefaultConstructor {
        public final String name;

        public WithoutDefaultConstructor(String name) {
            this.name = name;
            System.out.println("Hello, " + name);
        }
    }
}
