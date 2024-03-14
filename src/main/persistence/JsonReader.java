package persistence;

import model.Event;
import model.EventLog;
import model.SkincareProduct;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ui.RoutinesOverview;
import model.Routine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Routine read() throws IOException, FileNotFoundException, JSONException {  /// RoutinesOverview not void
        // System.out.println("file read");
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRoutine(jsonObject); // instead of workroom in model class,
    }                                             // im returning the Ui as an object..?


    // EFFECTS: reads source file as string and returns it. ie. reads JSON_SAVE
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // in between method: parse allRoutines()? as well as addRoutines// addRoutine?
    // EFFECTS: parses the Routine from JSON object and returns it
    private Routine parseRoutine(JSONObject jsonObject) throws FileNotFoundException {
        String dayOfWeek = jsonObject.getString("dayOfWeek");
        String timeOfDay = jsonObject.getString("timeOfDay"); // jsonObject = RoutinesOverview
        Routine r = new Routine(dayOfWeek, timeOfDay);
        addProducts(r, jsonObject);
        return r;
    }

    private void addProducts(Routine r, JSONObject jsonObject) { //jsonobject is Routines
        JSONArray jsonArray = jsonObject.getJSONArray("skincareroutine");
        for (Object json : jsonArray) {
            JSONObject nextProduct = (JSONObject) json; // selects routine. eg "monday morning"
            addProduct(r, nextProduct); // next layer is add routine.
        }
    }

    // MODIFIES: or
    // EFFECTS: parses a routine from JSON object and adds it to RoutinesOverview to view
    private void addProduct(Routine r, JSONObject jsonObject) { //jsonobject is a routine
        String brand = jsonObject.getString("productBrand");
        String type = jsonObject.getString("productType");
        String name = jsonObject.getString("productName");
        //SkincareProduct product = new SkincareProduct(brand, type, name); // not necessary?
        //HOPEFULLY THIS IS FINE??
        r.addProduct(brand, type, name); //?
    }
}

