package formation;

public interface InterUniteEnseignement {

    String getNomUE();

    String getNomEnseignant();
    
    int getNbPlacesMax();

    void setNomEnseignant(String nomEnseignant);

    int getNbPlaces();

    int hashCode();

    boolean equals(Object obj);

	void setNbPlaces();

}