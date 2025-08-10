package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {

    private final String n;
    private final float p;

    public BunTest(String n, float p) {
        this.n = n;
        this.p = p;
    }

    @Parameterized.Parameters(name = "{0}, {1}")
    public static Object[][] data() {
        return new Object[][]{
                {"black bun", 100f},
                {"white bun", 200f},
                {"red bun", 300.5f},
                {"mini bun", 0f},
                {"super bun", -50f}
        };
    }

    @Test
    public void name_should_match() {
        assertEquals(n, new Bun(n, p).getName());
    }

    @Test
    public void price_should_match() {
        assertEquals(p, new Bun(n, p).getPrice(), 0f);
    }
}
