/*
 * Projet EAI MenuisMIAGE.
 * Projet réalisé par Quentin DOURIS, Christian MICHIELAN, Trung LE DUC
 */
package miage.m2.exceptions;

/**
 * Excemtion qui gére les erreurs lors des appels d'API
 * @author QuentinDouris
 */
public class APIException extends Exception {

    public APIException(int codeErreur, String urlApi) {
        super("Erreur lors de l'appel de l'API : " + urlApi + " | Code d'erreur : " + codeErreur);
    }
    
}
