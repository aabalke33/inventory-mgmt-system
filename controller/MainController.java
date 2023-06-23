package balke.c482;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class provides control over the Main Page GUI
 * @author Aaron Balke
 */
public class MainController implements Initializable {
    /**
     * This variable defines the stage for JavaFX
     */
    private Stage stage;
    /**
     * This variable defines the scene for JavaFX
     */
    private Scene scene;
    /**
     * This variable defines the parent for JavaFX
     */
    private Parent root;
    /**
     * This variable defines the Parts Table
     */
    @FXML
    private TableView<Part> partsTable;
    /**
     * This variable defines the Parts Table ID Column
     */
    @FXML
    private TableColumn<Part, Integer> partsTableId;
    /**
     * This variable defines the Parts Table Name Column
     */
    @FXML
    private TableColumn<Part, String> partsTableName;
    /**
     * This variable defines the Parts Table Inventory Column
     */
    @FXML
    private TableColumn<Part, Integer> partsTableInvLevel;
    /**
     * This variable defines the Parts Table Price Column
     */
    @FXML
    private TableColumn<Part, Double> partsTablePrice;
    /**
     * This variable defines the Products Table
     */
    @FXML
    private TableView<Product> productsTable;
    /**
     * This variable defines the Products Table ID Column
     */
    @FXML
    private TableColumn<Product, Integer> productsTableId;
    /**
     * This variable defines the Products Table Name Column
     */
    @FXML
    private TableColumn<Product, String> productsTableName;
    /**
     * This variable defines the Products Table Inventory Column
     */
    @FXML
    private TableColumn<Product, Integer> productsTableInvLevel;
    /**
     * This variable defines the Products Table Price Column
     */
    @FXML
    private TableColumn<Product, Double> productsTablePrice;
    /**
     * This variable defines the Parts Search field
     */
    @FXML
    private TextField partsSearch;
    /**
     * This variable defines the Products Search field
     */
    @FXML
    private TextField productsSearch;

    /**
     *
     * This method initializes the Main GUI
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsSearch.setOnKeyPressed(this::searchPart);
        productsSearch.setOnKeyPressed(this::searchProduct);
        partsTable.setItems(Inventory.getAllParts());
        partsTableId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("Id"));
        partsTableName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partsTableInvLevel.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partsTablePrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        productsTable.setItems(Inventory.getAllProducts());
        productsTableId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("Id"));
        productsTableName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productsTableInvLevel.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productsTablePrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
    }

    /**
     * This method deletes the selected part from Parts Table
     *
     * @param actionEvent
     */
    public void partsDelOnClick(ActionEvent actionEvent) {
        if (confirm("Verify Deletion",
                "Verify Deletion",
                "Do you really want to delete the selected part?" )) {
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
            Inventory.deletePart(selectedPart);
        }
    }

    /**
     * This method opens the Part Modify Page for the selected part
     *
     * @param actionEvent
     * @throws IOException
     */
    public void partsModOnClick(ActionEvent actionEvent) throws IOException {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            Stage localStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("part-mod-view.fxml"));
            Parent localParent = loader.load();
            PartModController controller = loader.getController();
            controller.setPart(selectedPart);
            localStage.setScene(new Scene(localParent));
            localStage.show();
        }
    }

    /**
     * This method opens the Part Add Page to add a new part
     *
     * @param actionEvent
     * @throws IOException
     */
    public void partsAddOnClick(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("part-add-view.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method deletes the selected product from Products Table
     *
     * @param actionEvent
     */
    public void productsDelOnClick(ActionEvent actionEvent) {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        if (!selectedProduct.getAllAssociatedParts().isEmpty()) {
            error("Cannot Delete",
                    "Cannot Delete",
                    "You have to remove associated parts from this Product before deletion");
        } else {
            if (confirm("Verify Deletion",
                    "Verify Deletion",
                    "Do you really want to delete the selected product?")) {
                Inventory.deleteProduct(selectedProduct);
            }
        }
    }

    /**
     * This method opens the Product Modify Page for the selected product
     *
     * @param actionEvent
     */
    public void productsModOnClick(ActionEvent actionEvent) {
        try {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
            if (selectedProduct == null) {
                return;
            }
            Stage localStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("product-mod-view.fxml"));
            Parent localParent = loader.load();
            ProductModController controller = loader.getController();
            controller.setProduct(selectedProduct);
            localStage.setScene(new Scene(localParent));
            localStage.show();
        } catch (IOException e) {
        }
    }

    /**
     * This method opens the Product Add Page to add a new product
     *
     * @param actionEvent
     * @throws IOException
     */
    public void productsAddOnClick(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("product-add-view.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method closes the application
     *
     * @param actionEvent
     */
    public void exitOnClick(ActionEvent actionEvent) {
        System.out.println("Exiting...");
        Platform.exit();
    }

    /**
     * This method filters the Parts Table by the part search field input
     *
     * @param event
     */
    public void searchPart(KeyEvent event) {
        ObservableList<Part> matchPart = Inventory.lookupPart(partsSearch.getText());
        String searchInputInt = partsSearch.getText().replaceAll("[\\D]", "");

        if (!searchInputInt.isEmpty()) {
            Part matchPartId = Inventory.lookupPart(Integer.parseInt(searchInputInt));
            if (matchPartId != null) {
                matchPart.add(matchPartId);
            }
        }

        partsTable.setItems(matchPart);

        if (matchPart.isEmpty()) {
            Label searchText = new Label("No Matches. Please Expand your Search.");
            searchText.setTextFill(Color.color(1, 0, 0));
            partsTable.setPlaceholder(searchText);
        }
    }

    /**
     * This method filters the Products Table by the product search field input
     *
     * @param event
     */
    public void searchProduct(KeyEvent event) {
        ObservableList<Product> matchProduct = Inventory.lookupProduct(productsSearch.getText());
        String searchInputInt = productsSearch.getText().replaceAll("[\\D]", "");

        if (!searchInputInt.isEmpty()) {
            Product matchProductId = Inventory.lookupProduct(Integer.parseInt(searchInputInt));
            if (matchProductId != null) {
                matchProduct.add(matchProductId);
            }
        }

        productsTable.setItems(matchProduct);
        if (matchProduct.isEmpty()) {
            Label searchText = new Label("No Matches. Please Expand your Search.");
            searchText.setTextFill(Color.color(1, 0, 0));
            productsTable.setPlaceholder(searchText);
        }
    }

    /**
     * This method provides an alert for errors. Used when alert selection is not required.
     *
     * @param title
     * @param header
     * @param content
     */
    static void error(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * This method provides an alert for confirmation. Used when alert selection is required.
     *
     * @param title
     * @param header
     * @param content
     * @return
     */
    static boolean confirm(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
