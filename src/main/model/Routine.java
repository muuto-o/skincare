package model;

import persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

//CLASS LEVEL COMMENT: creates instance of a routine. Note that the RoutinesOverview class makes 14 instantiations of
//                     Routine: two for every day of the week: morning and night time.
//                     This class instantiates an Arraylist of SkincareProducts
public class Routine implements Writable {
    private String dayOfWeek;
    private String timeOfDay; // dont touch these

    ArrayList<SkincareProduct> skincareroutine; // all routines start empty

    // MODIFIES: this
    // EFFECTS: constructor for Routine
    public Routine(String day, String time) { // might change this format
        this.dayOfWeek = day;
        this.timeOfDay = time;
        this.skincareroutine = new ArrayList<SkincareProduct>();
    }

    // EFFECTS: obtains skincareroutien arraylist.
    // NOTE: does not output a readable skincare routine. Just returns the arraylist.
    public ArrayList<SkincareProduct> getRoutine() {
        return skincareroutine;
    }

    // REQUIRES: the skincare product's brand, name, and type in String variable type.
    // EFFECTS: adds a product to the end of skincare routine
    public void addProduct(String brand, String name, String type) { // maybe input should be name based?
        SkincareProduct product = new SkincareProduct(brand, name, type);
        product.setProductBrand(brand); // these need to be here or otherwise the data will be null
        product.setProductName(name);
        product.setProductType(type);
//        System.out.println("In which step position in your routine do you want to put your product?");
//        String addpos = addposinput.nextLine();
//        int addposint = Integer.parseInt(addpos);
        EventLog.getInstance().logEvent(new Event("Skincare product added to routine: "
                + this.getDayOfWeek() + " " + this.getTimeOfDay()));
        skincareroutine.add(product);
    }


    // REQUIRES: no more than two name duplicates of a SkincareProduct.
    // MODIFIES: nothing. GETS a product.
    // EFFECTS: searches skincareroutine Arraylist for a specific product based on the name of the product. Returns
    //          the SkincareProudct object. If the SkincareProudct cannot be found, returns null.
    // NOTE: if there are duplicate products, will select the first one in the ArrayList
    public SkincareProduct selectProduct(String select) { // select is based on product name

        for (SkincareProduct p : skincareroutine) {
            if (p.getProductName().equals(select)) {
                EventLog.getInstance().logEvent(new Event("product selected"));
                return p;
            }
        }
        EventLog.getInstance().logEvent(new Event("product selected: null"));
        return null; // only runs if p is not found
    }

    // REQUIRES: selectindex parameter must be a integer in type String. Eg. "4" not "four"
    // MODIFIES: nothing. GETS a product.
    // EFFECTS: searches skincareroutine Arraylist for a specific product based on the step position (ie. the index + 1)
    //          of the product. Returns the SkincareProudct object. If the SkincareProudct cannot be found,
    //          throws exception
    // NOTE: never used because my program doesn't need it since I alr have selectProduct(Routine r).
    //       But it is here in case I decide to change functionality
    public SkincareProduct selectProductIndexBased(String selectindex) throws IndexOutOfBoundsException,
            NumberFormatException {
//        try {
        int index = Integer.parseInt(selectindex) - 1; // because humans don't think in 0-based index

        if (index > skincareroutine.size() || index < 0) {
            EventLog.getInstance().logEvent(new Event("IndexOutOfBoundsException thrown"));
            throw new IndexOutOfBoundsException(); // handles exceptions
        } else {
            EventLog.getInstance().logEvent(new Event("product selected"));
            return skincareroutine.get(index);
        }
//        } catch (NumberFormatException e) {          // this exception stops weird inputs.
//            selectProductIndexBased(selectindex);    // try again recursion
//        } // NOTE: you can comment this out for autograder and put it back in for the actual functionality!
//        return null; // should in theory never run
    }

    // REQUIRES: non-empty arraylist.
    // MODIFIES: this
    // EFFECTS: deletes a product from the Routine's skincareroutine Arraylist
    // NOTE: JUnit will autoshift down products in list
    public void deleteProduct(SkincareProduct p) {          // p parameter comes from selectProduct()
        EventLog.getInstance().logEvent(new Event("product deleted in routine: "
                + this.getDayOfWeek() + " " + this.getTimeOfDay()));
        skincareroutine.remove(skincareroutine.indexOf(p)); // remember that remove can accept the product or the index
    }

    // REQUIRES; non-empty arraylist & position parameter must be a integer in type String. Eg. "4" not "four"
    // MODIFIES: this & the Routine's arraylist
    // EFFECTS: takes a product and a desired index position in the arraylist and then puts the product in that index
    //          position
    // NOTE: JUnit will autoshift down products in list
    public void reorderProduct(SkincareProduct p, String position) throws ArrayIndexOutOfBoundsException {
//        try {
        int intpos = Integer.parseInt(position) - 1; // due to computer 0-based indexing vs. human 1-based indexing
        if (intpos > skincareroutine.size() || intpos < 0) {
            EventLog.getInstance().logEvent(new Event("ArrayIndexOutOfBoundsException occured"));
            throw new ArrayIndexOutOfBoundsException(); // im not sure if this actually does anything
        } else {
            EventLog.getInstance().logEvent(new Event("product reordered in routine: "
                    + this.getDayOfWeek() + " " + this.getTimeOfDay()));

            skincareroutine.remove(skincareroutine.indexOf(p)); // using getindexOf allows duplicates to exist.
            skincareroutine.add(intpos, p);                    // bc the search is index based
        }
//        } catch (NumberFormatException e) { // im not sure how this got here. was auto put here by JUnit but it works
//            reorderProduct(p, position);    // this exception stops weird inputs. just tries again via recursion
//        } // NOTE: you can comment this out for autograder and put it back in for the actual functionality!
    }

    // EFFECTS: gets the day of the week the routine is done at
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    // EFFECTS: gets the time of day the routine is done at
    public String getTimeOfDay() {
        return timeOfDay;
    }

    // EFFECTS: asserts if routine's skincareroutine Arraylist is empty
    public boolean emptyRoutine() {
        if (skincareroutine.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: gets size of the routine's skincareroutien Arraylist
    public int getRoutineSize() {
        return skincareroutine.size();
    }

    @Override
    // MODIFIES: JSON Object
    // EFFECTS: creates a JSON object of the routine
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dayOfWeek", dayOfWeek); // might merge this into one?
        json.put("timeOfDay", timeOfDay);
        json.put("skincareroutine", routineToJson());
        return json;
    }

    // MODIFIES: JSONArray
    // EFFECTS: adds products in this routine to a JSON array and returns it
    private JSONArray routineToJson() { // so parsing routines is taken care of! yay!
        JSONArray jsonArray = new JSONArray();

        for (SkincareProduct p : skincareroutine) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
