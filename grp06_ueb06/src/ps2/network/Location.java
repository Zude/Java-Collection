/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps2.network;

/**
 *
 * @author inf102691
 */
public class Location {

    protected String key;
    protected String caption;
    protected Connection[] inbound;
    protected Connection[] outbound;

    public Location(String key, String caption) {
        this.key = key;
        this.caption = caption;
        this.inbound = new Connection[0];
        this.outbound = new Connection[0];
    }

    /**
     *
     * gibt den Schlüssel des Ortes aus
     *
     * @return der Schlüssel
     */
    public String getKey() {
        return this.key;
    }

    /**
     *
     * gibt die Bezeichnung des Ortes aus
     *
     * @return die Bezeichnung
     */
    public String getCaption() {
        return this.caption;
    }

    /**
     *
     * gibt die eingehenden Verbindungen aus
     *
     * @return die eingehende Verbindung
     */
    public Connection[] getInbound() {
        return this.inbound;

    }

    /**
     *
     * gibt die ausgehenden Verbindungen aus
     *
     * @return die ausgehende Verbindung
     */
    public Connection[] getOutbound() {
        return this.outbound;
    }

    /**
     *
     * Fügt dem Netzwerk eine neue Verbindung hinzu
     *
     * @param mainArray gibt das Netzwerk an
     * @param c gibt die neue Verbindung an
     *
     * @return das Netzwerk mit der neuen Verbindung
     */
    private Connection[] addArrayConnection(Connection[] mainArray, Connection c) {

        if (mainArray == null) {
            mainArray = new Connection[1];
            mainArray[0] = c;
        } else {
            Connection[] ourArray = new Connection[mainArray.length + 1];
            System.arraycopy(mainArray, 0, ourArray, 0, mainArray.length);

            ourArray[mainArray.length] = c;
            mainArray = ourArray;
        }
        return mainArray;
    }

    /**
     *
     * fügt den eingehenden Verbindungen die übergebene zu
     *
     * @param c die neue Verbindung
     */
    public void addInbound(Connection c) {

        assert c != null : "C darf nicht leer sein";
        this.inbound = this.addArrayConnection(this.inbound, c);

    }

    /**
     *
     * Prüft ob eine betsimmte Verbindung bereits im netzwerk exestiert
     *
     * @param mainArray das zu prüfende Netzwerk
     * @param c die zu prüfende Verbindung
     *
     * @return true, wenn die Verbindung exestiert
     */
    private boolean conExistinConArray(Connection[] mainArray, Connection c) {
        boolean mark = false;

        for (Connection mainArray1 : mainArray) {
            if (mainArray1 == c) {
                mark = true;
            }
        }
        return mark;
    }

    /**
     *
     * entfernt eine bestimmte Verbindung aus dem Netzwerk
     *
     * @param mainArray das Netzwerk
     * @param c die zu entfernende Verbdinung
     *
     * @return das Netzwerk ohne die Verbindung
     */
    private Connection[] rmvArrayConnection(Connection[] mainArray, Connection c) {
        int index1 = 0;

        if (mainArray.length == 0 || (!this.conExistinConArray(mainArray, c))) {

        } else {
            Connection[] ourArray = new Connection[mainArray.length - 1];
            for (int index = 0; index < mainArray.length; index++) {
                if (mainArray[index1] != c) {                    
                    ourArray[index1] = mainArray[index];
                    index1++;
                }
                
            }
            mainArray = ourArray;

        }
        return mainArray;
    }

    /**
     *
     * entfernt eine eingehende Verbindung
     *
     * @param c die eingehende Verbindung
     */
    public void rmvInbound(Connection c) {

        this.inbound = this.rmvArrayConnection(this.inbound, c);

    }

    /**
     *
     * fügt eine neue ausgehende verbindung hinzu
     *
     * @param c die neue ausgehende verbindung
     */
    public void addOutbound(Connection c) {
        assert c != null : "C darf nicht leer sein";

        this.outbound = this.addArrayConnection(this.outbound, c);

    }

    /**
     *
     * entfernt eine bestimmte ausgehende Verbindung
     *
     * @param c die zu entfernende ausgehende Verbindung
     */
    public void rmvOutbound(Connection c) {

        this.outbound = this.rmvArrayConnection(this.outbound, c);
    }

    /**
     *
     * entfernt eine Verbindung zu einem bestimmten Ort
     *
     * @param loc gibt den Ort an
     */
    public void rmvConnectionsWith(Location loc) {

        if (this.outbound.length != 0) {
            for (Connection outbound1 : this.outbound) {
                if ((outbound1.from) == loc || (outbound1.to == loc)) {
                    this.outbound = this.rmvArrayConnection(this.outbound, outbound1);
                }
            }
        }
        if (this.inbound.length != 0) {
            for (Connection inbound1 : this.inbound) {
                if ((inbound1.from) == loc || (inbound1.to == loc)) {
                    this.inbound = this.rmvArrayConnection(this.inbound, inbound1);
                }
            }
        }

    }

    /**
     *
     * bezeichnet alle Orte als gleich, die den gleichen Schlüssel enthalten.
     *
     * @param o ist das zu prüfende Objekt
     *
     * @return true, wenn es gleich ist
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        } else {
            Location myLoc = (Location) o;
            return myLoc.key == this.key;

        }
    }

    /**
     *
     * liefert die Beschreibung des Ortes gefolgt von seinem Schlüssel
     *
     * @return den Ort samt Schlüssel
     */
    @Override
    public String toString() {
        String myString;

        myString = this.caption + " (" + this.key + ")";
        return myString;
    }
}
