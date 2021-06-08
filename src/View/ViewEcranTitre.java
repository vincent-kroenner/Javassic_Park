package View;

import Controller.ControllerEcranTitre;

import Tools.Music;
import Tools.Path;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class ViewEcranTitre {

    private final ControllerEcranTitre controllerForm;
    private final Group root;
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
    int hauteurEcran = (int) primaryScreenBounds.getHeight();
    int largeurEcran = (int) primaryScreenBounds.getWidth();

    public Button start, quitter;
    public Text scenetitle;

    private ImageView imgBg;
    private ImageView imgBgBk;

    public ViewEcranTitre(Group root, ViewHandler vh) {
        this.root = root;
        this.controllerForm = new ControllerEcranTitre(this, vh);
        initBackground();
        initPage();
        //Music.startMainMenuMusic();
    }

    private void initBackground() {
        imgBg = new ImageView(Path.logo);
        imgBg.setPreserveRatio(true);
        imgBg.setFitHeight(hauteurEcran*0.6);
        imgBg.setTranslateX(largeurEcran*0.28);
        imgBg.setTranslateY(hauteurEcran*0.2);

        imgBgBk = new ImageView(Path.fond_bk);
        imgBgBk.setFitHeight(hauteurEcran);
        imgBgBk.setFitWidth(largeurEcran);
    }

    public void initPage() {
        scenetitle = new Text("Bienvenue dans");
        scenetitle.setFill(Color.YELLOW);
        scenetitle.setStyle("-fx-background-color: none;" +
                "-fx-effect : dropshadow(two-pass-box, black, 6, 2, 2, 2);");
        Font policeTitre = Font.loadFont(getClass().getResourceAsStream(Path.fontHeadCase), largeurEcran * 0.08);
        scenetitle.setFont(policeTitre);
        scenetitle.setTranslateX(largeurEcran * 0.37);
        scenetitle.setTranslateY(hauteurEcran * 0.2);

        start = new Button("Start");
        start.setStyle("-fx-background-color: none;" +
                "-fx-effect : dropshadow(two-pass-box, black, 6, 2, 2, 2);" +
                "-fx-text-fill: yellow;");
        Font policeTitre2 = Font.loadFont(getClass().getResourceAsStream(Path.fontHeadCase), (int) primaryScreenBounds.getWidth() * 0.06);
        start.setFont(policeTitre2);
        start.setTranslateX(largeurEcran * 0.42);
        start.setTranslateY(hauteurEcran * 0.75);
        start.setOnMouseClicked(controllerForm);

        start.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                start.setStyle("-fx-background-color: none; " +
                        "-fx-effect : dropshadow(two-pass-box, white, 6, 2, 2, 2);");
            }
        });

        start.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                start.setStyle("-fx-background-color: none; " +
                        "-fx-effect : dropshadow(two-pass-box, black, 6, 2, 2, 2);" +
                        "-fx-text-fill: yellow;");
            }
        });


        quitter = new Button("X");
        Font policeTitre3 = Font.loadFont(getClass().getResourceAsStream(Path.fontHeadCase), largeurEcran * 0.03);
        quitter.setFont(policeTitre3);
        quitter.setTranslateX(largeurEcran * 0.955);
        quitter.setStyle("-fx-background-color: none;" +
                "-fx-effect : dropshadow(two-pass-box, black, 6, 2, 2, 2);" +
                "-fx-text-fill: red;");
        quitter.setOnMouseClicked(controllerForm);

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
        root.getChildren().add(imgBgBk);
        root.getChildren().add(imgBg);
        root.getChildren().add(quitter);
        root.getChildren().add(scenetitle);
        root.getChildren().add(start);
    }
    public Button getStart() {
        return start;
    }

    public Button getQuitter() {
        return quitter;
    }

}