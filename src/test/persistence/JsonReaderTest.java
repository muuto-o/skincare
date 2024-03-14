package persistence;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import model.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// read monday morning
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/doesn'texist.json");
        try {
            Routine r = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRoutine() {
        JsonReader reader = new JsonReader("./data/JsonTestEmptyRoutine.json");
        try {
            Routine r = reader.read();
            assertEquals("Monday", r.getDayOfWeek());
            assertEquals("Morning", r.getTimeOfDay());
            assertEquals(0, r.getRoutineSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneral() {
        JsonReader reader = new JsonReader("./data/JsonTest.json");
        try {
            Routine r = reader.read();
            assertEquals("Tuesday", r.getDayOfWeek());
            assertEquals("Night", r.getTimeOfDay());
            List<SkincareProduct> products = r.getRoutine();
            assertEquals(2, products.size());
            checkProduct("Innisfree", "Cherry Blossom Cleanser", "Water Cleanser", products.get(0));
            checkProduct("Cerave", "Ceramides Moisturizer", "Moisturizer", products.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
