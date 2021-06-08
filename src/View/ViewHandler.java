package View;

import Tools.Path;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewHandler extends Application {


    private ViewEcranTitre trt;
    private ViewCarte crt;
    private ViewEcranQG qg;

    private ViewFormDino frmd;
    private ViewFormSoigneur frms;
    private ViewGestionEnclos ge;
    private ViewGestionDino gd;
    private ViewGestionSoigneur gs;
    private ViewEcranEnclos ecl;
    private ViewVueGenerale vg;


    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        Scene scene = new Scene(root);
        ViewEcranEnclos viewEcranEnclos = new ViewEcranEnclos(root,this);

        //Récupération de la taille de l'écran
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
        int hauteurEcran = (int) primaryScreenBounds.getHeight();
        int largeurEcran = (int) primaryScreenBounds.getWidth();


        trt = new ViewEcranTitre(root, this);
        crt = new ViewCarte(root, this, viewEcranEnclos);
        qg = new ViewEcranQG(root, this, viewEcranEnclos);
        frmd = new ViewFormDino(root, this, viewEcranEnclos);
        frms = new ViewFormSoigneur(root, this, viewEcranEnclos);
        ge = new ViewGestionEnclos(root, this, viewEcranEnclos);
        gd = new ViewGestionDino(root, this, viewEcranEnclos);
        gs = new ViewGestionSoigneur(root, this, viewEcranEnclos);
        ecl = new ViewEcranEnclos(root, this);
        vg = new ViewVueGenerale(root, this, viewEcranEnclos);

        primaryStage.setScene(scene);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.setTitle("Javassic Park");
        primaryStage.setFullScreenExitHint("");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setFullScreen(false);
        primaryStage.setWidth(largeurEcran);
        primaryStage.setHeight(hauteurEcran);
        primaryStage.show();

        Image image = new Image(Path.cursor);  //pass in the image path
        scene.setCursor(new ImageCursor(image,20,0));

        //afficherGestionDino();
        afficherEcranTitre();
        //afficherEcranQG();
        //afficherFormDino();
        // afficherCarte();
        //afficherVueGenerale();


    }

    public void afficherEcranTitre() {
        trt.setVueForm();
    }

    public void afficherCarte() {
        crt.setVueForm();
    }

    public void afficherEcranQG() {
        qg.setVueForm();
    }

    public void afficherFormDino() {
        frmd.setVueForm();
    }

    public void afficherFormSoigneur() {
        frms.setVueForm();
    }

    public void afficherGestionEnclos() {
        ge.setVueForm();
    }

    public void afficherGestionDino() {
        gd.setVueForm();
    }

    public void afficherGestionSoigneur() {
        gs.setVueForm();
    }

    public void afficherEcranEnclos() {
        ecl.setVueForm();
    }

    public void afficherVueGenerale() {vg.setVueForm();}

}