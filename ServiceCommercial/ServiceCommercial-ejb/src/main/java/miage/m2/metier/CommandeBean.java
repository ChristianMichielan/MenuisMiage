/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.metier;

import java.util.HashMap;
import javax.ejb.Singleton;
import miage.m2.entities.Commande;
import miage.m2.entities.Commercial;
import miage.m2.sharedachat.exceptions.SaisirCommandeException;

/**
 * EJB qui stocke les informations des commandes
 * @author QuentinDouris
 */
@Singleton
public class CommandeBean implements CommandeBeanLocal {
    // La clé correspond à l'id de l'affaire rattaché à la commande
    private HashMap<Integer, Commande> listeCommande;

    /**
     * Constructeur
     */
    public CommandeBean() {
        this.listeCommande = new HashMap<>();
    }

    /**
     * Créer une nouvelle commande dans le système
     * @param refCatCmd
     * @param coteLargeurCmd
     * @param coteLongueurCmd
     * @param montantNegoCmd
     * @param idAffaire
     * @param commercial 
     * @throws miage.m2.sharedachat.exceptions.SaisirCommandeException 
     */
    @Override
    public void creerCommande(String refCatCmd, double coteLargeurCmd, double coteLongueurCmd, double montantNegoCmd, int idAffaire, Commercial commercial) throws SaisirCommandeException {
        // vérifie que la commande n'existe pas déjà
        if(this.listeCommande.get(idAffaire) != null) {
            throw new SaisirCommandeException();
        }
        
        // Création de la commande dans le système
        Commande commande = new Commande(refCatCmd, coteLargeurCmd, coteLongueurCmd, montantNegoCmd, idAffaire, commercial);
        this.listeCommande.put(commande.getIdAffaire(), commande);
    }
}
