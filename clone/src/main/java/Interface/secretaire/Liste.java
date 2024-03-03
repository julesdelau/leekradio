/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.secretaire;

import Interface.secretaire.liste.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 *
 * @author leoce
 */
public class Liste extends VBox {

    VBox information = new VBox(10);
    List<InfoListe> listRDV = new ArrayList<>();

    public Liste() {
        Insets insets = new Insets(10);

        Label label = new Label("Rendez-vous");
        Button filtre = new Button("Trier");
        Button refresh = new Button("Rafraichir");
        Button search = new Button("Rechercher");


        HBox header = new HBox();
        header.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        header.setAlignment(Pos.CENTER);
        header.setPadding(insets);
        header.getChildren().add(label);

        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(filtre, search);

        AnchorPane buttonsPane = new AnchorPane();

        AnchorPane.setTopAnchor(buttons, 10.0);
        AnchorPane.setLeftAnchor(buttons, 10.0);
        AnchorPane.setTopAnchor(refresh, 10.0);
        AnchorPane.setRightAnchor(refresh, 10.0);
        double topOffset = (header.getHeight() + header.getWidth()) / 2;
        AnchorPane.setTopAnchor(buttons, topOffset);
        AnchorPane.setBottomAnchor(refresh, topOffset);
        buttonsPane.getChildren().addAll(buttons, refresh);



        information.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        information.setAlignment(Pos.CENTER);
        information.setPadding(insets);




        listRDV.add(new InfoListe("Genou", "Yves Vert", "2025-05-05-08-36", "Anna Dupont"));
        listRDV.add(new InfoListe("Coude", "Oscar Dupont", "2024-02-25-14-00", "Anna Dupont"));
        listRDV.add(new InfoListe("Genou", "Yves Vert", "2025-05-05-08-36", "Anna Dupont"));
        listRDV.add(new InfoListe("Coude", "Oscar Dupont", "2024-02-25-14-00", "Anna Dupont"));
        listRDV.add(new InfoListe("Genou", "Yves Vert", "2025-05-05-08-36", "Anna Dupont"));
        listRDV.add(new InfoListe("Coude", "Oscar Dupont", "2024-02-25-14-00", "Anna Dupont"));
        listRDV.add(new InfoListe("Genou", "Yves Vert", "2025-05-05-08-36", "Anna Dupont"));


        List<ListePanel> panneUrgent = new ArrayList<>();


        Collections.sort(listRDV, new Comparator<InfoListe>() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

            @Override
            public int compare(InfoListe info1, InfoListe info2) {
                try {
                    return sdf.parse(info1.getDate()).compareTo(sdf.parse(info2.getDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });

        // Create PanelsFiltre to hold all requests
        PanelsFiltreListe panelsFiltre = new PanelsFiltreListe("Toutes les requêtes");

        // Add sorted requests to PanelsFiltre
        List<ListePanel> requetesPanels = new ArrayList<>();
        for (InfoListe info : listRDV) {
            requetesPanels.add(new ListePanel(info));
        }
        panelsFiltre.setContent(requetesPanels);
        information.getChildren().add(panelsFiltre);

        BorderPane content = new BorderPane();
        content.setTop(buttonsPane);
        content.setCenter(information);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(content);
        scrollPane.setFitToWidth(true);


        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setPadding(insets);
        root.setCenter(scrollPane);


        root.prefWidthProperty().bind(this.widthProperty());
        root.prefHeightProperty().bind(this.heightProperty());

        this.getChildren().addAll(root);
        this.setPadding(insets);

        filtre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Bounds buttonBounds = filtre.localToScreen(filtre.getBoundsInLocal());
                double buttonX = buttonBounds.getMinX();
                double buttonY = buttonBounds.getMaxY();

                FiltresListe dialog = new FiltresListe((Stage) getScene().getWindow());
                dialog.setX(buttonX);
                dialog.setY(buttonY);
                dialog.showAndWait().ifPresent(filter -> {
                    String filterName = filter.getKey();
                    boolean filterValue = filter.getValue();


                    if(filterName.equals("Service") && filterValue) {
                        System.out.println("Service");
                        information.getChildren().clear();
                        Map<String, List<ListePanel>> serviceRequests = new HashMap<>();
                        for (InfoListe info : listRDV) {
                            if (!serviceRequests.containsKey(info.getType())) {
                                serviceRequests.put(info.getType(), new ArrayList<>());
                            }
                            serviceRequests.get(info.getType()).add(new ListePanel(info));
                        }
                        for (String serviceType : serviceRequests.keySet()) {
                            PanelsFiltreListe panelsFiltre = new PanelsFiltreListe(serviceType);
                            panelsFiltre.setContent(serviceRequests.get(serviceType));
                            information.getChildren().add(panelsFiltre);
                        }

                    }
                    if(filterName.equals("Medecin") && filterValue) {
                        System.out.println("Medecin");
                        information.getChildren().clear();
                        Map<String, List<ListePanel>> medecinRequests = new HashMap<>();
                        for (InfoListe info : listRDV) {
                            if (!medecinRequests.containsKey(info.getMedecin())) {
                                medecinRequests.put(info.getMedecin(), new ArrayList<>());
                            }
                            medecinRequests.get(info.getMedecin()).add(new ListePanel(info));
                        }
                        for (String medecinType : medecinRequests.keySet()) {
                            PanelsFiltreListe panelsFiltre = new PanelsFiltreListe(medecinType);
                            panelsFiltre.setContent(medecinRequests.get(medecinType));
                            information.getChildren().add(panelsFiltre);
                        }

                    }
                    if(filterName.equals("Patient") && filterValue) {
                        System.out.println("Patient");
                        information.getChildren().clear();
                        Map<String, List<ListePanel>> patientRequests = new HashMap<>();
                        for (InfoListe info : listRDV) {
                            if (!patientRequests.containsKey(info.getPatient())) {
                                patientRequests.put(info.getPatient(), new ArrayList<>());
                            }
                            patientRequests.get(info.getPatient()).add(new ListePanel(info));
                        }
                        for (String patientType : patientRequests.keySet()) {
                            PanelsFiltreListe panelsFiltre = new PanelsFiltreListe(patientType);
                            panelsFiltre.setContent(patientRequests.get(patientType));
                            information.getChildren().add(panelsFiltre);
                        }
                    }
                    if(filterName.equals("Date") && filterValue) {
                        System.out.println("Date");
                        information.getChildren().clear();
                        Collections.sort(listRDV, new Comparator<InfoListe>() {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

                            @Override
                            public int compare(InfoListe info1, InfoListe info2) {
                                try {
                                    return sdf.parse(info1.getDate()).compareTo(sdf.parse(info2.getDate()));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                    return 0;
                                }
                            }
                        });

                        // Create PanelsFiltre to hold all requests
                        PanelsFiltreListe panelsFiltre = new PanelsFiltreListe("Toutes les requêtes");

                        // Add sorted requests to PanelsFiltre
                        List<ListePanel> requetesPanels = new ArrayList<>();
                        for (InfoListe info : listRDV) {
                            requetesPanels.add(new ListePanel(info));
                        }
                        panelsFiltre.setContent(requetesPanels);
                        information.getChildren().add(panelsFiltre);
                    }
                    if(filterName.equals("Date2") && filterValue) {
                        System.out.println("Date2");
                        information.getChildren().clear();
                        Collections.sort(listRDV, new Comparator<InfoListe>() {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

                            @Override
                            public int compare(InfoListe info1, InfoListe info2) {
                                try {
                                    return sdf.parse(info2.getDate()).compareTo(sdf.parse(info1.getDate()));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                    return 0;
                                }
                            }
                        });

                        // Create PanelsFiltre to hold all requests
                        PanelsFiltreListe panelsFiltre = new PanelsFiltreListe("Toutes les requêtes");

                        // Add sorted requests to PanelsFiltre
                        List<ListePanel> requetesPanels = new ArrayList<>();
                        for (InfoListe info : listRDV) {
                            requetesPanels.add(new ListePanel(info));
                        }
                        panelsFiltre.setContent(requetesPanels);
                        information.getChildren().add(panelsFiltre);
                    }

                    if(filterName.equals("DateDuJour") && filterValue) {
                        System.out.println("DateDuJour");
                        information.getChildren().clear();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                        Date date = new Date();
                        String dateDuJour = sdf.format(date);
                        List<ListePanel> resultats = new ArrayList<>();
                        for (InfoListe info : listRDV) {
                            if (info.getDate().contains(dateDuJour)) {
                                resultats.add(new ListePanel(info));
                            }
                        }
                        if (!resultats.isEmpty()) {
                            PanelsFiltreListe panelsFiltre = new PanelsFiltreListe("Résultats de recherche pour " + dateDuJour);
                            panelsFiltre.setContent(resultats);
                            information.getChildren().add(panelsFiltre);
                        } else {
                            // Gérer le cas où aucune correspondance n'est trouvée
                            System.out.println("Aucun résultat trouvé pour " + dateDuJour);
                        }
                    }
                });
            }
        });

        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                information.getChildren().clear();
                Collections.sort(listRDV, new Comparator<InfoListe>() {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

                    @Override
                    public int compare(InfoListe info1, InfoListe info2) {
                        try {
                            return sdf.parse(info1.getDate()).compareTo(sdf.parse(info2.getDate()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return 0;
                        }
                    }
                });

                // Create PanelsFiltre to hold all requests
                PanelsFiltreListe panelsFiltre = new PanelsFiltreListe("Toutes les requêtes");

                // Add sorted requests to PanelsFiltre
                List<ListePanel> requetesPanels = new ArrayList<>();
                for (InfoListe info : listRDV) {
                    requetesPanels.add(new ListePanel(info));
                }
                panelsFiltre.setContent(requetesPanels);
                information.getChildren().add(panelsFiltre);


            }
        });

        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Bounds buttonBounds = search.localToScreen(search.getBoundsInLocal());
                double buttonX = buttonBounds.getMinX();
                double buttonY = buttonBounds.getMaxY();

                SearchListe dialog = new SearchListe((Stage) getScene().getWindow());
                dialog.setX(buttonX);
                dialog.setY(buttonY);
                dialog.showAndWait().ifPresent(search -> {
                    String searchName = search.getKey();
                    boolean searchValue = search.getValue();

                    if (searchName.equals("Patient") && searchValue) {
                        System.out.println("Patient");
                        information.getChildren().clear();
                        String nom = dialog.getNomPatient();
                        String prenom = dialog.getPrenomPatient();
                        recherchePatient(nom, prenom);


                    }
                    if (searchName.equals("Medecin") && searchValue) {
                        System.out.println("Medecin");
                        information.getChildren().clear();
                        String nom = dialog.getNomMedecin();
                        String prenom = dialog.getPrenomMedecin();
                        rechercheMedecin(nom, prenom);

                    }
                    if (searchName.equals("Patient et Medecin") && searchValue) {
                        System.out.println("Patient et Medecin");
                        information.getChildren().clear();
                        String nom1 = dialog.getNomPatient();
                        String prenom1 = dialog.getPrenomPatient();
                        String nom2 = dialog.getNomMedecin();
                        String prenom2 = dialog.getPrenomMedecin();
                        recherchePatientEtMedecin(nom1, prenom1, nom2, prenom2);
                    }
                    if(searchName.equals("nomPatient") && searchValue) {
                        System.out.println("nomPatient");
                        information.getChildren().clear();
                        String nom = dialog.getNomPatient();
                        for (InfoListe info : listRDV) {
                            if (info.getPatient().contains(nom)) {
                                information.getChildren().add(new ListePanel(info));
                            }
                        }
                    }
                    if(searchName.equals("prenomPatient") && searchValue) {
                        System.out.println("prenomPatient");
                        information.getChildren().clear();
                        String prenom = dialog.getPrenomPatient();
                        for (InfoListe info : listRDV) {
                            if (info.getPatient().contains(prenom)) {
                                information.getChildren().add(new ListePanel(info));
                            }
                        }
                    }
                    if(searchName.equals("nomMedecin") && searchValue) {
                        System.out.println("nomMedecin");
                        information.getChildren().clear();
                        String nom = dialog.getNomMedecin();
                        for (InfoListe info : listRDV) {
                            if (info.getMedecin().contains(nom)) {
                                information.getChildren().add(new ListePanel(info));
                            }
                        }
                    }
                    if(searchName.equals("prenomMedecin") && searchValue) {
                        System.out.println("prenomMedecin");
                        information.getChildren().clear();
                        String prenom = dialog.getPrenomMedecin();
                        for (InfoListe info : listRDV) {
                            if (info.getMedecin().contains(prenom)) {
                                information.getChildren().add(new ListePanel(info));
                            }
                        }
                    }
                    if(searchName.equals("Patient et nomMedecin") && searchValue) {
                        System.out.println("Patient et nomMedecin");
                        information.getChildren().clear();
                        String nom1 = dialog.getNomPatient();
                        String nom2 = dialog.getNomMedecin();
                        for (InfoListe info : listRDV) {
                            if (info.getPatient().contains(nom1) && info.getMedecin().contains(nom2)) {
                                information.getChildren().add(new ListePanel(info));
                            }
                        }
                    }
                    if(searchName.equals("Patient et prenomMedecin") && searchValue) {
                        System.out.println("Patient et prenomMedecin");
                        information.getChildren().clear();
                        String nom1 = dialog.getNomPatient();
                        String prenom2 = dialog.getPrenomMedecin();
                        for (InfoListe info : listRDV) {
                            if (info.getPatient().contains(nom1) && info.getMedecin().contains(prenom2)) {
                                information.getChildren().add(new ListePanel(info));
                            }
                        }
                    }
                    if(searchName.equals("Medecin et nomPatient") && searchValue) {
                        System.out.println("Medecin et nomPatient");
                        information.getChildren().clear();
                        String nom2 = dialog.getNomMedecin();
                        String nom1 = dialog.getNomPatient();
                        for (InfoListe info : listRDV) {
                            if (info.getMedecin().contains(nom2) && info.getPatient().contains(nom1)) {
                                information.getChildren().add(new ListePanel(info));
                            }
                        }
                    }
                    if(searchName.equals("Medecin et prenomPatient") && searchValue) {
                        System.out.println("Medecin et prenomPatient");
                        information.getChildren().clear();
                        String prenom2 = dialog.getPrenomMedecin();
                        String nom1 = dialog.getNomPatient();
                        for (InfoListe info : listRDV) {
                            if (info.getMedecin().contains(prenom2) && info.getPatient().contains(nom1)) {
                                information.getChildren().add(new ListePanel(info));
                            }
                        }
                    }

                    if (searchName.equals("") && !searchValue) {
                        System.out.println("Annuler");
                    }
                });
            }
        });


    }
    public void recherchePatient(String nom, String prenom) {
        information.getChildren().clear();
        List<ListePanel> resultats = new ArrayList<>();
        for (InfoListe info : listRDV) {
            if (info.getPatient().equals(prenom + " " + nom)) {
                resultats.add(new ListePanel(info));
            }
        }
        if (!resultats.isEmpty()) {
            PanelsFiltreListe panelsFiltre = new PanelsFiltreListe("Résultats de recherche pour " + nom + " " + prenom);
            panelsFiltre.setContent(resultats);
            information.getChildren().add(panelsFiltre);
        } else {
            // Gérer le cas où aucune correspondance n'est trouvée
            System.out.println("Aucun résultat trouvé pour " + nom + " " + prenom);
        }
    }
    public void rechercheMedecin(String nom, String prenom) {
        information.getChildren().clear();
        List<ListePanel> resultats = new ArrayList<>();
        for (InfoListe info : listRDV) {
            if (info.getMedecin().equals(prenom + " " + nom)) {
                resultats.add(new ListePanel(info));
            }
        }
        if (!resultats.isEmpty()) {
            PanelsFiltreListe panelsFiltre = new PanelsFiltreListe("Résultats de recherche pour " + nom + " " + prenom);
            panelsFiltre.setContent(resultats);
            information.getChildren().add(panelsFiltre);
        } else {
            // Gérer le cas où aucune correspondance n'est trouvée
            System.out.println("Aucun résultat trouvé pour " + nom + " " + prenom);
        }
    }

    public void recherchePatientEtMedecin(String nom1, String prenom1, String nom2, String prenom2) {
        information.getChildren().clear();
        List<ListePanel> resultats = new ArrayList<>();
        for (InfoListe info : listRDV) {
            if (info.getPatient().equals(prenom1 + " " + nom1)&& info.getMedecin().equals(prenom2 + " " + nom2)){
                resultats.add(new ListePanel(info));
            }
        }
        if (!resultats.isEmpty()) {
            PanelsFiltreListe panelsFiltre = new PanelsFiltreListe("Résultats de recherche:");
            panelsFiltre.setContent(resultats);
            information.getChildren().add(panelsFiltre);
        } else {
            // Gérer le cas où aucune correspondance n'est trouvée
            System.out.println("Aucun résultat trouvé");
        }

    }
}

