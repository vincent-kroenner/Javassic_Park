package Controller;

import Tools.BDDManager2;
import View.ViewEcranEnclos;
import View.ViewFormDino;
import View.ViewHandler;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ControllerFormDino implements EventHandler<MouseEvent> {

    private ViewFormDino vue;
    private ViewEcranEnclos vueEnclos;
    private ViewHandler vh;

    private BDDManager2 bddmanager = new BDDManager2();
    public Label lbdblon;
    public boolean oeuf = false;

    public ControllerFormDino(ViewFormDino viewFormDino, ViewHandler vh, ViewEcranEnclos vee) {
        this.vue = viewFormDino;
        this.vh = vh;
        this.vueEnclos = vee;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getSource().equals(vue.getRetour())) {
            vh.afficherEcranQG();
        } else if (event.getSource().equals(vue.getIncuber())) {
            bddmanager.start("jdbc:mysql://localhost:3306/Javassicpark", "root", "");
            String requete = "SELECT * FROM dino;";
            ArrayList<ArrayList<String>> listnomdino;
            listnomdino = bddmanager.select(requete);
            boolean nomOK = true;
            for (int i = 0; i < listnomdino.size(); i++) {
                if (listnomdino.get(i).get(1).equalsIgnoreCase(vue.getNomDino())) {
                    nomOK = false;
                }
            }
            if (nomOK) {
                String ctrlmilieu = "SELECT enclos.milieuEnclos, espece.milieuDeVieEspece, CASE WHEN enclos.milieuEnclos LIKE espece.milieuDeVieEspece OR enclos.milieuEnclos LIKE 'Infirmerie' THEN 'TRUE' ELSE 'FALSE' END FROM enclos, espece WHERE enclos.libelleEnclos LIKE '" + vue.getNomEnclos() + "' AND espece.nomEspece LIKE '" + vue.getEspece() + "';";
                ArrayList<ArrayList<String>> resuctrlmilieu;
                resuctrlmilieu=bddmanager.select(ctrlmilieu);
                System.out.println(vue.getNomEnclos());
                System.out.println(vue.getEspece());
                if (resuctrlmilieu.get(0).get(2).equals ("TRUE")) {
                    String ctrlplace = "SELECT `capaciteMax`,`capaciteActuelle`, CASE WHEN (`capaciteMax`-`capaciteActuelle`)<1 THEN 'FALSE' ELSE 'TRUE' END FROM enclos WHERE `id_enclos`= '" + vue.getIDEnclosDino() + "';";
                    ArrayList<ArrayList<String>> resuctrlplace;
                    resuctrlplace = bddmanager.select(ctrlplace);
                    if (resuctrlplace.get(0).get(2).equals("TRUE")) {
                        requete = "INSERT INTO dino values (NULL,'" + vue.getNomDino() + "','" + vue.getAgeDino() + "','" + vue.getestmalade() + "','" + vue.getCaractereDino() + "','" + vue.getIDEnclosDino() + "','" + vue.getIDEspeceDino() + "');";
                        boolean requetevalide = bddmanager.insert(requete);
                        lbdblon = new Label();
                        System.out.println(requetevalide);
                        if (requetevalide) {
                            String majqteenclos = "UPDATE enclos SET capaciteActuelle=capaciteActuelle+1 WHERE id_enclos= '" + vue.getIDEnclosDino() + "';";
                            boolean test = bddmanager.update(majqteenclos);
                            System.out.println(test);
                            System.out.println(majqteenclos);
                            lbdblon.setText("UN DINOSAURE VIENT DE NAITRE");
                        } else {
                            lbdblon.setText("ECHEC INSERTION");
                            if (verifInt(vue.getAgeDino())) {
                                lbdblon = new Label();
                                lbdblon.setText("AGE ERRONE VALEUR NUMERIQUE SVP");
                            }
                        }
                    } else {
                        lbdblon = new Label();
                        lbdblon.setText("ECHEC  ENCLOS PLEIN");
                    }
                } else {
                        lbdblon = new Label();
                        lbdblon.setText("ENCLOS INADAPTE");
                }
                lbdblon.setVisible(true);
            } else {
                lbdblon = new Label();
                lbdblon.setText("ERREUR CE NOM EXISTE DEJA");
                lbdblon.setVisible(true);
            }
            vue.setTextLbdblon(lbdblon.getText());
            vue.setOpaciteLbdblon(1);
            vue.setVisuLbdblon(lbdblon.isVisible());
            vue.apparition1();
            bddmanager.stop();
        } else if (event.getSource().equals(vue.getCréer())) {
            bddmanager.start("jdbc:mysql://localhost:3306/Javassicpark", "root", "");
            String requete = "SELECT * FROM espece;";
            ArrayList<ArrayList<String>> listnomespece;
            listnomespece = bddmanager.select(requete);
            boolean nomOK = true;
            for (int i = 0; i < listnomespece.size(); i++) {
                if (listnomespece.get(i).get(1).equalsIgnoreCase(vue.getNomEspece())) {
                    nomOK = false;
                }
            }
            if (nomOK) {
                requete = "INSERT INTO espece values (NULL,'" + vue.getNomEspece() + "','" + vue.getTailleEspece() + "','" + vue.getRegimeEspece() + "','" + vue.getMilieuEspece() + "');";
                boolean requetevalide = bddmanager.insert(requete);
                lbdblon = new Label();
                System.out.println(requetevalide);
                if (requetevalide) {
                    lbdblon.setText("NOUVELLE ESPECE CREEE");
                    refresh();
                } else {
                    lbdblon.setText("ECHEC INSERTION");
                }
                lbdblon.setVisible(true);
            } else {
                lbdblon = new Label();
                lbdblon.setText("ERREUR CETTE ESPECE EXISTE DEJA");
                lbdblon.setVisible(true);
            }
            vue.setTextLbdblon(lbdblon.getText());
            vue.setOpaciteLbdblon(1);
            vue.setVisuLbdblon(lbdblon.isVisible());
            bddmanager.stop();
            vue.apparition1();

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

    public boolean verifInt(String input) {
        boolean okInt = true;
        for (int i = 0; i < input.length(); i++) {
            if (!(Integer.valueOf(input.charAt(i)) / 1 >= 0)) {
                okInt = false;
                break;
            }
        }
        return okInt;
    }


    public void refresh() {
        try {
            Thread.sleep(1000);
            vh.afficherFormDino();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

