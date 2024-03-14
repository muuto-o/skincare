package persistence;

import org.json.JSONObject;

public interface Writable { // trevor says just to copy format teehee
    // EFFECTS: returns this as JSON object
    JSONObject toJson();

    // JSON CITATION: formatting for persistence borrowed from JsonSerializationDemo provided by UBC CPSC210 staff.
}


