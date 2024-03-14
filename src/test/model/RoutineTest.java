package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//CLASS LEVEL COMMENT: test class for classes in the model package
class RoutineTest {
    // layout copied from lab5
    private Routine mondayMorning;
    private SkincareProduct product1;
    private SkincareProduct product2;
    private SkincareProduct product3;
    //private String inputbrand = "Innisfree";

    @BeforeEach
    public void setUp() {
        mondayMorning = new Routine("Monday", "Morning");
        product1 = new SkincareProduct("Innisfree", "Green Tea Cleanser", "Water-based Cleanser");
        product2 = new SkincareProduct("Garnier", "Micellar Water", "Oil-based Cleanser");
        product3 = new SkincareProduct("CeraVe", "Ceramides Moisturizer", "Moisturizer");
    }

    @Test
    public void emptyRoutineTestTrue() {
        assertEquals(0, mondayMorning.getRoutineSize());
        assertTrue(mondayMorning.emptyRoutine());
        // tests emptyRoutine and emptyRoutine Handler and getRoutineSize
    }

    @Test
    public void emptyRoutineTestFalse() {
        mondayMorning.addProduct("Innisfree", "Green Tea Cleanser", "Water-based Cleanser");
        assertFalse(mondayMorning.emptyRoutine());
        // tests emptyRoutine and emptyRoutine Handler and getRoutineSize
    }

    @Test
    public void addProductTest() {
        int beforeSize = mondayMorning.getRoutineSize();
        assertEquals(0, beforeSize);
        mondayMorning.addProduct("Innisfree", "Green Tea Cleanser", "Water-based Cleanser");
        assertEquals(1, mondayMorning.getRoutineSize());
        assertTrue(mondayMorning.getRoutine().contains(mondayMorning.selectProduct("Green Tea Cleanser")));
        // use getRoutine here and getRoutineSize
    }

    @Test
    public void addDuplicateProductTest() {
        mondayMorning.addProduct("Innisfree", "Green Tea Cleanser", "Water-based Cleanser");
        mondayMorning.addProduct("Innisfree", "Green Tea Cleanser", "Water-based Cleanser");
        assertEquals(2, mondayMorning.getRoutineSize());

        // use getRoutine here and getRoutineSize
    }

    @Test
    public void addMultipleProductTest() {
        int beforeSize = mondayMorning.getRoutineSize();
        assertEquals(0, beforeSize);
        mondayMorning.addProduct("Innisfree", "Green Tea Cleanser", "Water-based Cleanser");
        mondayMorning.addProduct("Innisfree", "Cherry Blossom Cleanser", "Water-based Cleanser");
        assertEquals(2, mondayMorning.getRoutineSize());

        // use getRoutine here and getRoutineSize
    }

    @Test
    public void selectProductTest() {
        // note that we are making COPIES essentially
        mondayMorning.addProduct(product1.getProductBrand(), product1.getProductName(), product1.getProductType());
        mondayMorning.addProduct(product2.getProductBrand(), product2.getProductName(), product2.getProductType());
        mondayMorning.addProduct(product3.getProductBrand(), product3.getProductName(), product3.getProductType());
        SkincareProduct selectedprod = mondayMorning.selectProduct(product3.getProductName());
        assertEquals(product3.getProductName(), selectedprod.getProductName());
    }

    @Test
    public void selectProductTestFail() { // product not found
        mondayMorning.addProduct(product1.getProductBrand(), product1.getProductName(), product1.getProductType());
        mondayMorning.addProduct(product2.getProductBrand(), product2.getProductName(), product3.getProductType());
        mondayMorning.addProduct(product3.getProductBrand(), product3.getProductName(), product3.getProductType());
        assertNull(mondayMorning.selectProduct(product3.getProductBrand())); // note that we are using "getProductBrand"
    }

    @Test
    public void selectProductIndexTestPass(){
        mondayMorning.addProduct(product1.getProductBrand(), product1.getProductName(), product1.getProductType());
        mondayMorning.addProduct(product2.getProductBrand(), product2.getProductName(), product2.getProductType());
        mondayMorning.addProduct(product3.getProductBrand(), product3.getProductName(), product3.getProductType());
        try {
            SkincareProduct selectedprod; // pointing to null currently
             selectedprod = mondayMorning.selectProductIndexBased("3");
             assertEquals(product3.getProductName(), selectedprod.getProductName());
        } catch (ArrayIndexOutOfBoundsException e) {
            fail("This is not supposed to happen");
        }
    }

    @Test
    public void selectProductIndexTestFail(){
        mondayMorning.addProduct(product1.getProductBrand(), product1.getProductName(), product1.getProductType());
        mondayMorning.addProduct(product2.getProductBrand(), product2.getProductName(), product2.getProductType());
        mondayMorning.addProduct(product3.getProductBrand(), product3.getProductName(), product3.getProductType());
        try {
            SkincareProduct selectedprod = mondayMorning.selectProductIndexBased("5");
        } catch (IndexOutOfBoundsException e) {
            // exception caught. all good
        }
    }

    @Test
    public void selectProductIndexTestFailLessThan0(){
        mondayMorning.addProduct(product1.getProductBrand(), product1.getProductName(), product1.getProductType());
        mondayMorning.addProduct(product2.getProductBrand(), product2.getProductName(), product2.getProductType());
        mondayMorning.addProduct(product3.getProductBrand(), product3.getProductName(), product3.getProductType());
        try {
            SkincareProduct selectedprod = mondayMorning.selectProductIndexBased("-2");
        } catch (IndexOutOfBoundsException e) {
            // exception caught. all good
        }
    }

    @Test
    public void deleteProductTestPass() {
        mondayMorning.addProduct("Innisfree", "Green Tea Cleanser", "Water-based Cleanser");
        assertEquals(1, mondayMorning.getRoutineSize());
        try {
            mondayMorning.deleteProduct(mondayMorning.selectProduct("Green Tea Cleanser"));
        } catch (ArrayIndexOutOfBoundsException e) {
            fail("This is not supposed to happen");
        }
        assertEquals(0, mondayMorning.getRoutineSize());
    }

    @Test // delete product off empty array. should expect exception
    public void deleteProductTestFail() {
        try {
            mondayMorning.deleteProduct(mondayMorning.selectProduct("Green Tea Cleanser"));
        } catch (ArrayIndexOutOfBoundsException e) {
            // if caught, all good
        }
    }

    @Test
    public void reorderProductTestPass() {
        mondayMorning.addProduct("Innisfree", "Green Tea Cleanser", "Water-based Cleanser");
        mondayMorning.addProduct("Innisfree", "Apple Seed Oil Cleanser", "Oil Cleanser");
        mondayMorning.addProduct("Innisfree", "Cherry Blossom Cleanser", "Cream Cleanser");
        SkincareProduct product = mondayMorning.selectProduct("Cherry Blossom Cleanser");

        try {
            mondayMorning.reorderProduct(product, "1");
        } catch (IndexOutOfBoundsException e) {
            fail("this is not supposed to happen");
        } // catch (StackOverflowError e){
//            fail("this is not supposed to happen");
//        }
        assertEquals(0, mondayMorning.getRoutine().indexOf(product));
    }

    @Test
    public void reorderProductTestArrayFail() { // should catch exception for arraylistexception
        mondayMorning.addProduct("Innisfree", "Green Tea Cleanser", "Water-based Cleanser");
        mondayMorning.addProduct("Innisfree", "Apple Seed Oil Cleanser", "Oil Cleanser");
        mondayMorning.addProduct("Innisfree", "Cherry Blossom Cleanser", "Cream Cleanser");
        SkincareProduct product = mondayMorning.selectProduct("Cherry Blossom Cleanser");

        try {
            mondayMorning.reorderProduct(product, "4");
        } catch (IndexOutOfBoundsException e) {
            // if caught, means all good
        } //catch (StackOverflowError e) {
//            fail("this is not supposed to happen");
//        }
    }

        @Test
        public void reorderProductTestArrayFailLessThan0 () { // should catch exception for arraylistexception
            mondayMorning.addProduct("Innisfree", "Green Tea Cleanser", "Water-based Cleanser");
            mondayMorning.addProduct("Innisfree", "Apple Seed Oil Cleanser", "Oil Cleanser");
            mondayMorning.addProduct("Innisfree", "Cherry Blossom Cleanser", "Cream Cleanser");
            SkincareProduct product = mondayMorning.selectProduct("Cherry Blossom Cleanser");

            try {
                mondayMorning.reorderProduct(product, "-1");
            } catch (IndexOutOfBoundsException e) {
                // if caught, means all good
            } // catch (StackOverflowError e){
//            fail("this is not supposed to happen");
//        }
        }

        @Test
        public void reorderProductEmptyArraylistFail () { // should catch exception for arraylistexception
            SkincareProduct product = mondayMorning.selectProduct("Cherry Blossom Cleanser");
            try {
                mondayMorning.reorderProduct(product, "4");
            } catch (IndexOutOfBoundsException e) {
                // if caught, means all good
            } //catch (StackOverflowError e){
//            fail("this is not supposed to happen");
//        }
        }

//    @Test
//    public void reorderProductStringNumberFail() {
//        mondayMorning.addProduct("Innisfree", "Green Tea Cleanser", "Water-based Cleanser");
//        mondayMorning.addProduct("Innisfree", "Apple Seed Oil Cleanser", "Oil Cleanser");
//        mondayMorning.addProduct("Innisfree", "Cherry Blossom Cleanser", "Cream Cleanser");
//        SkincareProduct product = mondayMorning.selectProduct("Cherry Blossom Cleanser");
//        try {
//            mondayMorning.reorderProduct(product,"four");
//        } catch (IndexOutOfBoundsException e) {
//            fail("this is not supposed to happen");
//        } catch (StackOverflowError e){
//            // if caught, means all good
//        }
//    }

        @Test
        public void setProductTest () { // for brand, name, and type
            product1.setProductBrand("LOONA Cosmetics");
            product1.setProductName("Love Cherry Moisturizer");
            product1.setProductType("Moisturizer");

            assertEquals("LOONA Cosmetics", product1.getProductBrand());
            assertEquals("Love Cherry Moisturizer", product1.getProductName());
            assertEquals("Moisturizer", product1.getProductType());
        }

        @Test
        public void getRoutineTiming () { // for brand, name, and type
            assertEquals("Monday", mondayMorning.getDayOfWeek());
            assertEquals("Morning", mondayMorning.getTimeOfDay());
        }

    }

