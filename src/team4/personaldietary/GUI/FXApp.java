package team4.personaldietary.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The <tt>FXApp</tt> class
 *
 * @author Craig Boucher, Tanveer, Fan Zou, Osman Momoh, Xin Ma
 * @version 28/02/2020
 */
public class FXApp extends Application {
    /**
     * Initialize FX main stage
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("content.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Personal Dietary");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Main method for startup
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
