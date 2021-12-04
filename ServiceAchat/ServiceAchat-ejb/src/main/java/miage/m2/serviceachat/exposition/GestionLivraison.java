/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceachat.exposition;

import miage.m2.sharedachat.exposition.GestionLivraisonRemote;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import miage.m2.serviceachat.messagesproducer.EnregistreLivraisonBeanLocal;
import miage.m2.serviceachat.metier.CommandeFournisseurBeanLocal;
import miage.m2.sharedachat.exceptions.ReceptionCommandeInconnuException;
import miage.m2.sharedachat.transientobjects.ReceptionCommandeTransient;

/**
 * Classe Stateless GestionLivraison qui représente les différentes opérations possibles par un Magasinier depuis son poste client lourd
 * @author QuentinDouris
 */
@Stateless
public class GestionLivraison implements GestionLivraisonRemote {

    @EJB
    private EnregistreLivraisonBeanLocal enregistreLivraisonBean;

    @EJB
    private CommandeFournisseurBeanLocal commandeFournisseurBean;
    
    
    /**
     * Enregistre la livraison réceptionné dans le système et notifi la réception avec un JMS
     * @param receptionCommande
     * @throws ReceptionCommandeInconnuException 
     */
    @Override
    public void enregistrerLivraison(ReceptionCommandeTransient receptionCommande) throws ReceptionCommandeInconnuException {
        int idAffaireCommande = this.commandeFournisseurBean.enregistrerReceptionCommande(receptionCommande.getIdLivraisonReceptionnee());
        this.enregistreLivraisonBean.commandeFournisseurReceptionnee(idAffaireCommande);
    }

}
