package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

import Model.Model;
import controller.Controller;

// FlightGear connection: --telnet=socket,in,10,127.0.0.1,5402,tcp
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/mvc1flight/view/Window.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        WindowController wc = fxmlLoader.getController();

        // Initialize the Model and set it in the controller
        Model m = new Model("properties.txt");
        wc.setModel(m);

        // Initialize the sliders and model values
        wc.paint();
        m.setAileron(1);
        m.setRudder(-1);
        m.setElevators(1);
        m.setThrottle(1);

        // Create a new controller with the model and window controller
        new Controller(m, wc);

        // Set the scene and show the primary stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("FlightGear Controller");
        primaryStage.show();

        // Add key event handling
        scene.setOnKeyPressed(event -> wc.keyPress(event));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
