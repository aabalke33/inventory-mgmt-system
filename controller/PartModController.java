package balke.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import static balke.c482.MainController.error;
/**
 * This class provides control over the Part Modify Page GUI
 *
 * @author Aaron Balke
 */
public class PartModController {
    /**
     * This variable defines if the part is inhouse or outsourced
     */
    private boolean inHouse = true;

    /**
     * This variable defines the part selected to be modified
     */
    public Part selectedPart;

    /**
     * This variable defines the index of the part
     */
    private int index;
    /**
     * This variable defines the part ID
     */
    private static int id;
    /**
     * This variable defines the label from part option (InHouse or Outsourced)
     */
    @FXML private Label optionLabel;
    /**
     * This variable defines the ID Field
     */
    @FXML private TextField idField;
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
     * This variable defines the Inhouse Radio button
     */
    @FXML private RadioButton inhouse;
    /**
     * This variable defines the Outsourced Radio button
     */
    @FXML private RadioButton outsourced;
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
     * This method sets the modified information to update the part info
     * @param selectedPart
     */
    public void setPart(Part selectedPart) {
        this.selectedPart = selectedPart;
        index = Inventory.getAllParts().indexOf(selectedPart);
        idField.setText(Integer.toString(selectedPart.getId()));
        nameField.setText(selectedPart.getName());
        invField.setText(Integer.toString(selectedPart.getStock()));
        priceField.setText(Double.toString(selectedPart.getPrice()));
        maxField.setText(Integer.toString(selectedPart.getMax()));
        minField.setText(Integer.toString(selectedPart.getMin()));

        if (selectedPart instanceof InHouse) {
            InHouse part = (InHouse) selectedPart;
            inhouse.setSelected(true);
            optionLabel.setText("Machine ID");
            optionField.setText(Integer.toString(part.getMachineId()));
        } else {
            Outsourced part = (Outsourced) selectedPart;
            outsourced.setSelected(true);
            optionLabel.setText("Company Name");
            optionField.setText(part.getCompanyName());
        }
    }
    /**
     * This method saves the entered data as a part and returns the Main GUI Page
     * @param actionEvent
     */
    @FXML
    public void saveOnAction(ActionEvent actionEvent) {
        try {
            int partId = Integer.parseInt(idField.getText());
            String partName = nameField.getText();
            int partInv = Integer.parseInt(invField.getText());
            double partPrice = Double.parseDouble(priceField.getText());
            int partMin = Integer.parseInt(minField.getText());
            int partMax = Integer.parseInt(maxField.getText());
            String partOption = optionField.getText();

            if (partMin < partInv && partInv < partMax) {

                if (inHouse) {
                    int partMachineId = Integer.parseInt(partOption);
                    Inventory.updatePart(index,
                            new InHouse(partId, partName, partPrice, partInv, partMin, partMax, partMachineId));

                } else {
                    Inventory.updatePart(index,
                            new Outsourced(partId, partName, partPrice, partInv, partMin, partMax, partOption));
                }
                returnToMain(actionEvent);
            } else {
                error("Input Error", "Error in Maximum, Minimum and / or Inventory", "Maximum has to be greater than Minimum. Inventory must be between Minimum and Maximum" );
            }

        } catch (Exception e) {
            System.out.println("Please Provide all Fields");
            error("Input Error", "Missing Field or Incorrect Input Type", "Please verify all fields have values and all values are correctly formatted.");
        }
    }
    /**
     * This method changes the part to be in-house
     * @param actionEvent
     */
    @FXML
    public void inhouseOnAction(ActionEvent actionEvent) {
        inHouse = true;
        optionLabel.setText("Machine ID");
        optionField.setPromptText("Machine ID");
    }
    /**
     * This method changes the part to be outsourced
     * @param actionEvent
     */
    @FXML
    public void outsourcedOnAction(ActionEvent actionEvent) {
        inHouse = false;
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