package balke.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import static balke.c482.MainController.error;
/**
 * This class provides control over the Part Add Page GUI
 *
 * @author Aaron Balke
 */
public class PartAddController {
    /**
     * This variable defines if the part is inhouse or outsourced
     */
    private boolean inhouse = true;
    /**
     * This variable defines the part ID
     */
    private static int id;
    /**
     * This variable defines the label from part option (InHouse or Outsourced)
     */
    @FXML private Label optionLabel;
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
     * This variable defines Option Field (Either the Machine ID or Outsourced)
     */
    @FXML private TextField optionField;

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
            int partId = getId();
            String partName = nameField.getText();
            int partInv = Integer.parseInt(invField.getText());
            double partPrice = Double.parseDouble(priceField.getText());
            int partMin = Integer.parseInt(minField.getText());
            int partMax = Integer.parseInt(maxField.getText());
            String partOption = optionField.getText();

            if (partMin < partInv && partInv < partMax) {

                if (inhouse) {
                    int partMachineId = Integer.parseInt(partOption);
                    InHouse inHousePart = new InHouse(partId, partName, partPrice, partInv, partMin, partMax, partMachineId);
                    Inventory.addPart(inHousePart);
                } else {
                    Outsourced outsourcedPart = new Outsourced(partId, partName, partPrice, partInv, partMin, partMax, partOption);
                    Inventory.addPart(outsourcedPart);
                }
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
     * This method changes the part to be in-house
     * @param actionEvent
     */
    @FXML
    public void inhouseOnAction(ActionEvent actionEvent) {
        inhouse = true;
        optionLabel.setText("Machine ID");
        optionField.setPromptText("Machine ID");
    }

    /**
     * This method changes the part to be outsourced
     * @param actionEvent
     */
    @FXML
    public void outsourcedOnAction(ActionEvent actionEvent) {
        inhouse = false;
        optionLabel.setText("Company Name");
        optionField.setPromptText("Company Name");
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


}