package ui;

import formation.GestionFormation;

// SharedModel.java
public class SharedModel {
    private GestionFormation ges;

    public GestionFormation getSharedVariable() {
        return this.ges;
    }

    public void setSharedVariable(GestionFormation gestion) {
        this.ges = gestion;
    }
}

