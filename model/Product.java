package balke.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class defines a Product used in the inventory system
 */
public class Product {

    /**
     * This variable defines parts associated with the Product
     */
    //Vars
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    /**
     * This variable defines the product ID.
     */
    private int id;
    /**
     * This variable defines the product name
     */
    private String name;
    /**
     * This variable defines the product price
     */
    private double price;
    /**
     * This variable defines the product stock
     */
    private int stock;
    /**
     * This variable defines the product minimum
     */
    private int min;
    /**
     * This variable defines the product maximum
     */
    private int max;

    /**
     * This is the default constructor for a part.
     */
    //Constructor
    public Product() {

    }

    /**
     * This is the constructor for a product.
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */
    //Constructor
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * This method sets the product ID
     * @param id
     */
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    /**
     * This method sets the product Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * This method sets the product price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * This method sets the product stock
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    /**
     * This method sets the product minimum
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }
    /**
     * This method sets the product maximum
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }
    /**
     * This method returns the product ID
     * @return
     */
    //Getters
    public int getId() {
        return id;
    }
    /**
     * This method returns the product Name
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * This method returns the product Price
     * @return
     */
    public double getPrice() {
        return price;
    }
    /**
     * This method returns the product Stock
     * @return
     */
    public int getStock() {
        return stock;
    }
    /**
     * This method returns the product minimum
     * @return
     */
    public int getMin() {
        return min;
    }
    /**
     * This method returns the product maximum
     * @return
     */
    public int getMax() {
        return max;
    }

    /**
     * This method adds a part to a product
     * @param part
     */
    //Other Methods
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /**
     * This method deletes an associated part from a product
     * @param selectedAssociatedPart
     * @return
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * This method gets all associated parts for a product
     * @return
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}