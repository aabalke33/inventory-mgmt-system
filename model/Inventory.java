package balke.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class provides the Inventory to store Parts and Products
 */
public class Inventory {

    /**
     * This variable stores all Parts
     */
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    /**
     * This variable stores all Products
     */
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * This method adds an input Part to the inventory
     *
     * @param newPart
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * This method adds an input Product to the inventory
     *
     * @param newProduct
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * This method searches the inventory for a Part by Part ID
     *
     * @param partId
     * @return
     */
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * This method searches the inventory for a Product by Product ID
     *
     * @param productId
     * @return
     */
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (productId == product.getId()) {
                return product;
            }
        }
        return null;
    }

    /**
     * This method searches the inventory for a Part by Part Name
     *
     * @param partName
     * @return
     */
    public static ObservableList<Part> lookupPart(String partName) {

        ObservableList<Part> matchParts = FXCollections.observableArrayList();

        if (partName.length() != 0) {
            for (Part part : allParts) {
                if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                    matchParts.add(part);
                }
            }
            return matchParts;
        } else {
            return allParts;
        }
    }

    /**
     * This method searches the inventory for a Product by Product Name
     *
     * @param productName
     * @return
     */
    public static ObservableList<Product> lookupProduct(String productName) {

        ObservableList<Product> matchProducts = FXCollections.observableArrayList();

        if (productName.length() != 0) {
            for (Product product : allProducts) {
                if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                    matchProducts.add(product);
                }
            }
            return matchProducts;
        } else {
            return allProducts;
        }
    }

    /**
     * This method replaces a part in inventory with an input part
     *
     * @param index
     * @param newPart
     */
    public static void updatePart(int index, Part newPart) {
        allParts.set(index, newPart);
    }

    /**
     * This method replaces a product in inventory with an input product
     *
     * @param index
     * @param newProduct
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * This method deletes a part from the inventory
     *
     * @param selectedPart
     * @return
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * This method deletes a product from the inventory
     *
     * @param selectedProduct
     * @return
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * This method returns a list of all Parts in the inventory
     *
     * @return
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * This method returns a list of all Products in the inventory
     *
     * @return
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}