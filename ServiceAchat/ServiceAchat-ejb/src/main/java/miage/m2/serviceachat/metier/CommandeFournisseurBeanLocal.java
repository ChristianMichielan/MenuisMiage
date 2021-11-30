/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.serviceachat.metier;

import javax.ejb.Local;
import miage.m2.sharedachat.exceptions.CreerCommandeFournisseurException;

/**
 * Interface de l'EJB qui stocke les informations des commandes passées auprès du fournisseur
 * @author QuentinDouris
 */
@Local
public interface CommandeFournisseurBeanLocal {
    
    public void creerCommandeFournisseur(int idAffaire) throws CreerCommandeFournisseurException;
    
}
