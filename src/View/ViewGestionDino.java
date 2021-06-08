package View;

import Controller.ControllerGestionDino;
import Tools.BDDManager2;
import Tools.Path;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class ViewGestionDino {

    private final ControllerGestionDino controllerGestionDino;
    private final Group root;
    private Button voliere, aquarium, infirmerie, sud1, sud2, sud3, ouest1, ouest2, nord1, nord2, nord3, est1, est2, est3, qg, quitter, affecter, retour;
    private Tooltip tt, tt2, tt3, tt4, tt5, tt6, tt7, tt8, tt9, tt10, tt11, tt12, tt13, tt14, tt15;
    private ImageView imgBg, imgBg2;
    private Label idDino, idEnclos, lbdblon;
    private ComboBox<String> listeDino, listeEnclos;
    public static GridPane grid;
    private TextField scenetitle;

    HashMap<String, Integer> tableEnclos = new HashMap<>();
    HashMap<String, Integer> tableDino = new HashMap<>();

    private String appMess = "-fx-background-color: red;-fx-padding: 5 22 5 22;-fx-text-fill: black;-fx-opacity: 1;";
    private String appMessOK = "-fx-background-color: black;-fx-padding: 5 22 5 22;-fx-text-fill: green;-fx-opacity: 1;";
    private String appTitre = "-fx-background-color: black;-fx-padding: 5 22 5 22;-fx-text-fill: yellow;-fx-opacity: 0.8;";
    private String appLabel = "-fx-background-color: black;-fx-padding: 5 15 5 5;-fx-text-fill: white;-fx-font-weight: bold; -fx-opacity: 0.9";

    //Récupération de la taille de l'écran
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    int hauteurEcran = (int) primaryScreenBounds.getHeight();
    int largeurEcran = (int) primaryScreenBounds.getWidth();


    Font policeTitre = Font.loadFont(getClass().getResourceAsStream(Path.fontHeadCase), largeurEcran * 0.04);


    public ViewGestionDino(Group root, ViewHandler vh, ViewEcranEnclos vee) {
        this.root = root;
        this.controllerGestionDino = new ControllerGestionDino(this, vh, vee);
        initBackground();
        initPage();
        initCarte();
    }

    private void initBackground() {
        imgBg2 = new ImageView(Path.dino);
        imgBg2.setFitHeight(hauteurEcran);
        imgBg2.setPreserveRatio(true);

        imgBg = new ImageView(Path.carte);
        imgBg.setFitHeight(hauteurEcran);
        imgBg.setPreserveRatio(true);
        imgBg.setTranslateX(largeurEcran * 0.58);
    }

    public void initPage() {
        Font fontButton = Font.font("Roboto", FontWeight.NORMAL, largeurEcran * 0.01);
        Font policeTitre = Font.loadFont(getClass().getResourceAsStream(Path.fontHeadCase), largeurEcran * 0.04);
        Font policeLabel = new Font("Roboto", largeurEcran * 0.01);
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setTranslateX( largeurEcran * 0.08 );
        grid.setTranslateY( hauteurEcran * 0.07 );
        grid.setStyle("-fx-background-color: #282828aa;" +
                " -fx-border-color: #E7D832ee;" +
                " -fx-border-width: 1em;");

        //Titre page
        scenetitle = new TextField("Assigner un dinosaure a un enclos");
        scenetitle.setFont(policeTitre);
        scenetitle.setStyle(appTitre);
        scenetitle.setEditable(false);
        scenetitle.setPrefWidth(largeurEcran * 0.4);

        //Message de validation ou erreur
        lbdblon = new Label();
        lbdblon.setMinWidth(400);
        lbdblon.setVisible(false);
        lbdblon.setStyle(appMess);
        lbdblon.setFont(policeTitre);

        idDino = new Label("Dinosaure :");
        idDino.setStyle(appLabel);
        idDino.setFont(policeLabel);


        listeDino = new ComboBox<>();
        BDDManager2 bdd = new BDDManager2();
        bdd.start("jdbc:mysql://localhost:3306/Javassicpark", "root", "");
        ArrayList<ArrayList<String>> resultatListeDino = bdd.select("SELECT `id_dino`,`espece`.`nomEspece`AS NomEspece, dino.* FROM `dino`INNER JOIN espece ON `dino`.`id_espece` = `espece`.`id_espece` ORDER BY dino.nomDino ASC;");
        for (int i = 0; i < resultatListeDino.size(); i++) {
            listeDino.getItems().add(resultatListeDino.get(i).get(3) + " " + resultatListeDino.get(i).get(1));
        }
        for (int i = 0; i < resultatListeDino.size(); i++) {
            tableDino.put(resultatListeDino.get(i).get(3)+ " " + resultatListeDino.get(i).get(1), parseInt(resultatListeDino.get(i).get(0)));
        }
        bdd.stop();


        idEnclos = new Label("Enclos :");
        idEnclos.setStyle(appLabel);
        idEnclos.setFont(policeLabel);

        listeEnclos = new ComboBox<>();
        bdd.start("jdbc:mysql://localhost:3306/Javassicpark", "root", "");
        ArrayList<ArrayList<String>> resultatListeEnclos = bdd.select("SELECT * FROM `enclos`;");

        for (int i = 0; i < resultatListeEnclos.size(); i++) {
            listeEnclos.getItems().add(String.valueOf(resultatListeEnclos.get(i).get(1)));
        }
        for (int i = 0; i < resultatListeEnclos.size(); i++) {
            tableEnclos.put(resultatListeEnclos.get(i).get(1), parseInt(resultatListeEnclos.get(i).get(0)));
        }
        bdd.stop();

        affecter = new Button("Affecter");
        HBox affectBtn = new HBox(20);
        affectBtn.setAlignment(Pos.BOTTOM_RIGHT);
        affectBtn.getChildren().add(affecter);
        affecter.setOnMouseClicked(controllerGestionDino);
        affecter.setFont(fontButton);
        affecter.setStyle("-fx-background-color: #E7D832ee;");

        retour = new Button("Retour");
        HBox retourBtn = new HBox(20);
        retourBtn.setAlignment(Pos.BOTTOM_RIGHT);
        retourBtn.getChildren().add(retour);
        retour.setOnMouseClicked(controllerGestionDino);
        retour.setFont(fontButton);
        retour.setStyle("-fx-background-color: #E7D832ee;");


        //Position dans la grille
        grid.add(scenetitle, 1, 1, 3, 1);
        grid.add(idDino, 1, 3);
        grid.add(listeDino, 2, 3);
        grid.add(idEnclos, 1, 5);
        grid.add(listeEnclos, 2, 5);
        grid.add(affectBtn, 3, 7);
        grid.add(retourBtn, 1, 9, 3, 1);
        grid.add(lbdblon, 1, 8, 3,1);

    }


    private void initCarte() {

        voliere = new Button("");
        tt = new Tooltip();
        tt.setText("Volière");
        tt.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        voliere.setTooltip(tt);
        voliere.setTranslateX(largeurEcran * 0.8);
        voliere.setTranslateY(hauteurEcran * 0.675);
        voliere.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width:" + largeurEcran * 0.042 + ";" +
                        "-fx-min-height:" + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");

        voliere.setOnMouseClicked(controllerGestionDino);

        voliere.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                voliere.setText("Volière");
                voliere.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";");
            }
        });

        voliere.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                voliere.setText("");
                voliere.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        aquarium = new Button("");
        tt2 = new Tooltip();
        tt2.setText("Aquarium");
        tt2.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        aquarium.setTooltip(tt2);
        aquarium.setTranslateX(largeurEcran * 0.91);
        aquarium.setTranslateY(hauteurEcran * 0.565);
        aquarium.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");

        aquarium.setOnMouseClicked(controllerGestionDino);

        aquarium.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                aquarium.setText("");
                aquarium.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";");
            }
        });

        aquarium.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                aquarium.setText("");
                aquarium.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        infirmerie = new Button("");
        tt3 = new Tooltip();
        tt3.setText("Infirmerie");
        tt3.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        infirmerie.setTooltip(tt3);
        infirmerie.setTranslateX(largeurEcran * 0.785);
        infirmerie.setTranslateY(hauteurEcran * 0.512);
        infirmerie.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");

        infirmerie.setOnMouseClicked(controllerGestionDino);

        infirmerie.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                infirmerie.setText("Infirmerie");
                infirmerie.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";");
            }
        });

        infirmerie.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                infirmerie.setText("");
                infirmerie.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        sud1 = new Button("");
        tt4 = new Tooltip();
        tt4.setText("Sud 1");
        tt4.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        sud1.setTooltip(tt4);
        sud1.setTranslateX(largeurEcran * 0.792);
        sud1.setTranslateY(hauteurEcran * 0.8);
        sud1.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");

        sud1.setOnMouseClicked(controllerGestionDino);

        sud1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                sud1.setText("Sud 1");
                sud1.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";");
            }
        });

        sud1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                sud1.setText("");
                sud1.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        sud2 = new Button("");
        tt5 = new Tooltip();
        tt5.setText("Sud 2");
        tt5.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        sud2.setTooltip(tt5);
        sud2.setTranslateX(largeurEcran * 0.84);
        sud2.setTranslateY(hauteurEcran * 0.6);
        sud2.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width:" + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");

        sud2.setOnMouseClicked(controllerGestionDino);

        sud2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                sud2.setText("Sud 2");
                sud2.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";");
            }
        });

        sud2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                sud2.setText("");
                sud2.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        sud3 = new Button("");
        tt6 = new Tooltip();
        tt6.setText("Sud 3");
        tt6.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        sud3.setTooltip(tt6);
        sud3.setTranslateX(largeurEcran * 0.84);
        sud3.setTranslateY(hauteurEcran * 0.522);
        sud3.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");

        sud3.setOnMouseClicked(controllerGestionDino);

        sud3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                sud3.setText("Sud 3");
                sud3.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";");
            }
        });

        sud3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                sud3.setText("");
                sud3.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        ouest1 = new Button("");
        tt7 = new Tooltip();
        tt7.setText("Ouest 1");
        tt7.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        ouest1.setTooltip(tt7);
        ouest1.setTranslateX(largeurEcran * 0.665);
        ouest1.setTranslateY(hauteurEcran * 0.292);
        ouest1.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");

        ouest1.setOnMouseClicked(controllerGestionDino);

        ouest1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ouest1.setText("Ouest 1");
                ouest1.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";");
            }
        });

        ouest1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ouest1.setText("");
                ouest1.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        ouest2 = new Button("");
        tt8 = new Tooltip();
        tt8.setText("Ouest 2");
        tt8.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        ouest2.setTooltip(tt8);
        ouest2.setTranslateX(largeurEcran * 0.73);
        ouest2.setTranslateY(hauteurEcran * 0.318);
        ouest2.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height:" + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");

        ouest2.setOnMouseClicked(controllerGestionDino);

        ouest2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ouest2.setText("Ouest 2");
                ouest2.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height:" + largeurEcran * 0.042 + ";");
            }
        });

        ouest2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                ouest2.setText("");
                ouest2.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        nord1 = new Button("");
        tt9 = new Tooltip();
        tt9.setText("Nord 1");
        tt9.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        nord1.setTooltip(tt9);
        nord1.setTranslateX(largeurEcran * 0.795);
        nord1.setTranslateY(hauteurEcran * 0.304);
        nord1.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");

        nord1.setOnMouseClicked(controllerGestionDino);

        nord1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                nord1.setText("Nord 1");
                nord1.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-min-width:" + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";");
            }
        });

        nord1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                nord1.setText("");
                nord1.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height:" + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        nord2 = new Button("");
        tt10 = new Tooltip();
        tt10.setText("Nord 2");
        tt10.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        nord2.setTooltip(tt10);
        nord2.setTranslateX(largeurEcran * 0.86);
        nord2.setTranslateY(hauteurEcran * 0.173);
        nord2.setStyle(
                "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");

        nord2.setOnMouseClicked(controllerGestionDino);

        nord2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                nord2.setText("Nord 2");
                nord2.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";");
            }
        });

        nord2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                nord2.setText("");
                nord2.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        nord3 = new Button("");
        tt11 = new Tooltip();
        tt11.setText("Nord 3");
        tt11.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        nord3.setTooltip(tt11);
        nord3.setTranslateX(largeurEcran * 0.823);
        nord3.setTranslateY(hauteurEcran * 0.11);
        nord3.setStyle("-fx-background-radius: 5em; " +
                "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                "-fx-background-color: transparent;");

        nord3.setOnMouseClicked(controllerGestionDino);

        nord3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                nord3.setText("Nord 3");
                nord3.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width:" + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";");
            }
        });

        nord3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                nord3.setText("");
                nord3.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        est1 = new Button("");
        tt12 = new Tooltip();
        tt12.setText("Est 1");
        tt12.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        est1.setTooltip(tt12);
        est1.setTranslateX(largeurEcran * 0.887);
        est1.setTranslateY(hauteurEcran * 0.292);
        est1.setStyle("-fx-background-radius: 5em; " +
                "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                "-fx-background-color: transparent;");

        est1.setOnMouseClicked(controllerGestionDino);

        est1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                est1.setText("Est 1");
                est1.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";");
            }
        });

        est1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                est1.setText("");
                est1.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        est2 = new Button("");
        tt13 = new Tooltip();
        tt13.setText("Est 2");
        tt13.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        est2.setTooltip(tt13);
        est2.setTranslateX(largeurEcran * 0.867);
        est2.setTranslateY(hauteurEcran * 0.353);
        est2.setStyle("-fx-background-radius: 5em; " +
                "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                "-fx-background-color: transparent;");

        est2.setOnMouseClicked(controllerGestionDino);

        est2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                est2.setText("Est 2");
                est2.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";");
            }
        });

        est2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                est2.setText("");
                est2.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        est3 = new Button("");
        tt14 = new Tooltip();
        tt14.setText("Est 3");
        tt14.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        est3.setTooltip(tt14);
        est3.setTranslateX(largeurEcran * 0.92);
        est3.setTranslateY(hauteurEcran * 0.4);
        est3.setStyle("-fx-background-radius: 5em; " +
                "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                "-fx-background-color: transparent;");

        est3.setOnMouseClicked(controllerGestionDino);

        est3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                est3.setText("Est 3");
                est3.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height:" + largeurEcran * 0.042 + ";");
            }
        });

        est3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                est3.setText("");
                est3.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.042 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.042 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        qg = new Button("");
        tt15 = new Tooltip();
        tt15.setText("Quartier général");
        tt15.setStyle("-fx-font: normal bold 18 arial; -fx-text-fill: orange;");
        qg.setTooltip(tt15);
        qg.setTranslateX(largeurEcran * 0.7);
        qg.setTranslateY(hauteurEcran * 0.4);
        qg.setStyle("-fx-background-radius: 5em; " +
                "-fx-min-width: " + largeurEcran * 0.052 + ";" +
                "-fx-min-height: " + largeurEcran * 0.052 + ";" +
                "-fx-max-width: " + largeurEcran * 0.052 + ";" +
                "-fx-max-height: " + largeurEcran * 0.052 + ";" +
                "-fx-background-color: transparent;");

        qg.setOnMouseClicked(controllerGestionDino);

        qg.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                qg.setText("QG");
                qg.setStyle("-fx-background-color: none; " +
                        "-fx-text-fill: yellow;" +
                        "-fx-font-size:1.5em;" +
                        "-fx-min-width: " + largeurEcran * 0.052 + ";" +
                        "-fx-min-height: " + largeurEcran * 0.052 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.052 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.052 + ";" +
                        "-fx-border-style: solid;" +
                        "-fx-border-color: yellow;" +
                        "-fx-border-radius: 5em;" +
                        "-fx-border-width: " + largeurEcran * 0.0052 + ";");
            }
        });

        qg.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                qg.setText("");
                qg.setStyle("-fx-background-radius: 5em; " +
                        "-fx-min-width: " + largeurEcran * 0.052 + ";" +
                        "-fx-min-height:" + largeurEcran * 0.052 + ";" +
                        "-fx-max-width: " + largeurEcran * 0.052 + ";" +
                        "-fx-max-height: " + largeurEcran * 0.052 + ";" +
                        "-fx-background-color: transparent;");
            }
        });

        quitter = new Button("X");
        Font policeTitre2 = Font.loadFont(getClass().getResourceAsStream(Path.fontHeadCase), largeurEcran * 0.03);
        quitter.setFont(policeTitre2);
        quitter.setTranslateX(largeurEcran * 0.955);
        quitter.setStyle("-fx-background-color: none;" +
                "-fx-effect : dropshadow(two-pass-box, black, 6, 2, 2, 2);" +
                "-fx-text-fill: red;");
        quitter.setOnMouseClicked(controllerGestionDino);

        quitter.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                quitter.setStyle("-fx-background-color: none; " +
                        "-fx-effect : dropshadow(two-pass-box, white, 6, 2, 2, 2);");
            }
        });

        quitter.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                quitter.setStyle("-fx-background-color: none; " +
                        "-fx-effect : dropshadow(two-pass-box, black, 6, 2, 2, 2);" +
                        "-fx-text-fill: red;");
            }
        });
    }

    public void setVueForm() {
        root.getChildren().clear();
        root.getChildren().add(imgBg2);
        root.getChildren().add(imgBg);
        root.getChildren().add(voliere);
        root.getChildren().add(aquarium);
        root.getChildren().add(infirmerie);
        root.getChildren().add(nord1);
        root.getChildren().add(nord2);
        root.getChildren().add(nord3);
        root.getChildren().add(est1);
        root.getChildren().add(est2);
        root.getChildren().add(est3);
        root.getChildren().add(ouest1);
        root.getChildren().add(ouest2);
        root.getChildren().add(sud1);
        root.getChildren().add(sud2);
        root.getChildren().add(sud3);
        root.getChildren().add(qg);
        root.getChildren().add(quitter);
        initPage();
        root.getChildren().add(grid);
    }

    public ControllerGestionDino getControllerGestionDino() {
        return controllerGestionDino;
    }

    public Group getRoot() {
        return root;
    }

    public void setTextLbdblon(String txtlbdblon) {
        this.lbdblon.setText(txtlbdblon);
        if (txtlbdblon.contains("REUSSIE")) {
            this.lbdblon.setStyle(appMessOK);
        } else {
            this.lbdblon.setStyle(appMess);
        }
    }
    public Integer getIDEnclos() {
        return tableEnclos.get(listeEnclos.getValue());
    }
    public String getNomEnclos(){
        return listeEnclos.getValue();
    }
    public Integer getIDDinos() {
        return tableDino.get(listeDino.getValue());
    }
    public String getNomEspece(){
        String chaine=listeDino.getValue();
        int index= chaine.indexOf(' ');
        return chaine.substring(index+1);
    }

    public void setVisuLbdblon(boolean visuLbdblon) {
        this.lbdblon.setVisible(visuLbdblon);
    }

    public Button getRetour() {
        return retour;
    }

    public Button getAffecter() {
        return affecter;
    }

    public Button getVoliere() {
        return voliere;
    }

    public Button getAquarium() {
        return aquarium;
    }

    public Button getInfirmerie() {
        return infirmerie;
    }

    public Button getSud1() {
        return sud1;
    }

    public Button getSud2() {
        return sud2;
    }

    public Button getSud3() {
        return sud3;
    }

    public Button getOuest1() {
        return ouest1;
    }

    public Button getOuest2() {
        return ouest2;
    }

    public Button getNord1() {
        return nord1;
    }

    public Button getNord2() {
        return nord2;
    }

    public Button getNord3() {
        return nord3;
    }

    public Button getEst1() {
        return est1;
    }

    public Button getEst2() {
        return est2;
    }

    public Button getEst3() {
        return est3;
    }

    public Button getQg() {
        return qg;
    }

    public Button getQuitter() {
        return quitter;
    }
}
