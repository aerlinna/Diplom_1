package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTest {

    private final String bName;
    private final float bPrice;
    private final String i1Name;
    private final float i1Price;
    private final IngredientType i1Type;
    private final String i2Name;
    private final float i2Price;
    private final IngredientType i2Type;

    public BurgerTest(String bName, float bPrice,
                      String i1Name, float i1Price, IngredientType i1Type,
                      String i2Name, float i2Price, IngredientType i2Type) {
        this.bName = bName;
        this.bPrice = bPrice;
        this.i1Name = i1Name;
        this.i1Price = i1Price;
        this.i1Type = i1Type;
        this.i2Name = i2Name;
        this.i2Price = i2Price;
        this.i2Type = i2Type;
    }

    @Parameterized.Parameters(name = "{0} + {2} + {5}")
    public static Object[][] data() {
        return new Object[][]{
                {"black bun", 100f, "hot sauce", 50f, IngredientType.SAUCE, "cutlet", 150f, IngredientType.FILLING},
                {"white bun", 200f, "sour cream", 10f, IngredientType.SAUCE, "sausage", 25f, IngredientType.FILLING}
        };
    }

    @Test
    public void price_ok() {
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bPrice);

        Ingredient ing1 = mock(Ingredient.class);
        when(ing1.getPrice()).thenReturn(i1Price);

        Ingredient ing2 = mock(Ingredient.class);
        when(ing2.getPrice()).thenReturn(i2Price);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        float expected = bPrice * 2 + i1Price + i2Price;
        assertEquals(expected, burger.getPrice(), 0.0001f);
    }

    @Test
    public void move_ok() {
        Ingredient ing1 = mock(Ingredient.class);
        Ingredient ing2 = mock(Ingredient.class);

        Burger burger = new Burger();
        burger.setBuns(mock(Bun.class));
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        burger.moveIngredient(0, 1);

        assertEquals(2, burger.ingredients.size());
        assertEquals(Arrays.asList(ing2, ing1), burger.ingredients);
    }

    @Test
    public void remove_ok() {
        Ingredient ing1 = mock(Ingredient.class);
        Ingredient ing2 = mock(Ingredient.class);

        Burger burger = new Burger();
        burger.setBuns(mock(Bun.class));
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ing2, burger.ingredients.get(0));
    }

    @Test
    public void receipt_ok() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn(bName);
        when(bun.getPrice()).thenReturn(bPrice);

        Ingredient ing1 = mock(Ingredient.class);
        when(ing1.getName()).thenReturn(i1Name);
        when(ing1.getPrice()).thenReturn(i1Price);
        when(ing1.getType()).thenReturn(i1Type);

        Ingredient ing2 = mock(Ingredient.class);
        when(ing2.getName()).thenReturn(i2Name);
        when(ing2.getPrice()).thenReturn(i2Price);
        when(ing2.getType()).thenReturn(i2Type);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains(bName));
        assertTrue(receipt.contains(i1Name));
        assertTrue(receipt.contains(i2Name));

        float total = bPrice * 2 + i1Price + i2Price;
        String totalStr = String.format("%.6f", total).replace('.', ',');

        assertTrue(receipt.contains(totalStr));
    }
}
