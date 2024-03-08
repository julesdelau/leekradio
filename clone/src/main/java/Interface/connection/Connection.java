package Interface.connection;


import Interface.secretaire.Secretaire;
import Interface.profil.Profil;
import interfaceUser.Sql_handler;
import interfaceUser.encrypteur;
import interfaceUser.hub_medecin_et_observateur;
import javafx.event.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Lighting;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.SQLException;




public class Connection extends Application {

    Sql_handler moteur;
    AnchorPane header = new AnchorPane();
    private Profil profil;

    Connection connexion;

    public Connection(){
        profil= new Profil();
        moteur = new Sql_handler();
    }


    public Connection(Connection connection){
        this.connexion = connection;
        profil= new Profil();
        moteur = new Sql_handler();
    }

    public void start(Stage primaryStage){
        Label titre = new Label("Connectez-vous");
        Label text = new Label("Saisissez vos identifiants et mot de passe pour vous connecter");

        TextField nomUtilisateurField = new TextField();
        PasswordField motDePasseField = new PasswordField();

        Button connectionButton = new Button("Connection");

        nomUtilisateurField.setPromptText("Nom d'utilisateur");
        motDePasseField.setPromptText("Mot de passe");

        ImageView mdp = new ImageView(new Image("/mot-de-passe.png"));
        mdp.setFitHeight(30);
        mdp.setFitWidth(30);


        ImageView users = new ImageView(new Image("/homme.png"));
        users.setFitHeight(30);
        users.setFitWidth(30);


        titre.setFont(new Font("Arial", 30));
        titre.setTextFill(Color.WHITE);
        text.setTextFill(Color.WHITE);

        Image backgroundImage = new Image("/back2.jpg");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false));
        Background backgroundWithImage = new Background(background);




        ImageView logo = new ImageView(new Image("/logoHopital.png"));
        logo.setFitHeight(100);
        logo.setFitWidth(200);
        Insets insets = new Insets(10);



        header.setStyle("-fx-background-color: #444;");
        AnchorPane.setTopAnchor(logo, 10.0);
        AnchorPane.setLeftAnchor(logo, 10.0);
        double topOffset = (header.getHeight() + header.getWidth()) / 2;
        AnchorPane.setTopAnchor(logo, topOffset);
        header.setPadding(insets);
        header.getChildren().add(logo);

        BorderStroke borderStroke = new BorderStroke(
                Color.RED,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(0, 0, 2, 0) // Seule la bordure inférieure a une largeur de 2 pixels
        );

        Border border = new Border(borderStroke);
        header.setBorder(border);

        GridPane connection = new GridPane();


        connection.setAlignment(Pos.CENTER);
        connection.setHgap(10);
        connection.setVgap(10);

        connection.add(titre, 0, 0, 2, 1);
        connection.add(text, 0, 1, 2, 1);
        connection.add(users, 0, 2);
//        connection.add(nomUtilisateur, 0, 2);
        connection.add(nomUtilisateurField, 1, 2);
//        connection.add(motDePasse, 0, 3);
        connection.add(mdp, 0, 3);
        connection.add(motDePasseField, 1, 3);
        connection.add(connectionButton, 1, 4, 2, 1);


        VBox connectionPanel = new VBox(10);
        connectionPanel.setStyle("-fx-background-color: #444;");
        connectionPanel.setMaxSize(600, 300);
        connectionPanel.setAlignment(Pos.CENTER);
        connectionPanel.getChildren().addAll(titre,connection, connectionButton);

        Lighting lighting = new Lighting();
        lighting.setDiffuseConstant(1.5); // Ajuster la diffusion
        lighting.setSpecularConstant(1.0); // Ajuster le contraste de la lumière
        lighting.setSpecularExponent(10); // Ajuster l'intensité de la lumière

        connectionPanel.setEffect(lighting);


        BorderPane content = new BorderPane();
        content.setTop(header);
        content.setCenter(connectionPanel);

        StackPane root = new StackPane();
        root.setBackground(backgroundWithImage);
        root.getChildren().add(content);

        connectionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                encrypteur code = new encrypteur();
                String user = nomUtilisateurField.getText().trim();
                String temps = String.valueOf(motDePasseField.getText());
                String mdp = code.securiserFacilement(temps);



                int temp = moteur.connection(user, mdp);
                switch (temp) {
                    case (0):
                        System.out.println("nique ta mere");
                        break;
                    case (1):
                        primaryStage.close();
                        Secretaire secretaire = new Secretaire(connexion);
                        secretaire.mettreAJourUtilisateur(user);
                        secretaire.start(primaryStage);

                        break;
                    case (2):
                        new hub_medecin_et_observateur(false, user).setVisible(true);
                        primaryStage.close();
                        break;
                    case (3):
                        new hub_medecin_et_observateur(true, user).setVisible(true);
                        primaryStage.close();
                        break;

                }
            }
        });



        Scene scene = new Scene(root);
        primaryStage.setScene(scene);


        primaryStage.setTitle("Connection");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
