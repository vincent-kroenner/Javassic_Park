package Controller;


import View.ViewEcranEnclos;
import View.ViewGestionEnclos;
import View.ViewHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControllerGestionEnclos implements EventHandler<MouseEvent> {

    private ViewGestionEnclos vue;
    private ViewEcranEnclos vueEnclos;
    private ViewHandler vh;
    public int numEnclos;

    public ControllerGestionEnclos(ViewGestionEnclos viewGestionEnclos, ViewHandler vh, ViewEcranEnclos vee) {
        this.vue = viewGestionEnclos;
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
        } else if (event.getSource().equals(vue.getAffecterSoigneur())) {
            vh.afficherGestionSoigneur();
        } else if (event.getSource().equals(vue.getAffecterDino())) {
            vh.afficherGestionDino();
        } else if (event.getSource().equals(vue.getRetour())) {
            vh.afficherEcranQG();
        }
    }
    public int getNumEnclos (){
        this.numEnclos = numEnclos;
        return numEnclos;
    }
}
