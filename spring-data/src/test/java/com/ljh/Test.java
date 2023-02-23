package com.ljh;

import java.lang.reflect.Field;

/**
 * Test
 *
 * @author HP
 * @since 2023/2/13 15:21
 */
public class Test {

    static class X {
        private void accessY() throws NoSuchFieldException, IllegalAccessException {
            Y y = new Y();
            y.y = 1;

            Field field = Y.class.getDeclaredField("y");
            // field.setAccessible(true);
            field.setInt(y, 2);

            System.out.println(y.y);
        }
    }

    static class Y {
        private int y;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        X x = new X();
        x.accessY();
    }
}
