package balke.c482;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static balke.c482.MainController.confirm;
import static balke.c482.MainController.error;
/**
 * This class provides control over the Product Add Page GUI
 * @author Aaron Balke
 */
public class ProductAddController implements Initializable {
    /**
     * This variable defines the parts associated with the product
     */
    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    /**
     * This variable defines the part ID
     */
    private static int id;
    /**
     * This variable defines the Name Field
     */
    @FXML private TextField nameField;
    /**
     * This variable defines the Inventory Field
     */
    @FXML private TextField invField;
    /**
     * This variable defines the Price Field
     */
    @FXML private TextField priceField;
    /**
     * This variable defines the Maximum Field
     */
    @FXML private TextField maxField;
    /**
     * This variable defines the Minimum Field
     */
    @FXML private TextField minField;

    /**
     * This variable defines the search Field
     */
    @FXML private TextField search;

    /**
     * This variable defines the available parts Table
     */
    @FXML private TableView<Part> availableParts;

    /**
     * This variable defines the available parts Table ID Column
     */
    @FXML private TableColumn<Part, Integer> availablePartsId;
    /**
     * This variable defines the available parts Table Name Column
     */
    @FXML private TableColumn<Part, String> availablePartsName;
    /**
     * This variable defines the available parts Table Inventory Column
     */
    @FXML private TableColumn<Part, Integer> availablePartsInv;
    /**
     * This variable defines the available parts Table Price Column
     */
    @FXML private TableColumn<Part, Double> availablePartsPrice;
    /**
     * This variable defines the used parts Table
     */
    @FXML private TableView<Part> usedParts;
    /**
     * This variable defines the used parts Table ID Column
     */
    @FXML private TableColumn<Part, Integer> usedPartsId;
    /**
     * This variable defines the used parts Table Name Column
     */
    @FXML private TableColumn<Part, String> usedPartsName;
    /**
     * This variable defines the used parts Table Inventory Column
     */
    @FXML private TableColumn<Part, Integer> usedPartsInv;
    /**
     * This variable defines the used parts Table Price Column
     */
    @FXML private TableColumn<Part, Double> usedPartsPrice;
    /**
     * This method returns to the Main GUI Page
     * @param actionEvent
     * @throws IOException
     */
    private void returnToMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method initializes the Product Add GUI
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search.setOnKeyPressed(this::searchAvailablePart);
        availableParts.setItems(Inventory.getAllParts());
        availablePartsId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("Id"));
        availablePartsName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        availablePartsInv.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        availablePartsPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        usedParts.setItems(associatedParts);
        usedPartsId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("Id"));
        usedPartsName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        usedPartsInv.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        usedPartsPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
    }
    /**
     * This method returns to the Main GUI page when the cancel button is pressed
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void cancelOnAction(ActionEvent actionEvent) throws IOException {
        returnToMain(actionEvent);
    }
    /**
     * This method saves the entered data as a part and returns the Main GUI Page
     * @param actionEvent
     */
    @FXML
    public void saveOnAction(ActionEvent actionEvent) {
        try {
            int productId = getId();
            String productName = nameField.getText();
            double productPrice = Double.parseDouble(priceField.getText());
            int productInv = Integer.parseInt(invField.getText());
            int productMin = Integer.parseInt(minField.getText());
            int productMax = Integer.parseInt(maxField.getText());

            if (productMin < productInv && productInv < productMax) {
                Product product = new Product(productId, productName, productPrice, productInv, productMin, productMax);

                for (Part part : associatedParts) {
                    product.addAssociatedPart(part);
                }

                Inventory.addProduct(product);
                returnToMain(actionEvent);
            } else {
                error("Input Error",
                        "Error in Maximum, Minimum and / or Inventory",
                        "Maximum has to be greater than Minimum. Inventory must be between Minimum and Maximum" );
            }
        } catch (Exception e) {
            System.out.println("Please Provide all Fields");
            error("Input Error",
                    "Missing Field or Incorrect Input Type",
                    "Please verify all fields have values and all values are correctly formatted.");
        }
    }

    /**
     * This method removes the part from the product
     * @param actionEvent
     */
    @FXML
    public void removeOnAction(ActionEvent actionEvent) {
        Part selectedPart = usedParts.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            if (confirm("Verify Part Removal",
                    "Verify Part Removal",
                    "Do you really want to remove the selected part from the product?" )) {
                associatedParts.removeAll(selectedPart);
            }
        }
    }

    /**
     * This method adds the selected part to the product
     * @param actionEvent
     */
    @FXML
    public void addOnAction(ActionEvent actionEvent) {
        Part selectedPart = availableParts.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
        } else {
            associatedParts.add(selectedPart);
        }
    }
    /**
     * This method returns a unique ID
     * RUNTIME ERROR: My original getID() would find the size of the list and add 1 to that.
     * This worked perfect unless you deleted a product. If you deleted any problem >= n-1, the ID would match the last
     * item. The simple solution was just to increment a variable. This does provide a unique ID.
     * The main feature or bug of the current method is IDs are never reused for new items once deleted.
     * If reuse is a requirement (ex. once 2_147_483_647 items entered the id variable breaks),
     * a different implementation would be required.
     *
     * @return
     */
    public static int getId() {
        return ++id;
    }

    /**
     * This method filters the Available Parts Table by the part search field input
     * @param event
     */
    public void searchAvailablePart(KeyEvent event) {
        ObservableList<Part> matchPart = Inventory.lookupPart(search.getText());
        String searchInputInt = search.getText().replaceAll("[\\D]", "");

        if (!searchInputInt.isEmpty()) {
            Part matchPartId = Inventory.lookupPart(Integer.parseInt(searchInputInt));
            if (matchPartId != null) {
                matchPart.add(matchPartId);
            }
        }

        availableParts.setItems(matchPart);

        if (matchPart.isEmpty()) {
            Label searchText = new Label("No Matches. Please Expand your Search.");
            searchText.setTextFill(Color.color(1, 0, 0));
            availableParts.setPlaceholder(searchText);
        }
    }
}