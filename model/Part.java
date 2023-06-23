package balke.c482;

/**
 * This class defines a Part used in the inventory system
 */
public abstract class Part {
    /**
     * This variable defines the part ID.
     */
    private int id;
    /**
     * This variable defines the part name.
     */
    private String name;
    /**
     * This variable defines the part price
     */
    private double price;
    /**
     * This variable defines the part stock
     */
    private int stock;
    /**
     * This variable defines the part minimum
     */
    private int min;
    /**
     * This variable defines the part maximum
     */
    private int max;

    /**
     * This is the constructor for a part.
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * This method sets the part ID
     * @param id
     */
    //Setters
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method sets the part Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method sets the part price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This method sets the part stock
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * This method sets the part minimum
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * This method sets the part maximum
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * This method returns the part ID
     * @return
     */
    //Getters
    public int getId() {
        return id;
    }

    /**
     * This method returns the part Name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns the part Price
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * This method returns the part Stock
     * @return
     */
    public int getStock() {
        return stock;
    }

    /**
     * This method returns the part minimum
     * @return
     */
    public int getMin() {
        return min;
    }

    /**
     * This method returns the part maximum
     * @return
     */
    public int getMax() {
        return max;
    }
}