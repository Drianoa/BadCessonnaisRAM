package net.etrs.ram.bad_cessonais.common;

import java.util.List;

/**
 * Interface définissant les opérations CRUD et les opérations de recherche sur les entités.
 *
 * Par défaut une façade permet toutes les opérations CRUD et quelques opérations comme l'obtention d'une nouvelle instance du type paramétré ou l'obtention de la classe du type paramétré.
 *
 * <p>Cette interface prend n'importe quel type implémentant IdentifiableEntity.</p>
 *
 * <p style="padding : 4px; color : red ; border : solid red 1px;">Pour qu'une entité puisse être manipulée par une Facade elle doit impérativement implementer "IdentifiableEntity".</p>
 *
 *
 * @author francois.robin
 * @param <T> classe de l'entité persistante, gérée par JPA
 * @see IdentifiableEntity
 * @see AbstractFacade
 */
public interface Facade <T>
{

    /**
     * crée une nouvelle instance du type paramétrée &lt;T&gt; de la classe
     *
     * @return
     */
    T newInstance();

    /**
     * renvoie la classe du type paramétré de la classe &lt;T&gt;
     *
     * @return la définition d'une classe (objet Class)
     */
    Class<T> getBusinessClass();

    /**
     * ajoute une nouvelle entity au contexte JPA (persist).
     *
     * @param t instance d'une entité à ajouter.
     */
    void create(T t);

    /**
     * lit une instance de T dont l'ID est passé en paramètre (find).
     *
     * @param id
     * @return instance d'une entité
     */
    T read(Object id);

    /**
     * récupère toutes les instances de l'entité T. .
     *
     * @return liste des instances, éventuellement triées.
     */
    List<T> readAll();

    /**
     * récupère toutes les instances de l'entité T, éventuellement triées par ordre descendant (DESC) sur une liste d'attributs.
     *
     * @return liste des instances, éventuellement triées.
     */
    List<T> readAll(String... orderBy);

    /**
     * Mets à jour les données portée par l'instance de T (merge).
     *
     * @param t
     */
    T update(T t);

    /**
     * Supprime l'instance de T
     *
     * @param t
     */
    void delete(T t);

    /**
     * effectue une recherche toute simple sur la valeur d'un attribut avec l'opérateur d'égalité. 
     * Le résultat est trié (ou non) selon une liste d'attributs par ordre "ASC".
     *
     * @param parameterName attribut testé.
     * @param parameterValue valeur à tester.
     * @param orderBy liste d'attributs pour le tri ascendant.
     * @return liste triée des entités correpondant à la recherche.
     */
    public List<T> search(String parameterName,
            Object parameterValue,
            String... orderBy);

    
    /**
     * Compte le nombre d'instances T.
     * @return
     */
	public Long countAll();
}
