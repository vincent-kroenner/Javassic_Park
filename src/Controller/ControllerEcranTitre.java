package Controller;

import View.ViewHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import View.ViewEcranTitre;


public class ControllerEcranTitre implements EventHandler<MouseEvent> {

    private ViewEcranTitre vue;
    private ViewHandler vh;

    public ControllerEcranTitre(ViewEcranTitre viewEcranTitre, ViewHandler vh) {
        this.vue = viewEcranTitre;
        this.vh = vh;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getSource().equals(vue.getStart())) {
            vh.afficherCarte();
        } else if (event.getSource().equals(vue.getQuitter())) {
            System.exit(0);
        }
    }
}
