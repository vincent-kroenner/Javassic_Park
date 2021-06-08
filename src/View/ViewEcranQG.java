package View;

import Controller.ControllerEcranQG;

import Tools.Path;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class ViewEcranQG {

    private final ControllerEcranQG controllerEcranQG;
    private final Group root;
    public static Button addDino, addSoigneur, gestionEnclos, retour, vuegenerale;
    private Button voliere, aquarium, infirmerie, sud1, sud2, sud3, ouest1, ouest2, nord1, nord2, nord3, est1, est2, est3, qg, quitter;
    private ImageView imgBg, imgBg2;
    private Tooltip tt, tt2, tt3, tt4, tt5, tt6, tt7, tt8, tt9, tt10, tt11, tt12, tt13, tt14, tt15;
    public static GridPane grid;
    public static Rectangle tableauDeBord;

    private String appMess = "-fx-background-color: red;-fx-padding: 5 22 5 22;-fx-text-fill: black;-fx-opacity: 1;";
    private String appTitre = "-fx-background-color: black;-fx-padding: 5 22 5 22;-fx-text-fill: yellow;-fx-opacity: 0.8;";
    private String appLabel = "-fx-background-color: black;-fx-padding: 5 15 5 5;-fx-text-fill: white;-fx-font-weight: bold; -fx-opacity: 0.9";

    //Récupération de la taille de l'écran
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    double hauteurEcran = (int) primaryScreenBounds.getHeight();
    double largeurEcran = (int) primaryScreenBounds.getWidth();

    public ViewEcranQG(Group root, ViewHandler vh, ViewEcranEnclos vee) {
        this.root = root;
        this.controllerEcranQG = new ControllerEcranQG(this, vh, vee);
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

        TextField scenetitle = new TextField("Quartier General d Isla Nublar");
        scenetitle.setFont(policeTitre);
        scenetitle.setStyle(appTitre);
        scenetitle.setEditable(false);
        scenetitle.setPrefWidth(largeurEcran * 0.4);

        addDino = new Button("Concevoir un dinosaure");
        HBox addDinoBtn = new HBox(20);
        addDinoBtn.setAlignment(Pos.CENTER);
        addDinoBtn.getChildren().add(addDino);
        addDino.setOnMouseClicked(controllerEcranQG);
        addDino.setFont(fontButton);
        addDino.setStyle("-fx-background-color: #E7D832ee;");

        addSoigneur = new Button("Recruter un soigneur");
        HBox addSoigneurBtn = new HBox(20);
        addSoigneurBtn.setAlignment(Pos.CENTER);
        addSoigneurBtn.getChildren().add(addSoigneur);
        addSoigneur.setOnMouseClicked(controllerEcranQG);
        addSoigneur.setFont(fontButton);
        addSoigneur.setStyle("-fx-background-color: #E7D832ee;");

        gestionEnclos = new Button("Gestion des enclos");
        HBox gestionBtn = new HBox(20);
        gestionBtn.setAlignment(Pos.CENTER);
        gestionBtn.getChildren().add(gestionEnclos);
        gestionEnclos.setOnMouseClicked(controllerEcranQG);
        gestionEnclos.setFont(fontButton);
        gestionEnclos.setStyle("-fx-background-color: #E7D832ee;");

        vuegenerale = new Button("Vue Générale");
        HBox vuegaleBtn = new HBox(20);
        vuegaleBtn.setAlignment(Pos.CENTER);
        vuegaleBtn.getChildren().add(vuegenerale);
        vuegenerale.setOnMouseClicked(controllerEcranQG);
        vuegenerale.setFont(fontButton);
        vuegenerale.setStyle("-fx-background-color: #E7D832ee;");

        retour = new Button("Retour");
        HBox retourBtn = new HBox(20);
        retourBtn.setAlignment(Pos.BOTTOM_RIGHT);
        retourBtn.getChildren().add(retour);
        retour.setOnMouseClicked(controllerEcranQG);
        retour.setFont(fontButton);
        retour.setStyle("-fx-background-color: #E7D832ee;");

        //Position dans la grille
        grid.add(scenetitle, 1, 1, 3, 1);
        grid.add(addDinoBtn, 3, 4);
        grid.add(addSoigneurBtn, 3, 6);
        grid.add(gestionBtn, 3, 8);
        grid.add(vuegaleBtn, 3, 10);
        grid.add(retourBtn, 1, 14, 3, 1);
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

        voliere.setOnMouseClicked(controllerEcranQG);

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

        aquarium.setOnMouseClicked(controllerEcranQG);

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

        infirmerie.setOnMouseClicked(controllerEcranQG);

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

        sud1.setOnMouseClicked(controllerEcranQG);

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

        sud2.setOnMouseClicked(controllerEcranQG);

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

        sud3.setOnMouseClicked(controllerEcranQG);

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

        ouest1.setOnMouseClicked(controllerEcranQG);

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

        ouest2.setOnMouseClicked(controllerEcranQG);

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

        nord1.setOnMouseClicked(controllerEcranQG);

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

        nord2.setOnMouseClicked(controllerEcranQG);

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

        nord3.setOnMouseClicked(controllerEcranQG);

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

        est1.setOnMouseClicked(controllerEcranQG);

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

        est2.setOnMouseClicked(controllerEcranQG);

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

        est3.setOnMouseClicked(controllerEcranQG);

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

        qg.setOnMouseClicked(controllerEcranQG);

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
        quitter.setOnMouseClicked(controllerEcranQG);

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
        root.getChildren().add(grid);
    }

    public Group getRoot() {
        return root;
    }

    public static Button getAddDino() {
        return addDino;
    }

    public static Button getAddSoigneur() {
        return addSoigneur;
    }

    public static Button getGestionEnclos() {
        return gestionEnclos;
    }

    public static Button getVueGenerale() {return vuegenerale;}

    public static Button getRetour() {
        return retour;
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


