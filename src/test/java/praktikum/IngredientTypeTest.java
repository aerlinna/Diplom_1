package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private final IngredientType t;

    public IngredientTypeTest(IngredientType t) {
        this.t = t;
    }

    @Parameterized.Parameters(name = "Тип: {0}")
    public static Object[][] data() {
        return Arrays.stream(IngredientType.values())
                .map(x -> new Object[]{x})
                .toArray(Object[][]::new);
    }

    @Test
    public void valOf() {
        assertEquals(t, IngredientType.valueOf(t.name()));
    }

    @Test
    public void toStr() {
        assertEquals(t.name(), t.toString());
    }

    @Test
    public void hasAll() {
        assertTrue(Arrays.asList(IngredientType.values()).contains(IngredientType.SAUCE));
        assertTrue(Arrays.asList(IngredientType.values()).contains(IngredientType.FILLING));
        assertEquals(2, IngredientType.values().length);
    }
}
