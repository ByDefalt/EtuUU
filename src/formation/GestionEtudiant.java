package formation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Les services de gestion d'une ann�e de formation.
 *
 * @author LE BRAS Erwan
 * @author ROUSVAL Romain
 *
 */
public class GestionEtudiant implements InterEtudiant {
    // ******************************* ATTRIBUT D'INSTANCES
    private int nbEtudiant = 0;
    private final Set<Etudiant> listeEtudiants = new HashSet<>();
    public Set<Etudiant> getListeEtudiants() {
        return listeEtudiants;
    }

    private Set<UniteEnseignement> listeUE = new HashSet<>();
    public Set<UniteEnseignement> getListeUE() {
        return listeUE;
    }

    private Etudiant etudiantConnecte = null;

    /**
     * Constructeur vide de la classe Etudiant.
     */
    public GestionEtudiant() {
    }

    /**
     * Cr�e le compte d'un �tudiant � partir de ses informations personnelles et
     * de son mot de passe puis retourne son num�ro d'�tudiant g�n�r�
     * automatiquement.
     *
     * @param informationPersonnelle les informations personnelles de l'�tudiant
     * @param motDePasse             le mot de passe de l'�tudiant pour se connecter
     *                               (la
     *                               chaine doit �tre non vide)
     * @return le num�ro unique de l'�tudiant ou -1 en cas de probl�me
     */
    @Override
    public int inscription(InformationPersonnelle informationPersonnelle, String motDePasse) {
        if (motDePasse.isEmpty() || informationPersonnelle == null) {
            return -1;
        }

        Etudiant etudiant = new Etudiant(informationPersonnelle, motDePasse);
        etudiant.setNumero(this.nbEtudiant);
        this.nbEtudiant++;
        this.listeEtudiants.add(etudiant);

        return etudiant.getNumero();
    }

    /**
     * Connecte l'�tudiant avec son num�ro d'�tudiant et son mot de passe.
     *
     * @param numero     le num�ro de l'�tudiant
     * @param motDePasse le mot de passe de l'�tudiant
     * @return <code>true</code> si le couple num�ro/mot de passe est correct
     *         (l'�tudiant est alors consid�r� comme connect� au syst�me),
     *         <code>false</code> si le couple est incorrect
     */
    @Override
    public boolean connexion(int numero, String motDePasse) {
        for (Etudiant etudiant : this.listeEtudiants) {
            if (etudiant.getNumero() == numero && etudiant.getMotDePasse().equals(motDePasse)) {
                this.etudiantConnecte = etudiant;
                return true;
            }
        }
        return false;
    }

    /**
     * D�connecte l'�tudiant actuellement connect� au syst�me.
     *
     * @throws NonConnecteException si aucun �tudiant n'�tait connect�
     */
    @Override
    public void deconnexion() throws NonConnecteException {
        if (this.etudiantConnecte == null) {
            throw new NonConnecteException();
        }

        this.etudiantConnecte = null;
    }

    /**
     * L'ensemble des unit�s d'enseignement obligatoires de l'ann�e de formation.
     *
     * @return l'ensemble des UE obligatoires
     */
    @Override
    public Set<UniteEnseignement> enseignementsObligatoires() {
        Set<UniteEnseignement> uniteEnseignementsO = new HashSet<>();
        for (UniteEnseignement ue : this.listeUE)
            if (!ue.getOptionnel()) {
                uniteEnseignementsO.add(ue);
            }

        return uniteEnseignementsO;
    }

    /**
     * L'ensemble des unit�s d'enseignement optionnelles de l'ann�e de formation.
     *
     * @return l'ensemble des UE optionnelles
     */
    @Override
    public Set<UniteEnseignement> enseignementsOptionnels() {
        Set<UniteEnseignement> uniteEnseignementsF = new HashSet<>();
        for (UniteEnseignement ue : this.listeUE)
            if (ue.getOptionnel()) {
                uniteEnseignementsF.add(ue);
            }

        return uniteEnseignementsF;
    }

    /**
     * Définit la liste des unités d'enseignement possible pour un étudiant.
     *
     * @param listeUE La liste des unités d'enseignement à attribuer à
     *                un étudiant.
     */
    public void setListeUE(Set<UniteEnseignement> listeUE) {
        this.listeUE = listeUE;
    }

    /**
     * Retourne le nombre d'options que l'�tudiant doit choisir au total.
     *
     * @return le nombre d'options que l'�tudiant doit choisir ou -1 si ce nombre
     *         n'a pas �t� encore d�fini.
     * @throws NonConnecteException si la m�thode est appel�e alors que l'�tudiant
     *                              n'est pas connect�
     */
    @Override
    public int nombreOptions() throws NonConnecteException {
        if (this.etudiantConnecte == null) {
            throw new NonConnecteException();
        }

        return this.etudiantConnecte.getNbOption();
    }

    /**
     * Définit le nombre d'options de l'étudiant.
     *
     * @param nbOption Le nombre d'options à attribuer à l'étudiant.
     */
    public void setNbOption(int nbOption) throws NonConnecteException {
        if (this.etudiantConnecte == null) {
            throw new NonConnecteException();
        }
        this.etudiantConnecte.setNbOption(nbOption);
    }

    /**
     * Choix d'une UE optionnelle par l'�tudiant.
     *
     * @param ue l'UE que l'�tudiant veut choisir
     * @return <code>true</code> si l'�tudiant a �t� inscrit � l'UE,
     *         <code>false</code> si l'inscription n'a pas pu se faire (manque de
     *         places dans l'UE ou l'UE n'est pas une option)
     * @throws NonConnecteException si la m�thode est appel�e alors que l'�tudiant
     *                              n'est pas connect�
     */
    @Override
    public boolean choisirOption(UniteEnseignement ue) throws NonConnecteException {
        if (this.etudiantConnecte == null) {
            throw new NonConnecteException();
        }

        if (ue.getnbParticipant() < ue.getNbPlacesMax() && this.enseignementsOptionnels().contains(ue)) {
            this.etudiantConnecte.addUE(ue);
            ue.setnbParticipant();
            return true;
        }
        return false;
    }

    /**
     * Renvoie le nombre d'options choisi par l'étudiant.
     *
     * @return le nombre d'options choisi par l'étudiant
     * @throws NonConnecteException si la m�thode est appel�e alors que l'�tudiant
     *                              n'est pas connect�
     */
    public int nombresOptionsChoisi() throws NonConnecteException {
        if (this.etudiantConnecte == null) {
            throw new NonConnecteException();
        }

        Set<UniteEnseignement> ueCommun = new HashSet<>(this.etudiantConnecte.getListeUEsuivies());
        ueCommun.retainAll(this.enseignementsOptionnels());

        return ueCommun.size();
    }

    /**
     * Renvoie le num�ro de groupe de TD de l'�tudiant s'il a �t� d�fini.
     *
     * @return le num�ro de groupe de TD s'il a �t� d�fini ou -1 si �a n'est pas
     *         encore le cas
     * @throws NonConnecteException si la m�thode est appel�e alors que l'�tudiant
     *                              n'est pas connect�
     */
    @Override
    public int getNumeroGroupeTravauxDiriges() throws NonConnecteException {
        if (this.etudiantConnecte == null) {
            throw new NonConnecteException();
        }

        return this.etudiantConnecte.getNumeroTd();
    }

    /**
     * Définit le numéro de TD de l'étudiant.
     *
     * @param numeroTd Le numéro de TD à attribuer à l'étudiant.
     */
    public void setNumeroTd(int numeroTd) throws NonConnecteException {
        if (this.etudiantConnecte == null) {
            throw new NonConnecteException();
        }
        this.etudiantConnecte.setNumeroTd(numeroTd);
    }

    /**
     * Renvoie le num�ro de groupe de TP de l'�tudiant s'il a �t� d�fini.
     *
     * @return le num�ro de groupe de TP s'il a �t� d�fini ou -1 si �a n'est pas
     *         encore le cas
     * @throws NonConnecteException si la m�thode est appel�e alors que l'�tudiant
     *                              n'est pas connect�
     */
    @Override
    public int getNumeroGroupeTravauxPratiques() throws NonConnecteException {
        if (this.etudiantConnecte == null) {
            throw new NonConnecteException();
        }

        return this.etudiantConnecte.getNumeroTp();
    }

    /**
     * Définit le numéro de TP de l'étudiant.
     *
     * @param numeroTp Le numéro de TP à attribuer à l'étudiant.
     */
    public void setNumeroTp(int numeroTp) throws NonConnecteException {
        if (this.etudiantConnecte == null) {
            throw new NonConnecteException();
        }
        this.etudiantConnecte.setNumeroTp(numeroTp);
    }

    /**
     * Renvoie l'ensemble des enseignements suivis par l'�tudiant : les UE
     * obligatoires ainsi que les UE optionnelles o� il est inscrit.
     *
     * @return l'ensemble des UE suivies par l'�tudiant
     * @throws NonConnecteException si la m�thode est appel�e alors que l'�tudiant
     *                              n'est pas connect�
     */
    @Override
    public Set<UniteEnseignement> enseignementsSuivis() throws NonConnecteException {
        if (this.etudiantConnecte == null) {
            throw new NonConnecteException();
        }

        return this.etudiantConnecte.getListeUEsuivies();
    }

    /**
     * Renvoie la liste de tous les messages re�us par l'�tudiant (lus et non
     * lus), dans l'ordre o� ils ont �t� re�us.
     *
     * @return tous les messages de l'�tudiant
     * @throws NonConnecteException si la m�thode est appel�e alors que l'�tudiant
     *                              n'est pas connect�
     */
    @Override
    public List<String> listeTousMessages() throws NonConnecteException {
        if (this.etudiantConnecte == null) {
            throw new NonConnecteException();
        }

        List<String> contenus = new ArrayList<>();
        for (Message message : this.etudiantConnecte.getMessages()) {
            contenus.add(message.getContenu());
        }
        return contenus;
    }

    /**
     * Renvoie la liste des messages non lus par l'�tudiant, dans l'ordre o� ils
     * ont �t� re�us.
     *
     * @return les messages non lus de l'�tudiant
     * @throws NonConnecteException si la m�thode est appel�e alors que l'�tudiant
     *                              n'est pas connect�
     */
    @Override
    public List<String> listeMessageNonLus() throws NonConnecteException {
        if (this.etudiantConnecte == null) {
            throw new NonConnecteException();
        }

        List<String> contenus = new ArrayList<>();
        for (Message message : this.etudiantConnecte.getMessages()) {
            if (message.estLu()) {
                contenus.add(message.getContenu());
            }
        }
        return contenus;
    }

    /**
     * Indique si l'inscription de l'�tudiant est finalis�e, c'est-�-dire si
     * l'�tudiant :
     * <ul>
     * <li>A �t� affect� � un groupe de TD</li>
     * <li>A �t� affect� � un groupe de TP</li>
     * <li>A choisi autant d'options que requis</li>
     * </ul>
     * Si au moins une des conditions n'est pas valid�e, l'�tudiant n'a pas
     * finalis� son inscription.
     *
     * @return <code>true</code> si l'inscription de l'�tudiant est finalis�e,
     *         <code>false</code> sinon
     * @throws NonConnecteException si la m�thode est appel�e alors que l'�tudiant
     *                              n'est pas connect�
     */
    @Override
    public boolean inscriptionFinalisee() throws NonConnecteException {
        if (this.etudiantConnecte == null) {
            throw new NonConnecteException();
        }

        return etudiantConnecte.getNumeroTd() != -1 && etudiantConnecte.getNumeroTp() != -1
                && this.nombreOptions() == this.nombresOptionsChoisi();
    }

}
