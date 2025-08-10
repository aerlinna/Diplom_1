package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType t;
    private final String n;
    private final float p;

    public IngredientTest(IngredientType t, String n, float p) {
        this.t = t;
        this.n = n;
        this.p = p;
    }

    @Parameterized.Parameters(name = "{0} {1} {2}")
    public static Object[][] data() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100f},
                {IngredientType.SAUCE, "sour cream", 0f},
                {IngredientType.FILLING, "cutlet", 150f},
                {IngredientType.FILLING, "dinosaur", -50f}
        };
    }

    @Test
    public void getT() {
        assertEquals(t, new Ingredient(t, n, p).getType());
    }

    @Test
    public void getN() {
        assertEquals(n, new Ingredient(t, n, p).getName());
    }

    @Test
    public void getP() {
        assertEquals(p, new Ingredient(t, n, p).getPrice(), 0f);
    }
}
