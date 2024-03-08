package Interface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test extends Application {


    @Override
    public void start(Stage primaryStage) {
        // Création du panneau
        StackPane root = new StackPane();
        root.setPrefSize(200, 200);

        // Création de l'effet de lumière
        Lighting lighting = new Lighting();
        lighting.setDiffuseConstant(1.5); // Ajuster la diffusion
        lighting.setSpecularConstant(1.0); // Ajuster le contraste de la lumière
        lighting.setSpecularExponent(10); // Ajuster l'intensité de la lumière

        // Appliquer l'effet de lumière au panneau
        root.setEffect(lighting);

        // Affichage de la scène
        Scene scene = new Scene(root, 400, 400, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lighting Effect Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



