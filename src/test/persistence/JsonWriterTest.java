package persistence;

import model.SkincareProduct;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import model.Routine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// write monday morning
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Routine r = new Routine("Monday", "Morning");
            JsonWriter writer = new JsonWriter("./data/\0doesntexist.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRoutine() {
        try {
            Routine r = new Routine("Monday", "Morning");
            JsonWriter writer = new JsonWriter("./data/JsonTestEmptyRoutine.json");
            writer.open();
            writer.writeRoutine(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/JsonTestEmptyRoutine.json");
            r = reader.read();
            assertEquals("Monday", r.getDayOfWeek());
            assertEquals("Morning", r.getTimeOfDay());
            assertEquals(0, r.getRoutineSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRoutine() {
        try {
            Routine r = new Routine("Tuesday", "Night");
            r.addProduct("Innisfree", "Cherry Blossom Cleanser", "Water Cleanser");
            r.addProduct("Cerave", "Ceramides Moisturizer", "Moisturizer");
            JsonWriter writer = new JsonWriter("./data/JsonTest.json");
            writer.open();
            writer.writeRoutine(r);
            writer.close();

            JsonReader reader = new JsonReader("./data/JsonTest.json");
            r = reader.read();
            assertEquals("Tuesday", r.getDayOfWeek());
            assertEquals("Night", r.getTimeOfDay());
            List<SkincareProduct> products = r.getRoutine();
            assertEquals(2, products.size());
            checkProduct("Innisfree", "Cherry Blossom Cleanser", "Water Cleanser", products.get(0));
            checkProduct("Cerave", "Ceramides Moisturizer", "Moisturizer", products.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

