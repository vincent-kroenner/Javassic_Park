package Controller;

import Tools.BDDManager2;
import View.ViewEcranEnclos;
import View.ViewFormSoigneur;
import View.ViewHandler;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ControllerFormSoigneur implements EventHandler<MouseEvent> {

    private ViewFormSoigneur vue;
    private ViewEcranEnclos vueEnclos;
    private ViewHandler vh;
    private BDDManager2 bddmanager = new BDDManager2();
    public Label infoInsert;

    public ControllerFormSoigneur(ViewFormSoigneur viewFormSoigneur, ViewHandler vh, ViewEcranEnclos vee) {
        this.vue = viewFormSoigneur;
        this.vh = vh;
        this.vueEnclos = vee;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getSource().equals(vue.getRetour())) {
            vh.afficherEcranQG();
            System.out.println("Vous avez appuyer sur RETOUR !");
        } else if (event.getSource().equals(vue.getRecruter())) {
            bddmanager.start("jdbc:mysql://localhost:3306/Javassicpark", "root", "");
            String requete = "INSERT INTO soigneur values (NULL,'" + vue.getNomSoigneur() + "','" + vue.getPrenomSoigneur() + "','" + vue.getAgeSoigneur() + "','" + vue.getSexeSoigneur() + "','" + vue.getSalaireSoigneur() + "','" + vue.getCommSoigneur() + "');";
            boolean requetevalide = bddmanager.insert(requete);
            infoInsert = new Label();
            System.out.println(requetevalide);
            if (requetevalide) {
                infoInsert.setText("INSERTION REUSSIE");
            } else {
                infoInsert.setText("ECHEC INSERTION");
                 if (verifInt(vue.getAgeSoigneur())) {
                    System.out.println(vue.getAgeSoigneur());
                    infoInsert = new Label();
                    infoInsert.setText("AGE ERRONE VALEUR NUMERIQUE SVP");
                } else if (verifInt(vue.getSalaireSoigneur())) {
                    System.out.println(vue.getSalaireSoigneur());
                    infoInsert = new Label();
                    infoInsert.setText("SALAIRE ERRONE VALEUR NUMERIQUE SVP");
                }
            }
            infoInsert.setVisible(true);

            vue.setTextLbdblon(infoInsert.getText());
            vue.setVisuLbdblon(infoInsert.isVisible());
            bddmanager.stop();
        } else if (event.getSource().equals(vue.getQuitter())) {
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
        }
    }

    public boolean verifInt(String input){
        boolean okInt;
        try {
            int i = Integer.parseInt(input);
            okInt=false;
        }
        catch (Exception e) {
            okInt=true;
        }
        return okInt;
    }



}