package balke.c482;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class starts the application.
 * FUTURE ENHANCEMENT: The priority future enhancement would be the use of a database to store the inventory in a nonvolatile way.
 * Currently, the inventory disappears when the program closes. Which would not be desired in a commercial setting.
 * Minor enhancements could be making the searches work for all part/product data including Company Name, price, stock etc, or
 * creating a user access system, only allowing qualified used to edit, delete, or add parts and products.
 * @author Aaron Balke
 */
public class MainApp extends Application {
    /**
     * This method begins the application display.
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800 , 600);
        stage.setTitle("C482 - Inventory Management System - Aaron Balke");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * This method launches the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}