/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.sharedachat.exposition;

import javax.ejb.Remote;
import miage.m2.sharedachat.exceptions.ReceptionCommandeInconnuException;
import miage.m2.sharedachat.transientobjects.ReceptionCommandeTransient;

/**
 * Interface Remote GestionLivraisonRemote, elle représente les différentes signatures des opérations possibles par un Magasinier depuis son poste client lourd.
 * @author QuentinDouris
 */
@Remote
public interface GestionLivraisonRemote {
    
    public void enregistrerLivraison(ReceptionCommandeTransient receptionCommande) throws ReceptionCommandeInconnuException;

}
