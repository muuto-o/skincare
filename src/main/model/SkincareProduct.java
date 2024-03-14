package model;

import org.json.JSONObject;
import persistence.Writable;

//CLASS LEVEL COMMENT: creates instance of a skincare product. SkincareProducts are added to the arraylist
//                     created in the Routines class
public class SkincareProduct implements Writable {
    private String productName;
    private String productType; // cleanser? moisturizer? serum?
    private String productBrand; // Innisfree? Neutrogena? The Ordinary

    // EFFECTS: constructor for a SkincareProduct
    public SkincareProduct(String brand, String name, String type) { // should this have any parameters by convention?
        this.productBrand = brand;
        this.productName = name;
        this.productType = type;
    }

    // EFFECTS: sets the brand of the SkincareProduct.
    // NOTE: user input for the user to set the brand is in UI class
    public void setProductBrand(String brand) {
        productBrand = brand; //.nextLine();
    }

    // EFFECTS: sets the name of the SkincareProduct.
    // NOTE: user input for the user to set the brand is in UI class
    public void setProductName(String name) {
        productName = name; //.nextLine();
    }

    // EFFECTS: sets the type of the SkincareProduct.
    // NOTE: user input for the user to set the brand is in UI class
    public void setProductType(String type) {
        productType = type; //.nextLine();
    }

    // EFFECTS: gets the brand of the SkincareProduct
    public String getProductBrand() {
        return productBrand;
    }

    // EFFECTS: gets the name of the SkincareProduct
    public String getProductName() {
        return productName;
    }

    // EFFECTS: gets the type of the SkincareProduct
    public String getProductType() {
        return productType;
    }

    @Override
    // MODIFIES: JSON Object
    // EFFECTS: creates a JSON object of the SkincareProduct
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("productBrand", productBrand);
        json.put("productType", productType);
        json.put("productName", productName);
        return json;
    }
}
