package persistence;

import model.SkincareProduct;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkProduct(String brand, String type, String name, SkincareProduct p) {
        assertEquals(brand, p.getProductBrand());
        assertEquals(type, p.getProductType());
        assertEquals(name, p.getProductName());
    }
}

