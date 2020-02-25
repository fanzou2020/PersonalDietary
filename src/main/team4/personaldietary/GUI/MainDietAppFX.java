package main.team4.personaldietary.GUI;

/* Some code modified and adapted from:
 * https://www.tutorialspoint.com/javafx/layout_borderpane.htm
 * https://www.tutorialspoint.com/javafx/layout_gridpane.htm
 * http://tutorials.jenkov.com/javafx/index.html
 * https://gist.github.com/jewelsea/5559262
 */

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;

public class MainDietAppFX extends Application {

    // The primary window or frame of this application
    private Stage primaryStage;

    public MainDietAppFX() {
        super();
    }

    @Override
    public void start(Stage primaryStage) {

        // The Stage comes from the framework so make a copy to use elsewhere
        this.primaryStage = primaryStage;

        // Create the Scene and put it on the Stage
        configureStage();

        // Set the window title
        // In real product, here should use "ResourceBundle.getBundle("MessagesBundle").getString("title")"
        primaryStage.setTitle("Personal Dietary Application");
        // Raise the curtain on the Stage
        primaryStage.show();
    }


    /**
     * Load the FXML and bundle, create a Scene and put the Scene on Stage.
     *
     * Using this approach allows you to use loader.getController() to get a
     * reference to the fxml's controller should you need to pass data to it.
     * Not used in this archetype.
     */
    private void configureStage() {
        try {
            // Instantiate the FXMLLoader
            FXMLLoader loader = new FXMLLoader();

            // Set the location of the fxml file in the FXMLLoader
            //loader.setLocation(MainDietAppFX.class.getResource("/fxml/DbConnectionFX.fxml"));

            // Localize the loader with its bundle
            // Uses the default locale and if a matching bundle is not found
            // will then use MessagesBundle.properties
            //loader.setResources(ResourceBundle.getBundle("MessagesBundle"));

            // Parent is the base class for all nodes that have children in the
            // scene graph such as AnchorPane and most other containers
            Parent parent = (AnchorPane) loader.load();

            // Load the parent into a Scene
            Scene scene = new Scene(parent);
            //scene.getStylesheets().add("/styles/styles.css");
            // Put the Scene on Stage
            primaryStage.setScene(scene);

        } catch (IOException ex) { // getting resources or files could fail
            System.out.print(ex);
            System.exit(1);
        }
    }


    /**
     * Where it all begins
     *
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }
}
