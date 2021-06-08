package Controller;

import Tools.BDDManager2;
import View.ViewEcranEnclos;
import View.ViewHandler;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControllerEcranEnclos implements EventHandler<MouseEvent> {

    private ViewEcranEnclos vue;
    private ViewHandler vh;

    private BDDManager2 bddmanager = new BDDManager2();
    private boolean confirmation;
    private String dinoDelete;

    public ControllerEcranEnclos(ViewEcranEnclos viewEcranEnclos, ViewHandler vh) {
        this.vue = viewEcranEnclos;
        this.vh = vh;
        //this.vee =
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getSource().equals(vue.getQuitter())) {
            System.exit(0);
        } else if (event.getSource().equals(vue.getVoliere())) {
            vue.setnmEnclos(1);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getAquarium())) {
            vue.setnmEnclos(2);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getInfirmerie())) {
            vue.setnmEnclos(3);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getEst1())) {
            vue.setnmEnclos(12);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getEst2())) {
            vue.setnmEnclos(13);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getEst3())) {
            vue.setnmEnclos(14);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getNord1())) {
            vue.setnmEnclos(9);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getNord2())) {
            vue.setnmEnclos(10);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getNord3())) {
            vue.setnmEnclos(11);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getOuest1())) {
            vue.setnmEnclos(7);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getOuest2())) {
            vue.setnmEnclos(8);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getSud1())) {
            vue.setnmEnclos(4);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getSud2())) {
            vue.setnmEnclos(5);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getSud3())) {
            vue.setnmEnclos(6);
            vue.initPage();
            vh.afficherEcranEnclos();
        } else if (event.getSource().equals(vue.getQg())) {
            vh.afficherEcranQG();
        }
        for (int i = 0; i < vue.getListeButtonDelete().size(); i++) {
            if (event.getSource().equals(vue.getListeButtonDelete().get(i))) {
                dinoDelete=vue.getListresu_noms().get(i).getText();
                confirmation=vue.showConfirmation();
                System.out.println(confirmation);
                if (confirmation) {
                    System.out.println(vue.getListe_id_dino().get(i).getText());
                    bddmanager.start("jdbc:mysql://localhost:3306/Javassicpark", "root", "");
                    String requete = "DELETE FROM `dino` WHERE `dino`.`id_dino` = " + vue.getListe_id_dino().get(i).getText() + ";";
                    bddmanager.delete(requete);
                    String majqteenclosOUT = "UPDATE enclos SET capaciteActuelle=capaciteActuelle-1 WHERE id_enclos= '" + vue.getNmEnclos() + "';";
                    bddmanager.update(majqteenclosOUT);
                    bddmanager.stop();
                    vue.initPage();
                    vh.afficherEcranEnclos();
                }
            } else if (event.getSource().equals(vue.getListeButtonUpdate().get(i))) {
                System.out.println(vue.getListeButtonUpdate().get(i));
                bddmanager.start("jdbc:mysql://localhost:3306/Javassicpark", "root", "");
                String requete = "UPDATE `dino` SET `ageDino` = " + vue.getListresu_ages().get(i).getText() + ", `isMalade` = " + vue.getListresu_malades().get(i).isSelected() + ", `caractere` = '" + vue.getListresu_caracteres().get(i).getText() + "' WHERE `dino`.`id_dino` = " + vue.getListe_id_dino().get(i).getText() + ";";
                bddmanager.update(requete);
                System.out.println(requete);
                bddmanager.stop();
                vue.initPage();
                vh.afficherEcranEnclos();

            }
        }
        for (int i = 0; i < vue.getListeButtonDeleteSoigneur().size(); i++) {
            if (event.getSource().equals(vue.getListeButtonDeleteSoigneur().get(i))) {


                    bddmanager.start("jdbc:mysql://localhost:3306/Javassicpark", "root", "");
                    String requete = "DELETE FROM `soigneur_enclos` WHERE `soigneur_enclos`.`id_soigneur` =" + vue.getListe_id_soigneur().get(i).getText() + " AND `soigneur_enclos`.`id_enclos` = " + vue.getNmEnclos() + ";";
                    bddmanager.delete(requete);
                    System.out.println(requete);
                    bddmanager.stop();
                    vue.initPage();
                    vh.afficherEcranEnclos();

            } else if (event.getSource().equals(vue.getListeButtonUpdateSoigneur().get(i))) {
                System.out.println(vue.getListeButtonUpdateSoigneur().get(i));

                bddmanager.start("jdbc:mysql://localhost:3306/Javassicpark", "root", "");
                String requete = "UPDATE `soigneur` SET `ageSoigneur` = '" + vue.getListe_age().get(i).getText() + "', `salaireSoigneur` = '" + vue.getListe_salaire().get(i).getText() + "', `commentaire` = '" + vue.getListe_commentaire().get(i).getText() + "' WHERE `soigneur`.`id_soigneur` = " + vue.getListe_id_soigneur().get(i).getText() + ";";
                bddmanager.update(requete);
                System.out.println(requete);
                bddmanager.stop();
                vue.initPage();
                vh.afficherEcranEnclos();
            }
        }
    }
    public String getdinoDelete(){return dinoDelete;}
}

