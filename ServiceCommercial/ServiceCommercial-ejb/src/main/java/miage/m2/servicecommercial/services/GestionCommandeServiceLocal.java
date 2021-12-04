/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.services;

import javax.ejb.Local;
import miage.m2.sharedmenuis.exceptions.CommercialInconnuException;
import miage.m2.sharedachat.exceptions.SaisirCommandeException;
import miage.m2.sharedachat.transientobjects.CommandeTransient;

/**
 * Interface de l'EJB qui gère l'encapsulation JSON pour les commandes
 * @author QuentinDouris
 */
@Local
public interface GestionCommandeServiceLocal {
    
    /**
     * Enregistre une commande dans le système
     * @param refCatCmd
     * @param coteLargeurCmd
     * @param coteLongueurCmd
     * @param montantNegoCmd
     * @param idAffaire
     * @param idCommercial
     * @return
     * @throws SaisirCommandeException
     * @throws CommercialInconnuException 
     */
    public CommandeTransient saisirCommande(String refCatCmd, double coteLargeurCmd, double coteLongueurCmd, double montantNegoCmd, int idAffaire, int idCommercial) throws SaisirCommandeException, CommercialInconnuException;

}
