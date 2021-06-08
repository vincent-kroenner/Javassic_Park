package Controller;

import Tools.BDDManager2;
import View.ViewEcranEnclos;
import View.ViewGestionSoigneur;
import View.ViewHandler;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControllerGestionSoigneur implements EventHandler<MouseEvent> {

    private ViewGestionSoigneur vue;
    private ViewEcranEnclos vueEnclos;
    private ViewHandler vh;
    private BDDManager2 bddmanager = new BDDManager2();
    public Label lbdblon;

    public ControllerGestionSoigneur(ViewGestionSoigneur viewGestionSoigneur, ViewHandler vh, ViewEcranEnclos vee) {
        this.vue = viewGestionSoigneur;
        this.vh = vh;
        this.vueEnclos = vee;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getSource().equals(vue.getQuitter())) {
            System.exit(0);
        } else if (event.getSource().equals(vue.getVoliere())) {
            vueEnclos.setnmEnclos(1);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getAquarium())) {
            vueEnclos.setnmEnclos(2);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getInfirmerie())) {
            vueEnclos.setnmEnclos(3);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getEst1())) {
            vueEnclos.setnmEnclos(12);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getEst2())) {
            vueEnclos.setnmEnclos(13);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getEst3())) {
            vueEnclos.setnmEnclos(14);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getNord1())) {
            vueEnclos.setnmEnclos(9);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getNord2())) {
            vueEnclos.setnmEnclos(10);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getNord3())) {
            vueEnclos.setnmEnclos(11);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getOuest1())) {
            vueEnclos.setnmEnclos(7);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getOuest2())) {
            vueEnclos.setnmEnclos(8);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getSud1())) {
            vueEnclos.setnmEnclos(4);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getSud2())) {
            vueEnclos.setnmEnclos(5);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getSud3())) {
            vueEnclos.setnmEnclos(6);
            vueEnclos.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getQg())) {
            vh.afficherEcranQG();
        } else if (event.getSource().equals(vue.getRetour())) {
            vh.afficherGestionEnclos();
        } else if (event.getSource().equals(vue.getAffecter())) {
            bddmanager.start("jdbc:mysql://localhost:3306/Javassicpark", "root", "");
            String requete = "INSERT INTO `soigneur_enclos` values ('" + vue.getIDSoigneur() + "','" + vue.getIDEnclos() +"');";
            boolean requetevalide=bddmanager.insert(requete);
            lbdblon=new Label();
            System.out.println(requetevalide);
            if (requetevalide){
                lbdblon.setText("INSERTION REUSSIE");
            } else {
                lbdblon.setText("ECHEC INSERTION \n CE SOIGNEUR EST DEJA AFFECTE A CET ENCLOS");
            }
            vue.setTextLbdblon(lbdblon.getText());
            vue.setVisuLbdblon(lbdblon.isVisible());
            bddmanager.stop();
        }

    }

}
