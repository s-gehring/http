package http.v1_1;

import org.junit.Test;

public class FindExclusions {

    @Test
    public void test() {
        Character[] toGet = {
                '[', ']', '\\', '\r'
        };
        for (Character x : toGet) {
            System.out.println("Char " + x + " = " + (int) x + " = 0x" + Integer.toHexString(x));
        }
    }
}
