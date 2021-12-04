/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.servicecommercial.services;

import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.m2.exceptions.CommercialInconnuException;
import miage.m2.servicecommercial.messages.CommandeMessageBeanLocal;
import miage.m2.sharedachat.exceptions.SaisirCommandeException;
import miage.m2.sharedachat.transientobjects.CommandeTransient;

/**
 * EJB qui gère l'encapsulation JSON pour les commandes
 * @author QuentinDouris
 */
@Stateless
public class GestionCommandeService implements GestionCommandeServiceLocal {

    @EJB
    private CommandeMessageBeanLocal commandeMessageBean;

    // Convertisseur JSON
    private Gson gson;

    /**
     * Constructeur
     */
    public GestionCommandeService() {
        this.gson = new Gson();
    }
    
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
    @Override
    public CommandeTransient saisirCommande(String refCatCmd, double coteLargeurCmd, double coteLongueurCmd, double montantNegoCmd, int idAffaire, int idCommercial) throws SaisirCommandeException, CommercialInconnuException {
        return this.commandeMessageBean.saisirCommande(refCatCmd, coteLargeurCmd, coteLongueurCmd, montantNegoCmd, idAffaire, idCommercial);
    }
    
}
