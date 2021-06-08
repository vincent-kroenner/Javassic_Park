package View;

import Controller.ControllerEcranEnclos;
import Tools.Path;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import Tools.BDDManager2;

public class ViewEcranEnclos {

    private final ControllerEcranEnclos controllerEcranEnclos;
    private Group root;
    private Button voliere, aquarium, infirmerie, sud1, sud2, sud3, ouest1, ouest2, nord1, nord2, nord3, est1, est2, est3, qg, quitter;
    private ImageView imgBg, imgBg2;
    private Tooltip tt, tt2, tt3, tt4, tt5, tt6, tt7, tt8, tt9, tt10, tt11, tt12, tt13, tt14, tt15;
    public static GridPane grid;
    public static GridPane grid2;

    private TextField scenetitle, nomEnclos,capmaxEnclos,capmactEnclos,taillemaxdinoEnclos,milieuEnclos;
    private ArrayList<Text> listeDino;
    private ArrayList<Button> listeButtonUpdate, listeButtonDelete;

    private ArrayList<Button> listeButtonUpdateSoigneur, listeButtonDeleteSoigneur;
    private TextField soigneur_nom, soigneur_prenom, soigneur_age, soigneur_sexe, soigneur_salaire, soigneur_commentaire;
    private ArrayList<TextField> liste_nom, liste_prenom, liste_age, liste_sexe, liste_salaire, liste_commentaire , liste_id_dino, liste_id_soigneur;

    private Label alert;

    private ArrayList<TextField> listresu_dinos;
    private ArrayList<TextField> listresu_noms;
    private TextField dinos_nom, dino_id, soigneur_id;
    private ArrayList<TextField> listresu_ages;
    private TextField dinos_ages;
    //private ArrayList<TextField> listresu_malades;
    //private TextField dinos_malades;
    private ArrayList<TextField> listresu_caracteres;
    private TextField dinos_caracteres;
    private ArrayList<TextField> listresu_enclos;
    private TextField dinos_enclos;
    private ArrayList<TextField> listresu_especes;
    private TextField dinos_especes;
    private ArrayList<CheckBox> listresu_malades;
    private CheckBox dinos_malades;

    HashMap<Integer, String> tableEspece = new HashMap<>();
    HashMap<Integer, String> tableEnclos = new HashMap<>();

    BDDManager2 bdd = new BDDManager2();

    //Récupération de la taille de l'écran
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    int hauteurEcran = (int) primaryScreenBounds.getHeight();
    int largeurEcran = (int) primaryScreenBounds.getWidth();

    int nmEnclos;


    Font policeTitre = Font.loadFont(getClass().getResourceAsStream(Path.fontHeadCase), largeurEcran * 0.04);

    private String appMess = "-fx-background-color: red;-fx-padding: 5 22 5 22;-fx-text-fill: black;-fx-opacity: 1;";
    private String appTitre = "-fx-background-color: black;-fx-padding: 5 22 5 22;-fx-text-fill: yellow;-fx-opacity: 0.8;";
    private String appLabel = "-fx-background-color: yellow;-fx-padding: 5 15 5 5;-fx-text-fill: black;-fx-font-weight: bold; -fx-opacity: 0.9";
    private String appcase = "-fx-background-color: white;-fx-padding: 5 15 5 5;-fx-text-fill: black;-fx-font-weight: bold; -fx-opacity: 0.9";

    public ViewEcranEnclos(Group root, ViewHandler vh) {
        this.root = root;
        this.controllerEcranEnclos = new ControllerEcranEnclos(this, vh);
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

        grid = new GridPane();
        //grid.setAlignment(Pos.CENTER);
        //grid.setHgap(10);
        //grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setTranslateX(largeurEcran * 0.02);
        grid.setTranslateY(hauteurEcran * 0.05);
        grid.setStyle("-fx-background-color: #282828aa;" +
                " -fx-border-color: #E7D832ee;" +
                " -fx-border-width: 1em;");


        //Titre page
        scenetitle = new TextField("Vue Enclos");
        scenetitle.setFont(policeTitre);
        scenetitle.setStyle(appTitre);
        scenetitle.setEditable(false);
        scenetitle.setPrefWidth(largeurEcran * 0.4);
        grid.add(scenetitle, 0, 0, 7, 1);


        //Liste dino dans cet enclos
        BDDManager2 bdd = new BDDManager2();
        grid2 = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        grid2.setPadding(new Insets(40, 10, 10, 10));
        ColumnConstraints col0 = new ColumnConstraints((int) primaryScreenBounds.getWidth()*0.08);
        ColumnConstraints col1 = new ColumnConstraints((int) primaryScreenBounds.getWidth()*0.05);
        ColumnConstraints col2 = new ColumnConstraints((int) primaryScreenBounds.getWidth()*0.05);
        ColumnConstraints col3 = new ColumnConstraints((int) primaryScreenBounds.getWidth()*0.08);
        ColumnConstraints col4 = new ColumnConstraints((int) primaryScreenBounds.getWidth()*0.12);
        //ColumnConstraints col5 = new ColumnConstraints((int) primaryScreenBounds.getWidth()*0.12);


        grid.getColumnConstraints().addAll(col0,col1,col2,col3,col4);

        TextField nomg = new TextField("Nom");
        nomg.setStyle(appLabel);
        nomg.setAlignment(Pos.CENTER);
        nomg.setEditable(false);
        TextField ageg = new TextField("Age");
        ageg.setStyle(appLabel);
        ageg.setAlignment(Pos.CENTER);
        ageg.setEditable(false);
        TextField regimeg = new TextField("Malade");
        regimeg.setStyle(appLabel);
        regimeg.setAlignment(Pos.CENTER);
        regimeg.setEditable(false);
        TextField caractg = new TextField("Caractère");
        caractg.setStyle(appLabel);
        caractg.setAlignment(Pos.CENTER);
        caractg.setEditable(false);

        TextField especsg = new TextField("Espece");
        especsg.setStyle(appLabel);
        especsg.setAlignment(Pos.CENTER);
        especsg.setEditable(false);
        TextField action = new TextField("Action");
        action.setStyle(appLabel);
        action.setAlignment(Pos.CENTER);
        action.setEditable(false);



        grid.add(nomg,0,2);
        grid.add(ageg,1,2);
        grid.add(regimeg,2,2);
        grid.add(caractg,3,2);
        //grid2.add(milieug,4,0);
        grid.add(especsg,4,2);
        grid.add(action,5,2,2,1);

        /*"SELECT `soigneur`.*, `soigneur_enclos`.*\n" +
                "FROM `soigneur` \n" +
                "\tLEFT JOIN `soigneur_enclos` ON `soigneur_enclos`.`id_soigneur` = `soigneur`.`id_soigneur` WHERE id_enclos = " + nmEnclos + ";";*/

        bdd.start("jdbc:mysql://localhost:3306/Javassicpark", "root", "");
        ArrayList<ArrayList<String>> listeEnclos = bdd.select("SELECT id_enclos, libelleEnclos FROM ENCLOS;" );
        for (int i = 0; i < listeEnclos.size(); i++) {
            tableEnclos.put(Integer.parseInt(listeEnclos.get(i).get(0)), listeEnclos.get(i).get(1));
        }
        ArrayList<ArrayList<String>> listeEspeces = bdd.select("SELECT id_espece, nomEspece FROM ESPECE;" );
        for (int i = 0; i < listeEspeces.size(); i++) {
            tableEspece.put(Integer.parseInt(listeEspeces.get(i).get(0)), listeEspeces.get(i).get(1));
        }
        if(nmEnclos!=0) {
            ArrayList<ArrayList<String>> statusEnclos = bdd.select("SELECT * FROM ENCLOS WHERE id_enclos = " + nmEnclos + ";");
            System.out.println(statusEnclos);
            nomEnclos = new TextField(statusEnclos.get(0).get(1));
            nomEnclos.setEditable(false);
            capmaxEnclos = new TextField("Capacité maximum : "+statusEnclos.get(0).get(2));
            capmaxEnclos.setEditable(false);
            capmactEnclos = new TextField("Nb places disponibles : "+(Integer.parseInt(statusEnclos.get(0).get(2))-Integer.parseInt(statusEnclos.get(0).get(3))));
            capmactEnclos.setEditable(false);
            taillemaxdinoEnclos = new TextField("Gabarit max dino : "+statusEnclos.get(0).get(4));
            taillemaxdinoEnclos.setEditable(false);
            milieuEnclos = new TextField("Environnement : "+statusEnclos.get(0).get(5));
            milieuEnclos.setEditable(false);
            grid.add(nomEnclos, 2, 0, 2, 1);
            grid.add(capmaxEnclos, 0, 1, 2, 1);
            grid.add(capmactEnclos, 4, 0, 1, 1);
            grid.add(taillemaxdinoEnclos, 2, 1, 2, 1);
            grid.add(milieuEnclos, 4, 1, 1, 1);
        }
        bdd.stop();


        bdd.start("jdbc:mysql://localhost:3306/javassicpark", "root", "");
        String requete= "SELECT * FROM dino WHERE id_enclos = " + nmEnclos + ";";
        ArrayList<ArrayList<String>> resureq;
        resureq=bdd.select(requete);
        liste_id_dino = new ArrayList<>();
        listresu_noms = new ArrayList<>();
        listresu_ages = new ArrayList<>();
        listresu_malades = new ArrayList<>();
        listresu_caracteres = new ArrayList<>();
        listresu_enclos = new ArrayList<>();
        listresu_especes = new ArrayList<>();
        listeButtonUpdate = new ArrayList<>();
        listeButtonDelete = new ArrayList<>();
        for (int i = 0; i < resureq.size(); i++) {

            dino_id = new TextField();
            dino_id.setText(resureq.get(i).get(0));
            liste_id_dino.add(i, dino_id);
;
            dinos_nom= new TextField();
            dinos_nom.setStyle("-fx-border-color: black; -fx-border-width: 2;");
            dinos_nom.setText(resureq.get(i).get(1));
            dinos_nom.setAlignment(Pos.CENTER_LEFT);
            dinos_nom.setStyle(appcase);
            dinos_nom.setEditable(false);
            listresu_noms.add(i, dinos_nom);
            grid.add(dinos_nom,0,i+3);

            dinos_ages= new TextField();
            dinos_ages.setStyle("-fx-border-color: black; -fx-border-width: 2;");
            dinos_ages.setText(resureq.get(i).get(2));
            dinos_ages.setAlignment(Pos.CENTER);
            dinos_ages.setStyle(appcase);
            listresu_ages.add(i, dinos_ages);
            grid.add(dinos_ages,1,i+3);

            dinos_malades=new CheckBox("O/N");
            dinos_malades.setStyle(appcase);
            dinos_malades.setAlignment(Pos.CENTER_LEFT);
            if(Integer.parseInt(resureq.get(i).get(3))==0){
                dinos_malades.setSelected(false);
            } else {dinos_malades.setSelected(true);}
            listresu_malades.add(i, dinos_malades);
            grid.add(dinos_malades,2,i+3);

            dinos_caracteres= new TextField();
            dinos_caracteres.setStyle("-fx-border-color: black; -fx-border-width: 2;");
            dinos_caracteres.setText(resureq.get(i).get(4));
            dinos_caracteres.setAlignment(Pos.CENTER);
            dinos_caracteres.setStyle(appcase);
            listresu_caracteres.add(i, dinos_caracteres);
            grid.add(dinos_caracteres,3,i+3);

            dinos_especes= new TextField();
            dinos_especes.setStyle("-fx-border-color: black; -fx-border-width: 2;");
            dinos_especes.setText(tableEspece.get(Integer.parseInt(resureq.get(i).get(6))));
            dinos_especes.setAlignment(Pos.CENTER);
            dinos_especes.setStyle(appcase);
            dinos_especes.setEditable(false);
            listresu_especes.add(i, dinos_especes);
            grid.add(dinos_especes,4,i+3);

            listeButtonUpdate.add(new Button("Modifier"));
            grid.add(listeButtonUpdate.get(i), 5, i + 3);
            listeButtonUpdate.get(i).setUserData(i);
            listeButtonUpdate.get(i).setOnMouseClicked(controllerEcranEnclos);

            listeButtonDelete.add(new Button("X"));
            grid.add(listeButtonDelete.get(i), 6, i + 3);
            listeButtonDelete.get(i).setOnMouseClicked(controllerEcranEnclos);

        }
        bdd.stop();

        TextField nomsoi = new TextField("Nom");
        nomsoi.setStyle(appLabel);
        nomsoi.setAlignment(Pos.CENTER);
        nomsoi.setEditable(false);
        TextField prenomsoi = new TextField("Prénom");
        prenomsoi.setStyle(appLabel);
        prenomsoi.setAlignment(Pos.CENTER);
        prenomsoi.setEditable(false);
        TextField agesoi = new TextField("Age");
        agesoi.setStyle(appLabel);
        agesoi.setAlignment(Pos.CENTER);
        agesoi.setEditable(false);
        TextField salairesoi = new TextField("Salaire");
        salairesoi.setStyle(appLabel);
        salairesoi.setAlignment(Pos.CENTER);
        salairesoi.setEditable(false);
        TextField commentairesoi = new TextField("Commentaire");
        commentairesoi.setStyle(appLabel);
        commentairesoi.setAlignment(Pos.CENTER);
        commentairesoi.setEditable(false);
        TextField actionsoi = new TextField("Action");
        actionsoi.setStyle(appLabel);
        actionsoi.setAlignment(Pos.CENTER);
        actionsoi.setEditable(false);

        grid.add(nomsoi, 0, 20);
        grid.add(prenomsoi, 1, 20);
        grid.add(agesoi, 2, 20);
        grid.add(salairesoi, 3, 20);
        grid.add(commentairesoi, 4, 20);
        grid.add(actionsoi, 5, 20, 2, 1);


        bdd.start("jdbc:mysql://localhost:3306/javassicpark", "root", "");
        String requetes = "SELECT `soigneur`.*, `soigneur_enclos`.*\n" +
                "FROM `soigneur` \n" +
                "\tLEFT JOIN `soigneur_enclos` ON `soigneur_enclos`.`id_soigneur` = `soigneur`.`id_soigneur` WHERE id_enclos = " + nmEnclos + ";";
        ArrayList<ArrayList<String>> resureqs;
        resureqs = bdd.select(requetes);
        liste_id_soigneur = new ArrayList<>();
        liste_nom = new ArrayList<>();
        liste_prenom = new ArrayList<>();
        liste_age = new ArrayList<>();
        liste_sexe = new ArrayList<>();
        liste_salaire = new ArrayList<>();
        liste_commentaire = new ArrayList<>();
        listeButtonUpdateSoigneur = new ArrayList<>();
        listeButtonDeleteSoigneur = new ArrayList<>();
        for (int i = 0; i < resureqs.size(); i++) {

            soigneur_id = new TextField();
            soigneur_id.setText(resureqs.get(i).get(0));
            liste_id_soigneur.add(i, soigneur_id);

            soigneur_nom = new TextField();
            soigneur_nom.setStyle("-fx-border-color: black; -fx-border-width: 2;");
            soigneur_nom.setText(resureqs.get(i).get(1));
            soigneur_nom.setAlignment(Pos.CENTER_LEFT);
            soigneur_nom.setStyle(appcase);
            soigneur_nom.setEditable(false);
            liste_nom.add(i, soigneur_nom);
            grid.add(soigneur_nom, 0, i + 21);

            soigneur_prenom = new TextField();
            soigneur_prenom.setStyle("-fx-border-color: black; -fx-border-width: 2;");
            soigneur_prenom.setText(resureqs.get(i).get(2));
            soigneur_prenom.setAlignment(Pos.CENTER);
            soigneur_prenom.setStyle(appcase);
            soigneur_prenom.setEditable(false);
            liste_prenom.add(i, soigneur_prenom);
            grid.add(soigneur_prenom, 1, i + 21);

            soigneur_age = new TextField();
            soigneur_age.setStyle("-fx-border-color: black; -fx-border-width: 2;");
            soigneur_age.setText(resureqs.get(i).get(3));
            soigneur_age.setAlignment(Pos.CENTER);
            soigneur_age.setStyle(appcase);
            liste_age.add(i, soigneur_age);
            grid.add(soigneur_age, 2, i + 21);

            soigneur_salaire = new TextField();
            soigneur_salaire.setStyle("-fx-border-color: black; -fx-border-width: 2;");
            soigneur_salaire.setText(resureqs.get(i).get(5));
            soigneur_salaire.setAlignment(Pos.CENTER);
            soigneur_salaire.setStyle(appcase);
            liste_salaire.add(i, soigneur_salaire);
            grid.add(soigneur_salaire, 3, i + 21);

            soigneur_commentaire = new TextField();
            soigneur_commentaire.setStyle("-fx-border-color: black; -fx-border-width: 2;");
            soigneur_commentaire.setText(resureqs.get(i).get(6));
            soigneur_commentaire.setAlignment(Pos.CENTER);
            soigneur_commentaire.setStyle(appcase);
            liste_commentaire.add(i, soigneur_commentaire);
            grid.add(soigneur_commentaire, 4, i + 21);

            listeButtonUpdateSoigneur.add(new Button("Modifier"));
            grid.add(listeButtonUpdateSoigneur.get(i), 5, i + 21);
            listeButtonUpdateSoigneur.get(i).setOnMouseClicked(controllerEcranEnclos);

            listeButtonDeleteSoigneur.add(new Button("X"));
            grid.add(listeButtonDeleteSoigneur.get(i), 6, i + 21);
            listeButtonDeleteSoigneur.get(i).setOnMouseClicked(controllerEcranEnclos);

        }
        bdd.stop();
    }
    public boolean showConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention ! La suppression est définitive !");
        alert.setHeaderText("Confirmez-vous la suppression ci-dessous ?");
        alert.setContentText(controllerEcranEnclos.getdinoDelete());
        boolean confirmation=false;
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            confirmation=true;
        } else if (option.get() == ButtonType.CANCEL) {
            confirmation=false;
        }
        return confirmation;
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

        voliere.setOnMouseClicked(controllerEcranEnclos);

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

        aquarium.setOnMouseClicked(controllerEcranEnclos);

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

        infirmerie.setOnMouseClicked(controllerEcranEnclos);

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

        sud1.setOnMouseClicked(controllerEcranEnclos);

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

        sud2.setOnMouseClicked(controllerEcranEnclos);

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

        sud3.setOnMouseClicked(controllerEcranEnclos);

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

        ouest1.setOnMouseClicked(controllerEcranEnclos);

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

        ouest2.setOnMouseClicked(controllerEcranEnclos);

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

        nord1.setOnMouseClicked(controllerEcranEnclos);

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

        nord2.setOnMouseClicked(controllerEcranEnclos);

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

        nord3.setOnMouseClicked(controllerEcranEnclos);

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

        est1.setOnMouseClicked(controllerEcranEnclos);

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

        est2.setOnMouseClicked(controllerEcranEnclos);

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

        est3.setOnMouseClicked(controllerEcranEnclos);

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

        qg.setOnMouseClicked(controllerEcranEnclos);

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
        quitter.setOnMouseClicked(controllerEcranEnclos);

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
        root.getChildren().add(grid);
        root.getChildren().add(quitter);
    }

    public void setnmEnclos (int nmEnclo){
        this.nmEnclos = nmEnclo;
    }

    public int getNmEnclos(){
        return nmEnclos;
    }

    public ArrayList<TextField> getListe_id_dino() {
        return liste_id_dino;
    }

    public ArrayList<TextField> getListe_age() {
        return liste_age;
    }

    public ArrayList<TextField> getListe_salaire() {
        return liste_salaire;
    }

    public ArrayList<TextField> getListe_commentaire() {
        return liste_commentaire;
    }

    public ArrayList<TextField> getListresu_ages() {
        return listresu_ages;
    }
    public ArrayList<TextField> getListresu_noms() {
        return listresu_noms;
    }

    public ArrayList<TextField> getListresu_caracteres() {
        return listresu_caracteres;
    }

    public ArrayList<CheckBox> getListresu_malades() {return listresu_malades;}

    public ArrayList<TextField> getListe_id_soigneur() {
        return liste_id_soigneur;
    }

    public ControllerEcranEnclos getControllerEcranEnclos() {
        return controllerEcranEnclos;
    }

    public ArrayList<Button> getListeButtonUpdateSoigneur() {
        return listeButtonUpdateSoigneur;
    }

    public ArrayList<Button> getListeButtonDeleteSoigneur() {
        return listeButtonDeleteSoigneur;
    }

    public ArrayList<Button> getListeButtonUpdate() {
        return listeButtonUpdate;
    }

    public ArrayList<Button> getListeButtonDelete() {
        return listeButtonDelete;
    }

    public Group getRoot() { return root; }

    public void setRoot(Group root) {
        this.root = root;
    }

    public Button getVoliere() {
        return voliere;
    }

    public Button getAquarium() { return aquarium; }

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