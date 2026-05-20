package iut.gon.hexagonalcoordinates;

/**
 * Exception non vérifiée (RuntimeException) levée lorsqu'une opération nécessite 
 * que deux coordonnées soient alignées sur un même axe (comme le calcul d'une ligne 
 * directe entre deux points), mais que ce n'est pas le cas.
 */
public class DifferentAxisException extends RuntimeException {
    
    /**
     * Identifiant de version de la classe pour la sérialisation.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construit une nouvelle exception avec le message de détail spécifié.
     *
     * @param message Le message décrivant la raison précise de l'exception.
     */
	public DifferentAxisException(String message) {
        super(message);
    }
}