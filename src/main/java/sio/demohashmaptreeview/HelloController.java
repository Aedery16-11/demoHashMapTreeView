package sio.demohashmaptreeview;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sio.demohashmaptreeview.model.Epreuve;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ComboBox cboSport;
    @FXML
    private TextField txtEpreuve;
    @FXML
    private TreeView tvOlympique;
    @FXML
    private Button btnAjouter;

    //Variables globales

    HashMap<String, ArrayList<Epreuve>> jeux; //automatiquement en private

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jeux = new HashMap<>();
        cboSport.getItems().addAll("Judo", "Natation", "Cyclisme");
        cboSport.getSelectionModel().selectFirst(); 
    }

    @FXML
    public void btnAjouterClicked(Event event)
    {
        //Stocker nos objets dans la HashMap
        //Vérifier si la clé existe dans la HashMap
        Epreuve monEpreuve = new Epreuve(txtEpreuve.getText());
        if (!jeux.containsKey(cboSport.getSelectionModel().getSelectedItem().toString())) //vérifier que le sport sélectionné (clé) n'existe pas dans la HashMap, et qu'aucune épreuve n'a été crée pour ce sport
        {
            ArrayList<Epreuve> lesEpreuves = new ArrayList<>();
            //Epreuve monEpreuve = new Epreuve(txtEpreuve.getText()); //le nom de la nouvelle épreuve est entrée par l'user, qui sera stockée dans lesEpreuves, c'est monEpreuve
            lesEpreuves.add(monEpreuve);
            jeux.put(cboSport.getSelectionModel().getSelectedItem().toString(), lesEpreuves); //on ajoute dans "jeux" (la hashmap) lesEpreuves (qui contient la nouvelle épreuve) et la key pour se repérer est le sport sélectionné. On fait getText car on demande un string.
        }
        else
        {
            //Epreuve monEpreuve = new Epreuve(txtEpreuve.getText());
            jeux.get(cboSport.getSelectionModel().getSelectedItem().toString()).add(monEpreuve);
        }

        //Remplir le TreeView
        TreeItem root = new TreeItem("Tous les sports");

        for (String nomSport : jeux.keySet())
        {
            TreeItem noeudSport = new TreeItem<>(nomSport);
            for (Epreuve epreuve : jeux.get(nomSport))
            {
                TreeItem noeudEpreuve = new TreeItem<>(epreuve.getNomEpreuve());
                noeudSport.getChildren().add(noeudEpreuve);
                noeudSport.setExpanded(true);
            }
            root.getChildren().add(noeudSport);
            root.setExpanded(true);
        }

        tvOlympique.setRoot(root);

    }
}