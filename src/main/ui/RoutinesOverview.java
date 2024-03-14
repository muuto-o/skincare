package ui;

import model.Routine;
import model.SkincareProduct;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//CLASS LEVEL COMMENT: Skincare Routine Scheduler app. Makes 14 instantiations of Routine class –
//                     two for every day of the week:  morning and nighttime.
public class RoutinesOverview {
    // FOR PERSISTENCE
    private static final String MONM_SAVE = "./data/monM.json";
    private static final String MONN_SAVE = "./data/monN.json";
    private static final String TUEM_SAVE = "./data/tueM.json";
    private static final String TUEN_SAVE = "./data/tueN.json";
    private static final String WEDM_SAVE = "./data/wedM.json";
    private static final String WEDN_SAVE = "./data/wedN.json";
    private static final String THUM_SAVE = "./data/thuM.json";
    private static final String THUN_SAVE = "./data/thuN.json";
    private static final String FRIM_SAVE = "./data/friM.json";
    private static final String FRIN_SAVE = "./data/friN.json";
    private static final String SATM_SAVE = "./data/satM.json";
    private static final String SATN_SAVE = "./data/satN.json";
    private static final String SUNM_SAVE = "./data/sunM.json";
    private static final String SUNN_SAVE = "./data/sunN.json";
    // per days of week
    private JsonWriter jsonWriterMonM;
    private JsonReader jsonReaderMonM;
    private JsonWriter jsonWriterMonN;
    private JsonReader jsonReaderMonN;
    private JsonWriter jsonWriterTueM;
    private JsonReader jsonReaderTueM;
    private JsonWriter jsonWriterTueN;
    private JsonReader jsonReaderTueN;
    private JsonWriter jsonWriterWedM;
    private JsonReader jsonReaderWedM;
    private JsonWriter jsonWriterWedN;
    private JsonReader jsonReaderWedN;
    private JsonWriter jsonWriterThuM;
    private JsonReader jsonReaderThuM;
    private JsonWriter jsonWriterThuN;
    private JsonReader jsonReaderThuN;
    private JsonWriter jsonWriterFriM;
    private JsonReader jsonReaderFriM;
    private JsonWriter jsonWriterFriN;
    private JsonReader jsonReaderFriN;
    private JsonWriter jsonWriterSatM;
    private JsonReader jsonReaderSatM;
    private JsonWriter jsonWriterSatN;
    private JsonReader jsonReaderSatN;
    private JsonWriter jsonWriterSunM;
    private JsonReader jsonReaderSunM;
    private JsonWriter jsonWriterSunN;
    private JsonReader jsonReaderSunN;
    //SCANNERS FOR INPUTS
    private Scanner input = new Scanner(System.in);
    // for selectProduct methods:
    private Scanner selectInput = new Scanner(System.in);
    private Scanner selectIndexInput = new Scanner(System.in);
    // for reorder Routine method:
    private Scanner positionInput = new Scanner(System.in);
    // for SkincareProduct class methods:
    private Scanner inputName = new Scanner(System.in);
    private Scanner inputType = new Scanner(System.in);
    private Scanner inputBrand = new Scanner(System.in);

    // constant number of routines
    private Routine monMorning = new Routine("Monday", "Morning");
    private Routine monNight = new Routine("Monday", "Night");
    private Routine tueMorning = new Routine("Tuesday", "Morning");
    private Routine tueNight = new Routine("Tuesday", "Night");
    private Routine wedMorning = new Routine("Wednesday", "Morning");
    private Routine wedNight = new Routine("Wednesday", "Night");
    private Routine thuMorning = new Routine("Thursday", "Morning");
    private Routine thuNight = new Routine("Thursday", "Night");
    private Routine friMorning = new Routine("Friday", "Morning");
    private Routine friNight = new Routine("Friday", "Night");
    private Routine satMorning = new Routine("Saturday", "Morning");
    private Routine satNight = new Routine("Saturday", "Night");
    private Routine sunMorning = new Routine("Sunday", "Morning");
    private Routine sunNight = new Routine("Sunday", "Night");

    // EFFECTS: constructor class. Also instantiates
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public RoutinesOverview() throws FileNotFoundException {
        jsonWriterMonM = new JsonWriter(MONM_SAVE);
        jsonReaderMonM = new JsonReader(MONM_SAVE);
        jsonWriterMonN = new JsonWriter(MONN_SAVE);
        jsonReaderMonN = new JsonReader(MONN_SAVE);
        jsonWriterTueM = new JsonWriter(TUEM_SAVE);
        jsonReaderTueM = new JsonReader(TUEM_SAVE);
        jsonWriterTueN = new JsonWriter(TUEN_SAVE);
        jsonReaderTueN = new JsonReader(TUEN_SAVE);
        jsonWriterWedM = new JsonWriter(WEDM_SAVE);
        jsonReaderWedM = new JsonReader(WEDM_SAVE);
        jsonWriterWedN = new JsonWriter(WEDN_SAVE);
        jsonReaderWedN = new JsonReader(WEDN_SAVE);
        jsonWriterThuM = new JsonWriter(THUM_SAVE);
        jsonReaderThuM = new JsonReader(THUM_SAVE);
        jsonWriterThuN = new JsonWriter(THUN_SAVE);
        jsonReaderThuN = new JsonReader(THUN_SAVE);
        jsonWriterFriM = new JsonWriter(FRIM_SAVE);
        jsonReaderFriM = new JsonReader(FRIM_SAVE);
        jsonWriterFriN = new JsonWriter(FRIN_SAVE);
        jsonReaderFriN = new JsonReader(FRIN_SAVE);
        jsonWriterSatM = new JsonWriter(SATM_SAVE);
        jsonReaderSatM = new JsonReader(SATM_SAVE);
        jsonWriterSatN = new JsonWriter(SATN_SAVE);
        jsonReaderSatN = new JsonReader(SATN_SAVE);
        jsonWriterSunM = new JsonWriter(SUNM_SAVE);
        jsonReaderSunM = new JsonReader(SUNM_SAVE);
        jsonWriterSunN = new JsonWriter(SUNN_SAVE);
        jsonReaderSunN = new JsonReader(SUNN_SAVE);
        runRoutinesOverview();
    }

    // MODIFIES: this
    // EFFECTS: instantiates the Main Menu Screen 1
    public void runRoutinesOverview() {
        boolean keepGoing = true;

        while (keepGoing) {
            displayRoutinesOverview();
            String command = input.nextLine();

            if (!validInput(command)) {
                System.out.println("uNACCEPTABLE!!! INVALID INPUT!! Please try again");
                System.out.println(" ");
                //keepGoing = false;
                runRoutinesOverview();
                // displayRoutines();
            } else {
                processRoutinesOverviewCommand(command);
            }
        }
    }


    // EFFECTS: displays the RoutinesOverview menu. Helper method for runRoutinesOverview();
    public void displayRoutinesOverview() {
        System.out.println("--- <3 <3 MY SKINCARE SCHEDULER <3 <3 ---");
        System.out.println("Select from:");
        System.out.println("Load -> Load a previous save");
        System.out.println("Save -> Save your current edits");
        System.out.println("–––");
        System.out.println("MonM -> View: Monday Morning Routine");
        System.out.println("MonN -> View: Monday Night Routine");
        System.out.println("TueM -> View: Tuesday Morning Routine");
        System.out.println("TueN -> View: Tuesday Night Routine");
        System.out.println("WedM -> View: Wednesday Morning Routine");
        System.out.println("WedN -> View: Wednesday Night Routine");
        System.out.println("ThuM -> View: Thursday Morning Routine");
        System.out.println("ThuN -> View: Thursday Night Routine");
        System.out.println("FriM -> View: Friday Morning Routine");
        System.out.println("FriN -> View: Friday Night Routine");
        System.out.println("SatM -> View: Saturday Morning Routine");
        System.out.println("SatN -> View: Saturday Night Routine");
        System.out.println("SunM -> View: Sunday Morning Routine");
        System.out.println("SunN -> View: Sunday Night Routine");
    }


    // EFFECTS: Verifies if a user input String is valid. Helper method for runRoutinesOverview();
    public boolean validInput(String command) { // can replace else-ifs with switch casee
        if (command.equals("MonM") || command.equals("MonN")) {
            return true;
        } else if (command.equals("TueM") || command.equals("TueN")) {
            return true;
        } else if (command.equals("WedM") || command.equals("WedN")) {
            return true;
        } else if (command.equals("ThuM") || command.equals("ThuN")) {
            return true;
        } else if (command.equals("FriM") || command.equals("FriN")) {
            return true;
        } else if (command.equals("SatM") || command.equals("SatN")) {
            return true;
        } else if (command.equals("SunM") || command.equals("SunN")) {
            return true;
        } else if (command.equals("Load") || command.equals("Save")) {
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: editcommand String input must be valid, verified by validInput(String command).
    // MODIFIES: this
    // EFFECTS: processes valid user inputs for Menu Screen 1. Runs other called methods based on various user input.
    // NOTE:    user input is case-sensitive and whitespace sensitive
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void processRoutinesOverviewCommand(String command) { // access routine
        if (command.equals("MonM")) {
            runRoutine(monMorning); // show new object WORK ON IT
        } else if (command.equals("MonN")) {
            runRoutine(monNight);
        } else if (command.equals("TueM")) {
            runRoutine(tueMorning);
        } else if (command.equals("TueN")) {
            runRoutine(tueNight);
        } else if (command.equals("WedM")) {
            runRoutine(wedMorning);
        } else if (command.equals("WedN")) {
            runRoutine(wedNight);
        } else if (command.equals("ThuM")) {
            runRoutine(thuMorning);
        } else if (command.equals("ThuN")) {
            runRoutine(thuNight);
        } else if (command.equals("FriM")) {
            runRoutine(friMorning);
        } else if (command.equals("FriN")) {
            runRoutine(friNight);
        } else if (command.equals("SatM")) {
            runRoutine(satMorning);
        } else if (command.equals("SatN")) {
            runRoutine(satNight);
        } else if (command.equals("SunM")) {
            runRoutine(sunMorning);
        } else if (command.equals("SunN")) {
            runRoutine(sunNight);
        } else if (command.equals("Load")) {
            loadRoutinesOverview();
        } else if (command.equals("Save")) {
            saveRoutinesOverview();
        }
    }

    // MODIFIES: this
    // EFFECTS: instantiates Menu Screen 2
    public void runRoutine(Routine r) {
        boolean keepGoing = true;

        while (keepGoing) {
            outputRoutine(r); //previously r.outputRoutine();
            System.out.println("Want to edit your skincare routine? Select from:");
            System.out.println("add -> add product to end of routine.");
            System.out.println("delete -> delete product off routine");
            System.out.println("reorder -> reorder product in routine");
            System.out.println("edit -> edit product in routine");
            System.out.println("or.... return -> return to main screen");
            String command = input.nextLine(); // need help with input

            if (!validInputScreen2(command)) {
                invalidInputPrint();
                runRoutine(r);
            } else {
                processRoutineCommand(command, r);
            }
        }
    }


    // EFFECTS: Verifies if a user input String is valid. Helper method for runRoutine(Routine r);
    public boolean validInputScreen2(String command) {
        if (command.equals("add") || command.equals("delete") || command.equals("return")) {
            return true;
        } else if (command.equals("reorder") || command.equals("edit")) {
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: editcommand String input must be valid, verified by validInputScreen2(String command).
    // MODIFIES: this
    // EFFECTS: processes valid user inputs for Menu Screen 1. Runs other called methods based on various user input.
    // NOTE:    user input is case-sensitive and whitespace sensitive
    public void processRoutineCommand(String command, Routine r) {
        if (command.equals("add")) {
            addProductToRoutine(r);
        } else if (command.equals("delete")) {
            if (r.getRoutineSize() != 0) {
                deleteProduct(r);
            } else {
                emptyRoutinePrint(r);
            }
        } else if (command.equals("reorder")) {
            if (r.getRoutineSize() != 0) {
                reorderProduct(r);
            } else {
                emptyRoutinePrint(r);
            }
        } else if (command.equals("edit")) {
            if (r.getRoutineSize() != 0) {
                editProduct(r);
            } else {
                emptyRoutinePrint(r);
            }
        } else if (command.equals("return")) {
            runRoutinesOverview();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new SkincareProduct object and adds it to Routine's skincareroutine Arraylist
    public void addProductToRoutine(Routine r) {
        System.out.println("Now specify the product you want to add...");

        System.out.println("Set your product brand: ");
        String brand = inputBrand.nextLine();

        System.out.println("Set your product name: ");
        String name = inputName.nextLine();

        System.out.println("Set your product type: ");
        String type = inputType.nextLine();

        r.addProduct(brand, name, type);
        commandSuccessPrint();
    }

    // REQUIRES: SkincareProduct parameter (ie.selectedproduct) cannot be null
    // MODIFIES: this
    // EFFECTS: selects a product, then deletes a new SkincareProduct object from Routine's skincareroutine Arraylist
    public void deleteProduct(Routine r) {
        System.out.println("Now specify the product you want to delete...");
        SkincareProduct selectedproduct = selectProduct(r);
        r.deleteProduct(selectedproduct);
        commandSuccessPrint();
    }

    // MODIFIES: SkincareProduct parameter (ie.selectedproduct) cannot be null
    // MODIFIES: this
    // EFFECTS: selects a product, then changes its position in the skincareroutine Arraylist
    public void reorderProduct(Routine r) {
        System.out.println("Now specify the product you want to reorder...");
        SkincareProduct selectedproduct = selectProduct(r);
        System.out.println("In which STEP POSITION NUMBER in your routine do you want to put your product?");
        String position = positionInput.nextLine();

        // TODO: put this in the actionlistener if stmt in ur GUI
        try {
            r.reorderProduct(selectedproduct, position);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("INVALID INPUT!! This step position does not exist.");
            reorderProduct(r);
        }
        commandSuccessPrint();
    }

    // REQUIRES: no more than two name duplicates of a SkincareProduct.
    // MODIFIES: nothing. GETS a product.
    // EFFECTS: searches for a product based on its name, and returns the product.
    //          if selectedProduct cannot be found, and null is returned by r.selectProduct(select), method will
    //          try again via recursion. This is useful in case the user inputs a typo.
    //          Helper method for deleteProduct(Routine r) and deleteProduct(Routine r) and reorderProduct(Routine r)
    public SkincareProduct selectProduct(Routine r) {
        System.out.println("What is the NAME of the product you want to select? Make sure you don't make typos!");
        String select = selectInput.nextLine();
        SkincareProduct p = r.selectProduct(select);

        // faux exception handler teehee
        if (p == null) {
            System.out.println("Product not found! Try again...");
            p = selectProduct(r); // i wrote selectProduct(r); instead of p = selectProduct(r); and thats why p kept
            // staying null. I want to kermit
        }
        return p;
    }

    // MODIFIES: nothing. gets a Skincare object from the Routine object
    // EFFECTS: searches for a product based on its index position, and returns the product.
    //          if selectedProduct cannot be found, and null is returned by r.selectProduct(select), method will
    //          try again via recursion. This is useful in case the user inputs a typo.
    // NOTE: never called because my program doesn't need it since I alr have
    //       selectProduct(Routine r). But here in case I decide to change functionality
    public SkincareProduct selectProductIndexBased(Routine r) {
        System.out.println("What is the STEP POSITION of the product you want to select? Don't make typos!");
        String selectindex = selectIndexInput.nextLine();
        SkincareProduct p = r.selectProductIndexBased(selectindex);

        // faux exception handler teehee
        if (p == null) {
            System.out.println("Product not found! Try again...");
            p = selectProductIndexBased(r);
        }
        return p;
    }

    // REQUIRES: a non-null SkincareProduct input parameter
    // MODIFIES: this
    // EFFECTS: outputs Menu Screen 3 to user
    public void editProductScreen3(Routine r, SkincareProduct p) {
        boolean keepGoing = true;

        while (keepGoing) {
//            System.out.println("You have selected a product. Make edits as you want:");
            System.out.println("You have selected the " + p.getProductName() + " by " + p.getProductBrand() + ".");
            System.out.println("Make edits: as you want");
            System.out.println("brand -> edit product brand");
            System.out.println("name -> edit product name");
            System.out.println("type -> edit product type");
            System.out.println("Or... return -> select a different product to edit");
            String editcommand = input.nextLine();

            if (!validInputEditScreen(editcommand)) {
                invalidInputPrint();
                editProductScreen3(r, p);
            } else {
                processEditCommand(editcommand, r, p);
            }
        }
    }

    // MODIFIES: this & a SkincareProduct that the user selects
    // EFFECTS: selects a SkincareProduct for the user to edit, and then edits the Product details accordingly
    public void editProduct(Routine r) {
        System.out.println("Select a product to edit...");
        SkincareProduct selectedproduct = selectProduct(r);
        // Unofficial test for selectedproduct nullness:
//        if (selectedproduct == null) { // HAHA WE FOUND IT. BECOMES NULL HERE HHH
//            System.out.println("Oh naur. Chleaur. The recursion....its not working... Shes null");
//        }
        editProductScreen3(r, selectedproduct); // returns to Menu Screen 3
    }

    // EFFECTS: verifies if user input is valid. Helper method to editProductScreen3(Routine r, SkincareProduct p)
    public boolean validInputEditScreen(String command) {
        if (command.equals("name") || command.equals("type") || command.equals("brand") || command.equals("return")) {
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: editcommand String input must be valid, verified by validInputEditScreen(String command).
    // MODIFIES: this
    // EFFECTS: calls various relevant methods based on the user input in editcommand
    public void processEditCommand(String editcommand, Routine r, SkincareProduct p) {
        if (editcommand.equals("brand")) {
            System.out.println("Set your product brand: ");
            String brand = inputBrand.nextLine();
            p.setProductBrand(brand);
            commandSuccessPrint();
            outputRoutine(r);
        } else if (editcommand.equals("name")) { //the issue is def here. why can't make edits?
            System.out.println("Set your product name: ");
            String name = inputName.nextLine();
            p.setProductName(name);
            commandSuccessPrint();
            outputRoutine(r);
        } else if (editcommand.equals("type")) {
            System.out.println("Set your product type: ");
            String type = inputType.nextLine();
            p.setProductType(type);
            commandSuccessPrint();
            outputRoutine(r);
        } else if (editcommand.equals("return")) {
            runRoutine(r);
        }
    }

    // Small Helpers ---
    // EFFECTS: outputs the full skincareroutine Arraylist to the user in a comprehensible way
    public void outputRoutine(Routine r) {
        if (r.emptyRoutine()) {
            System.out.println("[no products in your routine...yet!]");
        } else {
            System.out.println("––– My Skincare Routine for " + r.getDayOfWeek() + " " + r.getTimeOfDay() + " –––");
            for (SkincareProduct p : r.getRoutine()) {
                System.out.println("Step " + (r.getRoutine().indexOf(p) + 1) + ": " + outputProduct(p));
            }
            System.out.println("–––---");
        }
    }

    // EFFECTS: outputs a single product's unformation in a comprehensible way.
    //          helper function to outputRoutine(Routine r).
    public String outputProduct(SkincareProduct p) {
        return p.getProductType() + " - " + p.getProductName() + " " + "by " + p.getProductBrand();
    }

    // EFFECTS: outputs this to the user if their input is invalid
    public void invalidInputPrint() {
        System.out.println("INVALID INPUT!! Please try again");
        System.out.println(" ");
    }

    // EFFECTS: outputs this to the user if their user input command was succesful.
    public void commandSuccessPrint() {
        System.out.println("Command successful! Here is what your routine looks like now");
    }

    // EFFECTS: outputs this to the user if the Rutine's skincareroutine arraylist is empty
    public void emptyRoutinePrint(Routine r) {
        if (r.emptyRoutine()) {
            System.out.println("[routine is empty! Can't do anything... Try adding a product in first!]");

        }
    }

    // PERSISTENCE METHODS ---
    // MODIFIES: JSON save files for every routine object
    // EFFECTS: saves the routines edited as JSON objects
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void saveRoutinesOverview() {
        try {
            jsonWriterMonM.open(); // TODO: to abstract this you probably could put all of th eobjects in an array?
            jsonWriterMonN.open();
            jsonWriterTueM.open();
            jsonWriterTueN.open();
            jsonWriterWedM.open();
            jsonWriterWedN.open();
            jsonWriterThuM.open();
            jsonWriterThuN.open();
            jsonWriterFriM.open();
            jsonWriterFriN.open();
            jsonWriterSatM.open();
            jsonWriterSatN.open();
            jsonWriterSunM.open();
            jsonWriterSunN.open();
            jsonWriterMonM.writeRoutine(monMorning); // TODO: make a new jason writer and reader for every routine
            jsonWriterMonN.writeRoutine(monNight);
            jsonWriterTueM.writeRoutine(tueMorning);
            jsonWriterTueN.writeRoutine(tueNight);
            jsonWriterWedM.writeRoutine(wedMorning);
            jsonWriterWedN.writeRoutine(wedNight);
            jsonWriterThuM.writeRoutine(thuMorning);
            jsonWriterThuN.writeRoutine(thuNight);
            jsonWriterFriM.writeRoutine(friMorning);
            jsonWriterFriN.writeRoutine(friNight);
            jsonWriterSatM.writeRoutine(satMorning);
            jsonWriterSatN.writeRoutine(satNight);
            jsonWriterSunM.writeRoutine(sunMorning);
            jsonWriterSunN.writeRoutine(sunNight);
            jsonWriterMonM.close();
            jsonWriterMonN.close();
            jsonWriterTueM.close();
            jsonWriterTueN.close();
            jsonWriterWedM.close();
            jsonWriterWedN.close();
            jsonWriterThuM.close();
            jsonWriterThuN.close();
            jsonWriterFriM.close();
            jsonWriterFriN.close();
            jsonWriterSatM.close();
            jsonWriterSatN.close();
            jsonWriterSunM.close();
            jsonWriterSunN.close();
            System.out.println("Routines saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }
    }

    // EFFECTS: loads the routines from a previous save as JSON objects
    public void loadRoutinesOverview() {
        try {
            monMorning = jsonReaderMonM.read(); // issue
            monNight = jsonReaderMonN.read();
            tueMorning = jsonReaderTueM.read();
            tueNight = jsonReaderTueN.read();
            wedMorning = jsonReaderWedM.read();
            wedNight = jsonReaderWedN.read();
            thuMorning = jsonReaderThuM.read();
            thuNight = jsonReaderThuN.read();
            friMorning = jsonReaderFriM.read();
            friNight = jsonReaderFriN.read();
            satMorning = jsonReaderSatM.read();
            satNight = jsonReaderSatN.read();
            sunMorning = jsonReaderSunM.read();
            sunNight = jsonReaderSunN.read();
            System.out.println("Loaded previous save!");
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }
}
