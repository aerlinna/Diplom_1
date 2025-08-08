package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {

    private final String n;
    private final float p;
    private final boolean valid;

    public BunTest(String n, float p, boolean valid) {
        this.n = n;
        this.p = p;
        this.valid = valid;
    }

    @Parameterized.Parameters(name = "{0}, {1}, valid={2}")
    public static Object[][] data() {
        return new Object[][]{
                {"black bun", 100f, true},
                {"white bun", 200f, true},
                {"red bun", 300.5f, true},
                {"mini bun", 0f, true},
                {"super bun", -50f, false}
        };
    }

    @Test
    public void nameShouldMatch() {
        assertEquals(n, new Bun(n, p).getName());
    }

    @Test
    public void priceShouldMatch() {
        assertEquals(p, new Bun(n, p).getPrice(), 0f);
    }

    @Test
    public void priceValidityCheck() {
        if (valid) {
            assertTrue("Цена должна быть неотрицательной", new Bun(n, p).getPrice() >= 0);
        } else {
            assertTrue("Цена должна быть отрицательной", new Bun(n, p).getPrice() < 0);
        }
    }
}
