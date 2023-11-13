package formation;

public interface InterUniteEnseignement {

    String getNomUE();

    String getNomEnseignant();

    void setNomEnseignant(String nomEnseignant);

    int getNbPlaces();

    void setNbPlaces(int nbPlaces);

    int hashCode();

    boolean equals(Object obj);

}