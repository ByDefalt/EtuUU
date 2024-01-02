package formation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Les services de gestion d'une ann�e de formation.
 *
 * @author LE BRAS Erwan
 * @author ROUSVAL Romain
 * 
 */

public class GestionFormation implements InterGestionFormation {

    private String nomFormation;
    private String nomResponsable;
    private String email;
    private final Set<UniteEnseignement> UniteEseignements = new HashSet<>();
    private final Map<Integer, Set<Etudiant>> tds = new HashMap<>();
    private final Map<Integer, Set<Etudiant>> tps = new HashMap<>();
    private final Set<Etudiant> listeEtudiants = new HashSet<>();
    private int tailleGroupeDirige = -1;
    private int tailleGroupePratique = -1;
    private int NBoption = -1;

    /**
     *  Instancie la Formation
     */
    public GestionFormation() {

    }
    
    
    /**
     * Renvoi le nombre d'options
     * 
     * @return le nombre d'options
     */
    public int getNBoption() {
        return NBoption;
    }

    /**
     * Permet de définir le nombre d'option d'un étudiant
     * 
     * @param etu
     */
    public void setnbOptionEtudiant(Etudiant etu) {
        etu.setNbOption(this.NBoption);
    }

    /**
     * Permet de définir la liste des UE
     * 
     * @param etu
     */
    public void setlisteUEEtudiant(Etudiant etu) {
        etu.setListeUE(UniteEseignements);
    }

    /**
     * Renvoi la map des groupe de TD
     * 
     * @return map des groupe de TD
     */
    public Map<Integer, Set<Etudiant>> getTds() {
        return tds;
    }

    /**
     * Renvoi la map des groupe de TP
     * 
     * @return map des groupe de TP
     */
    public Map<Integer, Set<Etudiant>> getTps() {
        return tps;
    }

    /**
     * Renvoi la liste des étuduant
     * 
     * @return la liste des étuduant
     */
    public Set<Etudiant> getlisteEtudiants() {
        return listeEtudiants;
    }

    /**
     * Cr�e une (ann�e de) formation avec son nom et celui du responsable. Si une
     * formation existait d�j� dans le syst�me, la nouvelle la remplace et efface
     * la pr�c�dente.
     *
     * @param nomFormation   le nom de la formation (chaine non vide)
     * @param nomResponsable le nom et pr�nom du responsable (chaine non vide)
     * @param email          l'email du responsable (adresse email valide)
     */
    @Override
    public void creerFormation(String nomFormation, String nomResponsable, String email) {
        this.nomFormation = nomFormation;
        this.nomResponsable = nomResponsable;
        this.email = email;
        this.UniteEseignements.clear();
        this.tds.clear();
        this.tps.clear();
        this.listeEtudiants.clear();
        this.tailleGroupeDirige = -1;
        this.tailleGroupePratique = -1;
        this.NBoption = -1;
    }

    /**
     * Renvoie le nom du responsable de formation.
     * s
     * 
     * @return le nom du responsable de formation ou <code>null</code> s'il n'a
     *         pas �t� d�fini
     */
    @Override
    public String getNomResponsableFormation() {
        return this.nomResponsable;
    }

    /**
     * Renvoie l'adresse email du responsable de formation.
     *
     * @return l'adresse email du responsable de formation ou <code>null</code> si
     *         elle n'a pas �t� d�finie
     */
    @Override
    public String getEmailResponsableFormation() {
        return this.email;
    }

    /**
     * Renvoie le nom de la formation.
     *
     * @return le nom de la formation
     */
    @Override
    public String getNomFormation() {
        return this.nomFormation;
    }

    /**
     * Rajoute une UE obligatoire � la formation. L'UE ne doit pas d�j� �tre dans
     * la liste des UE de la formation (ni en obligatoire, ni en optionnel).
     *
     * @param ue l'UE � rajouter
     * @return <code>true</code> si l'ajout a �t� fait, <code>false</code> en cas
     *         de probl�me
     */
    @Override
    public boolean ajouterEnseignementObligatoire(UniteEnseignement ue) {
        if (!UniteEseignements.contains(ue) && ue.getNbPlacesMax() == 0 && ue.getNomUE()!=null && ue.getNomEnseignant()!=null) {
            UniteEseignements.add(ue);
            return true;
        }
        return false;
    }

    /**
     * Rajoute une UE optionnelle � la formation. L'UE ne doit pas d�j� �tre dans
     * la liste des UE de la formation (ni en obligatoire, ni en optionnel).
     *
     * @param ue       l'UE � rajouter
     * @param nbPlaces le nombre de places maximum dans l'option (nombre sup�rieur
     *                 � 1) ou 0 pour pr�ciser qu'il n'y a pas de limite de places
     * @return <code>true</code> si l'ajout a �t� fait, <code>false</code> en cas
     *         de probl�me
     */
    @Override
    public boolean ajouterEnseignementOptionnel(UniteEnseignement ue, int nbPlaces) {
        if (!UniteEseignements.contains(ue) && ue.getNbPlacesMax() > 0) {
            ue.setNbPlacesMax(nbPlaces);
            UniteEseignements.add(ue);
            return true;
        }
        return false;
    }

    /**
     * D�finit le nombre d'options que doit choisir un �tudiant. Ne peut plus �tre
     * modifi� une fois d�fini.
     *
     * @param nombre le nombre d'options � choisir pour un �tudiant (nombre
     *               sup�rieur ou �gal � 1)
     */
    @Override
    public void definirNombreOptions(int nombre) {
        if (this.NBoption == -1) {
            this.NBoption = nombre;
        }
    }

    /**
     * D�finit le nombre de places dans un groupe de TD. Ne peut plus �tre modifi�
     * une fois d�fini.
     *
     * @param taille le nombre de place dans un groupe de TD (nombre sup�rieur �
     *               1)
     */
    @Override
    public void setTailleGroupeDirige(int taille) {
        if (this.tailleGroupeDirige == -1) {
            this.tailleGroupeDirige = taille;
        }
    }

    /**
     * D�finit le nombre de places dans un groupe de TP. Ne peut plus �tre modifi�
     * une fois d�fini.
     *
     * @param taille le nombre de place dans un groupe de TP (nombre sup�rieur �
     *               1)
     */
    @Override
    public void setTailleGroupePratique(int taille) {
        if (this.tailleGroupePratique == -1) {
            this.tailleGroupePratique = taille;
        }
    }

    /**
     * Renvoie le nombre de places dans un groupe de TD.
     *
     * @return le nombre de places dans un groupe de TD ou -1 s'il n'a pas encore
     *         �t� d�fini
     */
    @Override
    public int getTailleGroupeDirige() {
        return this.tailleGroupeDirige;
    }

    /**
     * Renvoie le nombre de places dans un groupe de TP.
     *
     * @return le nombre de places dans un groupe de TP ou -1 s'il n'a pas encore
     *         �t� d�fini
     */
    @Override
    public int getTailleGroupePratique() {
        return this.tailleGroupePratique;
    }

    /**
     * Attribue automatiquement les �tudiants non encore affect�s � des groupes de
     * TD et de TP. Au besoin, cr�e de nouveaux groupes de TD ou de TP. Pour
     * harmoniser la taille des groupes, des �tudiants d�j� plac�s peuvent �tre
     * d�plac�s. Les �tudiants concern�s par une affectation ou un changement
     * d'affectation re�oivent un message pour leur pr�ciser ce qu'il s'est pass�.
     */
    @Override
    public void attribuerAutomatiquementGroupes() {
        int nombreGroupesTravauxDiriges = listeEtudiants.size() / tailleGroupeDirige + 1;
        Set<Etudiant> listevide = new HashSet<>();
        boolean estpresent = false;
        while (nombreGroupesTravauxDiriges != this.tds.size()) {
            tds.put(this.nombreGroupesTravauxDiriges() + 1, listevide);
        }
        int numeroGroupeTailleMin = 1;
        for (Etudiant etu : listeEtudiants) {
            for (Map.Entry<Integer, Set<Etudiant>> entry : tds.entrySet()) {
                int key = entry.getKey();
                Set<Etudiant> value = entry.getValue();
                if (value.size() < numeroGroupeTailleMin) {
                    numeroGroupeTailleMin = key;
                }
            }
            estpresent = false;
            for (Map.Entry<Integer, Set<Etudiant>> entry : tds.entrySet()) {
                Set<Etudiant> value = entry.getValue();
                if (value.contains(etu)) {
                    estpresent = true;
                }
            }
            if (!estpresent) {
                changerGroupe(etu, numeroGroupeTailleMin, 0);
            }
        }
        this.homogenisation();
    }

    /**
     * Permet d'harmoniser les groupe en fonction du nombre d'élève dans les groupes
     */
    public void homogenisation() {
        double nombreEtudiantParGroupeTd = listeEtudiants.size() / this.nombreGroupesTravauxDiriges();
        int numeroGroupeTailleMin = 1;
        int numeroGroupeTailleMax = 1;
        while (interval(nombreEtudiantParGroupeTd, this.tds)) {
            for (Map.Entry<Integer, Set<Etudiant>> entry : tds.entrySet()) {
                int key = entry.getKey();
                Set<Etudiant> value = entry.getValue();
                if (value.size() < numeroGroupeTailleMin) {
                    numeroGroupeTailleMin = key;
                }
                if (value.size() > numeroGroupeTailleMax) {
                    numeroGroupeTailleMax = key;
                }
            }
            Iterator<Etudiant> it = tds.get(numeroGroupeTailleMax).iterator();
            changerGroupe(it.next(), numeroGroupeTailleMin, 0);
        }
    }

    /**
     * Permet de savoir si les groupe sont harmoniser
     * 
     * @param valeur Le nombre de perssone par groupe pour avoir un equilibre
     * @param mamap la map de TD ou TP
     * @return 
     *         <ul>
     *         <li>True si le nombre d'étudiant de chaque groupe est compris dans un interval de +1/-1 du parametre valeur </li>
     *         <li>False sinon</li>
     *         </ul>
     */
    public boolean interval(double valeur, Map<Integer, Set<Etudiant>> mamap) {
        boolean res = true;
        for (Map.Entry<Integer, Set<Etudiant>> entry : mamap.entrySet()) {
            Set<Etudiant> value = entry.getValue();
            if (value.size() > valeur + 1 || value.size() < valeur - 1) {
                res = false;
            }
        }
        return res;
    }

    /**
     * D�place � la main un �tudiant d'un groupe de TD/TP. L'op�ration peut
     * �chouer si les groupes sont d�j� pleins.
     *
     * @param etudiant       l'�tudiant � d�placer
     * @param groupeDirige   le nouveau groupe de TD (ou 0 si on ne change pas de
     *                       groupe de TD)
     * @param groupePratique le nouveau groupe de TP (ou 0 si on ne change de
     *                       groupe de TP)
     * @return
     *         <ul>
     *         <li>0 si le ou les d�placements ont �t� r�alis�s correctement</li>
     *         <li>-1 si le d�placement de TD n'a pas pu �tre fait</li>
     *         <li>-2 si le d�placement de TP n'a pas pu �tre fait</li>
     *         <lI>-3 si les d�placements de TD et de TP n'ont pas pu �tre
     *         faits</li>
     *         </ul>
     */
    @Override
    public int changerGroupe(Etudiant etudiant, int groupeDirige, int groupePratique) {
        int res = 0;
        int numgroupetd = -1;
        int numgroupetp = -1;
        for (Map.Entry<Integer, Set<Etudiant>> entry : tds.entrySet()) {
            int key = entry.getKey();
            Set<Etudiant> value = entry.getValue();
            if (value.contains(etudiant)) {
                numgroupetd = key;
            }
        }
        for (Map.Entry<Integer, Set<Etudiant>> entry : tps.entrySet()) {
            int key = entry.getKey();
            Set<Etudiant> value = entry.getValue();
            if (value.contains(etudiant)) {
                numgroupetp = key;
            }
        }
        if (groupeDirige > 0) {
            if (listeEtudiantsGroupeDirige(groupeDirige).size() < this.tailleGroupeDirige) {
                tds.get(groupeDirige).add(etudiant);
                etudiant.setNumeroTd(groupeDirige);
                if (numgroupetd != -1) {
                    tds.get(numgroupetd).remove(etudiant);
                }
            } else {
                res = -1;
            }
        }
        if (groupePratique > 0) {
            if (listeEtudiantsGroupePratique(groupePratique).size() < this.tailleGroupePratique) {
                tps.get(groupePratique).add(etudiant);
                etudiant.setNumeroTp(groupePratique);
                if (numgroupetp != -1) {
                    tps.get(numgroupetp).remove(etudiant);
                    envoyermessage(etudiant, "nouveaux groupe");
                } else {
                    envoyermessage(etudiant, "changement de groupe");
                }
            } else {
                if (res == -1) {
                    res = -3;
                } else {
                    res = -2;
                }
            }
        }
        return res;
    }

    /**
     * Envoie un message à un etudiant
     * 
     * @param etu L'Étudiant à qui envoyer le message
     * @param message Le message à envoyer
     */
    public void envoyermessage(Etudiant etu, String message) {
        etu.getMessage().put(false, message);
    }

    /**
     * Renvoie le nombre de groupes de TD actuellement d�finis dans la formation.
     *
     * @return nombre de groupes de TD
     */
    @Override
    public int nombreGroupesTravauxDiriges() {
        return tds.size();
    }

    /**
     * Renvoie le nombre de groupes de TP actuellement d�finis dans la formation.
     *
     * @return nombre de groupes de TP
     */
    @Override
    public int nombreGroupesTravauxPratiques() {
        return tps.size();
    }

    /**
     * Les �tudiants affect�s � un certain groupe de TD.
     *
     * @param groupe le groupe de TD
     * @return l'ensemble des �tudiants affect�s au groupe ou <code>null</code> si
     *         le groupe n'existe pas
     */
    @Override
    public Set<Etudiant> listeEtudiantsGroupeDirige(int groupe) {
        return tds.get(groupe);
    }

    /**
     * Les �tudiants affect�s � un certain groupe de TP.
     *
     * @param groupe le groupe de TP
     * @return l'ensemble des �tudiants affect�s au groupe ou <code>null</code> si
     *         le groupe n'existe pas
     */
    @Override
    public Set<Etudiant> listeEtudiantsGroupePratique(int groupe) {
        return tps.get(groupe);
    }

    /**
     * Les �tudiants inscrits � une certaine option.
     *
     * @param option l'option
     * @return l'ensemble des �tudiants inscrits � l'UE ou <code>null</code> si
     *         l'UE n'est pas propos�e en option
     */
    @Override
    public Set<Etudiant> listeEtudiantsOption(UniteEnseignement option) {
        if (UniteEseignements.contains(option)) {
            return null;
        }
        Set<Etudiant> listeetu = new HashSet<>();
        for (Etudiant etu : listeEtudiants) {
            for (UniteEnseignement ue : etu.getListeUEsuivies()) {
                if (ue.equals(option)) {
                    listeetu.add(etu);
                }
            }
        }
        return listeetu;
    }
}
