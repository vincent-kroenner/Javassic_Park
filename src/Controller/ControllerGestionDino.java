package Controller;


import View.ViewEcranEnclos;
import View.ViewGestionDino;
import View.ViewHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import Tools.BDDManager2;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class ControllerGestionDino implements EventHandler<MouseEvent> {

    private ViewGestionDino vue;
    private ViewEcranEnclos vueEnclos;
    private ViewHandler vh;
    public Label lbdblon;

    private BDDManager2 bddmanager = new BDDManager2();

    public ControllerGestionDino(ViewGestionDino viewGestionDino, ViewHandler vh, ViewEcranEnclos vee) {
        this.vue = viewGestionDino;
        this.vh = vh;
        this.vueEnclos =vee;
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
            String ctrlmilieu = "SELECT enclos.milieuEnclos, espece.milieuDeVieEspece, CASE WHEN enclos.milieuEnclos LIKE espece.milieuDeVieEspece OR enclos.milieuEnclos LIKE 'Infirmerie' THEN 'TRUE' ELSE 'FALSE' END FROM enclos, espece WHERE enclos.libelleEnclos LIKE '" + vue.getNomEnclos() + "' AND espece.nomEspece LIKE '" + vue.getNomEspece() + "';";
            ArrayList<ArrayList<String>> resuctrlmilieu;
            resuctrlmilieu = bddmanager.select(ctrlmilieu);
            System.out.println(vue.getNomEnclos());
            System.out.println(vue.getNomEspece());
            if (resuctrlmilieu.get(0).get(2).equals("TRUE")) {
                String ctrlplace = "SELECT `capaciteMax`,`capaciteActuelle`, CASE WHEN (`capaciteMax`-`capaciteActuelle`)<1 THEN 'FALSE' ELSE 'TRUE' END FROM enclos WHERE `id_enclos`= '" + vue.getIDEnclos() + "';";
                ArrayList<ArrayList<String>> resuctrlplace;
                resuctrlplace = bddmanager.select(ctrlplace);
                if (resuctrlplace.get(0).get(2).equals("TRUE")) {
                    String recupIDEnclosDino = "SELECT id_enclos FROM dino WHERE id_dino= '" + vue.getIDDinos() + "';";
                    int idEnclosinitial = Integer.valueOf(bddmanager.select(recupIDEnclosDino).get(0).get(0));
                    if (!(idEnclosinitial == vue.getIDEnclos())) {
                        String requete = "UPDATE `dino` SET `id_enclos` = '" + vue.getIDEnclos() + "' WHERE `dino`.`id_dino` = '" + vue.getIDDinos() + "';";
                        boolean requetevalide = bddmanager.update(requete);
                        lbdblon = new Label();
                        if (requetevalide) {
                            String majqteenclosIN = "UPDATE enclos SET capaciteActuelle=capaciteActuelle+1 WHERE id_enclos= '" + vue.getIDEnclos() + "';";
                            boolean test1 = bddmanager.update(majqteenclosIN);
                            String majqteenclosOUT = "UPDATE enclos SET capaciteActuelle=capaciteActuelle-1 WHERE id_enclos= '" + idEnclosinitial + "';";
                            boolean test2 = bddmanager.update(majqteenclosOUT);
                            lbdblon.setText("INSERTION REUSSIE");
                        } else {
                            lbdblon.setText("ECHEC INSERTION");
                        }
                    } else {
                        lbdblon = new Label();
                        lbdblon.setText("LE DINO EST DEJA DANS CET ENCLOS");
                    }
                } else {
                    lbdblon = new Label();
                    lbdblon.setText("ECHEC : ENCLOS PLEIN");
                }
            } else {
                lbdblon = new Label();
                lbdblon.setText("ENCLOS INADAPTE");
            }
            vue.setTextLbdblon(lbdblon.getText());
            vue.setVisuLbdblon(lbdblon.isVisible());
            bddmanager.stop();
        }
    }
}